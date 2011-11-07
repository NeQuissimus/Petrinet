package com.nequissimus.university.k1584.ui;

import java.util.Set;

import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

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
     * This method utilizes the controller's currentNet!
     * @param net Petri net
     */
    public static void convert(final PetriNet net) {

        /*final PetriController controller = PetriController.getInstance();
        final Set<PetriPlace> places = net.getPlaces();
        final Set<PetriTransition> transitions = net.getTransitions();
        final PetriNet currentNet = controller.getCurrentNet();

        final Set<PetriPlaceLabel> placeLabels =
            new HashSet<PetriPlaceLabel>();
        final Set<PetriTransitionLabel> transitionLabels =
            new HashSet<PetriTransitionLabel>();

        // Clean canvas
        PetriCanvas canvas = PetriWindow.getCanvas();
        canvas.getCanvas().removeAll();

        // Set up currentNet
        currentNet.setName(net.getName());

        // Add places
        for (final PetriPlace petriPlace : places) {

            final Point location = currentNet.getPosition(petriPlace);
            final PetriPlaceLabel label = new PetriPlaceLabel(petriPlace);

            controller.addLabel(label);
            placeLabels.add(label);

            final PetriPlace newPlace =
                currentNet.addPlace(currentNet.getName(petriPlace));
            currentNet.setPosition(newPlace, location);

        }

        // Add transitions
        for (final PetriTransition petriTransition : transitions) {

            final Point location = currentNet.getPosition(petriTransition);
            final PetriTransitionLabel label =
                new PetriTransitionLabel(petriTransition);

            controller.addLabel(label);
            transitionLabels.add(label);

            final PetriTransition newTransition =
                currentNet.addTransition(currentNet
                    .getName(petriTransition));
            currentNet.setPosition(newTransition, location);

        }

        // Add edges
        for (final PetriTransitionLabel label : transitionLabels) {

            PetriTransition transition =
                (PetriTransition) label.getObject();

            Set<PetriPlace> incoming = currentNet.getInputEdges(transition);
            Set<PetriPlace> outgoing =
                currentNet.getOutputEdges(transition);

            for (PetriPlace petriPlace : incoming) {

                // Empty out selected connection
                controller.arrowConnect(null);

                PetriPlaceLabel placeLabel =
                    (PetriPlaceLabel) findLabel(placeLabels, petriPlace);

                controller.arrowConnect(label);
                controller.arrowConnect(placeLabel);

            }

            for (PetriPlace petriPlace : outgoing) {

                // Empty out selected connection
                controller.arrowConnect(null);

                PetriPlaceLabel placeLabel =
                    (PetriPlaceLabel) findLabel(placeLabels, petriPlace);

                controller.arrowConnect(placeLabel);
                controller.arrowConnect(label);
            }

        }

        // Redraw canvas
        controller.redrawCanvas();*/

    }

    /**
     * Find the matching label for a Petri object.
     * @param set Set of labels to look through
     * @param object Object to look for
     * @return Petri object if found, else NULL
     */
    private static AbstractLabel findLabel(
        final Set<? extends AbstractLabel> set, final PetriObject object) {
/*
        for (AbstractLabel label : set) {
            final PetriObject tmpObject = label.getObject();
            if (tmpObject.equals(object)) {
                return label;
            }
        }

        return null;
*/
        
        return null;
    }

}
