/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
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
