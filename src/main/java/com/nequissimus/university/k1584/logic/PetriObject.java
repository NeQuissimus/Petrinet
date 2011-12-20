// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Abstract class for places and transitions.
 * @author Tim Steinbach
 */
public abstract class PetriObject {

    /**
     * Internal object id.
     */
    private final String id;

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
     * Clone the Petri object.
     * @return Clone
     */
    @Override
    protected abstract PetriObject clone();

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
