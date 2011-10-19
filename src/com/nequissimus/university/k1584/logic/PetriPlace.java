package com.nequissimus.university.k1584.logic;

/**
 * Place class for a Petri net
 * @author Tim Schram
 *
 */
public class PetriPlace extends PetriObject {

    private int markings = 0;

    PetriPlace(final String name) {

	super(name);

    }

    /**
     * Increase the value of markings by one
     */
    void increaseMarkings() {

	if (this.markings < Integer.MAX_VALUE)
	    this.markings++;

    }

    /**
     * Decrease the value of markings by one
     */
    void decreaseMarkings() {

	if (this.markings > 0)
	    this.markings--;

    }
    
    /**
     * Set the value of markings
     * @param value If value < 0, value = 0 will be assumed
     */
    void setMarkings(int value) {
	
	if (value < 0)
	    value = 0;
	
	this.markings = value;
	
    }
    
    /**
     * Get the number of markings set for this place
     * @return Number of markings
     */
    int getMarkings() {return this.markings;}

    @Override
    public String toString() {
	return "PetriPlace [" + this.getName() + ", markings=" + this.markings + "]";
    }
    

}
