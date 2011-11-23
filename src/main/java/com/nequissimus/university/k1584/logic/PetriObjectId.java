/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
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
