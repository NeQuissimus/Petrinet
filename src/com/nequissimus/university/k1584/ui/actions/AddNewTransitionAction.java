package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action to add new transitions to the net.
 * @author Tim Steinbach
 *
 */
public class AddNewTransitionAction implements ActionListener {

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        PetriController.getInstance().addTransition();

    }

}
