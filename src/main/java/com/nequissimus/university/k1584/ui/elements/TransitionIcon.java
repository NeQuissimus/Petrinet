package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * Transition icon that is resizable.
 * @author Tim Steinbach
 */
public class TransitionIcon extends AbstractIcon implements Resizable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5314485571108672835L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Create new transition icon with a certain size.
     * @param size Icon size
     */
    protected TransitionIcon(final IconSize size) {

        super(size);

        this.setDefaultImage(new ImageIcon(TransitionIcon.CONFIG
            .getImageTransition()));

        this.draw();

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

}