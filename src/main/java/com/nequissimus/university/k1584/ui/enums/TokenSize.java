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

/**
 * Preset values for token sizes.<br />
 * Radius(R) is the radius used when one token is displayed.<br />
 * FontSize(F) is used as the font size for when there are &gt;1 tokens.<br />
 * <br />
 * SMALL: R3 F11<br />
 * MEDIUM: R6 F13<br />
 * LARGE: R10 F16<br />
 * VERY_LARGE: R20 F24<br />
 * @author Tim Steinbach
 */
public enum TokenSize {

    /**
     * Token size values.
     */
    SMALL(3, 11), MEDIUM(6, 13), LARGE(10, 16), VERY_LARGE(20, 24);

    /**
     * Font size.
     */
    private final int fontSize;

    /**
     * Circle radius.
     */
    private final int radius;

    /**
     * Instantiate the enumeration.
     * @param radius Radius
     * @param fontSize Font size
     */
    private TokenSize(final int radius, final int fontSize) {

        this.radius = radius;
        this.fontSize = fontSize;

    }

    /**
     * Get font size for when more than one token is to be displayed in text
     * form.
     * @return Font size
     */
    public int getFontSize() {

        return this.fontSize;

    }

    /**
     * Get circle radius for when exactly one token is to be displayed.
     * @return Radius
     */
    public int getRadius() {

        return this.radius;

    }

}
