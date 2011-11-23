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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class that can turn Petri net elements into PNML.
 * @author Tim Steinbach
 */
class PetrinetToMarkup {

    /**
     * PNML document.
     */
    private final Document doc;

    /**
     * Create new PNML parser for a document.
     * @param doc PNML document
     */
    PetrinetToMarkup(final Document doc) {

        this.doc = doc;

    }

    /**
     * Get PNML document.
     * @return PNML document
     */
    Document getDoc() {

        return this.doc;

    }

    /**
     * Create a root element for this document.
     * @return Root element
     */
    Element createRoot() {

        final Element root = this.doc.createElement(PnmlElements.ROOT);
        this.doc.appendChild(root);

        return root;

    }

    /**
     * Add a Petri net element to a given root element.
     * @param root Root element
     * @param id Petri net ID
     * @return Net element
     */
    Element addNet(final Element root, final String id) {

        final Element net = this.doc.createElement(PnmlElements.NET);

        net.setAttribute(PnmlElements.NET_TYPE, PnmlElements.NET_TYPE_VALUE);
        net.setAttribute(PnmlElements.NET_ID, id);

        root.appendChild(net);

        return net;

    }

    /**
     * Add a transition to a net element.
     * @param net Net element
     * @param id Transition ID
     * @return Transition element
     */
    Element addTransition(final Element net, final String id) {

        final Element transition =
            this.doc.createElement(PnmlElements.TRANSITION);

        transition.setAttribute(PnmlElements.TRANSITION_ID, id);

        net.appendChild(transition);

        return transition;

    }

    /**
     * Add graphics information for Petri object.
     * @param petriObject Petri object
     * @param position Position on the canvas
     * @param size Size
     */
    void addGraphics(final Element petriObject, final Point position,
        final Dimension size) {

        final Element graphics =
            this.doc.createElement(PnmlElements.GRAPHICS);

        final Element pos = this.doc.createElement(PnmlElements.POSITION);

        pos.setAttribute(PnmlElements.POSITION_X,
            String.valueOf(position.x));
        pos.setAttribute(PnmlElements.POSITION_Y,
            String.valueOf(position.y));

        final Element dim = this.doc.createElement(PnmlElements.DIMENSION);

        dim.setAttribute(PnmlElements.DIMENSION_HEIGHT,
            String.valueOf(size.height));
        dim.setAttribute(PnmlElements.DIMENSION_WIDTH,
            String.valueOf(size.width));

        graphics.appendChild(pos);
        graphics.appendChild(dim);

        petriObject.appendChild(graphics);

    }

    /**
     * Add a place to a net element.
     * @param net Net element
     * @param id Place ID
     * @param markings Value of markings
     * @return Place element
     */
    Element
        addPlace(final Element net, final String id, final int markings) {

        final Element place = this.doc.createElement(PnmlElements.PLACE);

        place.setAttribute(PnmlElements.PLACE_ID, id);

        final Element marking =
            this.doc.createElement(PnmlElements.PLACE_INITIAL_MARKING);
        final Element value =
            this.doc.createElement(PnmlElements.PLACE_INITIAL_MARKING_TEXT);
        value.setTextContent(String.valueOf(markings));

        marking.appendChild(value);
        place.appendChild(marking);

        net.appendChild(place);

        return place;

    }

    /**
     * Add edge to a net element.
     * @param net Net element
     * @param id Edge ID
     * @param sourceId ID for source Petri object
     * @param targetId ID for target Petri object
     */
    void addEdge(final Element net, final String id, final String sourceId,
        final String targetId) {

        final Element edge = this.doc.createElement(PnmlElements.EDGE);

        edge.setAttribute(PnmlElements.EDGE_ID, id);
        edge.setAttribute(PnmlElements.EDGE_SOURCE, sourceId);
        edge.setAttribute(PnmlElements.EDGE_TARGET, targetId);

        net.appendChild(edge);

    }

}
