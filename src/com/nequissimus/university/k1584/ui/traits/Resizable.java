package com.nequissimus.university.k1584.ui.traits;

import java.awt.Dimension;

/**
 * Interface to make a component resizable.
 * @author Tim Steinbach
 *
 */
public interface Resizable {

    /**
     * Resize the component.
     * @param newSize Component size
     */
    void resize(final Dimension newSize);

}
