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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.nequissimus.library.data.Singleton;

/**
 * Main Petri net class that holds all information and methods about the net.
 * This is basically the delegate API of the Petri net logic, no other classes
 * need to be accessed to work with the net. Since the methods to work with the
 * objects are not public, this class provides all necessary access to the net's
 * as well as each component's behaviour.
 * @author Tim Steinbach
 */
public class PetriNet implements Cloneable {

    /**
     * Configuration.
     */
    private static PetriConfig config = Singleton
        .getObject(PetriConfig.class);

    /**
     * Marking currently in use.
     */
    private PetriMarking currentMarking = null;

    /**
     * Markings of places.
     */
    private final Set<PetriMarking> markings = new HashSet<PetriMarking>();

    /**
     * This net's name.
     */
    private String name;

    /**
     * All logical places for this net.
     */
    private final Set<PetriPlace> places = new HashSet<PetriPlace>();

    /**
     * All logical transitions for this net.
     */
    private final Set<PetriTransition> transitions =
        new HashSet<PetriTransition>();

    /**
     * Create a new net with the given name.
     * @param name Name
     */
    public PetriNet(final String name) {

        this.name = name;

    }

    /**
     * Add a new place to the net.
     * @return Returns the newly created place
     */
    public final PetriPlace addPlace() {
        return this.addPlace(PetriNet.config.getPlaceName());
    }

    /**
     * Add a new place to the net.
     * @param name Place name
     * @return Returns the newly created place
     */
    public final PetriPlace addPlace(final String name) {

        final PetriPlace newPlace = new PetriPlace(name);
        this.places.add(newPlace);

        return newPlace;

    }

    /**
     * Add a new place to the net.
     * @param name Place name
     * @param id Place id
     * @return Returns the newly created place
     */
    public final PetriPlace addPlace(final String name, final String id) {

        final PetriPlace newPlace = new PetriPlace(name, id);
        this.places.add(newPlace);

        return newPlace;

    }

    /**
     * Add a new transition to the net.
     * @return Return the newly created transition
     */
    public final PetriTransition addTransition() {

        return this.addTransition(PetriNet.config.getTransitionName());

    }

    /**
     * Add a new transition to the net.
     * @param name Transition name
     * @return Return the newly created transition
     */
    public final PetriTransition addTransition(final String name) {

        final PetriTransition newTransition = new PetriTransition(name);
        this.transitions.add(newTransition);

        return newTransition;

    }

    /**
     * Add a new transition to the net.
     * @param name Transition name
     * @param id Transition id
     * @return Return the newly created transition
     */
    public final PetriTransition addTransition(final String name,
        final String id) {

        final PetriTransition newTransition = new PetriTransition(name, id);
        this.transitions.add(newTransition);

        return newTransition;

    }

    @Override
    public final PetriNet clone() {

        try {

            super.clone();

        } catch (final CloneNotSupportedException e) {

            return null;

        }

        final PetriNet result = new PetriNet(this.name);

        final Set<PetriPlace> newPlaces = new HashSet<PetriPlace>();
        final Set<PetriTransition> newTransitions =
            new HashSet<PetriTransition>();

        // Use old ids with new places to map when creating the transitions
        final Map<String, PetriPlace> oldIdWithNewPlace =
            new HashMap<String, PetriPlace>();

        for (final PetriPlace petriPlace : this.places) {

            final PetriPlace newPlace = petriPlace.clone();

            oldIdWithNewPlace.put(petriPlace.getId(), newPlace);

            newPlaces.add(newPlace);

        }

        for (final PetriTransition petriTransition : this.transitions) {

            final PetriTransition transition = petriTransition.clone();

            final Set<PetriPlace> input = petriTransition.getInput();
            final Set<PetriPlace> output = petriTransition.getOutput();

            for (final PetriPlace petriPlace : input) {

                final String oldId = petriPlace.getId();

                final PetriPlace newPlace = oldIdWithNewPlace.get(oldId);
                transition.addInput(newPlace);

            }

            for (final PetriPlace petriPlace : output) {

                final String oldId = petriPlace.getId();

                final PetriPlace newPlace = oldIdWithNewPlace.get(oldId);
                transition.addOutput(newPlace);

            }

            newTransitions.add(transition);

        }

        result.addPlaces(newPlaces);
        result.addTransitions(newTransitions);

        return result;

    }

    /**
     * Connect a place to a transition (place -> transition).
     * @param from Connect from this place
     * @param to Connect to this transition
     */
    public final void connect(final PetriPlace from,
        final PetriTransition to) {

        to.addInput(from);

    }

    /**
     * Connect a transition to a place (transition -> place).
     * @param from Connect from this transition
     * @param to Connect to this place
     */
    public final void connect(final PetriTransition from,
        final PetriPlace to) {

        from.addOutput(to);

    }

    /**
     * Create a new marking.<br />
     * @param name Marking name
     * @param id Marking id
     * @return New marking
     */
    public final PetriMarking createMarking(final String name,
        final String id) {

        final PetriMarking marking = new PetriMarking(id, name);

        this.markings.add(marking);

        return marking;

    }

    /**
     * Create a new marking.<br />
     * The new marking is activated after its creation.
     * @param name New name
     * @return New marking
     */
    public final PetriMarking createNewMarking(final String name) {

        this.currentMarking =
            new PetriMarking(PetriMarkingId.getId(), name);
        this.markings.add(this.currentMarking);

        return this.currentMarking;

    }

    /**
     * Decrease the number of tokens for the given place.
     * @param place Decrease number of tokens for this place
     */
    public final void decreaseTokens(final PetriPlace place) {

        place.decreaseTokens();

        this.currentMarking.setTokens(place, place.getTokens());

    }

    /**
     * Remove the selected marking.
     * @param marking Marking to be removed
     */
    public final void deleteMarking(final PetriMarking marking) {

        this.markings.remove(marking);

    }

    /**
     * Get currently active marking.
     * @return Active marking
     */
    public final PetriMarking getActiveMarking() {

        return this.currentMarking;

    }

    /**
     * Get an object's id.
     * @param object Petri object
     * @return Id
     */
    public final String getId(final PetriObject object) {
        return object.getId();
    }

    /**
     * Get all input places for a transition.
     * @param transition Transition
     * @return Incoming places
     */
    public final Set<PetriPlace> getInputEdges(
        final PetriTransition transition) {

        return transition.getInput();

    }

    /**
     * Get all markings for the net.
     * @return Markings
     */
    public final Set<PetriMarking> getMarkings() {

        return this.markings;

    }

    /**
     * Get this net's name.
     * @return Name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Get a Petri net object's name.
     * @param object Petri net object
     * @return Object's name
     */
    public final String getName(final PetriObject object) {

        return object.getName();

    }

    /**
     * Get all output places for a transition.
     * @param transition Transition
     * @return Outgoing places
     */
    public final Set<PetriPlace> getOutputEdges(
        final PetriTransition transition) {

        return transition.getOutput();

    }

    /**
     * Get a place by its id.
     * @param id Id
     * @return Place with given id, NULL if no such place exists
     */
    public final PetriPlace getPlaceById(final String id) {

        for (final PetriPlace place : this.places) {

            if (place.getId().equals(id)) {
                return place;
            }

        }

        return null;

    }

    /**
     * Get all places for this net.
     * @return Places
     */
    public final Set<PetriPlace> getPlaces() {
        return this.places;
    }

    /**
     * Get an object's position.
     * @param object Object
     * @return Position
     */
    public final Point getPosition(final PetriObject object) {

        return object.getPosition();

    }

    /**
     * Get an object's size.
     * @param object Object
     * @return Size
     */
    public final Dimension getSize(final PetriObject object) {

        return object.getSize();

    }

    /**
     * Get the number of tokens set for the place.
     * @param place Petri net place
     * @return Number of tokens set for place
     */
    public final int getTokens(final PetriPlace place) {

        return place.getTokens();

    }

    /**
     * Get a transition by its id.
     * @param id Id
     * @return Transition with given id, NULL is none was found
     */
    public final PetriTransition getTransitionById(final String id) {

        for (final PetriTransition transition : this.transitions) {

            if (transition.getId().equals(id)) {
                return transition;
            }

        }

        return null;

    }

    /**
     * Get all transitions for this net.
     * @return Transitions
     */
    public final Set<PetriTransition> getTransitions() {
        return this.transitions;
    }

    /**
     * Increase the number of tokens for the given place.
     * @param place Increase number of tokens for this place
     */
    public final void increaseTokens(final PetriPlace place) {

        place.increaseTokens();

        this.currentMarking.setTokens(place, place.getTokens());

    }

    /**
     * Check whether a transition is active. A transition is active if each of
     * its input places has at least one marking.
     * @param transition Transition to be checked
     * @return Whether the transition is active
     */
    public final boolean isActive(final PetriTransition transition) {

        return transition.isActive();

    }

    /**
     * Check whether a transition is reverse-active. A transition is active if
     * each of its output places has at least one marking.
     * @param transition Transition to be checked
     * @return Whether the transition is reverse-active
     */
    public final boolean isReverseActive(final PetriTransition transition) {

        return transition.isReverseActive();

    }

    /**
     * Make a transition occur (Take one marking away from each input place and
     * give one to each output place) if the transition is active. Nothing
     * happens if the transition is not active.
     * @param transition Transition to occur
     * @return Petri places that have been changed
     */
    public final Set<PetriPlace> occur(final PetriTransition transition) {

        return transition.occur(this);

    }

    /**
     * Remove a Petri object. This method decides whether the object is a place
     * or a transition and then calls the correct method for the respective
     * sub-type.
     * @param object Petri object
     */
    public final void remove(final PetriObject object) {

        if (object instanceof PetriPlace) {
            this.remove((PetriPlace) object);
        } else if (object instanceof PetriTransition) {
            this.remove((PetriTransition) object);
        }

    }

    /**
     * Remove a place and all edges touching the object.
     * @param place Place to be removed
     */
    public final void remove(final PetriPlace place) {

        for (final PetriTransition transition : this.transitions) {

            transition.removeInput(place);
            transition.removeOutput(place);

        }

        this.places.remove(place);
        this.removeFromMarkings(place);

    }

    /**
     * Remove a transition and all edges touching the object.
     * @param transition Transition to be removed
     */
    public final void remove(final PetriTransition transition) {

        this.transitions.remove(transition);

    }

    /**
     * Remove an input edge.
     * @param transition Transition
     * @param place Place
     */
    public final void removeInput(final PetriTransition transition,
        final PetriPlace place) {
        transition.removeInput(place);
    }

    /**
     * Remove an output edge.
     * @param transition Transition
     * @param place Place
     */
    public final void removeOutput(final PetriTransition transition,
        final PetriPlace place) {
        transition.removeOutput(place);
    }

    /**
     * Rename an object.
     * @param object Petri net object to be renamed
     * @param name New name
     */
    public final void rename(final PetriObject object, final String name) {

        object.setName(name);

    }

    /**
     * Rename a marking.
     * @param marking Marking to be renamed
     * @param name New name
     */
    public final void renameMarking(final PetriMarking marking,
        final String name) {

        marking.setName(name);

    }

    /**
     * Make a transition reverse-occur (Take one marking away from each output
     * place and give one to each input place) if the transition is active.
     * Nothing happens if the transition is not active.
     * @param transition Transition to occur
     * @return Petri places that have been changed
     */
    public final Set<PetriPlace> reverseOccur(
        final PetriTransition transition) {

        return transition.reverseOccur(this);

    }

    /**
     * Set this net's name.
     * @param newName Name
     */
    public final void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Set position for a given object.
     * @param object Object
     * @param position Position
     */
    public final void setPosition(final PetriObject object,
        final Point position) {

        object.setPosition(position);

    }

    /**
     * Change the size for all existing objects.
     * @param size New size
     */
    public final void setSize(final Dimension size) {

        for (final PetriPlace place : this.places) {
            this.setSize(place, size);
        }

        for (final PetriTransition transition : this.transitions) {
            this.setSize(transition, size);
        }

    }

    /**
     * Set size for a given object.
     * @param object Object
     * @param size Size
     */
    public final void
        setSize(final PetriObject object, final Dimension size) {

        object.setSize(size);

    }

    /**
     * Switch to a different marking.<br />
     * All places for the net will be set to the correct number of tokens.
     * @param marking Marking to switch to
     */
    public final void switchMarking(final PetriMarking marking) {

        this.currentMarking = marking;

        for (final PetriPlace place : this.places) {

            place.setTokens(marking.getTokens(place));

        }

    }

    @Override
    public final String toString() {
        return this.name;
    }

    /**
     * Add a set of places to the net.
     * @param places Set of places
     */
    private void addPlaces(final Set<PetriPlace> places) {

        this.places.addAll(places);

    }

    /**
     * Add a set of transitions to the net.
     * @param transitions Set of transitions
     */
    private void addTransitions(final Set<PetriTransition> transitions) {

        this.transitions.addAll(transitions);

    }

    /**
     * Remove a place from all markings.
     * @param place Place to be removed
     */
    private void removeFromMarkings(final PetriPlace place) {

        for (final PetriMarking petriMarking : this.markings) {

            petriMarking.setTokens(place, 0);

        }

    }

    /**
     * Method for tests to inject a custom configuration.
     * @param config Configuration
     */
    final void setConfig(final PetriConfig config) {

        PetriNet.config = config;

    }

}
