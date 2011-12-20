// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

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

    /**
     * Get menu.
     * @return Menu
     */
    protected final CanvasIconMenu getMenu() {

        return this.menu;

    }

    /**
     * Open the context menu.
     * @param e Mouse event
     */
    protected final void openMenu(final MouseEvent e) {

        this.menu.show(e.getComponent(), e.getX(), e.getY());

    }

    /**
     * Set new menu. This methods needs to be implemented in sub-classes to
     * create individual menus for different objects.
     */
    protected abstract void setMenu();

    /**
     * Set a new menu.
     * @param menu New menu
     */
    protected final void setMenu(final CanvasIconMenu menu) {

        this.menu = menu;

    }

}
