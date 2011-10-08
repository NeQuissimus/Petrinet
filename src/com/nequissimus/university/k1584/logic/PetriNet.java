package com.nequissimus.university.k1584.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Main Petri net class that holds all information and methods about the net.
 * This is basically the delegate API of the Petri net logic, no other classes need to be accessed
 * to work with the net. 
 * @author Tim Schram
 *
 */
public class PetriNet {

    final Set<PetriPlace> places;
    final Set<PetriTransition> transitions;
    
    final Properties config;

    public PetriNet() {

	this.places = new HashSet<PetriPlace>();
	this.transitions = new HashSet<PetriTransition>();
	this.config = new PetriConfig();
	
	this.readProperties();

    }

    /**
     * Read the properties file 'config.properties'
     * If reading that file fails, a default config will be used
     * @see PetriConfig
     */
    private void readProperties() {

	InputStream is = null;
	
	try {
	    
	    is = new FileInputStream("config.properties");
	    this.config.load(is);
	    
	} catch(IOException e) {
	    
	    // If something went wrong with reading the properties file, the default values
	    // will still be in the config object
	    
	} finally {
	    
	    if(null != is) 
		try {is.close();} catch(IOException e) {}
	    
	}	

    }

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

}
