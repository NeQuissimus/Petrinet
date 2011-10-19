package com.nequissimus.university.k1584.ui.enums;

import javax.swing.SwingConstants;

public enum TextPosition {

    RIGHT(SwingConstants.RIGHT, SwingConstants.CENTER), 
    BELOW(SwingConstants.CENTER, SwingConstants.BOTTOM);
    
    private final int x;
    private final int y;
    
    private TextPosition(int x, int y) {
	
	this.x = x;
	this.y = y;
	
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}
    
}
