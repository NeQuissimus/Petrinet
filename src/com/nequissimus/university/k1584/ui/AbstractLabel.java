package com.nequissimus.university.k1584.ui;

import javax.swing.JLabel;

import com.nequissimus.university.k1584.ui.enums.TextPosition;

public abstract class AbstractLabel extends JLabel {

    private static final long serialVersionUID = 6558385524618595255L;
    
    protected String name;
    
    public AbstractLabel(final String name) {
	
	this.name = name;
	
	this.setIcon(getPetriIcon());
	this.setText(this.name);
	this.setOpaque(false);
	
	this.moveText(TextPosition.RIGHT);
	
    }
    
    void moveText(final TextPosition pos) {
	
	this.setHorizontalTextPosition(pos.getX());
	this.setVerticalTextPosition(pos.getY());
	
    }
    
    abstract AbstractIcon getPetriIcon();

}
