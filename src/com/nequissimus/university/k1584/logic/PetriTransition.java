package com.nequissimus.university.k1584.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * Transition in a Petri net
 * @author Tim Schram
 *
 */
public class PetriTransition extends PetriObject {
    
    final Set<PetriPlace> input;
    final Set<PetriPlace> output;

    PetriTransition(final String name) {
	
	super(name);
	
	this.input = new HashSet<PetriPlace>();
	this.output = new HashSet<PetriPlace>();
	
    }    
    
    Set<PetriPlace> getInput() {return this.input;}
    
    Set<PetriPlace> getOutput() {return this.output;}
    
    /**
     * Check whether each of the input's places has at least one marking
     * @return Whether the transition is active
     */
    boolean isActive() {
	
	for (final PetriPlace place : input) {
	    
	    if (place.getMarkings() == 0)
		return false;
	    
	}
	
	return true;
	
    }
    
    /**
     * Take one marking out of each input place and 
     * place one marking into each of the output places
     */
    void occur() {
	
	if (this.isActive()) {
	    
	    for (final PetriPlace place : input) {
		
		place.decreaseMarkings();
		
	    }
	    
	    for (final PetriPlace place : output) {
		
		place.increaseMarkings();
		
	    }
	    
	}
	
    }
    
    @Override
    public String toString() {
	return "PetriTransition [input=" + this.input + ", output="
		+ this.output + "]";
    }

    void addInput(final PetriPlace input) {this.input.add(input);}
    boolean removeInput(final PetriPlace input) {return this.input.remove(input);}
    void addOutput(final PetriPlace output) {this.output.add(output);}
    boolean removeOutput(final PetriPlace output) {return this.output.remove(output);}

}
