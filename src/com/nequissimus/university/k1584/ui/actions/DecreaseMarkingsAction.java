package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;

/**
 * Action for decreasing the number of markings on a place.
 * @author Tim Steinbach
 */
public final class DecreaseMarkingsAction implements ActionListener {

    /**
     * Invoking place label.
     */
    private final PlaceLabel invoker;

    /**
     * Create a new action for decreasing markings.
     * @param invoker Invoking place
     */
    public DecreaseMarkingsAction(final PlaceLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (null != this.invoker) {
            PetriController.getInstance().decreaseMarkings(this.invoker);
        }

    }

}
