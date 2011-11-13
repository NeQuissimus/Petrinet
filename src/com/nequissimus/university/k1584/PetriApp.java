package com.nequissimus.university.k1584;

import java.awt.EventQueue;

import com.nequissimus.library.os.MacMenuBar;
import com.nequissimus.library.os.MacWindow;
import com.nequissimus.library.os.OSystem;
import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This is the application launcher which causes the editor to be created and
 * started.
 * @author Tim Steinbach
 */
public final class PetriApp {

    // TODO: Load snapshots
    // TODO: Create snapshots
    // TODO: Delete snapshots
    // TODO: Rename snapshots
    // TODO: Make drag and drop selected objects visible somehow
    // TODO: Tests

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Hide constructor.
     */
    private PetriApp() {
    }

    /**
     * Point of entry for executing the application.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {

        final OSystem os = OSystem.getCurrentOs();

        if (os == OSystem.MACOSX) {

            // This is needed for Mac OS X only - It changes the application
            // name in the menu bar from the fully-qualified class name to the
            // given value
            MacMenuBar.setApplicationName(PetriApp.CONFIG
                .getApplicationName());

            // Disallow the resize box to interfere with the actual interface.
            MacWindow.allowGrowboxIntrusion(false);

        }

        EventQueue.invokeLater(PetriController.getInstance());

    }

}
