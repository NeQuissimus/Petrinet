package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Abstract class for places and transitions.
 * @author Tim Steinbach
 */
public abstract class PetriObject {

    /**
     * Object's name.
     */
    private String name;

    /**
     * Position on graphical net.
     */
    private Point position;

    /**
     * Size of graphical representation.
     */
    private Dimension size;

    /**
     * Internal object id.
     */
    private final String id;

    /**
     * Create new object with a given name.
     * @param name Name
     */
    PetriObject(final String name) {

        this.name = name;
        this.id = PetriObjectId.getId();

    }

    /**
     * Get the object's id.
     * @return Id
     */
    final String getId() {
        return this.id;
    }

    /**
     * Get the object's name.
     * @return Name
     */
    final String getName() {
        return this.name;
    }

    /**
     * Set a new name.
     * @param name Name to be set
     */
    final void setName(final String name) {
        this.name = name;
    }

    /**
     * Get the object's position.
     * @return Position
     */
    final Point getPosition() {

        return position;

    }

    /**
     * Set the object's position.
     * @param position Position
     */
    final void setPosition(final Point position) {

        this.position = position;

    }

    /**
     * Get the object's size.
     * @return Size
     */
    final Dimension getSize() {

        return size;

    }

    /**
     * Set the object's size.
     * @param size Size
     */
    final void setSize(final Dimension size) {

        this.size = size;

    }

}
