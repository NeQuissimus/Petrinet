package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.ui.PetriSidebar;

/**
 * This listener automatically resizes and moves a sidebar component.
 * (It will always have the same size as the window and be moved to the right side of the window)
 * @author Tim Schram
 *
 */
public class ResizeSidebarListener extends ResizeListener {

    final PetriSidebar sidebar;

    public ResizeSidebarListener(Dimension minSize, PetriSidebar sidebar) {

	super(minSize);
	this.sidebar = sidebar;

    }

    @Override
    public void componentResized(final ComponentEvent e) {

	super.componentResized(e);

	if (this.sidebar != null) {

	    final Dimension size = this.sidebar.getSize();

	    final Component component = (Component) e.getSource();

	    size.height = component.getSize().height;

	    this.sidebar.setSize(size);
	    
	    this.sidebar.setLocation(component.getWidth() - this.sidebar.getWidth(), 0);

	    this.sidebar.validate();
	    this.sidebar.repaint();

	}

    }

}
