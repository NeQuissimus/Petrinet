package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;

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
     * Add an arrow to the UI.
     * @param arrow Arrow to add
     */
    void addArrow(final Arrow arrow);

    /**
     * Add a new Petri place to the UI and return the label component.
     * @return Label component
     */
    PlaceLabel addPlace();

    /**
     * Add a new Petri place to the UI with a given name.
     * @param name Label name
     * @return Label component
     */
    PlaceLabel addPlace(final String name);

    /**
     * Add a new Petri transition to the UI ad return the label component.
     * @return Label component
     */
    TransitionLabel addTransition();

    /**
     * Add a new transition to the UI with a given name.
     * @param name Label name
     * @return Label component
     */
    TransitionLabel addTransition(final String name);

    /**
     * Calculate a new canvas size.<br />
     * Canvas sizes are calculated in 500px increments and always square.<br />
     * If the given minimum size was (1600, 2100), the calculated size would be
     * (2500, 2500). If the minimum was (1600, 1600), the result would be (2000,
     * 2000).
     * @param minimum Minimum canvas size
     * @return New canvas size
     */
    Dimension calculateCanvasSize(final Dimension minimum);

    /**
     * Clean the UI, remove all components.
     */
    void clean();

    /**
     * Decrease the number of markings on the place.
     * @param label Place
     */
    void decreaseMarkings(final PlaceLabel label);

    /**
     * Get the main canvas.
     * @return Editor canvas
     */
    Canvas getCanvas();

    /**
     * Return the editor canvas' size.
     * @return Canvas size
     */
    Dimension getCanvasSize();

    /**
     * Get the size of the icons used for displaying the Petri net components.
     * @return Icon size
     */
    IconSize getIconSize();

    /**
     * Get the minimum size needed to display all components on the canvas.
     * @return Minimum size
     */
    Dimension getMinCanvasSize();

    /**
     * Get the application window.
     * @return Application window
     */
    Window getWindow();

    /**
     * Hide the application window.
     */
    void hideWindow();

    /**
     * Increase the number of markings on the place.
     * @param label Place
     */
    void increaseMarkings(final PlaceLabel label);

    /**
     * Change a transition's UI whether it is active or not.
     * @param label Transition
     * @param active Active or not
     */
    void markTransitionActive(final TransitionLabel label,
        final boolean active);

    /**
     * Move a label component to a new location.
     * @param label Label component
     * @param location New location
     */
    void moveLabel(final AbstractLabel label, final Point location);

    /**
     * Redraw the canvas.<br />
     * This method should take care of the entire process, not just call
     * {@link java.awt.Component#repaint}.
     */
    void redrawCanvas();

    /**
     * Remove an arrow from the UI.
     * @param arrow Arrow to remove
     */
    void removeArrow(final Arrow arrow);

    /**
     * Remove a label component representing a Petri object.
     * @param label Label component
     */
    void removeLabel(final AbstractLabel label);

    /**
     * Rename a label component.
     * @param label Label to be renamed
     * @param name New name
     */
    void renameLabel(final AbstractLabel label, final String name);

    /**
     * Report a message to the user by opening an appropriate dialog.
     * @param severity Message severity
     * @param message Message text
     */
    void reportMessage(final Severity severity, final String message);

    /**
     * Resize all the arrow components' canvases.
     * @param size New size
     */
    void resizeArrowCanvas(final Dimension size);

    /**
     * Resize the editor canvas to a new size.<br />
     * The canvas can only be resized to a size larger than its
     * {@link java.awt.Component#getPreferredSize()}, so that its components
     * will be visible after resizing.
     * @param size New size
     * @return Whether the canvas has been resized
     */
    boolean resizeCanvas(final Dimension size);

    /**
     * Resize the canvas by a certain amount of pixels in each direction.
     * @param difference Number of pixels
     * @return Whether the canvas has been resized
     * @see #resizeArrowCanvas(Dimension)
     */
    boolean resizeCanvas(final int difference);

    /**
     * Resize the editor window.
     * @param size New size
     */
    void resizeEditorWindow(final Dimension size);

    /**
     * Set the canvas to a new size.
     * @param size New size
     */
    void setCanvasSize(final Dimension size);

    /**
     * Set a new size for all icons representing Petri net components.
     * @param size New icon size
     */
    void setIconSize(final IconSize size);

    /**
     * Show the application window.
     */
    void showWindow();

    /**
     * Update the markings on places, e.g. after a transition has occurred.
     * @param places Places to update with their new values
     */
    void updateMarkings(final Map<PlaceLabel, Integer> places);

}
