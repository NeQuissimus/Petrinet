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

package com.nequissimus.university.k1584.ui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConstants;
import com.nequissimus.university.k1584.ui.enums.BoxSide;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

/**
 * Arrow connecting two Petri objects. The arrow is automatically aligned to the
 * objects according to their relative position to each other. That way the
 * arrow never crosses through the object itself. The arrow aligns on the top,
 * bottom, left or right side of the label icon and is always centered on the
 * respective side.
 * @author Tim Steinbach
 */
public class Arrow extends JPanel {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 3151568700241791450L;

    /**
     * Arrow cross line.
     */
    private final int[] crossLine = new int[4];

    /**
     * Connect arrow from this label.
     */
    private final AbstractLabel from;

    /**
     * Arrow head line.
     */
    private final int[] headLine = new int[2];

    /**
     * Connect arrow to this label.
     */
    private final AbstractLabel to;

    /**
     * Create new arrow.
     * @param from Connect from this label
     * @param to Connect to this label
     */
    public Arrow(final AbstractLabel from, final AbstractLabel to) {

        this.from = from;
        this.to = to;

    }

    /**
     * Get arrow origin.
     * @return Arrow origin
     */
    public final AbstractLabel getFrom() {
        return this.from;
    }

    /**
     * Get arrow target.
     * @return Arrow target
     */
    public final AbstractLabel getTo() {
        return this.to;
    }

    @Override
    public final void paintComponent(final Graphics g) {

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);

        final Dimension iconSize = Arrow.CONTROLLER.getIconSize().getSize();

        final Point from = this.from.getLocation();
        final Point to = this.to.getLocation();

        if (this.from.getHeight() > iconSize.height) {

            final Point fromLoc = this.from.getLocation();
            final Dimension fromSize = this.from.getSize();

            final int offset = (fromSize.width - iconSize.width) / 2;

            from.setLocation(fromLoc.x + offset, fromLoc.y);

        }

        if (this.to.getHeight() > iconSize.height) {

            final Point toLoc = this.to.getLocation();
            final Dimension toSize = this.to.getSize();

            final int offset = (toSize.width - iconSize.width) / 2;

            to.setLocation(toLoc.x + offset, toLoc.y);

        }

        boolean fromAbove = false;
        boolean fromLeft = false;
        boolean fromBelow = false;
        boolean fromRight = false;

        if ((from.y + iconSize.height) < to.y) {
            fromAbove = true;
        }

        if ((to.y + iconSize.height) < from.y) {
            fromBelow = true;
        }

        if ((from.x + iconSize.width) < to.x) {
            fromLeft = true;
        }

        if ((to.x + iconSize.width) < from.x) {
            fromRight = true;
        }

        if (fromAbove) {

            this.moveToSide(from, BoxSide.BOTTOM);
            this.moveToSide(to, BoxSide.TOP);

            // this.from.moveText(TextPosition.RIGHT);

        } else if (fromBelow) {

            this.moveToSide(from, BoxSide.TOP);
            this.moveToSide(to, BoxSide.BOTTOM);

            // this.to.moveText(TextPosition.RIGHT);

        } else if (fromLeft) {

            this.moveToSide(from, BoxSide.RIGHT);
            this.moveToSide(to, BoxSide.LEFT);

            this.from.moveText(TextPosition.BELOW);

        } else if (fromRight) {

            this.moveToSide(from, BoxSide.LEFT);
            this.moveToSide(to, BoxSide.RIGHT);

            this.to.moveText(TextPosition.BELOW);

        }

        g.drawLine(from.x, from.y, to.x, to.y);
        g.fillPolygon(this.getArrow(from, to,
            PetriConstants.ARROW_HEAD_SIZE));

    }

    /**
     * Get polygon for painting the arrow.
     * @param from Run arrow from this point
     * @param to Run arrow to this point
     * @param headsize Size of arrow head
     * @return Arrow polygon
     */
    private Polygon getArrow(final Point from, final Point to,
        final int headsize) {

        this.resetArrowHeadLine(from, to, headsize);

        final int[] crossBase = this.headLine;
        final int[] headBase = this.headLine;

        this.resetArrowHeadCrossLine(crossBase, to);

        final Polygon head = new Polygon();

        head.addPoint(headBase[0], headBase[1]);
        head.addPoint(this.crossLine[0], this.crossLine[1]);
        head.addPoint(to.x, to.y);
        head.addPoint(this.crossLine[2], this.crossLine[3]);
        head.addPoint(headBase[0], headBase[1]);
        head.addPoint(from.x, from.y);

        return head;
    }

    /**
     * Move the point attaching the arrow to one side of the label.
     * @param p Attaching point
     * @param side Side to move to
     */
    private void moveToSide(final Point p, final BoxSide side) {

        final Dimension iconSize = Arrow.CONTROLLER.getIconSize().getSize();

        switch (side) {

            case LEFT:
                p.y += iconSize.height / 2;
                break;
            case TOP:
                p.x += iconSize.width / 2;
                break;
            case BOTTOM:
                p.x += iconSize.width / 2;
                p.y += iconSize.height;
                break;
            case RIGHT:
                p.x += iconSize.width;
                p.y += iconSize.height / 2;
                break;

            default:
                break;

        }

    }

    /**
     * Set arrow head cross line.
     * @param crosslineBase Base to calculate cross against
     * @param cross Point to cross at
     */
    private void resetArrowHeadCrossLine(final int[] crosslineBase,
        final Point cross) {

        final int x1 = crosslineBase[0];
        final int x2 = crosslineBase[1];

        final int crossX =
            (int) (((cross.x - x1) * PetriConstants.ARROW_HEAD_FACTOR) + x1);
        final int crossY =
            (int) (((cross.y - x2) * PetriConstants.ARROW_HEAD_FACTOR) + x2);

        this.crossLine[0] = (((x1 + x2) - crossY));
        this.crossLine[1] = (((x2 + crossX) - x1));
        this.crossLine[2] =
            this.crossLine[0] + ((x1 - this.crossLine[0]) * 2);
        this.crossLine[3] =
            this.crossLine[1] + ((x2 - this.crossLine[1]) * 2);

    }

    /**
     * Set head line for the arrow.
     * @param from Point to connect from
     * @param to Point to connect to
     * @param distance Distance of head to end point
     */
    private void resetArrowHeadLine(final Point from, final Point to,
        final int distance) {

        final double stretch =
            1 - (distance / (Math.sqrt(((to.x - from.x) * (to.x - from.x))
                + ((to.y - from.y) * (to.y - from.y)))));

        this.headLine[0] = (int) (stretch * (to.x - from.x)) + from.x;
        this.headLine[1] = (int) (stretch * (to.y - from.y)) + from.y;

    }

}
