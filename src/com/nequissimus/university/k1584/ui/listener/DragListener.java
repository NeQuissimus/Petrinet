package com.nequissimus.university.k1584.ui.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.PetriWindow;

/**
 * Mouse listener that allows drag and drop for UI components.
 * @author Tim Steinbach
 */
public class DragListener implements MouseListener, MouseMotionListener,
    Serializable {

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
    public final void mouseDragged(final MouseEvent e) {

        final int x =
            this.label.getBounds().x + e.getX() - this.mouseDownPoint.x;
        final int y =
            this.label.getBounds().y + e.getY() - this.mouseDownPoint.y;

        this.label.setLocation(x, y);

        PetriWindow.getCanvas().revalidate();
        PetriWindow.getCanvas().repaint();

    }

    @Override
    public final void mousePressed(final MouseEvent e) {

        this.mouseDownPoint.setLocation(e.getPoint());

    }

    @Override
    public void mouseMoved(final MouseEvent arg0) {
    }

    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }

    @Override
    public void mouseExited(final MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(final MouseEvent arg0) {
    }

}
