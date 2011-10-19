package com.nequissimus.university.k1584.logic.pnml;


/**
 * This exception will be thrown when there is something wrong while
 * converting a Petrinet into EPNML or back.
 * @author Tim Schram
 *
 */
public class PnmlException extends Exception {

    private static final long serialVersionUID = -165689230946792569L;

    public PnmlException(String msg) {
	
	super("PnmlException: " + msg);
	
    }
    
    public PnmlException(Exception e) {
	
	super(e);
	
    }

}
