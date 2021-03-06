// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.nequissimus.library.data.BiMap;
import com.nequissimus.library.data.TwoKeyMap;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * This class takes a logical net and draws all components onto the UI canvas.
 * @author Tim Steinbach
 */
public final class LogicToUi {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Hide constructor.
     */
    private LogicToUi() {
    }

    /**
     * Draw a Petri net onto the canvas used by the application's controller.
     * @param net Petri net
     */
    public static void convert(final PetriNet net) {

        // Get all components

        final PetriUi ui = LogicToUi.CONTROLLER.getUi();
        final BiMap<PetriObject, AbstractLabel> objects =
            LogicToUi.CONTROLLER.getObjects();
        final TwoKeyMap<AbstractLabel, AbstractLabel, Arrow> arrows =
            LogicToUi.CONTROLLER.getArrows();

        // Clean the controller
        objects.clear();
        arrows.clear();
        LogicToUi.CONTROLLER.arrowConnect(null);
        LogicToUi.CONTROLLER.arrowDisconnect(null);

        // Clean the canvas
        ui.clean();

        // Set icon size
        Set<PetriPlace> places = net.getPlaces();

        if (null != places) {

            final Iterator<PetriPlace> placeIterator = places.iterator();

            if (placeIterator.hasNext()) {

                final PetriPlace firstPlace = placeIterator.next();
                final Dimension size = net.getSize(firstPlace);
                final IconSize iconSize = IconSize.getIconSize(size);
                ui.setIconSize(iconSize);

            }

        } else {
            places = new HashSet<PetriPlace>();
        }

        // Add places

        for (final PetriPlace place : places) {

            final PlaceLabel label = ui.addPlace(net.getName(place));
            final int markings = net.getTokens(place);

            label.setTokens(markings);
            ui.moveLabel(label, net.getPosition(place));
            objects.put(place, label);

        }

        // Add transitions
        final Set<PetriTransition> transitions = net.getTransitions();

        for (final PetriTransition transition : transitions) {

            final TransitionLabel label =
                ui.addTransition(net.getName(transition));
            ui.moveLabel(label, net.getPosition(transition));
            objects.put(transition, label);

        }

        // Add arrows
        for (final PetriTransition transition : transitions) {

            final TransitionLabel transitionLabel =
                (TransitionLabel) objects.get(transition);

            // Incoming arrows
            final Set<PetriPlace> sources = net.getInputEdges(transition);

            for (final PetriPlace source : sources) {

                final PlaceLabel sourceLabel =
                    (PlaceLabel) objects.get(source);

                final Arrow arrow = new Arrow(sourceLabel, transitionLabel);

                ui.addArrow(arrow);
                arrows.put(sourceLabel, transitionLabel, arrow);

            }

            // Outgoing arrows
            final Set<PetriPlace> targets = net.getOutputEdges(transition);

            for (final PetriPlace target : targets) {

                final PlaceLabel targetLabel =
                    (PlaceLabel) objects.get(target);

                final Arrow arrow = new Arrow(transitionLabel, targetLabel);

                ui.addArrow(arrow);
                arrows.put(targetLabel, transitionLabel, arrow);

            }

        }

        // Set canvas to size larger than needed by coordinates
        int largest = LogicToUi.findLargestCoord(net, places, transitions);
        largest += ui.getIconSize().getSize().height;
        final Dimension newSize =
            ui.calculateCanvasSize(new Dimension(largest, largest));
        ui.resizeCanvas(newSize);
        ui.resizeArrowCanvas(newSize);

    }

    /**
     * Find the largest coordinate for all elements.
     * @param net Petri net to work with
     * @param places Places to go through
     * @param transitions Transition to go through
     * @return Largest coordinate for Petri objects
     */
    private static int
        findLargestCoord(final PetriNet net, final Set<PetriPlace> places,
            final Set<PetriTransition> transitions) {

        int largest = 0;

        for (final PetriTransition petriTransition : transitions) {

            final Point point = net.getPosition(petriTransition);

            if (point.x > largest) {
                largest = point.x;
            }

            if (point.y > largest) {
                largest = point.y;
            }

        }

        for (final PetriPlace petriPlace : places) {

            final Point point = net.getPosition(petriPlace);

            if (point.x > largest) {
                largest = point.x;
            }

            if (point.y > largest) {
                largest = point.y;
            }

        }

        return largest;

    }

}
