package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Abstract class for icons that will be used by UI labels.
 * @author Tim Steinbach
 */
public abstract class AbstractIcon extends ImageIcon {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -100231737768771611L;

    /**
     * Icon size.
     */
    private final Dimension size;

    /**
     * Default image.
     */
    private static ImageIcon defaultImage;

    /**
     * Create a new icon with a given size.
     * @param size Icon size
     */
    protected AbstractIcon(final Dimension size) {

        super();

        this.size = size;

    }

    /**
     * Set default image.
     * @param image Image
     */
    protected static void setDefaultImage(final ImageIcon image) {
        AbstractIcon.defaultImage = image;
    }

    /**
     * Draw the image.
     */
    final void draw() {

        Image i = defaultImage.getImage();
        Image newImage =
            i.getScaledInstance(this.size.width, this.size.height,
                Image.SCALE_SMOOTH);

        this.setImage(newImage);

    }

    /**
     * Set new icon size.
     * @param size Size
     */
    final void setSize(final Dimension size) {

        this.size.setSize(size);

    }

}
