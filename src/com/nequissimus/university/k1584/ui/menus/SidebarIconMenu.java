package com.nequissimus.university.k1584.ui.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.nequissimus.university.k1584.ui.AbstractLabel;
import com.nequissimus.university.k1584.ui.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.actions.AddNewPlaceAction;
import com.nequissimus.university.k1584.ui.actions.AddNewTransitionAction;

public class SidebarIconMenu extends JPopupMenu {

    private static final long serialVersionUID = 8479743006947699772L;
    
    final AbstractLabel invoker;
    
    public SidebarIconMenu(final AbstractLabel invoker) {
	
	super();
	this.invoker = invoker;
	
	this.setupMenu();
	
    }
    
    private void setupMenu() {
	
	JMenuItem item = new JMenuItem("Add new");
	
	ActionListener action = null;
	
	if (invoker instanceof PetriPlaceLabel)
	    action = new AddNewPlaceAction();
	else if (invoker instanceof PetriTransitionLabel)
	    action = new AddNewTransitionAction();
	
	if (action != null) {
	
	    item.addActionListener(action);
	    this.add(item);
	
	}
    }
    
    

}
