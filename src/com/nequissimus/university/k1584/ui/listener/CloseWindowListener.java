package com.nequissimus.university.k1584.ui.listener;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseWindowListener extends WindowAdapter {
    
    @Override
    public void windowClosing(WindowEvent e) {
	
	for (Frame frame : Frame.getFrames()) {
	 
	    frame.dispose();
	    
	}
	
	System.exit(0);
      
    }

}
