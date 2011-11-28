package com.nequissimus.library.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that globally holds singleton objects and returns them.<br />
 * At any given time this class will only have one object of any given class.
 * This class is thread-safe.<br />
 * By using this Singleton class, there is no need to apply a singleton pattern
 * to the implementation of a class.
 * @author Tim Steinbach
 */
public final class Singleton {

    /**
     * Map with singleton objects.
     */
    private static final Map<Class<Object>, Object> OBJECTS =
        new HashMap<Class<Object>, Object>();

    /**
     * Locking object to operate thread-safe.
     */
    private static final Object LOCK = new Object();

    /**
     * Hide constructor.
     */
    private Singleton() {

    }

    /**
     * Add an object which shall be used as the global singleton instance.
     * @param object Singleton object
     */
    public static void addObject(final Object object) {

        @SuppressWarnings("unchecked")
        final Class<Object> c = (Class<Object>) object.getClass();

        synchronized (Singleton.LOCK) {

            if (Singleton.OBJECTS.containsKey(c)) {
                throw new IllegalArgumentException(
                    "Object of this type already exists!");
            } else {
                Singleton.OBJECTS.put(c, object);
            }

        }

    }

    /**
     * Check whether a singleton object of a given class exists.
     * @param c Class to find object for
     * @return Whether there is a singleton object for the class
     */
    public static boolean exists(final Class<Object> c) {

        boolean exists = false;

        synchronized (Singleton.LOCK) {

            exists = Singleton.OBJECTS.containsKey(c);

        }

        return exists;

    }

    /**
     * Get a singleton object of a given class.
     * @param c Class of singleton object to be returned
     * @param <T> Type to cast object to
     * @return Singleton object of given class, null if none found
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(final Class<T> c) {

        T result = null;

        synchronized (Singleton.LOCK) {

            result = (T) Singleton.OBJECTS.get(c);

        }

        return result;

    }

}
