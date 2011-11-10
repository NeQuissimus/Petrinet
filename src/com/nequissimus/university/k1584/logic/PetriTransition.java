package com.nequissimus.university.k1584.logic;

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
     * Get the set of input places.
     * @return Incoming places
     */
    final Set<PetriPlace> getInput() {
        return this.input;
    }

    /**
     * Get the set of output places.
     * @return Outgoing places
     */
    final Set<PetriPlace> getOutput() {
        return this.output;
    }

    /**
     * Check whether each of the input's places has at least one marking or has
     * not input places at all.
     * @return Whether the transition is active
     */
    final boolean isActive() {

        for (final PetriPlace place : this.input) {

            if (place.getMarkings() == 0) {
                return false;
            }

        }

        return true;

    }

    /**
     * Take one marking out of each input place and place one marking into each
     * of the output places.
     * @return Places that have been changed
     */
    final Set<PetriPlace> occur() {

        final Set<PetriPlace> changed = new HashSet<PetriPlace>();

        if (this.isActive()) {

            changed.addAll(this.input);
            changed.addAll(this.output);

            for (final PetriPlace place : this.input) {

                place.decreaseMarkings();

            }

            for (final PetriPlace place : this.output) {

                place.increaseMarkings();

            }

        }

        return changed;

    }

    @Override
    public final String toString() {
        return "PetriTransition [input=" + this.input + ", output="
            + this.output + "]";
    }

    /**
     * Add a place to this transition's incoming places.
     * @param input Incoming place
     */
    final void addInput(final PetriPlace input) {
        this.input.add(input);
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
     * Add a place to this transition's outgoing places.
     * @param output Outgoing place
     */
    final void addOutput(final PetriPlace output) {
        this.output.add(output);
    }

    /**
     * Remove place from list of outgoing places.
     * @param output Place
     * @return True if removal was successful
     */
    final boolean removeOutput(final PetriPlace output) {
        return this.output.remove(output);
    }

}
