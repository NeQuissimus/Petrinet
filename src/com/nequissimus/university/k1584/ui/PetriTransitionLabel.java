package com.nequissimus.university.k1584.ui;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.listener.SidebarIconMenuListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

public class PetriTransitionLabel extends AbstractLabel implements Draggable {

    private static final long serialVersionUID = 5447519082475950763L;
    
    private EventListener mouseListener;

    public PetriTransitionLabel(final PetriTransition transition) {
	
	super(transition);
	
	this.registerDraggable();
	
    }
    
    public PetriTransitionLabel() {
	
	super();
	
	this.addSidebarMenu();
	
    }

    @Override
    AbstractIcon getPetriIcon() {
	
	return new PetriTransitionIcon(this.controller.getIconSize());
	
    }

    @Override
    public void registerDraggable() {
	
	this.mouseListener = new DragListener(this);
	
	this.addMouseListener((MouseListener) this.mouseListener);
	this.addMouseMotionListener((MouseMotionListener) this.mouseListener);
	
	
    }

    @Override
    public void unregisterDraggable() {
	
	this.removeMouseListener((MouseListener) this.mouseListener);
	this.removeMouseMotionListener((MouseMotionListener) this.mouseListener);
	
    }  
    
    public void addSidebarMenu() {
	
	this.addMouseListener(new SidebarIconMenuListener(this));
	
    }

}
