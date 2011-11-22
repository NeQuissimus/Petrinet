package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.listener.SelectListener;

/**
 * Remove all selected objects from the canvas and logical net.<br />
 * If no object has been selected explicitly, the one the action has been
 * executed on will be removed.
 * @author Tim Steinbach
 */
public final class RemoveObjectAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Invoking Petri label.
     */
    private final AbstractLabel invoker;

    /**
     * Create new action that can remove a given label.
     * @param invoker Label to be removed
     */
    public RemoveObjectAction(final AbstractLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        final Set<AbstractLabel> labels =
            SelectListener.getSelectedLabels();

        if (labels.isEmpty()) {
            labels.add(this.invoker);
        }

        for (final AbstractLabel abstractLabel : labels) {

            RemoveObjectAction.CONTROLLER.removeObject(abstractLabel);

        }

    }

}
