package com.nequissimus.university.k1584.ui.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.nequissimus.university.k1584.ui.AbstractLabel;

public class DragListener implements MouseListener, MouseMotionListener {
    
    private final AbstractLabel label;
    
    protected final Point mouseDownPoint = new Point(0, 0);
    
    public DragListener(final AbstractLabel panel) {
	
	this.label = panel;
	
    }
    
    @Override
    public void mouseDragged(final MouseEvent e) {
	
	final int x = this.label.getBounds().x + e.getX() - this.mouseDownPoint.x;
	final int y = this.label.getBounds().y + e.getY() - this.mouseDownPoint.y;
	
	this.label.setLocation(x, y);
    
    }

    @Override
    public void mousePressed(final MouseEvent e) {
	
	this.mouseDownPoint.setLocation(e.getPoint());
	
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {}

    @Override
    public void mouseClicked(MouseEvent arg0) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

}
