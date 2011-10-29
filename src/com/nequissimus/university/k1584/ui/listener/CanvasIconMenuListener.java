package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.menus.CanvasIconMenu;

/**
 * Mouse listener that opens the context menu for icons that are on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasIconMenuListener implements MouseListener {

    /**
     * Invoking Petri label.
     */
    private final AbstractLabel invoker;

    /**
     * Create listener for opening the context menu on canvas icons.
     * @param invoker Invoking label
     */
    public CanvasIconMenuListener(final AbstractLabel invoker) {

        this.invoker = invoker;

    }

    /**
     * Open the context menu.
     * @param e Mouse event
     */
    private void openMenu(final MouseEvent e) {

        final CanvasIconMenu menu = new CanvasIconMenu(this.invoker);
        menu.show(e.getComponent(), e.getX(), e.getY());

    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {

        if (e.isPopupTrigger()) {
            this.openMenu(e);
        }

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

        if (e.isPopupTrigger()) {
            this.openMenu(e);
        }

    }

}
