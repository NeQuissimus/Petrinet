package com.nequissimus.university.k1584.ui.listener;

import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.menus.CanvasTransitionMenu;

/**
 * Listener for opening a context menu on transitions that are on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasTransitionListener extends CanvasIconMenuListener {

    /**
     * Listener for creating a new menu for transitions on the canvas.
     * @param invoker Invoking label
     */
    public CanvasTransitionListener(final TransitionLabel invoker) {
        super(invoker);
    }

    @Override
    protected void setMenu() {

        final TransitionLabel label = (TransitionLabel) this.getInvoker();

        this.setMenu(new CanvasTransitionMenu(label));

    }

}
