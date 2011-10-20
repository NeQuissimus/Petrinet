package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;

public class AddNewPlaceAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	
	PetriController.addPlace();	
	
    }

}
