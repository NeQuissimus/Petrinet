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

package com.nequissimus.university.k1584.ui.enums;

import javax.swing.SwingConstants;

/**
 * Label text position. The label text can be moved relative to its image which
 * this position enumeration.
 * @author Tim Steinbach
 */
public enum TextPosition {

    /**
     * Values with horizontal and vertical positions.
     */
    BELOW(SwingConstants.CENTER, SwingConstants.BOTTOM), RIGHT(
        SwingConstants.RIGHT, SwingConstants.CENTER);

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
