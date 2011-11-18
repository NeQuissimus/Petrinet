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
    private final Point position = new Point();

    /**
     * Size of graphical representation.
     */
    private final Dimension size = new Dimension();

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
     * Create new object with a given name and id.
     * @param name Name
     * @param id Id
     */
    PetriObject(final String name, final String id) {

        this.name = name;
        this.id = id;

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
     * Get the object's position.
     * @return Position
     */
    final Point getPosition() {

        return new Point(this.position);

    }

    /**
     * Get the object's size.
     * @return Size
     */
    final Dimension getSize() {

        return new Dimension(this.size);

    }

    /**
     * Set a new name.
     * @param name Name to be set
     */
    final void setName(final String name) {
        this.name = name;
    }

    /**
     * Set the object's position.
     * @param position Position
     */
    final void setPosition(final Point position) {

        this.position.setLocation(position);

    }

    /**
     * Set the object's size.
     * @param size Size
     */
    final void setSize(final Dimension size) {

        this.size.setSize(size);

    }

}
