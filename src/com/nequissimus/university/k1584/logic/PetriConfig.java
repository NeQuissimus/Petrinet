package com.nequissimus.university.k1584.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load the properties file into this configuration object.<br />
 * This class has default values, in case the configuration file cannot be loaded<br /><br />
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
	
	this.readProperties();
	
    }
    
    /**
     * Fill the configuration object with default values.
     */
    private void getDefaults() {	

	this.put(PLACE_NAME, "");
	this.put(TRANSITION_NAME, "");
	
    }
    
    /**
     * Read the properties file 'config.properties'
     * If reading that file fails, a default config will be used
     */
    private void readProperties() {

	InputStream is = null;
	
	try {
	    
	    is = new FileInputStream("config.properties");
	    this.load(is);
	    
	} catch(IOException e) {
	    
	    // If something went wrong with reading the properties file, the default values
	    // will still be in the needed in the config object
	    this.getDefaults();
	    
	} finally {
	    
	    if(null != is) 
		try {is.close();} catch(IOException e) {}
	    
	}	

    }
    
}
