package com.nequissimus.university.k1584.ui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * UI Icon for Petri places.
 * @author Tim Steinbach
 */
public class PlaceIcon extends AbstractIcon implements Resizable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -68079442351799309L;

    /**
     * Number of markings.
     */
    private int markings = 0;

    /**
     * Create a new place icon with a given size.
     * @param size Icon size
     */
    protected PlaceIcon(final IconSize size) {

        super(size);

        this.setDefaultImage(new ImageIcon("./img/circle.png"));

        this.draw();

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

    /**
     * Set value of markings.
     * @param value Markings
     */
    public final void setMarkings(final int value) {
        this.markings = value;
        this.drawMarkings();
    }

    /**
     * Draw markings onto icon.
     */
    public final void drawMarkings() {

        final Image oldImage = this.getImage();

        final int height = oldImage.getHeight(null);
        final int width = oldImage.getWidth(null);

        final BufferedImage image =
            new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.BLACK);
        graphics.drawImage(oldImage, 0, 0, null);

        if (this.markings == 1) {

            final int ovalRadius = this.getIconHeight() / 20;
            final int coord = (width / 2) - ovalRadius;

            graphics.fillOval(coord, coord, ovalRadius * 2, ovalRadius * 2);

        } else if (this.markings > 1) {

            final String text = String.valueOf(this.markings);

            final FontMetrics metrics = graphics.getFontMetrics();

            final int textWidth = metrics.stringWidth(text);
            final int textHeight = metrics.getAscent();

            graphics.drawString(text, (width - textWidth) / 2,
                (height + textHeight) / 2);

        }

        this.setImage(image);

    }

    @Override
    final void drawOnFile() {

        this.drawMarkings();

    }

}
