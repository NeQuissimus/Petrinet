package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action called when trying to increase the token size.
 * @author Tim Steinbach
 */
public final class TokenLargerAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    @Override
    public void actionPerformed(final ActionEvent e) {

        TokenLargerAction.CONTROLLER.increaseTokenSize();

    }

}
