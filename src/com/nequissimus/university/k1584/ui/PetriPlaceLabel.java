package com.nequissimus.university.k1584.ui;

public class PetriPlaceLabel extends AbstractLabel {

    private static final long serialVersionUID = -1093238662290231525L;

    public PetriPlaceLabel(final String name) {
	
	super(name);
	
    }

    @Override
    AbstractIcon getPetriIcon() {
	
	return new PetriPlaceIcon(PetriWindow.getIconSize().getSize());
	
    }    

}
