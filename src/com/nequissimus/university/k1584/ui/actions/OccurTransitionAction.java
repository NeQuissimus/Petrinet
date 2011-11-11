package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;

/**
 * Action that makes an event on a given transition occur.
 * @author Tim Steinbach
 */
public final class OccurTransitionAction implements ActionListener {

    /**
     * Invoking transition.
     */
    private final TransitionLabel invoker;

    /**
     * Create a new action for having transitions occur.
     * @param invoker Invoking transition
     */
    public OccurTransitionAction(final TransitionLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        PetriController.getInstance().occur(this.invoker);

    }

}
