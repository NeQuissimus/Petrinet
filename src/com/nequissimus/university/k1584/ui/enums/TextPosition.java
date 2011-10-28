package com.nequissimus.university.k1584.ui.enums;

import javax.swing.SwingConstants;

/**
 * Label text position. The label text can be moved relative to its image which
 * this position enummeration.
 * @author Tim Steinbach
 */
public enum TextPosition {

    /**
     * Values with horizontal and vertical positions.
     */
    RIGHT(SwingConstants.RIGHT, SwingConstants.CENTER), BELOW(
        SwingConstants.CENTER, SwingConstants.BOTTOM);

    /**
     * Horizontal position.
     */
    private final int horPosition;

    /**
     * Vertical position.
     */
    private final int vertPosition;

    /**
     * Create a new position value.
     * @param horizontal Horizontal position
     * @param vertical Vertical position
     */
    private TextPosition(final int horizontal, final int vertical) {

        this.horPosition = horizontal;
        this.vertPosition = vertical;

    }

    /**
     * Get the horizontal position.
     * @return Horizontal position
     */
    public final int getX() {
        return this.horPosition;
    }

    /**
     * Get the vertical position.
     * @return Vertical position
     */
    public int getY() {
        return this.vertPosition;
    }

}
