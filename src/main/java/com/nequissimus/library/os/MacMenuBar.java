package com.nequissimus.library.os;

/**
 * This is a class that helps working with the Mac OS X menu bar.
 * @author Tim Steinbach
 */
public final class MacMenuBar {

    /**
     * Hidden constructor to force access via static methods.
     */
    private MacMenuBar() {
    }

    /**
     * Set the application name shown in the OSX menu bar.
     * @param name Application name
     */
    public static void setApplicationName(final String name) {

        System.setProperty(
            "com.apple.mrj.application.apple.menu.about.name", name);

    }

}
