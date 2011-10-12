package com.nequissimus.university.k1584;

import java.awt.EventQueue;

import com.nequissimus.university.k1584.ui.PetriWindow;


/**
 * This is the application launcher which causes the editor to be created and started.
 * @author Tim Schram
 *
 */
public class PetriApp {

    public static void main(String[] args) {

	EventQueue.invokeLater(new PetriAppRunnable());

    }

}

class PetriAppRunnable implements Runnable {

    final PetriWindow window;
    
    public PetriAppRunnable() {
	
	this.window = new PetriWindow();
	
    }

    @Override
    public void run() {
	
	window.validate();
	window.repaint();
	window.setVisible(true);

    }

}
