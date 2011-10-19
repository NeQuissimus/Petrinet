package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

/**
 * Abstract class for places and transitions
 * @author Tim Schram
 *
 */
public abstract class PetriObject {

    private String name;
    private Point position;
    private Dimension size;
    
    PetriObject(final String name) {
	
	this.name = name;
	
    }

    /**
     * Get the object's name
     * @return Name
     */
    String getName() {return this.name;}
    
    /**
     * Set a new name
     * @param name Name to be set
     */
    void setName(final String name) {this.name = name;}

    Point getPosition() {
	
	return position;
	
    }

    void setPosition(Point position) {
	
	this.position = position;
	
    }

    Dimension getSize() {
	
	return size;
	
    }

    void setSize(Dimension size) {
	
	this.size = size;
	
    }    
    
}
