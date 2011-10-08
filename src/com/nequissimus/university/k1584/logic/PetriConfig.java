package com.nequissimus.university.k1584.logic;

import java.util.Properties;

/**
 * This class has the default properties, in case the config file cannot be loaded<br /><br />
 * Default values:<br />
 * PlaceName = ""<br />
 * TransitionName = ""<br />
 * @author Tim Schram
 *
 */
public class PetriConfig extends Properties {

    private static final long serialVersionUID = -6320268661029188694L;

    public static final String PLACE_NAME = "PlaceName";
    public static final String TRANSITION_NAME = "TransitionName";

    public PetriConfig() {
	
	super();
	this.put(PLACE_NAME, "");
	this.put(TRANSITION_NAME, "");
	
    }
    
}
