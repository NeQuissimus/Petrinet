package com.nequissimus.library.os;

/**
 * Methods that help work with Java under Mac OS X more easily, specifically
 * with application windows.
 * @author Tim Steinbach
 */
public final class MacWindow {

    /**
     * Hide constructor.
     */
    private MacWindow() {
    }

    /**
     * Enable or disable the intrusion by the grow box. The grow box is the
     * small box in the bottom right corner that allows the user to resize the
     * window.<br />
     * If the intrusion is set to true (which is the default behaviour), other
     * elements might be behind the grow box. If the behaviour is set to false,
     * the window will automatically be slightly larger to accommodate the grow
     * box and draw it below the lowest element.
     * @param allow Whether to allow the grow box to be above other elements
     */
    public static void allowGrowboxIntrusion(final boolean allow) {

        System.setProperty("com.apple.mrj.application.growbox.intrudes",
            String.valueOf(allow));

    }

}
