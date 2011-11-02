package com.nequissimus.university.k1584.logic.pnml;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
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
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Hide constructor.
     */
    private PetriMarkup() {
    }

    /**
     * Convert a EPNML document into a {@link PetriNet} with all its snapshots.
     * @param doc EPNML document
     * @return PetriSnapshots object with all PetriNets
     */
    public static PetriSnapshots toPetrinet(final Document doc) {

        throw new UnsupportedOperationException();

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

        DocumentBuilderFactory factory =
            DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;

        try {

            docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            logic = toPetrinet(doc);

        } catch (Exception e) {
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

        Document doc = toPnml(nets);

        Source source = new DOMSource(doc);
        Result result = new StreamResult(file);

        try {

            final Transformer xformer =
                TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);

        } catch (Exception t) {

            throw new PnmlException(t);

        }

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

            addAllPetriNets(list, pnmlBuilder, root);

        } catch (final ParserConfigurationException e) {

            throw new PnmlException(e);

        }

        return pnml;

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

            addAllPlaces(petriNet, places, pnmlBuilder, net);

            final Set<PetriTransition> transitions =
                petriNet.getTransitions();

            addAllTransitions(petriNet, transitions, pnmlBuilder, net);

            addAllEdges(petriNet, transitions, pnmlBuilder, net);

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

            final Element place =
                pnmlBuilder.addPlace(net, petriNet.getName(petriPlace));
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

            final Element transition =
                pnmlBuilder.addTransition(net,
                    petriNet.getName(petriTransition));
            final Point position = petriNet.getPosition(petriTransition);
            final Dimension size = petriNet.getSize(petriTransition);

            pnmlBuilder.addGraphics(transition, position, size);

        }

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

            final String sourceId = petriNet.getName(petriTransition);

            for (PetriPlace petriPlace : inEdges) {

                final String targetId = petriNet.getName(petriPlace);

                pnmlBuilder.addEdge(net, edgeIdPrefix + edgeId, sourceId,
                    targetId);

            }

        }

    }

}
