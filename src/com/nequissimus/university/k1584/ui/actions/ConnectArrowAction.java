package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Action used to connect a label with another one.
 * @author Tim Steinbach
 */
public class ConnectArrowAction implements ActionListener {

    /**
     * Label invoking this action.
     */
    private final AbstractLabel invoker;

    /**
     * Create a new connect action.
     * @param invoker Label invoking the action
     */
    public ConnectArrowAction(final AbstractLabel invoker) {
        this.invoker = invoker;
    }

    @Override
    public final void actionPerformed(final ActionEvent arg0) {
        PetriController.getInstance().arrowConnect(this.invoker);
    }

}
