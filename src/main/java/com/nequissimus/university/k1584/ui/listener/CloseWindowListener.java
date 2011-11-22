package com.nequissimus.university.k1584.ui.listener;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Listener cleaning up all window frames when the application window is closed.
 * @author Tim Steinbach
 */
public class CloseWindowListener extends WindowAdapter {

    @Override
    public final void windowClosing(final WindowEvent e) {

        for (final Frame frame : Frame.getFrames()) {

            frame.dispose();

        }

        System.exit(0);

    }

}
