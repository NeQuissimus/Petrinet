package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.listener.ResizeCanvasListener;
import com.nequissimus.university.k1584.ui.listener.ResizeSidebarListener;


/**
 * The main window that holds all the major UI elements, such as the editing canvas, menus etc.
 * @author Tim Schram
 *
 */
public class PetriWindow extends JFrame {

    private static final long serialVersionUID = 6276277357529619473L;
    
    private static IconSize iconSize = IconSize.VERY_SMALL;
    
    private final PetriConfig config;

    private final PetriCanvas canvas;
    private final PetriSidebar sidebar;

    public PetriWindow() {

	super();

	this.setLayout(null);

	this.config = PetriConfig.getInstance();

	this.canvas = new PetriCanvas();
	this.sidebar = new PetriSidebar();

	this.resetTitle();
	this.resetSize();
	this.resetLocation();
	this.resetCanvas();
	this.resetSidebar();
	
	this.resetListeners();

	this.add(this.canvas);
	this.add(this.sidebar);
	
	this.setBackground(this.config.getWindowBackgroundColor());
		
    }
    
    public static void setIconSize(IconSize size) {PetriWindow.iconSize = size;}
    public static IconSize getIconSize() {return PetriWindow.iconSize;}

    private void resetSize() {

	final int windowWidth = this.config.getWindowWidth();
	final int windowHeight = this.config.getWindowHeight();

	final Dimension windowSize = new Dimension(windowWidth, windowHeight);

	this.setPreferredSize(windowSize);
	this.setSize(windowSize);

    }

    private void resetLocation() {

	final int windowX = this.config.getWindowX();
	final int windowY = this.config.getWindowY();

	final Point windowLocation = new Point(windowX, windowY);

	this.setLocation(windowLocation);

    }

    private void resetTitle() {

	this.setTitle(this.config.getWindowTitle());

    }

    private void resetCanvas() {

	this.canvas.setLocation(new Point(0, 0));
	this.canvas.validate();
	this.canvas.repaint();

    }
    
    private void resetSidebar() {
	
	final Point location = new Point(this.config.getWindowWidth() - this.config.getSidebarWidth(), 0);
	
	this.sidebar.setLocation(location);
	
    }
    
    private void resetListeners() {
	
	final Dimension minSize = new Dimension(this.config.getWindowMinWidth(), this.config.getWindowMinHeight());
	
	this.addComponentListener(new ResizeCanvasListener(minSize, this.canvas));
	this.addComponentListener(new ResizeSidebarListener(minSize, this.sidebar));
	
    }

}
