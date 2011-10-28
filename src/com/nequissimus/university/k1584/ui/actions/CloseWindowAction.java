package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * Action called when trying to close the window via the window menu bar.
 * @author Tim Steinbach
 *
 */
public class CloseWindowAction implements ActionListener {

    /**
     * Window to be closed.
     */
    private final JFrame window;

    /**
     * Create new action for a given window.
     * @param target Window
     */
    public CloseWindowAction(final JFrame target) {

        super();

        this.window = target;

    }

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        this.window.dispatchEvent(new WindowEvent(this.window,
            WindowEvent.WINDOW_CLOSING));

    }

}
