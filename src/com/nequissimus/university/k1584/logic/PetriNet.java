package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Main Petri net class that holds all information and methods about the net.
 * This is basically the delegate API of the Petri net logic, no other classes need to be accessed
 * to work with the net.
 * Since the methods to work with the objects are not public, this class provides all necessary access
 * to the net's as well as each component's behaviour.
 * @author Tim Schram
 *
 */
public class PetriNet {

    final Set<PetriPlace> places;
    final Set<PetriTransition> transitions;
    
    final Properties config;
    
    private String name;

    public PetriNet(final String name) {

	this.places = new HashSet<PetriPlace>();
	this.transitions = new HashSet<PetriTransition>();
	this.config = PetriConfig.getInstance();
	
	this.name = name;

    }
    
    public Set<PetriPlace> getPlaces() {return this.places;}
    
    public Set<PetriTransition> getTransitions() {return this.transitions;}
    
    public String getName() {return this.name;}
    
    void setName(final String newName) {this.name = newName;}

    /**
     * Remove a place and all edges touching the object
     * @param place Place to be removed
     */
    public void remove(final PetriPlace place) {

	for (final PetriTransition transition : this.transitions) {

	    transition.removeInput(place);
	    transition.removeOutput(place);

	}

	this.places.remove(place);

    }

    /**
     * Remove a transition and all edges touching the object
     * @param transition Transition to be removed
     */
    public void remove(final PetriTransition transition) {

	this.transitions.remove(transition);

    }

    /**
     * Add a new place to the net
     * @return Returns the newly created place
     */
    public PetriPlace addPlace() {

	final PetriPlace newPlace = new PetriPlace(this.config.getProperty(PetriConfig.PLACE_NAME));
	this.places.add(newPlace);
	
	return newPlace;

    }
    
    /**
     * Add a new transition to the net
     * @return Return the newly created transition
     */
    public PetriTransition addTransition() {
	
	final PetriTransition newTransition = new PetriTransition(this.config.getProperty(PetriConfig.TRANSITION_NAME));
	this.transitions.add(newTransition);
	
	return newTransition;
	
    }
    
    /**
     * Connect a place to a transition (place -> transition)
     * @param from Connect from this place
     * @param to Connect to this transition
     */
    public void connect(final PetriPlace from, final PetriTransition to) {
	
	to.addInput(from);
	
    }
    
    /**
     * Connect a transition to a place (transition -> place)
     * @param from Connect from this transition
     * @param to Connect to this place
     */
    public void connect(final PetriTransition from, final PetriPlace to) {
	
	from.addOutput(to);
	
    }
    
    /**
     * Make a transition occur (Take one marking away from each input place and give one to each output place)
     * if the transition is active.
     * Nothing happens if the transition is not active.
     * @param transition Transition to occur
     */
    public void occur(final PetriTransition transition) {
	
	transition.occur();
	
    }
    
    /**
     * Check whether a transition is active.
     * A transition is active if each of its input places has at least one marking.
     * @param transition Transition to be checked
     * @return Whether the transition is active
     */
    public boolean isActive(final PetriTransition transition) {
	
	return transition.isActive();
	
    }
    
    /**
     * Increase the number of markings for the given place
     * @param place Increase number of markings for this place
     */
    public void increaseMarkings(final PetriPlace place) {
	
	place.increaseMarkings();
	
    }
    
    /**
     * Decrease the number of markings for the given place
     * @param place Decrease number of markings for this place
     */
    public void decreaseMarkings(final PetriPlace place) {
	
	place.decreaseMarkings();
	
    }

    @Override
    public String toString() {
	return "PetriNet [places=" + this.places + ", transitions="
		+ this.transitions + ", config=" + this.config + "]";
    }
    
    /**
     * Rename an object
     * @param object Petri net object to be renamed
     * @param name New name
     */
    public void rename(final PetriObject object, final String name) {
	
	object.setName(name);
	
    }
    
    /**
     * Get a Petri net object's name
     * @param object Petri net object
     * @return Object's name
     */
    public String getName(final PetriObject object) {
	
	return object.getName();
	
    }
    
    /**
     * Get the number of markings set for the place
     * @param place Petri net place
     * @return Number of markings set for place
     */
    public int getMarkings(final PetriPlace place) {
	
	return place.getMarkings();
	
    }
    
    public Point getPosition(final PetriObject object) {
	
	return object.getPosition();
	
    }
    
    public Dimension getSize(final PetriObject object) {
	
	return object.getSize();
	
    }
    
    public Set<PetriPlace> getInputEdges(final PetriTransition transition) {
	
	return transition.getInput();
	
    }
    
    public Set<PetriPlace> getOutputEdges(final PetriTransition transition) {
	
	return transition.getOutput();
	
    }
    
    public void setSize(final PetriObject object, final Dimension size) {
	
	object.setSize(size);
	
    }
    
    public void setPosition(final PetriObject object, final Point position) {
	
	object.setPosition(position);
	
    }

}
