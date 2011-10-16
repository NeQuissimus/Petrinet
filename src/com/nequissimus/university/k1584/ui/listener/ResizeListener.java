package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * This Listener forces a certain size for its source component.
 * @author Tim Schram
 *
 */
public class ResizeListener implements ComponentListener {
    
    private final Dimension minSize;
    
    ResizeListener(final Dimension minSize) {
	
	this.minSize = minSize;
	
    }

    @Override
    public void componentHidden(final ComponentEvent e) {}

    @Override
    public void componentMoved(final ComponentEvent e) {}

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentResized(final ComponentEvent e) {
	
	final Component source = (Component) e.getSource();
	
	if (null != source) {
	    
	    final Dimension size = source.getSize();
	    
	    if (size.width < this.minSize.width)
		size.width = this.minSize.width;
	    
	    if (size.height < this.minSize.height)
		size.height = this.minSize.height;
	    
	    source.setSize(size);
	    
	}
	
    }


    
}