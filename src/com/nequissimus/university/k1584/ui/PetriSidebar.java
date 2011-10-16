package com.nequissimus.university.k1584.ui;

import java.awt.Color;

import javax.swing.JPanel;

import com.nequissimus.university.k1584.logic.PetriConfig;

public class PetriSidebar extends JPanel {

    private static final long serialVersionUID = -7934305863238811085L;
    
    private final PetriConfig config = PetriConfig.getInstance();
    
    PetriSidebar() {
	
	super();
	
	final int width = this.config.getSidebarWidth();
	final int height = this.config.getWindowHeight();
	
	this.setBackground(Color.BLACK);
	
	this.setSize(width, height);
	
    }

}
