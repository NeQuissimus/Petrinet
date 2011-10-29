package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Remove an object from the canvas and logical net.
 * @author Tim Steinbach
 */
public final class RemoveObjectAction implements ActionListener {

    /**
     * Invoking Petri label.
     */
    private final AbstractLabel invoker;

    /**
     * Create new action that can remove a given label.
     * @param invoker Label to be removed
     */
    public RemoveObjectAction(final AbstractLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        PetriController.getInstance().removeObject(this.invoker);

    }

}
