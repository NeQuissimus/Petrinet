package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.menus.SidebarIconMenu;

/**
 * Sidebar menu listener. This listener opens the context menu for sidebar
 * labels.
 * @author Tim Steinbach
 */
public class SidebarIconMenuListener implements MouseListener {

    /**
     * Invoker component.
     */
    private final AbstractLabel invoker;

    /**
     * Create a new listener for the given label component.
     * @param invoker Label component
     */
    public SidebarIconMenuListener(final AbstractLabel invoker) {

        this.invoker = invoker;

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
     * Open the context menu.
     * @param e Mouse event
     */
    private void openMenu(final MouseEvent e) {

        final SidebarIconMenu menu = new SidebarIconMenu(this.invoker);
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

}
