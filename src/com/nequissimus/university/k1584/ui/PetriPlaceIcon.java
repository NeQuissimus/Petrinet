package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;

import javax.swing.ImageIcon;

import com.nequissimus.university.k1584.ui.traits.Resizable;

public class PetriPlaceIcon extends AbstractIcon implements Resizable {

    private static final long serialVersionUID = -68079442351799309L;

    protected PetriPlaceIcon(final Dimension size) {
	
	super(size);
	
	DEFAULT_IMAGE = new ImageIcon("./img/circle.png");
	
	this.draw();
	
    }

    @Override
    public void resize(final Dimension newSize) {
	
	this.size.setSize(newSize);
	this.draw();

    }

}
