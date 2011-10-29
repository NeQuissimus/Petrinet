package com.nequissimus.university.k1584.logic.pnml;

/**
 * All necessary XML elements for EPNML and their attributes. Some attributes
 * have default values that should not be changed. The naming of these constants
 * follows the following logic:<br />
 * <br />
 * XYZ = Element XYZ <br />
 * XYZ_ABC = Attribute ABC for element XYZ<br />
 * XYZ_ABC_VALUE = Default value for attribute ABC of element XYZ
 * @author Tim Steinbach
 */
public final class PnmlElements {

    /**
     * PNML tag for a root.
     */
    public static final String ROOT = "pnml";

    /**
     * PNML tag for a net.
     */
    public static final String NET = "net";

    /**
     * Type attribute for net.
     */
    public static final String NET_TYPE = "type";

    /**
     * Default type value for net value.
     */
    public static final String NET_TYPE_VALUE =
        "http://www.yasper.org/specs/epnml-1.1";

    /**
     * ID attribute for net.
     */
    public static final String NET_ID = "id";

    /**
     * PNML tag for a transition.
     */
    public static final String TRANSITION = "transition";

    /**
     * ID attribute for transition.
     */
    public static final String TRANSITION_ID = "id";

    /**
     * PNML tag for graphics.
     */
    public static final String GRAPHICS = "graphics";

    /**
     * PNML tag for a position.
     */
    public static final String POSITION = "position";

    /**
     * X attribute for a position.
     */
    public static final String POSITION_X = "x";

    /**
     * Y attribute for a position.
     */
    public static final String POSITION_Y = "y";

    /**
     * PNML tag for a dimension.
     */
    public static final String DIMENSION = "dimension";

    /**
     * Width attribute for a dimension.
     */
    public static final String DIMENSION_WIDTH = "x";

    /**
     * Height attribute for a dimension.
     */
    public static final String DIMENSION_HEIGHT = "y";

    /**
     * PNML tag for a place.
     */
    public static final String PLACE = "place";

    /**
     * ID attribute for a place.
     */
    public static final String PLACE_ID = "id";

    /**
     * PNML tag for an edge.
     */
    public static final String EDGE = "arc";

    /**
     * ID attribute for an edge.
     */
    public static final String EDGE_ID = "id";

    /**
     * Source attribute for an edge.
     */
    public static final String EDGE_SOURCE = "source";

    /**
     * Target attribute for an edge.
     */
    public static final String EDGE_TARGET = "target";

    /**
     * Hide constructor.
     */
    private PnmlElements() {
    }

}
