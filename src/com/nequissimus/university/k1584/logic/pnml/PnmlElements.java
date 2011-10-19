package com.nequissimus.university.k1584.logic.pnml;

/**
 * All necessary XML elements for EPNML and their attributes.
 * Some attributes have default values that should not be changed.
 * The naming of these constants follows the following logic:<br /><br />
 * XYZ = Element XYZ <br />
 * XYZ_ABC = Attribute ABC for element XYZ<br />
 * XYZ_ABC_VALUE = Default value for attribute ABC of element XYZ
 * @author Tim Schram
 *
 */
public class PnmlElements {

    public static final String ROOT = "pnml";
    
    public static final String NET = "net";
    public static final String NET_TYPE = "type";
    public static final String NET_TYPE_VALUE = "http://www.yasper.org/specs/epnml-1.1";
    public static final String NET_ID = "id";
    
    public static final String TRANSITION = "transition";
    public static final String TRANSITION_ID = "id";
    
    public static final String GRAPHICS = "graphics";
    
    public static final String POSITION = "position";
    public static final String POSITION_X = "x";
    public static final String POSITION_Y = "y";
    
    public static final String DIMENSION = "dimension";
    public static final String DIMENSION_WIDTH = "x";
    public static final String DIMENSION_HEIGHT = "y";
    
    public static final String PLACE = "place";
    public static final String PLACE_ID = "id";
    
    public static final String EDGE = "arc";
    public static final String EDGE_ID = "id";
    public static final String EDGE_SOURCE = "source";
    public static final String EDGE_TARGET = "target";
    
    
    private PnmlElements() {}
    
}
