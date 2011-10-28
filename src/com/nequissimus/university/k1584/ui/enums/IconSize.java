package com.nequissimus.university.k1584.ui.enums;

import java.awt.Dimension;

/**
 * Preset values for icon sizes.<br />
 * VERY_SMALL: 30x30 px<br />
 * SMALL: 45x45 px<br />
 * MEDIUM: 60x60 px<br />
 * LARGE: 100x100 px<br />
 * @author Tim Steinbach
 */
public enum IconSize {

    /**
     * Icon size values.
     */
    VERY_SMALL(30, 30), SMALL(45, 45), MEDIUM(60, 60), LARGE(100, 100);

    /**
     * Icon size as dimension object.
     */
    private Dimension size;

    /**
     * Create a new icon size value.
     * @param width Icon width
     * @param height Icon height
     */
    private IconSize(final int width, final int height) {

        this.size = new Dimension(width, height);

    }

    /**
     * Get icon size.
     * @return Icon size
     */
    public Dimension getSize() {
        return this.size;
    }

}
