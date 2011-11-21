package com.nequissimus.university.k1584.logic;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Class that generates a unique ID for each Petri object.
 * @author Tim Steinbach
 */
public final class PetriObjectId {

    /**
     * All ids that have been used.
     */
    private static final Set<String> USED_IDS = new HashSet<String>();

    /**
     * Hide constructor.
     */
    private PetriObjectId() {
    }

    /**
     * Add an id to the list of used ones.
     * @param id Used id
     */
    public static void addUsedId(final String id) {

        PetriObjectId.USED_IDS.add(id);

    }

    /**
     * Return the next available id.
     * @return Unique id
     */
    public static String getId() {

        UUID uid = UUID.randomUUID();
        String id = uid.toString();

        while (PetriObjectId.USED_IDS.contains(id)) {

            uid = UUID.randomUUID();
            id = uid.toString();

        }

        PetriObjectId.USED_IDS.add(id);

        return id;
    }

    /**
     * Empty the list of used ids.
     */
    public static void resetUsedIds() {

        PetriObjectId.USED_IDS.clear();

    }

}
