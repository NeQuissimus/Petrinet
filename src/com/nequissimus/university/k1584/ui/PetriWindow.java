package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.nequissimus.university.k1584.logic.PetriConfig;


/**
 * The main window that holds all the major UI elements, such as the editing canvas, menus etc.
 * @author Tim Schram
 *
 */
public class PetriWindow extends JFrame {

    private static final long serialVersionUID = 6276277357529619473L;
    
    private final PetriConfig config;
    
    private final PetriCanvas canvas;

    public PetriWindow() {

	super();
	
	this.setLayout(null);
	
	this.config = PetriConfig.getInstance();
	
	this.canvas = new PetriCanvas();
	
	this.resetTitle();
	this.resetSize();
	this.resetLocation();
	this.resetCanvas();
	
	this.add(this.canvas);

    }

    private void resetSize() {

	final int windowWidth = new Integer(config.get(PetriConfig.WINDOW_WIDTH));
	final int windowHeight = new Integer(config.get(PetriConfig.WINDOW_HEIGHT));

	final Dimension windowSize = new Dimension(windowWidth, windowHeight);

	this.setPreferredSize(windowSize);
	this.setSize(windowSize);

    }

    private void resetLocation() {

	final int windowX = new Integer(config.get(PetriConfig.WINDOW_X));
	final int windowY = new Integer(config.get(PetriConfig.WINDOW_Y));

	final Point windowLocation = new Point(windowX, windowY);

	this.setLocation(windowLocation);

    }

    private void resetTitle() {

	this.setTitle(config.get(PetriConfig.WINDOW_TITLE));

    }

    private void resetCanvas() {
	
	this.canvas.setLocation(new Point(0, 0));
	this.canvas.validate();
	this.canvas.repaint();
	
    }
    
}
