package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.print.attribute.standard.Severity;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.Canvas;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.elements.Window;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Interface providing all methods needed to work with the Petri net UI.
 * @author Tim Steinbach
 */
public interface PetriUi {

    /**
     * Get the application window.
     * @return Application window
     */
    Window getWindow();

    /**
     * Get the size of the icons used for displaying the Petri net components.
     * @return Icon size
     */
    IconSize getIconSize();

    /**
     * Set a new size for all icons representing Petri net components.
     * @param size New icon size
     */
    void setIconSize(final IconSize size);

    /**
     * Redraw the canvas.<br />
     * This method should take care of the entire process, not just call
     * {@link java.awt.Component#repaint}.
     */
    void redrawCanvas();

    /**
     * Add a new Petri place to the UI and return the label component.
     * @return Label component
     */
    PlaceLabel addPlace();

    /**
     * Add a new Petri transition to the UI ad return the label component.
     * @return Label component
     */
    TransitionLabel addTransition();

    /**
     * Remove a label component representing a Petri object.
     * @param label Label component
     */
    void removeLabel(final AbstractLabel label);

    /**
     * Report a message to the user by opening an appropriate dialog.
     * @param severity Message severity
     * @param message Message text
     */
    void reportMessage(final Severity severity, final String message);

    /**
     * Move a label component to a new location.
     * @param label Label component
     * @param location New location
     */
    void moveLabel(final AbstractLabel label, final Point location);

    /**
     * Resize all the arrow components' canvases.
     * @param size New size
     */
    void resizeArrowCanvas(final Dimension size);

    /**
     * Resize the editor canvas to a new size.
     * @param size New size
     */
    void resizeCanvas(final Dimension size);

    /**
     * Get the main canvas.
     * @return Editor canvas
     */
    Canvas getCanvas();

    /**
     * Add an arrow to the UI.
     * @param arrow Arrow to add
     */
    void addArrow(final Arrow arrow);

    /**
     * Remove an arrow from the UI.
     * @param arrow Arrow to remove
     */
    void removeArrow(final Arrow arrow);

    /**
     * Show the application window.
     */
    void showWindow();

    /**
     * Hide the application window.
     */
    void hideWindow();

    /**
     * Change a transition's UI whether it is active or not.
     * @param label Transition
     * @param active Active or not
     */
    void markTransitionActive(TransitionLabel label, boolean active);

}
