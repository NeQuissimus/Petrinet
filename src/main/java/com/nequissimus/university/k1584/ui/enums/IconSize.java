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
     * Try to find an enumeration representation for a given size. Only either
     * an element's height <b>OR</b> width is checked as the length of the label
     * size can vary.<br />
     * This methods returns MEDIUM if no match could be found.
     * @param size Desired dimension
     * @return IconSize representation if one was found, MEDIUM otherwise
     */
    public static IconSize getIconSize(final Dimension size) {

        for (final IconSize iconSize : IconSize.values()) {

            final Dimension iSize = iconSize.getSize();

            if ((iSize.height == size.height)
                || (iSize.width == size.width)) {
                return iconSize;
            }

        }

        return MEDIUM;

    }

    /**
     * Get icon size.
     * @return Icon size
     */
    public Dimension getSize() {
        return this.size;
    }

}
