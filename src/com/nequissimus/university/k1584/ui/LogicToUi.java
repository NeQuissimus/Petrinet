package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;
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
        final PetriController controller = PetriController.getInstance();
        final PetriUi ui = controller.getUi();
        final BiMap<PetriObject, AbstractLabel> objects =
            controller.getObjects();
        final TwoKeyMap<PlaceLabel, TransitionLabel, Arrow> arrows =
            controller.getArrows();

        // Clean the controller
        objects.clear();
        arrows.clear();
        controller.arrowConnect(null);
        controller.arrowDisconnect(null);

        // Clean the canvas
        ui.clean();

        // Set icon size
        final PetriPlace firstPlace = net.getPlaces().iterator().next();
        final Dimension size = net.getSize(firstPlace);
        final IconSize iconSize = IconSize.getIconSize(size);
        ui.setIconSize(iconSize);

        // Add places
        final Set<PetriPlace> places = net.getPlaces();

        for (final PetriPlace place : places) {

            final PlaceLabel label = ui.addPlace(net.getName(place));
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

                final Arrow arrow =
                    new Arrow(transitionLabel, transitionLabel);

                ui.addArrow(arrow);
                arrows.put(targetLabel, transitionLabel, arrow);

            }

        }

        // Set canvas to size larger than needed by coordinates
        int largest = LogicToUi.findLargestCoord(net, places, transitions);
        largest += ui.getIconSize().getSize().height;
        final Dimension newSize =
            ui.calculateCanvasSize(new Dimension(largest, largest));
        ui.setCanvasSize(newSize);

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
