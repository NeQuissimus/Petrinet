package com.nequissimus.university.k1584.logic;

/**
 * Abstract class for places and transitions
 * @author Tim Schram
 *
 */
public abstract class PetriObject {

    protected String name;
    
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
    
}
