package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * Transition icon that is resizable.
 * @author Tim Steinbach
 */
public class PetriTransitionIcon extends AbstractIcon implements Resizable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5314485571108672835L;

    /**
     * Create new transition icon with a certain size.
     * @param size Icon size
     */
    protected PetriTransitionIcon(final IconSize size) {

        super(size);

        setDefaultImage(new ImageIcon("./img/square.png"));

        this.draw();

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

}
