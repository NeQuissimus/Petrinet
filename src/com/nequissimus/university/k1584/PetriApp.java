package com.nequissimus.university.k1584;

import java.awt.EventQueue;

import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This is the application launcher which causes the editor to be created and
 * started.
 * @author Tim Steinbach
 */
public final class PetriApp {

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

        // This is needed for Mac OS X only - It changes the application name in
        // the menu bar
        // from the fully-qualified class name to the given value
        System.setProperty(
            "com.apple.mrj.application.apple.menu.about.name",
            PetriApp.CONFIG.getApplicationName());

        EventQueue.invokeLater(PetriController.getInstance());

    }

}
