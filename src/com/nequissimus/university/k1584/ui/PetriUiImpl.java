package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Set;

import javax.print.attribute.standard.Severity;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.PetriCanvas;
import com.nequissimus.university.k1584.ui.elements.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.elements.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.elements.PetriWindow;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Main class that holds all information and provides all necessary methods for
 * working with the Petri net UI.
 * @author Tim Steinbach
 */
public final class PetriUiImpl implements PetriUi {

    /**
     * Graphical user interface.
     */
    private PetriWindow ui;

    /**
     * Size for all icons.
     */
    private IconSize iconSize = IconSize.LARGE;

    @Override
    public PetriWindow getWindow() {

        if (null == this.ui) {

            this.ui = new PetriWindow();

        }

        return this.ui;

    }

    @Override
    public void setIconSize(final IconSize size) {

        if (size != this.iconSize) {

            this.iconSize = size;

            final PetriCanvas canvas = this.ui.getCanvas();

            final Set<AbstractLabel> labels = canvas.getLabels();

            for (final AbstractLabel label : labels) {
                label.resizeIcon(size);
            }

            this.redrawCanvas();

        }

    }

    @Override
    public void redrawCanvas() {
        this.ui.getCanvas().repaint();
    }

    @Override
    public IconSize getIconSize() {
        // TODO: Auto-generated method stub
        return null;
    }

    @Override
    public PetriPlaceLabel addPlace() {
        // TODO: Auto-generated method stub
        return null;
    }

    @Override
    public PetriTransitionLabel addTransition() {
        // TODO: Auto-generated method stub
        return null;
    }

    @Override
    public void removeLabel(final AbstractLabel label) {
        // TODO: Auto-generated method stub

    }

    @Override
    public void
        reportMessage(final Severity severity, final String message) {
        // TODO: Auto-generated method stub

    }

    @Override
    public void moveLabel(final AbstractLabel label, final Point location) {
        // TODO: Auto-generated method stub

    }

    @Override
    public void resizeArrowCanvas(final Dimension size) {
        // TODO: Auto-generated method stub

    }

    @Override
    public void resizeCanvas(final Dimension size) {
        // TODO: Auto-generated method stub

    }

    @Override
    public PetriCanvas getCanvas() {
        // TODO: Auto-generated method stub
        return null;
    }

    @Override
    public void removeArrow(final Arrow arrow) {
        // TODO: Auto-generated method stub

    }

}
