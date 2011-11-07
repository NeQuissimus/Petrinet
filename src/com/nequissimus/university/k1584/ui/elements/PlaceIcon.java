package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * UI Icon for Petri places.
 * @author Tim Steinbach
 */
public class PlaceIcon extends AbstractIcon implements Resizable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -68079442351799309L;

    /**
     * Create a new place icon with a given size.
     * @param size Icon size
     */
    protected PlaceIcon(final IconSize size) {

        super(size);

        AbstractIcon.setDefaultImage(new ImageIcon("./img/circle.png"));

        this.draw();

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

}
