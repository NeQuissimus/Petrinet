package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action to add a new place to the net.
 * @author Tim Steinbach
 */
public class AddNewPlaceAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        AddNewPlaceAction.CONTROLLER.addPlace();

    }

}
