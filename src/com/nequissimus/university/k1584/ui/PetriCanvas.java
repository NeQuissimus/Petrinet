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
    
    PetriCanvas() {
	
	super();
	
	this.config = PetriConfig.getInstance();
	
	this.canvas = new JPanel();
	
	this.resetCanvas();	
	
	this.canvas.validate();
	this.canvas.repaint();

	final int visibleWidth = this.config.getWindowWidth() - this.config.getSidebarWidth();
	final int visibleHeight = this.config.getWindowHeight() - this.config.getScrollbarHeight();
	final Dimension visibleSize = new Dimension(visibleWidth, visibleHeight);
	
	this.setPreferredSize(visibleSize);
	this.setSize(visibleSize);
	
	this.setViewportView(canvas);

	this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
    }
    
    private void resetCanvas() {
	
	this.canvas.setBackground(Color.WHITE);
	this.canvas.setOpaque(true);

	final int canvasWidth = this.config.getCanvasWidth();
	final int canvasHeight = this.config.getCanvasHeight();
	
	final Dimension canvasSize = new Dimension(canvasWidth, canvasHeight);
	
	this.canvas.setPreferredSize(canvasSize);
	this.canvas.setSize(canvasSize);
	
	this.canvas.setLayout(null);
	
    } 
    
    public void add(AbstractLabel label) {
	
	this.canvas.add(label);
	
    }

}
