/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.nequissimus.university.k1584.logic.pnml;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.library.util.XmlUtil;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObjectId;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;

/**
 * Transform a {@link PetriNet} object into EPNML 1.1 and back. This markup is
 * XML-based. The following is a basic example. <br />
 * <br />
 * &lt;pnml&gt; <br />
 * &lt;net type="http://www.yasper.org/specs/epnml-1.1" id="net1"&gt; <br />
 * &lt;transition id="transition1"&gt; <br />
 * &lt;graphics&gt; <br />
 * &lt;position x="78" y="78"/&gt; <br />
 * &lt;dimension x="24" y="24"/&gt; <br />
 * &lt;/graphics&gt; <br />
 * &lt;/transition&gt; <br />
 * &lt;place id="place1"&gt; <br />
 * &lt;graphics&gt; <br />
 * &lt;position x="78" y="12"/&gt; <br />
 * &lt;dimension x="20" y="20"/&gt; <br />
 * &lt;/graphics&gt; <br />
 * &lt;initialMarking&gt; <br />
 * &lt;text&gt;1&lt;/text&gt; <br />
 * &lt;/initialMarking&gt; <br />
 * &lt;/place&gt; <br />
 * &lt;place id="place2"&gt; <br />
 * &lt;graphics&gt; <br />
 * &lt;position x="144" y="78"/&gt; <br />
 * &lt;dimension x="20" y="20"/&gt; <br />
 * &lt;/graphics&gt; <br />
 * &lt;/place&gt; <br />
 * &lt;arc id="arc1" source="place1" target="transition1"/&gt; <br />
 * &lt;arc id="arc2" source="transition1" target="place2"/&gt; <br />
 * &lt;/net&gt;
 * @see http://www.petriweb.org/specs/epnml-1.1/pnmldef.pdf
 * @see http://www.petriweb.org/specs/simple.pnml
 * @author Tim Steinbach
 */
public final class PetriMarkup {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Hide constructor.
     */
    private PetriMarkup() {
    }

    /**
     * Load a file and get all Petri nets from it.
     * @param file File to load
     * @return Petri nets
     * @throws PnmlException Error while parsing PNML
     */
    public static PetriSnapshots loadPnmlFile(final File file)
        throws PnmlException {

        PetriSnapshots logic = null;

        final DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder;

        try {

            docBuilder = factory.newDocumentBuilder();
            final Document doc = docBuilder.parse(file);
            logic = PetriMarkup.toPetrinet(doc);

        } catch (final Exception e) {

            throw new PnmlException(e);

        }

        return logic;

    }

    /**
     * Convert all Petri nets and write PNML to file.
     * @param file File to write to
     * @param nets Petri nets
     * @throws PnmlException Error while parsing PNML
     */
    public static void savePnmlFile(final File file,
        final PetriSnapshots nets) throws PnmlException {

        File tmpFile = file;

        final String extension = PetriMarkup.CONFIG.getFileExtension();
        final String fileName = tmpFile.getAbsolutePath();

        if (!fileName.endsWith(extension)) {
            tmpFile = new File(fileName + "." + extension);
        }

        final Document doc = PetriMarkup.toPnml(nets);

        final Source source = new DOMSource(doc);
        final Result result = new StreamResult(tmpFile);

        try {

            final Transformer xformer =
                TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
                "yes");
            xformer.transform(source, result);

        } catch (final Exception t) {

            throw new PnmlException(t);

        }

    }

    /**
     * Convert a EPNML document into a {@link PetriNet} with all its snapshots.
     * @param doc EPNML document
     * @return PetriSnapshots object with all PetriNets
     * @throws PnmlException Error parsing the file
     */
    public static PetriSnapshots toPetrinet(final Document doc)
        throws PnmlException {

        final PetriSnapshots result = new PetriSnapshots();

        final Element root = doc.getDocumentElement();

        XmlUtil.removeEmptyTextNodes(root);

        if (root.hasChildNodes()) {

            PetriMarkup.addAllNets(root, result);

        }

        return result;

    }

    /**
     * Convert all Petri nets from the {@link PetriSnapshots} object into an
     * EPNML document.
     * @param nets PetriSnapshots element with Petri nets
     * @return EPNML document
     * @throws PnmlException Thrown if the XML parser cannot work with the input
     *             from the snapshots object
     */
    public static Document toPnml(final PetriSnapshots nets)
        throws PnmlException {

        Document pnml = null;

        try {

            final DocumentBuilderFactory docFactory =
                DocumentBuilderFactory.newInstance();
            final DocumentBuilder docBuilder =
                docFactory.newDocumentBuilder();

            pnml = docBuilder.newDocument();

            final PetrinetToMarkup pnmlBuilder = new PetrinetToMarkup(pnml);

            final Element root = pnmlBuilder.createRoot();

            final List<PetriNet> list = nets.getNets();

            PetriMarkup.addAllPetriNets(list, pnmlBuilder, root);

        } catch (final ParserConfigurationException e) {

            throw new PnmlException(e);

        }

        return pnml;

    }

    /**
     * Add all edges to a PNML net element.
     * @param petriNet Petri net to work with
     * @param transitions Transitions to read edges from
     * @param pnmlBuilder PNML builder
     * @param net Net element to add to
     */
    private static void addAllEdges(final PetriNet petriNet,
        final Set<PetriTransition> transitions,
        final PetrinetToMarkup pnmlBuilder, final Element net) {

        int edgeId = 0;
        final String edgeIdPrefix = PetriMarkup.CONFIG.getEdgeIdPrefix();

        for (final PetriTransition petriTransition : transitions) {

            final Set<PetriPlace> inEdges =
                petriNet.getInputEdges(petriTransition);

            final String targetId = petriNet.getId(petriTransition);

            for (final PetriPlace petriPlace : inEdges) {

                final String sourceId = petriNet.getId(petriPlace);

                pnmlBuilder.addEdge(net, edgeIdPrefix + edgeId++, sourceId,
                    targetId);

            }

            final Set<PetriPlace> outEdges =
                petriNet.getOutputEdges(petriTransition);

            final String sourceId = targetId;

            for (final PetriPlace petriPlace : outEdges) {

                final String targetId2 = petriNet.getId(petriPlace);

                pnmlBuilder.addEdge(net, edgeIdPrefix + edgeId++, sourceId,
                    targetId2);

            }

        }

    }

    /**
     * Add all nets from the root element to the resulting logic.
     * @param root XML root element
     * @param result Logical result
     * @throws PnmlException Error parsing the file
     */
    private static void addAllNets(final Element root,
        final PetriSnapshots result) throws PnmlException {

        // Get individual nets

        final NodeList nets = root.getChildNodes();

        final int size = nets.getLength();

        for (int i = 0; i < size; i++) {

            final Node node = nets.item(i);

            // Makes loading the file much more error-resistant
            if (node instanceof Element) {

                final Element net = (Element) node;

                PetriMarkup.addNextNet(result, net);

            }

        }

    }

    /**
     * Add all Petri nets to the root element.
     * @param list List of Petri nets
     * @param pnmlBuilder PNML to markup builder
     * @param root Root element
     */
    private static void addAllPetriNets(final List<PetriNet> list,
        final PetrinetToMarkup pnmlBuilder, final Element root) {

        for (final PetriNet petriNet : list) {

            final Element net =
                pnmlBuilder.addNet(root, petriNet.getName());

            final Set<PetriPlace> places = petriNet.getPlaces();

            PetriMarkup.addAllPlaces(petriNet, places, pnmlBuilder, net);

            final Set<PetriTransition> transitions =
                petriNet.getTransitions();

            PetriMarkup.addAllTransitions(petriNet, transitions,
                pnmlBuilder, net);

            PetriMarkup
                .addAllEdges(petriNet, transitions, pnmlBuilder, net);

        }

    }

    /**
     * Add all places to a PNML net element.
     * @param petriNet Petri net to work with
     * @param places Set of places to be added
     * @param pnmlBuilder PNML builder
     * @param net Net element
     */
    private static void addAllPlaces(final PetriNet petriNet,
        final Set<PetriPlace> places, final PetrinetToMarkup pnmlBuilder,
        final Element net) {

        for (final PetriPlace petriPlace : places) {

            final String id =
                petriNet.getId(petriPlace) + "_"
                    + petriNet.getName(petriPlace);
            final int value = petriNet.getTokens(petriPlace);

            final Element place = pnmlBuilder.addPlace(net, id, value);
            final Point position = petriNet.getPosition(petriPlace);
            final Dimension size = petriNet.getSize(petriPlace);

            pnmlBuilder.addGraphics(place, position, size);

        }

    }

    /**
     * Add all transitions to a PNML net element.
     * @param petriNet Petri net to work with
     * @param transitions Set of transitions to be added
     * @param pnmlBuilder PNML builder
     * @param net Net element
     */
    private static void addAllTransitions(final PetriNet petriNet,
        final Set<PetriTransition> transitions,
        final PetrinetToMarkup pnmlBuilder, final Element net) {

        for (final PetriTransition petriTransition : transitions) {

            final String id =
                petriNet.getId(petriTransition) + "_"
                    + petriNet.getName(petriTransition);

            final Element transition = pnmlBuilder.addTransition(net, id);
            final Point position = petriNet.getPosition(petriTransition);
            final Dimension size = petriNet.getSize(petriTransition);

            pnmlBuilder.addGraphics(transition, position, size);

        }

    }

    /**
     * Add the connection between a place and a transition.
     * @param logicalNet Logical net
     * @param elem Arc element
     */
    private static void
        addArc(final PetriNet logicalNet, final Element elem) {

        final String sourceId = elem.getAttribute(PnmlElements.EDGE_SOURCE);
        final String targetId = elem.getAttribute(PnmlElements.EDGE_TARGET);

        PetriTransition transition = null;
        PetriPlace place = null;

        boolean isInput = false;

        transition = logicalNet.getTransitionById(sourceId);

        if (null == transition) {

            transition = logicalNet.getTransitionById(targetId);
            place = logicalNet.getPlaceById(sourceId);
            isInput = true;

        } else {

            place = logicalNet.getPlaceById(targetId);

        }

        if (isInput) {

            logicalNet.connect(place, transition);

        } else {

            logicalNet.connect(transition, place);

        }

    }

    /**
     * Method that adds the next Petri net from its XML representation to the
     * logical net.
     * @param result Resulting logic
     * @param net Net element
     * @throws PnmlException Error because of malformed file
     */
    private static void addNextNet(final PetriSnapshots result,
        final Element net) throws PnmlException {

        final String netName = net.getAttribute(PnmlElements.NET_ID);

        final PetriNet logicalNet = result.add(netName);

        // Places, transitions, arcs
        final NodeList pta = net.getChildNodes();

        final int ptaSize = pta.getLength();

        for (int j = 0; j < ptaSize; j++) {

            final Node elemNode = pta.item(j);

            if (elemNode instanceof Element) {

                final Element elem = (Element) elemNode;

                final String nodeName = elem.getNodeName();

                if (PnmlElements.PLACE.equals(nodeName)) {

                    PetriMarkup.addPlace(logicalNet, elem);

                } else if (PnmlElements.TRANSITION.equals(nodeName)) {

                    PetriMarkup.addTransition(logicalNet, elem);

                } else if (PnmlElements.EDGE.equals(nodeName)) {

                    PetriMarkup.addArc(logicalNet, elem);

                } else {
                    throw new PnmlException("File malformed!");
                }

            }

        }
    }

    /**
     * Add a place to the logical net.
     * @param logicalNet Logical net
     * @param elem PNML element for place
     * @throws PnmlException Error parsing the file
     */
    private static void addPlace(final PetriNet logicalNet,
        final Element elem) throws PnmlException {

        final String nameId = elem.getAttribute(PnmlElements.PLACE_ID);
        final String name = nameId.substring(nameId.indexOf("_") + 1);
        final String id = nameId.substring(0, nameId.indexOf("_"));

        PetriObjectId.addUsedId(id);

        final Element graphics =
            PetriMarkup.getElementByName(elem, PnmlElements.GRAPHICS);
        final Element markings =
            PetriMarkup.getElementByName(elem,
                PnmlElements.PLACE_INITIAL_MARKING);

        final int markingsValue =
            Integer.parseInt(PetriMarkup.getTextValue(markings,
                PnmlElements.PLACE_INITIAL_MARKING_TEXT));

        final int[] graphicsValues =
            PetriMarkup.getGraphicsValues(graphics);
        final int x = graphicsValues[0];
        final int y = graphicsValues[1];
        final int width = graphicsValues[2];
        final int height = graphicsValues[3];

        final PetriPlace place = logicalNet.addPlace(name, id);
        for (int i = 0; i < markingsValue; i++) {
            logicalNet.increaseTokens(place);
        }
        logicalNet.setPosition(place, new Point(x, y));
        logicalNet.setSize(place, new Dimension(width, height));

    }

    /**
     * Add a transition to the logical net by parsing the PNML element.
     * @param logicalNet Logical net
     * @param elem PNML element
     * @throws PnmlException Error parsing element
     */
    private static void addTransition(final PetriNet logicalNet,
        final Element elem) throws PnmlException {

        final String nameId = elem.getAttribute(PnmlElements.TRANSITION_ID);
        final String name = nameId.substring(nameId.indexOf("_") + 1);
        final String id = nameId.substring(0, nameId.indexOf("_"));

        PetriObjectId.addUsedId(id);

        final Element graphics =
            PetriMarkup.getElementByName(elem, PnmlElements.GRAPHICS);
        final int[] graphicsValues =
            PetriMarkup.getGraphicsValues(graphics);
        final int x = graphicsValues[0];
        final int y = graphicsValues[1];
        final int width = graphicsValues[2];
        final int height = graphicsValues[3];

        final PetriTransition transition =
            logicalNet.addTransition(name, id);
        logicalNet.setSize(transition, new Dimension(width, height));
        logicalNet.setPosition(transition, new Point(x, y));

    }

    /**
     * Find an element under a root element.
     * @param root Root element
     * @param name Name of child
     * @return Child element
     * @throws PnmlException Error finding child element
     */
    private static Element getElementByName(final Element root,
        final String name) throws PnmlException {

        final NodeList children = root.getChildNodes();
        final int size = children.getLength();

        for (int i = 0; i < size; i++) {

            final Node node = children.item(i);

            if (node instanceof Element) {

                final Element elem = (Element) node;
                if ((null != elem) && (name.equals(elem.getNodeName()))) {
                    return elem;
                }

            }

        }

        throw new PnmlException("Element not found");

    }

    /**
     * Get the graphics values form a graphics PNML element.
     * @param graphics Graphics PNML element
     * @return {x, y, width, height}
     * @throws PnmlException Error parsing file
     */
    private static int[] getGraphicsValues(final Node graphics)
        throws PnmlException {

        final int[] result = new int[4];

        final Element graphics1 = (Element) graphics.getFirstChild();
        final Element graphics2 = (Element) graphics.getLastChild();

        if ((null == graphics1) || (null == graphics2)) {
            throw new PnmlException("File malformed!");
        } else {

            Element position = null;
            Element dimension = null;

            if ((PnmlElements.POSITION.equals(graphics1.getNodeName()))
                && (PnmlElements.DIMENSION.equals(graphics2.getNodeName()))) {

                position = graphics1;
                dimension = graphics2;

            } else if ((PnmlElements.POSITION.equals(graphics2
                .getNodeName()))
                && (PnmlElements.DIMENSION.equals(graphics1.getNodeName()))) {

                position = graphics2;
                dimension = graphics1;

            } else {
                throw new PnmlException("File malformed!");
            }

            final String xText =
                position.getAttribute(PnmlElements.POSITION_X);
            final String yText =
                position.getAttribute(PnmlElements.POSITION_Y);
            final String widthText =
                dimension.getAttribute(PnmlElements.DIMENSION_WIDTH);
            final String heightText =
                dimension.getAttribute(PnmlElements.DIMENSION_HEIGHT);

            if ((null == xText) || (null == yText) || (null == widthText)
                || (null == heightText)) {
                throw new PnmlException("File malformed!");
            } else {
                result[0] = Integer.parseInt(xText);
                result[1] = Integer.parseInt(yText);
                result[2] = Integer.parseInt(widthText);
                result[3] = Integer.parseInt(heightText);
            }

        }

        return result;

    }

    /**
     * Get the text value of a tag under the root element.
     * @param root Root element
     * @param tag Tag to find
     * @return Text value of tag under root
     * @throws PnmlException Error finding tag
     */
    private static String
        getTextValue(final Element root, final String tag)
            throws PnmlException {

        final NodeList list = root.getChildNodes();
        final int size = list.getLength();

        for (int i = 0; i < size; i++) {

            final Node node = list.item(i);

            if (node instanceof Element) {

                final Element elem = (Element) node;

                if ((null != elem) && (tag.equals(elem.getNodeName()))) {
                    return elem.getTextContent();
                }

            }

        }

        throw new PnmlException("Tag not found!");

    }

}
