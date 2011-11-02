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
    TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;

    /**
     * Determine which corner a point is for a given box.
     * @param box Rectangle
     * @param corner Point
     * @return Which corner the point is
     */
    public static BoxCorner getCorner(final Rectangle box, final Point corner) {

        Point tl = new Point(box.x, box.y);
        Point tr = new Point(box.x + box.width, box.y);
        Point bl = new Point(box.x, box.y + box.height);
        Point br = new Point(box.x + box.width, box.y + box.height);

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
