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
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * UI Icon for Petri places.
 * @author Tim Steinbach
 */
public class PlaceIcon extends AbstractIcon implements Resizable {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -68079442351799309L;

    /**
     * Number of tokens.
     */
    private int tokens = 0;

    /**
     * Create a new place icon with a given size.
     * @param size Icon size
     */
    protected PlaceIcon(final IconSize size) {

        super(size);

        this.setDefaultImage(new ImageIcon(PlaceIcon.CONFIG.getImagePlace()));

        this.draw();

    }

    /**
     * Draw markings onto icon.
     */
    public final void drawTokens() {

        final Image oldImage = this.getImage();

        final int height = oldImage.getHeight(null);
        final int width = oldImage.getWidth(null);

        final BufferedImage image =
            new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.BLACK);
        graphics.drawImage(oldImage, 0, 0, null);

        if (this.tokens == 1) {

            final int ovalRadius = this.getIconHeight() / 20;
            final int coord = (width / 2) - ovalRadius;

            graphics.fillOval(coord, coord, ovalRadius * 2, ovalRadius * 2);

        } else if (this.tokens > 1) {

            final String text = String.valueOf(this.tokens);

            final FontMetrics metrics = graphics.getFontMetrics();

            final int textWidth = metrics.stringWidth(text);
            final int textHeight = metrics.getAscent();

            graphics.drawString(text, (width - textWidth) / 2,
                (height + textHeight) / 2);

        }

        this.setImage(image);

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

    /**
     * Set value of tokens.
     * @param value Tokens
     */
    public final void setTokens(final int value) {
        this.tokens = value;
        this.drawTokens();
    }

    @Override
    final void drawOnFile() {

        this.drawTokens();

    }

}
