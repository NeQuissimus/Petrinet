package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.ui.PetriCanvas;
import com.nequissimus.university.k1584.ui.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.PetriWindow;

public class AddNewPlaceAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	
	PetriCanvas canvas = PetriWindow.getCanvas();
	
	PetriPlaceLabel label = new PetriPlaceLabel("New");
	label.setBounds(10, 10, 100, 100);
	
	canvas.add(label);
	canvas.validate();
	canvas.repaint();	
	
    }

}
