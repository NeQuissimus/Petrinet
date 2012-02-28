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

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.OccurTransitionAction;
import com.nequissimus.university.k1584.ui.actions.ReverseOccurTransitionAction;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;

/**
 * Context menu for transitions on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasTransitionMenu extends CanvasIconMenu {

    /**
     * Message pool.
     */
    private static final MessagePool MSG = Singleton
        .getObject(MessagePool.class);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -3627078926497615342L;

    /**
     * Create new context menu for a transition.
     * @param invoker Invoking label
     */
    public CanvasTransitionMenu(final TransitionLabel invoker) {
        super(invoker);
    }

    @Override
    void addCustomItems() {

        final JMenuItem item =
            new JMenuItem(CanvasTransitionMenu.MSG.getIconMenuOccur());
        item.addActionListener(new OccurTransitionAction(
            (TransitionLabel) this.getPetriLabel()));
        this.add(item);

        final JMenuItem item2 =
            new JMenuItem(
                CanvasTransitionMenu.MSG.getIconMenuReverseOccur());
        item2.addActionListener(new ReverseOccurTransitionAction(
            (TransitionLabel) this.getPetriLabel()));
        this.add(item2);

    }
}
