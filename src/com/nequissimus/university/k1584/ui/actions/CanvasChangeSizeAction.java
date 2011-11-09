package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * This abstract action helps implement actions that will resize the canvas.
 * @author Tim Steinbach
 */
public abstract class CanvasChangeSizeAction implements ActionListener {

    /**
     * Amount of pixels to change.
     */
    private final int change;

    /**
     * Create a new action that will change the canvas by a number of pixels.
     * @param change Number of pixels the canvas will be changed
     */
    protected CanvasChangeSizeAction(final int change) {

        this.change = change;

    }

    @Override
    public final void actionPerformed(final ActionEvent e) {

        PetriController.getInstance().resizeCanvas(this.change);

    }

}
