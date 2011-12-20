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

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Enumeration for the four corners of a rectangle.
 * @author Tim Steinbach
 */
public enum BoxCorner {

    /**
     * All four corners.
     */
    BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT;

    /**
     * Determine which corner a point is for a given box.
     * @param box Rectangle
     * @param corner Point
     * @return Which corner the point is
     */
    public static BoxCorner getCorner(final Rectangle box,
        final Point corner) {

        final Point tl = new Point(box.x, box.y);
        final Point tr = new Point(box.x + box.width, box.y);
        final Point bl = new Point(box.x, box.y + box.height);
        final Point br = new Point(box.x + box.width, box.y + box.height);

        if (corner.equals(tl)) {
            return TOP_LEFT;
        } else if (corner.equals(tr)) {
            return TOP_RIGHT;
        } else if (corner.equals(bl)) {
            return BOTTOM_LEFT;
        } else if (corner.equals(br)) {
            return BOTTOM_RIGHT;
        } else {
            throw new IllegalArgumentException();
        }

    }

}
