package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.traits.Resizable;

public class PetriTransitionIcon extends AbstractIcon implements Resizable {

    private static final long serialVersionUID = 5314485571108672835L;

    protected PetriTransitionIcon(final Dimension size) {

	super(size);

	setDefaultImage(new ImageIcon("./img/square.png"));

	this.draw();

    }

    @Override
    public void resize(final Dimension newSize) {

	this.size.setSize(newSize);
	this.draw();

    }

}
