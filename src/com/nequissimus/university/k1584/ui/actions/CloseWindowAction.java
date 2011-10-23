package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class CloseWindowAction implements ActionListener {
    
    public final JFrame window;
    
    public CloseWindowAction(JFrame target) {
	
	super();
	
	this.window = target;
	
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	
	this.window.dispatchEvent(new WindowEvent(this.window, WindowEvent.WINDOW_CLOSING));

    }

}
