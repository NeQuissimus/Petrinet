package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * This action changes the size for all objects on the canvas.
 * @author Tim Steinbach
 */
public final class ChangeIconSizeAction implements ActionListener {

    /**
     * New icon size.
     */
    private final IconSize size;

    /**
     * Create a new action that will change all icons to a given size.
     * @param size Size
     */
    public ChangeIconSizeAction(final IconSize size) {

        this.size = size;

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        PetriController.getInstance().setIconSize(this.size);

    }

}
