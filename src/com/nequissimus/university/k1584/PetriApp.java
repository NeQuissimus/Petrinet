package com.nequissimus.university.k1584;

import java.awt.EventQueue;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.PetriWindow;


/**
 * This is the application launcher which causes the editor to be created and started.
 * @author Tim Schram
 *
 */
public class PetriApp {

    public static void main(String[] args) {
	
	final PetriConfig config = PetriConfig.getInstance();
	
	// This is needed for Mac OS X only - It changes the application name in the menu bar
	// from the fully-qualified class name to the given value
	System.setProperty("com.apple.mrj.application.apple.menu.about.name", config.getApplicationName());
	    
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
