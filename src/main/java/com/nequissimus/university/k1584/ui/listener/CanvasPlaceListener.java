package com.nequissimus.university.k1584.ui.listener;

import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.menus.CanvasPlaceMenu;

/**
 * Listener for opening a context menu on places that are on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasPlaceListener extends CanvasIconMenuListener {

    /**
     * Create a new place listener.
     * @param invoker Invoking place
     */
    public CanvasPlaceListener(final PlaceLabel invoker) {
        super(invoker);
    }

    @Override
    protected void setMenu() {

        final PlaceLabel label = (PlaceLabel) this.getInvoker();

        this.setMenu(new CanvasPlaceMenu(label));

    }

}
