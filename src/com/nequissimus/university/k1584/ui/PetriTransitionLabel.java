package com.nequissimus.university.k1584.ui;

public class PetriTransitionLabel extends AbstractLabel {

    private static final long serialVersionUID = 5447519082475950763L;

    public PetriTransitionLabel(final String name) {
	
	super(name);
	
    }

    @Override
    AbstractIcon getPetriIcon() {
	
	return new PetriTransitionIcon(PetriWindow.getIconSize().getSize());
	
    }

}
