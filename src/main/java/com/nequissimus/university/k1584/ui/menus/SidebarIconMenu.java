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

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.AddNewPlaceAction;
import com.nequissimus.university.k1584.ui.actions.AddNewTransitionAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;

/**
 * Context menu for sidebar icons.
 * @author Tim Steinbach
 */
public class SidebarIconMenu extends AbstractContextMenu {

    /**
     * Message pool.
     */
    private static final MessagePool MSG = Singleton
        .getObject(MessagePool.class);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 8479743006947699772L;

    /**
     * Create side bar instance for a given label instance.
     * @param invoker Invoker label
     */
    public SidebarIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    @Override
    final void setupMenu() {

        final JMenuItem item =
            new JMenuItem(SidebarIconMenu.MSG.getIconMenuSidebarAdd());

        ActionListener action = null;

        if (this.getPetriLabel() instanceof PlaceLabel) {
            action = new AddNewPlaceAction();
        } else if (this.getPetriLabel() instanceof TransitionLabel) {
            action = new AddNewTransitionAction();
        }

        if (action != null) {

            item.addActionListener(action);
            this.add(item);

        }
    }

}
