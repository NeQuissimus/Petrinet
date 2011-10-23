package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

public abstract class AbstractLabel extends JLabel {

    private static final long serialVersionUID = 6558385524618595255L;

    protected PetriObject object = null;
    
    protected final PetriController controller;

    public AbstractLabel(final PetriObject object) {

	this();

	this.object = object;

	this.setText(this.controller.getName(object));

    }

    public AbstractLabel() {
	
	this.controller = PetriController.getInstance();

	this.setIcon(getPetriIcon());
	this.setText("");
	this.setOpaque(false);

	this.moveText(TextPosition.RIGHT);
	
    }

    void moveText(final TextPosition pos) {

	this.setHorizontalTextPosition(pos.getX());
	this.setVerticalTextPosition(pos.getY());

    }

    abstract AbstractIcon getPetriIcon();
    
    @Override
    public void setText(final String text) {
	
	super.setText(text);
	this.setSize(this.getPreferredSize());
	
    }

    @Override
    public void setSize(Dimension size) {

	super.setSize(size);

	if (null != this.object)
	    this.controller.setSize(object, size);

    }
    
    @Override
    public void setBounds(final int x, final int y, final int width, final int height) {
	
	super.setBounds(x, y, width, height);
	
	if (null != this.object) {
	
	    this.controller.setSize(object, new Dimension(width, height));
	    this.controller.setPosition(object, new Point(x, y));
	
	}
	
    }
    
    @Override
    public void setLocation(final Point location) {
	
	super.setLocation(location);
	
	if (null != this.object)
	    this.controller.setPosition(object, location);
	
    }

}
