package com.nequissimus.library.util;

/**
 * This is a utility class for working with method parameters.
 * @author Tim Steinbach
 */
public final class ParamUtil {

    /**
     * Hide constructor.
     */
    private ParamUtil() {
    }

    /**
     * Check an object for a null value. If the object is null, throw a
     * {@link java.lang.IllegalArgumentException}.
     * @param object Object to be checked
     */
    public static void checkNotNull(final Object object) {

        if (null == object) {
            throw new IllegalArgumentException("Object may not be null");
        }

    }

}
