package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class AbstractIcon extends ImageIcon {

    private static final long serialVersionUID = -100231737768771611L;
    
    protected final Dimension size;
    protected static ImageIcon DEFAULT_IMAGE; 
    
    protected AbstractIcon(final Dimension size) {
	
	super();
	
	this.size = size;
	
    }
    
    protected static void setDefaultImage(ImageIcon image) {AbstractIcon.DEFAULT_IMAGE = image;}
    
    void draw() {
		
	Image i = DEFAULT_IMAGE.getImage();
	Image newImage = i.getScaledInstance(this.size.width, this.size.height, Image.SCALE_SMOOTH);
	
	this.setImage(newImage);
	
    }

}
