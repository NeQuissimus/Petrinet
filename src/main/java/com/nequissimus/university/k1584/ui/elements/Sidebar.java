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
package com.nequissimus.university.k1584.ui.elements;

import javax.swing.JPanel;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

/**
 * Sidebar UI for adding new elements to the canvas.
 * @author Tim Steinbach
 */
public class Sidebar extends JPanel {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -7934305863238811085L;

    /**
     * Configuration instance.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Create a new sidebar instance.
     */
    Sidebar() {

        super();

        final int width = Sidebar.CONFIG.getSidebarWidth();
        final int height = Sidebar.CONFIG.getWindowHeight();

        // Use same color as window background (This prevents ugly effects when
        // resizing the window)
        this.setOpaque(false);

        this.addIcons();

        this.setSize(width, height);

    }

    /**
     * Add the default icons to the sidebar. (1 place, 1 transition)
     */
    private void addIcons() {

        final PlaceLabel label = new PlaceLabel();
        label.moveText(TextPosition.BELOW);
        label.setBounds(10, 10, 100, 100);

        this.add(label);

        final TransitionLabel label2 = new TransitionLabel();
        label2.moveText(TextPosition.BELOW);
        label2.setBounds(0, 120, 100, 100);

        this.add(label2);

    }

}
