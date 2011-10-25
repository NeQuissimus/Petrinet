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
 * Transform a {@link PetriNet} object into EPNML 1.1 and back.
 * 
 * This markup is XML-based. The following is a basic example. <br /><br />
 * 
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
 * 
 * @see http://www.petriweb.org/specs/epnml-1.1/pnmldef.pdf
 * @see http://www.petriweb.org/specs/simple.pnml
 * @author Tim Schram
 *
 */
public class PetriMarkup {

    private final static PetriConfig config = PetriConfig.getInstance();

    /**
     * Hide constructor
     */
    private PetriMarkup() {}

    /**
     * Convert a EPNML document into a {@link PetriNet} with all its snapshots
     * @param doc EPNML document
     * @return PetriSnapshots object with all PetriNets
     */
    public static PetriSnapshots toPetrinet(Document doc) {
	
	//TODO: Implement!
	return null;}
    
    public static void toPnmlFile(final File file, final PetriSnapshots nets) throws PnmlException {
	
	Document doc = toPnml(nets);

	Source source = new DOMSource(doc);	
	Result result = new StreamResult(file);

	try {
	    
	    final Transformer xformer = TransformerFactory.newInstance().newTransformer();
	    xformer.transform(source, result);
	    
	} catch (Exception t) {
	    
	    throw new PnmlException(t);
	    
	}
	
    } 

    /**
     * Convert all Petri nets from the {@link PetriSnapshots} object into a EPNML document
     * @param nets PetriSnapshots element with Petri nets
     * @return EPNML document
     * @throws PnmlException Thrown if the XML parser cannot work with the input from the snapshots object
     */
    public static Document toPnml(PetriSnapshots nets) throws PnmlException {

	Document pnml = null;

	try {

	    final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

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

    private static void addAllPetriNets(List<PetriNet> list, PetrinetToMarkup pnmlBuilder, Element root) {

	for (final PetriNet petriNet : list) {

	    final Element net = pnmlBuilder.addNet(root, petriNet.getName());

	    final Set<PetriPlace> places = petriNet.getPlaces();

	    addAllPlaces(petriNet, places, pnmlBuilder, net);

	    final Set<PetriTransition> transitions = petriNet.getTransitions();

	    addAllTransitions(petriNet, transitions, pnmlBuilder, net);

	    addAllEdges(petriNet, transitions, pnmlBuilder, net);

	}

    }

    private static void addAllPlaces(PetriNet petriNet, Set<PetriPlace> places, 
	    PetrinetToMarkup pnmlBuilder, Element net) {

	for (PetriPlace petriPlace : places) {

	    final Element place = pnmlBuilder.addPlace(net, petriNet.getName(petriPlace));
	    final Point position = petriNet.getPosition(petriPlace);
	    final Dimension size = petriNet.getSize(petriPlace);

	    pnmlBuilder.addGraphics(place, position, size);

	}

    }

    private static void addAllTransitions(PetriNet petriNet, Set<PetriTransition> transitions, 
	    PetrinetToMarkup pnmlBuilder, Element net) {

	for (PetriTransition petriTransition : transitions) {

	    final Element transition = pnmlBuilder.addTransition(net, petriNet.getName(petriTransition));
	    final Point position = petriNet.getPosition(petriTransition);
	    final Dimension size = petriNet.getSize(petriTransition);

	    pnmlBuilder.addGraphics(transition, position, size);

	}

    }

    private static void addAllEdges(PetriNet petriNet, Set<PetriTransition> transitions, 
	    PetrinetToMarkup pnmlBuilder, Element net) {

	int edgeId = 0;
	final String edgeIdPrefix = PetriMarkup.config.getEdgeIdPrefix();

	for (PetriTransition petriTransition : transitions) {

	    final Set<PetriPlace> inEdges = petriNet.getInputEdges(petriTransition);

	    final String sourceId = petriNet.getName(petriTransition);

	    for (PetriPlace petriPlace : inEdges) {

		final String targetId = petriNet.getName(petriPlace);

		pnmlBuilder.addEdge(net, edgeIdPrefix + edgeId, sourceId, targetId);

	    }

	}

    }

}
