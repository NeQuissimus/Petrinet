package com.nequissimus.university.k1584.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This is the main canvas on which all the editing will take place.
 * It consists of this panel that is scrollable and comes with a predefined size.
 * Its size can be changed to allow for a larger editing canvas.
 * @see PetriConfig
 * @author Tim Schram
 *
 */
public class PetriCanvas extends JScrollPane {

    private static final long serialVersionUID = 6158181636171175402L;
    
    private final JPanel canvas;
    private final PetriConfig config;
    
    public PetriCanvas() {
	
	super();
	
	this.config = PetriConfig.getInstance();
	
	this.canvas = new JPanel();
	
	this.canvas.setBackground(Color.RED);
	this.canvas.setForeground(Color.GREEN);
	this.canvas.setOpaque(true);

	final int canvasWidth = new Integer(this.config.get(PetriConfig.CANVAS_WIDTH));
	final int canvasHeight = new Integer(this.config.get(PetriConfig.CANVAS_HEIGHT));
	final int visibleWidth = new Integer(this.config.get(PetriConfig.CANVAS_WINDOW_WIDTH));
	final int visibleHeight = new Integer(this.config.get(PetriConfig.CANVAS_WINDOW_HEIGHT));
	
	final Dimension canvasSize = new Dimension(canvasWidth, canvasHeight);
	final Dimension visibleSize = new Dimension(visibleWidth, visibleHeight);
	
	this.canvas.setPreferredSize(canvasSize);
	this.canvas.setSize(canvasSize);
	
	this.canvas.validate();
	this.canvas.repaint();
	
	//this.add(this.canvas);
	
	this.setPreferredSize(visibleSize);
	this.setSize(visibleSize);
	
	this.setViewportView(canvas);
	
    }
    
    

}
