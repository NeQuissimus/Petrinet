package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Action that disconnects a label from another one.
 * @author Tim Steinbach
 */
public class DisconnectArrowAction implements ActionListener {

    /**
     * Invoking label.
     */
    private final AbstractLabel invoker;

    /**
     * Create a new action disconnecting one label from another.
     * @param invoker Invoking label
     */
    public DisconnectArrowAction(final AbstractLabel invoker) {
        this.invoker = invoker;
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        PetriController.getInstance().arrowDisconnect(this.invoker);
    }

}
