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

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConstants;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.enums.TextPosition;
import com.nequissimus.university.k1584.ui.listener.SidebarIconMenuListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

/**
 * Abstract class defining essential properties for each label component.
 * @author Tim Steinbach
 */
public abstract class AbstractLabel extends JLabel implements Draggable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6558385524618595255L;

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Create a new UI label without it being associated to a logical component.
     */
    protected AbstractLabel() {

        this.setIcon(this.getPetriIcon(IconSize.MEDIUM));
        this.setText("");
        this.setOpaque(false);

        this.moveText(TextPosition.RIGHT);

    }

    /**
     * Create a new UI label for a given logical component.
     * @param name Label name
     */
    protected AbstractLabel(final String name) {

        this();

        this.setIcon(this.getPetriIcon(AbstractLabel.CONTROLLER
            .getIconSize()));

        this.setText(name);

        this.setBackground(PetriConstants.COLOR_NORMAL);

    }

    /**
     * Add the context menu for elements on the canvas.
     */
    public abstract void addCanvasMenu();

    /**
     * Add the context menu for adding new elements to the canvas.
     */
    public final void addSidebarMenu() {

        this.addMouseListener(new SidebarIconMenuListener(this));

    }

    /**
     * Return the associated icon.
     * @param size Icon size
     * @return Icon
     */
    public abstract AbstractIcon getPetriIcon(final IconSize size);

    /**
     * Resize label icon.
     * @param size New size
     */
    public final void resizeIcon(final IconSize size) {

        this.setIcon(this.getPetriIcon(size));
        this.setSize(this.getPreferredSize());

    }

    @Override
    public final void setText(final String text) {

        super.setText(text);
        this.setSize(this.getPreferredSize());

    }

    @Override
    protected final void paintComponent(final Graphics g) {
        // Override to draw background

        final Color background = this.getBackground();

        // If alpha == 0, background is invisible (no need to paint)
        if (background.getAlpha() > 0) {

            g.setColor(background);
            g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);

        }

        super.paintComponent(g);

    }

    /**
     * Move the text to a different position relative to its icon.
     * @param pos Text position
     */
    final void moveText(final TextPosition pos) {

        this.setHorizontalTextPosition(pos.getX());
        this.setVerticalTextPosition(pos.getY());

    }

}
