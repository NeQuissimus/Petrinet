package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.enums.IconSize;

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
    private ImageIcon defaultImage;

    /**
     * Create a new icon with a given size.
     * @param size Icon size
     */
    protected AbstractIcon(final IconSize size) {

        super();

        this.size = size.getSize();

    }

    /**
     * Set default image.
     * @param image Image
     */
    protected final void setDefaultImage(final ImageIcon image) {
        this.defaultImage = image;
    }

    /**
     * Set new icon size.
     * @param size Size
     */
    final void setSize(final Dimension size) {

        this.size.setSize(size);

    }

    /**
     * Draw the icon.
     */
    final void draw() {

        final Image i = this.defaultImage.getImage();
        final Image newImage =
            i.getScaledInstance(this.size.width, this.size.height,
                Image.SCALE_SMOOTH);

        this.setImage(newImage);

        this.drawOnFile();

    }

    /**
     * Method to be overridden when something custom is to be drawn in addition
     * to the image loaded.
     */
    void drawOnFile() {
    }

}
