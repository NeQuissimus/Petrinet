package com.nequissimus.university.k1584.ui.enums;

import java.awt.Dimension;

public enum IconSize {

    VERY_SMALL(60, 60), SMALL(100, 100), MEDIUM(150, 150), LARGE(200, 200);
    
    private Dimension size;
    
    private IconSize(final int width, final int height) {
	
	this.size = new Dimension(width, height);
	
    }
    
    public Dimension getSize() {return this.size;}
    
}
