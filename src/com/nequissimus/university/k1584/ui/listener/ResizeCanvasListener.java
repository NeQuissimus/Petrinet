package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This Listener extends {@link ResizeListener} by the feature of resizing
 * another component in height according to the original source.
 * The slave component will always be the same height as the original source component.
 * @author Tim Schram
 *
 */
public class ResizeCanvasListener extends ResizeListener {
    
    private Component slave = null;
    PetriConfig config = PetriConfig.getInstance();
    
    public ResizeCanvasListener(final Dimension minSize, Component slave) {
	
	super(minSize);
	
	this.slave = slave;
	
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
	
	super.componentResized(e);
	
	if (this.slave != null) {
	    
	    final Dimension size = this.slave.getSize();
	    
	    final Component component = (Component) e.getSource();
	    
	    size.height = component.getSize().height - this.config.getScrollbarHeight();
	    size.width = component.getSize().width - this.config.getSidebarWidth();
	    
	    this.slave.setSize(size);
	    
	    this.slave.validate();
	    this.slave.repaint();
	    
	}
	
    }

}
