package com.nequissimus.library.os;

/**
 * Get user environment variables.
 * @author Tim Steinbach
 */
public final class User {

    /**
     * Hide constructor.
     */
    private User() {
    }

    /**
     * Return the user's home directory.
     * @return Home directory
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * Get the value of JAVA_HOME.
     * @return Java home
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

}
