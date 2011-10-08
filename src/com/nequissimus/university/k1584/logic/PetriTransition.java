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
    
    void addInput(final PetriPlace input) {this.input.add(input);}
    boolean removeInput(final PetriPlace input) {return this.input.remove(input);}
    void addOutput(final PetriPlace output) {this.output.add(output);}
    boolean removeOutput(final PetriPlace output) {return this.output.remove(output);}

}
