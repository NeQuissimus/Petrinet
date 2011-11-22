package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;

/**
 * Action for increasing the number of markings on a place.
 * @author Tim Steinbach
 */
public final class IncreaseMarkingsAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Invoking place label.
     */
    private final PlaceLabel invoker;

    /**
     * Create a new action to increase the markings.
     * @param invoker Invoking label
     */
    public IncreaseMarkingsAction(final PlaceLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (null != this.invoker) {
            IncreaseMarkingsAction.CONTROLLER
                .increaseMarkings(this.invoker);
        }

    }

}
