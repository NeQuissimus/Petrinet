/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
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
     * Node for markings.
     */
    public static final String PLACE_INITIAL_MARKING = "initialMarking";

    /**
     * Text element holding the markings value.
     */
    public static final String PLACE_INITIAL_MARKING_TEXT = "text";

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
