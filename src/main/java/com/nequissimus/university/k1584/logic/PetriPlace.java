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
