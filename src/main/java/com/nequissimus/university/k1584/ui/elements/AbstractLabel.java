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

package com.nequissimus.university.k1584.ui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
     * Horizontal padding between text and icon.
     */
    private static final int PADDING_TEXT_ICON_HOR = 20;

    /**
     * Vertical padding between text and icon.
     */
    private static final int PADDING_TEXT_ICON_VER = 10;

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6558385524618595255L;

    /**
     * Font metrics.
     */
    private FontMetrics metrics = null;

    /**
     * Text position.
     */
    private TextPosition textPosition = TextPosition.BELOW;

    /**
     * Create a new UI label without it being associated to a logical component.
     */
    protected AbstractLabel() {

        this.setIcon(this.getPetriIcon(IconSize.MEDIUM));
        this.setText("");
        this.setOpaque(false);

        this.setVerticalTextPosition(this.textPosition.getY());
        this.setHorizontalTextPosition(this.textPosition.getX());

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

        AbstractLabel.CONTROLLER.redrawCanvas();

    }

    @Override
    protected final void paintComponent(final Graphics g) {
        // Override to draw background

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        this.metrics = g.getFontMetrics();

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

        if (this.textPosition != pos) {

            this.setVerticalTextPosition(pos.getY());
            this.setHorizontalTextPosition(pos.getX());
            this.setHorizontalAlignment(SwingConstants.LEFT);

            // Resize label to fit text and icon
            final IconSize iconSize =
                AbstractLabel.CONTROLLER.getIconSize();
            final Dimension size = new Dimension(iconSize.getSize());

            if (null != this.metrics) {

                this.textPosition = pos;

                final int fontSize = this.metrics.getFont().getSize();
                final int textWidth =
                    this.metrics.stringWidth(this.getText());

                if (pos == TextPosition.BELOW) {

                    int width = (int) size.getWidth();
                    width = (width > textWidth) ? width : textWidth;

                    final int height =
                        (int) (size.getHeight()) + fontSize
                            + AbstractLabel.PADDING_TEXT_ICON_VER;

                    size.setSize(width, height);

                } else if (pos == TextPosition.RIGHT) {

                    size.setSize(size.getWidth() + textWidth
                        + AbstractLabel.PADDING_TEXT_ICON_HOR,
                        size.getHeight());

                }

                this.setSize(size);
                this.setPreferredSize(size);

            }

        }

    }

}
