/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.nequissimus.university.k1584.ui.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Set;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Mouse listener that allows drag and drop for UI components. If any of the
 * configured modifier keys is pressed, the selected component will be added to
 * a set of components that can all be moved at once.
 * @author Tim Steinbach
 */
public class DragListener implements MouseListener, MouseMotionListener,
    Serializable {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5540857588445774985L;

    /**
     * UI component to be moved via drag and drop.
     */
    private final AbstractLabel label;

    /**
     * Location on application window where drag and drop originated.
     */
    private final Point mouseDownPoint = new Point(0, 0);

    /**
     * Create a new listener for a label component.
     * @param panel Label component
     */
    public DragListener(final AbstractLabel panel) {

        this.label = panel;

    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public final void mouseDragged(final MouseEvent e) {

        final Set<AbstractLabel> labels =
            SelectListener.getSelectedLabels();

        if (labels.isEmpty()) {

            labels.add(this.label);

        }

        for (final AbstractLabel label : labels) {

            final int x =
                (label.getBounds().x + e.getX()) - this.mouseDownPoint.x;
            final int y =
                (label.getBounds().y + e.getY()) - this.mouseDownPoint.y;

            DragListener.CONTROLLER.moveLabel(label, new Point(x, y));

        }

        DragListener.CONTROLLER.redrawCanvas();

    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public final void mouseExited(final MouseEvent e) {
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    @Override
    public final void mousePressed(final MouseEvent e) {

        final Point mousePoint = e.getPoint();

        this.mouseDownPoint.setLocation(mousePoint);

    }

    @Override
    public final void mouseReleased(final MouseEvent e) {
    }
}
