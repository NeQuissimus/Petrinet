package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Place class for a Petri net.
 * @author Tim Steinbach
 */
public class PetriPlace extends PetriObject {

    /**
     * Number of markings set.
     */
    private int markings = 0;

    /**
     * Create a new logical place with a given name.
     * @param name Name
     */
    PetriPlace(final String name) {

        super(name);

    }

    /**
     * Create a new logical place with a given name.
     * @param name Name
     * @param id Id
     */
    PetriPlace(final String name, final String id) {

        super(name, id);

    }

    @Override
    public final String toString() {
        return "PetriPlace [" + this.getId() + "]";
    }

    @Override
    protected final PetriPlace clone() {

        final PetriPlace clone =
            new PetriPlace(this.getName(), PetriObjectId.getId());

        clone.setMarkings(this.getMarkings());
        clone.setPosition(new Point(this.getPosition()));
        clone.setSize(new Dimension(this.getSize()));

        return clone;

    }

    /**
     * Decrease the value of markings by one.
     */
    final void decreaseMarkings() {

        if (this.markings > 0) {
            this.markings--;
        }

    }

    /**
     * Get the number of markings set for this place.
     * @return Number of markings
     */
    final int getMarkings() {
        return this.markings;
    }

    /**
     * Increase the value of markings by one.
     */
    final void increaseMarkings() {

        if (this.markings < Integer.MAX_VALUE) {
            this.markings++;
        }

    }

    /**
     * Set the value of markings.
     * @param value If value < 0, value = 0 will be assumed
     */
    final void setMarkings(final int value) {

        int tmpValue = value;

        if (tmpValue < 0) {
            tmpValue = 0;
        }

        this.markings = tmpValue;

    }

}
