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

import java.util.HashMap;
import java.util.Map;

/**
 * A marking that saves the number of tokens set for places.<br />
 * Only tokens > 0 will be saved, places missing from the map of saved tokens
 * are assumed 0.
 * @author Tim Steinbach
 */
public final class PetriMarking {

    /**
     * ID.
     */
    private final String id;

    /**
     * Marking name.
     */
    private String name;

    /**
     * Tokens for places.
     */
    private final Map<PetriPlace, Integer> tokens =
        new HashMap<PetriPlace, Integer>();

    /**
     * Create a new marking for all places in the net.
     * @param id Identification
     * @param name Name
     */
    PetriMarking(final String id, final String name) {

        this.id = id;
        this.name = name;

    }

    /**
     * Get the marking's name.
     * @return Name
     */
    public String getName() {

        return this.name;

    }

    /**
     * Turn the marking into its String representation.
     * @return String representation
     */
    @Override
    public String toString() {

        return this.name;

    }

    /**
     * Get the marking's identification.
     * @return ID
     */
    String getId() {

        return this.id;

    }

    /**
     * Get the number of tokens of a place.
     * @param place Place to get tokens for
     * @return Number of tokens, 0 if place could not be found in marking
     */
    int getTokens(final PetriPlace place) {

        Integer tokens = this.tokens.get(place);

        if (null == tokens) {
            tokens = 0;
        }

        return tokens;

    }

    /**
     * Set a new name for the marking.
     * @param name New name
     */
    void setName(final String name) {

        this.name = name;

    }

    /**
     * Set the given number of tokens for a place.<br />
     * If the number of tokens is 0 or smaller, the place will be removed from
     * the marking.
     * @param place Place to set tokens for
     * @param tokens Number of tokens
     */
    void setTokens(final PetriPlace place, final int tokens) {

        if (tokens <= 0) {
            this.tokens.remove(place);
        } else {
            this.tokens.put(place, tokens);
        }

    }

}
