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

    @Override
    public void actionPerformed(final ActionEvent e) {

        final TransitionLabel invoker = (TransitionLabel) e.getSource();

        PetriController.getInstance().occur(invoker);

    }

}
