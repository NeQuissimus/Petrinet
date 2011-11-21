package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action that resets the entire application, logic and UI.
 * @author Tim Steinbach
 */
public final class ResetApplicationAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    @Override
    public void actionPerformed(final ActionEvent e) {

        ResetApplicationAction.CONTROLLER.resetApplication();

    }

}
