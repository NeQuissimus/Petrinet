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

package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.ConnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.DisconnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.RemoveObjectAction;
import com.nequissimus.university.k1584.ui.actions.RenameObjectAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Context menu that is displayed for Petri objects on the canvas.
 * @author Tim Steinbach
 */
public abstract class CanvasIconMenu extends AbstractContextMenu {

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -906932943870192331L;

    /**
     * Create a new context menu for a label.
     * @param invoker Label
     */
    public CanvasIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    /**
     * Add custom menu entries in sub-classes.
     */
    abstract void addCustomItems();

    @Override
    final void setupMenu() {

        JMenuItem item =
            new JMenuItem(CanvasIconMenu.MSG.getIconMenuRemove());
        item.addActionListener(new RemoveObjectAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem(CanvasIconMenu.MSG.getIconMenuRename());
        item.addActionListener(new RenameObjectAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem(CanvasIconMenu.MSG.getIconMenuConnect());
        item.addActionListener(new ConnectArrowAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem(CanvasIconMenu.MSG.getIconMenuDisconnect());
        item.addActionListener(new DisconnectArrowAction(this
            .getPetriLabel()));
        this.add(item);

        this.addCustomItems();

    }

}
