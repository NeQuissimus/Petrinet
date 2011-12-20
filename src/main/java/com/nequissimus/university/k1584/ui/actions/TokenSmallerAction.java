package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Action called when trying to decrease the token size.
 * @author Tim Steinbach
 */
public final class TokenSmallerAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    @Override
    public void actionPerformed(final ActionEvent e) {

        TokenSmallerAction.CONTROLLER.decreaseTokenSize();

    }

}
