package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action called when trying to close the window via the window menu bar.
 * @author Tim Steinbach
 */
public class CloseWindowAction implements ActionListener {

    /**
     * Create new action for exiting the application.
     */
    public CloseWindowAction() {

        super();

    }

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        PetriController.getInstance().exit();

    }
}
