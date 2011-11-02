package com.nequissimus.university.k1584.logic;

/**
 * Class that generates a unique ID for each Petri object.
 * @author Tim Steinbach
 */
final class PetriObjectId {

    /**
     * Id counter.
     */
    private static long nextId = 0;

    /**
     * Hide constructor.
     */
    private PetriObjectId() {
    }

    /**
     * Return the next available id.
     * @return Unique id
     */
    public static String getId() {
        return Long.toHexString(PetriObjectId.nextId++);
    }

}
