package com.nequissimus.university.k1584.ui;

import javax.swing.JPanel;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

public class PetriSidebar extends JPanel {

    private static final long serialVersionUID = -7934305863238811085L;
    
    private final PetriConfig config = PetriConfig.getInstance();
    
    PetriSidebar() {
	
	super();
	
	final int width = this.config.getSidebarWidth();
	final int height = this.config.getWindowHeight();
	
	// Use same color as window background (This prevents ugly effects when resizing the window)
	this.setOpaque(false);
	
	this.addIcons();
	
	this.setSize(width, height);
		
    }
    
    private void addIcons() {
	
	PetriPlaceLabel label = new PetriPlaceLabel("New place");
	label.moveText(TextPosition.BELOW);
	label.setBounds(10, 10, 100, 100);
	label.unregisterDraggable();
	label.addSidebarMenu();
	
	this.add(label);
	
	PetriTransitionLabel label2 = new PetriTransitionLabel("New transition");
	label2.moveText(TextPosition.BELOW);
	label2.setBounds(0, 120, 100, 100);
	label2.unregisterDraggable();
	label2.addSidebarMenu();
	
	this.add(label2);
	
    }

}
