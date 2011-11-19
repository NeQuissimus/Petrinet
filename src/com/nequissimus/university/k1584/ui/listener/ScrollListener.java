package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Listener that refreshed the canvas when the surrounding
 * {@link javax.swing.JScrollPane} has been moved.
 * @author Tim Steinbach
 */
public class ScrollListener implements AdjustmentListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    @Override
    public final void adjustmentValueChanged(final AdjustmentEvent e) {

        if (e.getValueIsAdjusting()) {
            return;
        }

        ScrollListener.CONTROLLER.redrawCanvas();

    }
}
