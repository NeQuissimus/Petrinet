package com.nequissimus.university.k1584.logic.pnml;

/**
 * This exception will be thrown when there is something wrong while converting
 * a Petrinet into EPNML or back.
 * @author Tim Steinbach
 */
public class PnmlException extends Exception {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -165689230946792569L;

    /**
     * Create new PnmlException.
     * @param msg Message
     */
    public PnmlException(final String msg) {

        super("PnmlException: " + msg);

    }

    /**
     * Create new PnmlException.
     * @param e Root exception
     */
    public PnmlException(final Exception e) {

        super(e);

    }

}
