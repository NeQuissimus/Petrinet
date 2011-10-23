package com.nequissimus.university.k1584.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.actions.CloseWindowAction;

public class PetriMenuBar extends JMenuBar {

    private static final long serialVersionUID = -415488481991662662L;
    
    private final JFrame window;
    
    public PetriMenuBar(JFrame window) {
	
	super();
	
	this.window = window;
	
	this.resetMenu();
	
    }
    
    public void resetMenu() {
	
	JMenu menu = new JMenu("File");
	
	JMenuItem item = new JMenuItem("Close");
	item.addActionListener(new CloseWindowAction(this.window));
	
	menu.add(item);
	
	this.add(menu);
	
    }

}
