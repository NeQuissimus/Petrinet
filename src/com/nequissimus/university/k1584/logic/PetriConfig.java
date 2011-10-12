package com.nequissimus.university.k1584.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load the properties file into this configuration object.<br />
 * This class has default values, in case the configuration file cannot be loaded<br /><br />
 * Default values:<br />
 * PlaceName = "" // Initial name for a place<br />
 * TransitionName = "" // Initial name for a transition<br />
 * CanvasWidth = 1000 // Width for the editing canvas<br />
 * CanvasHeight = 1000 // Height for the editing canvas<br />
 * CanvasWindowWidth = 300 // Width for the visible part of the canvas<br />
 * CanvasWindowHeight = 300 // Height for the visible part of the canvas<br />
 * WindowTitle = "Tim Schram - q7485417" // Window title<br />
 * WindowWidth = 500 // Window width<br />
 * WindowHeight = 400 // Window height<br />
 * WindowX = 100 // Initial X coordinate for window<br />
 * WindowY = 100 // Initial Y coordinate for window<br />
 * @author Tim Schram
 *
 */
public class PetriConfig extends Properties {

    private static final long serialVersionUID = -6320268661029188694L;

    public static final String PLACE_NAME = "PlaceName";
    public static final String TRANSITION_NAME = "TransitionName";
    public static final String CANVAS_WIDTH = "CanvasWidth";
    public static final String CANVAS_HEIGHT = "CanvasHeight";
    public static final String CANVAS_WINDOW_WIDTH = "CanvasWindowWidth";
    public static final String CANVAS_WINDOW_HEIGHT = "CanvasWindowHeight";
    public static final String WINDOW_TITLE = "WindowTitle";
    public static final String WINDOW_WIDTH = "WindowWidth";
    public static final String WINDOW_HEIGHT = "WindowHeight";
    public static final String WINDOW_X = "WindowX";
    public static final String WINDOW_Y = "WindowY";

    private static PetriConfig config = null;
    
    private PetriConfig() {
	
	super();
	
	this.readProperties();
	
    }
    
    /**
     * Instead of using the constructor, this method is used to make this a singleton.
     * This is to make sure the properties file is only read once.
     * @return Configuration instance
     */
    public static PetriConfig getInstance() {
	
	if (config == null)
	    config = new PetriConfig();
	
	return config;
	
    }
    
    /**
     * Fill the configuration object with default values.
     */
    private void getDefaults() {	

	this.put(PLACE_NAME, "");
	this.put(TRANSITION_NAME, "");
	this.put(CANVAS_HEIGHT, 1000);
	this.put(CANVAS_WIDTH, 1000);
	this.put(CANVAS_WINDOW_HEIGHT, 300);
	this.put(CANVAS_WINDOW_WIDTH, 300);
	this.put(WINDOW_TITLE, "Tim Schram - q7485417");
	this.put(WINDOW_WIDTH, 500);
	this.put(WINDOW_HEIGHT, 400);
	this.put(WINDOW_X, 100);
	this.put(WINDOW_Y, 100);
	
    }
    
    /**
     * Read the properties file 'config.properties'
     * If reading that file fails, a default configuration will be used
     */
    private void readProperties() {

	InputStream is = null;
	
	try {
	    
	    is = new FileInputStream("config.properties");
	    this.load(is);
	    
	} catch(final IOException e) {
	    
	    // If something went wrong with reading the properties file, the default values
	    // will still be in the needed in the configuration object
	    this.getDefaults();
	    
	} finally {
	    
	    if(null != is) 
		try {is.close();} catch(final IOException e) {}
	    
	}	

    }
    
    /**
     * Return a property value as String
     * @param key Key to look for
     * @return Value as String
     */
    public String get(final String key) {
	
	return String.valueOf(super.get(key));
	
    }
    
}
