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

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Transition in a Petri net.
 * @author Tim Steinbach
 */
public class PetriTransition extends PetriObject {

    /**
     * Incoming places.
     */
    private final Set<PetriPlace> input;

    /**
     * Outgoing places.
     */
    private final Set<PetriPlace> output;

    /**
     * Create a new transition.
     * @param name Transition name
     */
    PetriTransition(final String name) {

        super(name);

        this.input = new HashSet<PetriPlace>();
        this.output = new HashSet<PetriPlace>();

    }

    /**
     * Create a new transition.
     * @param name Name
     * @param id Id
     */
    PetriTransition(final String name, final String id) {

        super(name, id);

        this.input = new HashSet<PetriPlace>();
        this.output = new HashSet<PetriPlace>();

    }

    /**
     * Occur a transition by taking tokens from the "from" list of places and
     * giving some to the "to" list of places. This only takes place if the
     * transition is active.
     * @param from List of places to take tokens from
     * @param to List of places to give tokens to
     * @param net PetriNet to work with
     * @param transition Transition to occur
     * @param active Whether the transition is active
     * @return Places that have been changed
     */
    private static Set<PetriPlace> abstractOccur(
        final Set<PetriPlace> from, final Set<PetriPlace> to,
        final PetriNet net, final PetriTransition transition,
        final boolean active) {

        final Set<PetriPlace> changed = new HashSet<PetriPlace>();

        if (active) {

            changed.addAll(from);
            changed.addAll(to);

            for (final PetriPlace place : from) {

                net.decreaseTokens(place);

            }

            for (final PetriPlace place : to) {

                net.increaseTokens(place);

            }

        }

        return changed;

    }

    @Override
    public final String toString() {
        return "PetriTransition [" + this.getId() + "]";
    }

    @Override
    protected final PetriTransition clone() {

        final PetriTransition clone =
            new PetriTransition(this.getName(), PetriObjectId.getId());

        clone.setPosition(new Point(this.getPosition()));
        clone.setSize(new Dimension(this.getSize()));

        return clone;

    }

    /**
     * Add a place to this transition's incoming places.
     * @param input Incoming place
     */
    final void addInput(final PetriPlace input) {
        this.input.add(input);
    }

    /**
     * Add a place to this transition's outgoing places.
     * @param output Outgoing place
     */
    final void addOutput(final PetriPlace output) {
        this.output.add(output);
    }

    /**
     * Get the set of input places.
     * @return Incoming places
     */
    final Set<PetriPlace> getInput() {
        return new HashSet<PetriPlace>(this.input);
    }

    /**
     * Get the set of output places.
     * @return Outgoing places
     */
    final Set<PetriPlace> getOutput() {
        return new HashSet<PetriPlace>(this.output);
    }

    /**
     * Check whether each of the input's places has at least one marking or has
     * not input places at all.
     * @return Whether the transition is active
     */
    final boolean isActive() {

        for (final PetriPlace place : this.input) {

            if (place.getTokens() == 0) {
                return false;
            }

        }

        return true;

    }

    /**
     * Check whether each of the output's places has at least one marking or has
     * no output places at all.
     * @return Whether the transaction is reverse-active
     */
    final boolean isReverseActive() {

        for (final PetriPlace place : this.output) {
            if (place.getTokens() == 0) {
                return false;
            }
        }

        return true;

    }

    /**
     * Take one marking out of each input place and place one marking into each
     * of the output places.
     * @param net Net to work on
     * @return Places that have been changed
     */
    final Set<PetriPlace> occur(final PetriNet net) {

        // final Set<PetriPlace> changed = new HashSet<PetriPlace>();
        //
        // if (this.isActive()) {
        //
        // changed.addAll(this.input);
        // changed.addAll(this.output);
        //
        // for (final PetriPlace place : this.input) {
        //
        // net.decreaseTokens(place);
        //
        // }
        //
        // for (final PetriPlace place : this.output) {
        //
        // net.increaseTokens(place);
        //
        // }
        //
        // }
        //
        // return changed;

        return PetriTransition.abstractOccur(this.input, this.output, net,
            this, this.isActive());

    }

    /**
     * Remove place from list of incoming places.
     * @param input Place
     * @return True if removal was successful
     */
    final boolean removeInput(final PetriPlace input) {
        return this.input.remove(input);
    }

    /**
     * Remove place from list of outgoing places.
     * @param output Place
     * @return True if removal was successful
     */
    final boolean removeOutput(final PetriPlace output) {
        return this.output.remove(output);
    }

    /**
     * Reverse occur this transition.
     * @param net Petri net to be used
     * @return All places that have been changed
     */
    final Set<PetriPlace> reverseOccur(final PetriNet net) {

        return PetriTransition.abstractOccur(this.output, this.input, net,
            this, this.isReverseActive());

    }

}
