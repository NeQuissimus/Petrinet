// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

package com.nequissimus.university.k1584.logic;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Class that generates a unique ID for each marking.
 * @author Tim Steinbach
 */
public final class PetriMarkingId {

    /**
     * All ids that have been used.
     */
    private static final Set<String> USED_IDS = new HashSet<String>();

    /**
     * Hide constructor.
     */
    private PetriMarkingId() {
    }

    /**
     * Add an id to the list of used ones.
     * @param id Used id
     */
    public static void addUsedId(final String id) {

        PetriMarkingId.USED_IDS.add(id);

    }

    /**
     * Return the next available id.
     * @return Unique id
     */
    public static String getId() {

        UUID uid = UUID.randomUUID();
        String id = uid.toString();

        while (PetriObjectId.isUsed(id)) {

            uid = UUID.randomUUID();
            id = uid.toString();

        }

        PetriMarkingId.USED_IDS.add(id);

        return id;
    }

    /**
     * Check whether an id is in use.
     * @param id Id to be checked
     * @return True if id has been used before
     */
    public static boolean isUsed(final String id) {

        return PetriMarkingId.USED_IDS.contains(id);

    }

    /**
     * Empty the list of used ids.
     */
    public static void resetUsedIds() {

        PetriMarkingId.USED_IDS.clear();

    }

}
