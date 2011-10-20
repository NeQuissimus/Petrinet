package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.nequissimus.university.k1584.ui.AbstractLabel;
import com.nequissimus.university.k1584.ui.menus.SidebarIconMenu;

public class SidebarIconMenuListener implements MouseListener {
    
    private final AbstractLabel invoker;

    public SidebarIconMenuListener(final AbstractLabel invoker) {
	
	this.invoker = invoker;
	
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
	
	if (e.isPopupTrigger())
	    this.openMenu(e);
	
    }
    
    @Override
    public void mouseReleased(final MouseEvent e) {
	
	if (e.isPopupTrigger())
	    this.openMenu(e);
	
    }
    
    private void openMenu(final MouseEvent e) {
	
	final SidebarIconMenu menu = new SidebarIconMenu(this.invoker);
	menu.show(e.getComponent(), e.getX(), e.getY());
	
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}
    
}
