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
package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.DecreaseMarkingsAction;
import com.nequissimus.university.k1584.ui.actions.IncreaseMarkingsAction;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;

/**
 * Context menu for Petri place icons on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasPlaceMenu extends CanvasIconMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6468863481232638652L;

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Create a new menu for places.
     * @param invoker Invoking place
     */
    public CanvasPlaceMenu(final PlaceLabel invoker) {
        super(invoker);
    }

    @Override
    void addCustomItems() {

        JMenuItem item =
            new JMenuItem(CanvasPlaceMenu.MSG.getIconMenuIncreaseMarkings());
        item.addActionListener(new IncreaseMarkingsAction((PlaceLabel) this
            .getPetriLabel()));
        this.add(item);

        item =
            new JMenuItem(CanvasPlaceMenu.MSG.getIconMenuDecreaseMarkings());
        item.addActionListener(new DecreaseMarkingsAction((PlaceLabel) this
            .getPetriLabel()));
        this.add(item);

    }

}
