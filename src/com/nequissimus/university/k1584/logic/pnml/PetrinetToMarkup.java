package com.nequissimus.university.k1584.logic.pnml;

import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.DIMENSION;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.DIMENSION_HEIGHT;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.DIMENSION_WIDTH;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.EDGE;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.EDGE_ID;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.EDGE_SOURCE;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.EDGE_TARGET;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.GRAPHICS;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.NET_ID;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.NET_TYPE;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.NET_TYPE_VALUE;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.PLACE;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.PLACE_ID;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.POSITION;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.POSITION_X;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.POSITION_Y;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.ROOT;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.TRANSITION;
import static com.nequissimus.university.k1584.logic.pnml.PnmlElements.TRANSITION_ID;

import java.awt.Dimension;
import java.awt.Point;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class PetrinetToMarkup {

    private final Document doc;

    PetrinetToMarkup(final Document doc) {

        this.doc = doc;

    }

    Document getDoc() {

        return this.doc;

    }

    Element createRoot() {

        final Element root = this.doc.createElement(ROOT);
        this.doc.appendChild(root);

        return root;

    }

    Element addNet(final Element root, final String id) {

        final Element net = this.doc.createElement(PnmlElements.NET);

        net.setAttribute(NET_TYPE, NET_TYPE_VALUE);
        net.setAttribute(NET_ID, id);

        root.appendChild(net);

        return net;

    }

    Element addTransition(final Element net, final String id) {

        final Element transition = this.doc.createElement(TRANSITION);

        transition.setAttribute(TRANSITION_ID, id);

        net.appendChild(transition);

        return transition;

    }

    void addGraphics(final Element petriObject, final Point position,
        final Dimension size) {

        final Element graphics = this.doc.createElement(GRAPHICS);

        final Element pos = this.doc.createElement(POSITION);

        pos.setAttribute(POSITION_X, String.valueOf(position.x));
        pos.setAttribute(POSITION_Y, String.valueOf(position.y));

        final Element dim = this.doc.createElement(DIMENSION);

        dim.setAttribute(DIMENSION_HEIGHT, String.valueOf(size.height));
        dim.setAttribute(DIMENSION_WIDTH, String.valueOf(size.width));

        graphics.appendChild(pos);
        graphics.appendChild(dim);

        petriObject.appendChild(graphics);

    }

    Element addPlace(final Element net, final String id) {

        final Element place = this.doc.createElement(PLACE);

        place.setAttribute(PLACE_ID, id);

        net.appendChild(place);

        return place;

    }

    void addEdge(final Element net, final String id, final String sourceId,
        final String targetId) {

        final Element edge = this.doc.createElement(EDGE);

        edge.setAttribute(EDGE_ID, id);
        edge.setAttribute(EDGE_SOURCE, sourceId);
        edge.setAttribute(EDGE_TARGET, targetId);

        net.appendChild(edge);

    }

}
