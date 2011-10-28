package com.nequissimus.university.k1584.ui.traits;

/**
 * Interface to make a component capable of drag and drop.
 * @author Tim Steinbach
 *
 */
public interface Draggable {

    /**
     * Register component as draggable.
     */
    void registerDraggable();

    /**
     * Unregister dragging capabilities.
     */
    void unregisterDraggable();

}
