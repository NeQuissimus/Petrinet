package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import com.nequissimus.university.k1584.PetriController;

/**
 * Automatically resize the canvas used by the arrows to match the size of the
 * net canvas.
 * @author Tim Steinbach
 */
public final class ResizeArrowCanvasListener implements ComponentListener {

    @Override
    public void componentHidden(final ComponentEvent arg0) {
    }

    @Override
    public void componentMoved(final ComponentEvent arg0) {
    }

    @Override
    public void componentShown(final ComponentEvent arg0) {
    }

    @Override
    public void componentResized(final ComponentEvent arg0) {

        PetriController.getInstance().resizeArrowCanvas();

    }

}
