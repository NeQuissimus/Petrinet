package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.menus.CanvasIconMenu;

/**
 * Mouse listener that opens the context menu for icons that are on the canvas.
 * @author Tim Steinbach
 */
public abstract class CanvasIconMenuListener implements MouseListener {

    /**
     * Invoking Petri label.
     */
    private final AbstractLabel invoker;

    /**
     * Menu to use.
     */
    private CanvasIconMenu menu;

    /**
     * Create listener for opening the context menu on canvas icons.
     * @param invoker Invoking label
     */
    public CanvasIconMenuListener(final AbstractLabel invoker) {

        this.invoker = invoker;
        this.setMenu();

    }

    /**
     * Get menu.
     * @return Menu
     */
    protected final CanvasIconMenu getMenu() {

        return this.menu;

    }

    /**
     * Set a new menu.
     * @param menu New menu
     */
    protected final void setMenu(final CanvasIconMenu menu) {

        this.menu = menu;

    }

    /**
     * Set new menu. This methods needs to be implemented in sub-classes to
     * create individual menus for different objects.
     */
    protected abstract void setMenu();

    /**
     * Open the context menu.
     * @param e Mouse event
     */
    protected final void openMenu(final MouseEvent e) {

        this.menu.show(e.getComponent(), e.getX(), e.getY());

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
    public final void mousePressed(final MouseEvent e) {

        if (e.isPopupTrigger()) {
            this.openMenu(e);
        }

    }

    @Override
    public final void mouseReleased(final MouseEvent e) {

        if (e.isPopupTrigger()) {
            this.openMenu(e);
        }

    }

    /**
     * Get the invoking component.
     * @return Invoker
     */
    protected final AbstractLabel getInvoker() {

        return this.invoker;

    }

}
