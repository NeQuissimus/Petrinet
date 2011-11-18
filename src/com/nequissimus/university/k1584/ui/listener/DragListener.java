package com.nequissimus.university.k1584.ui.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.nequissimus.library.swing.ModifierKeys;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
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
     * Serializable UID.
     */
    private static final long serialVersionUID = 5540857588445774985L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Collection for all labels to be moved.
     */
    private static final Set<AbstractLabel> LABELS =
        new HashSet<AbstractLabel>();

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

        final PetriController controller = PetriController.getInstance();

        if (DragListener.LABELS.isEmpty()) {

            DragListener.LABELS.add(this.label);

        }

        for (final AbstractLabel label : DragListener.LABELS) {

            final int x =
                (label.getBounds().x + e.getX()) - this.mouseDownPoint.x;
            final int y =
                (label.getBounds().y + e.getY()) - this.mouseDownPoint.y;

            controller.moveLabel(label, new Point(x, y));

        }

        controller.redrawCanvas();

    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public final void mouseExited(final MouseEvent e) {

        this.mouseReleased(e);

    }

    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    @Override
    public final void mousePressed(final MouseEvent e) {

        final Point mousePoint = e.getPoint();

        if (this.isConnectKeyDown(e)) {

            final PetriController controller =
                PetriController.getInstance();

            controller.highlightLabel(this.label);

            DragListener.LABELS.add(this.label);

        }

        this.mouseDownPoint.setLocation(mousePoint);

    }

    @Override
    public final void mouseReleased(final MouseEvent e) {

        if (!this.isConnectKeyDown(e)) {

            final PetriController controller =
                PetriController.getInstance();

            controller.unhighlightLabels(DragListener.LABELS);

            DragListener.LABELS.clear();

        }

    }

    /**
     * Check whether at least one of the allowed modifier keys is pressed.
     * @param e Mouse event to check
     * @return True if at least one key was pressed when the event occurred
     */
    private boolean isConnectKeyDown(final MouseEvent e) {

        final ModifierKeys[] allowed =
            DragListener.CONFIG.getAllowedModifierKeys();

        for (final ModifierKeys modifierKeys : allowed) {

            if (modifierKeys.isActive(e)) {
                return true;
            }

        }

        return false;

    }
}
