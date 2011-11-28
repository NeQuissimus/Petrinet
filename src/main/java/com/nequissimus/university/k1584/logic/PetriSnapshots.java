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

import java.util.ArrayList;
import java.util.List;

import com.nequissimus.library.data.Singleton;

/**
 * Container that holds all snapshots of Petri nets as well as the current one.
 * @author Tim Steinbach
 */
public class PetriSnapshots {

    /**
     * Configuration.
     */
    private static PetriConfig config;

    /**
     * Petri nets.
     */
    private final List<PetriNet> nets = new ArrayList<PetriNet>();

    /**
     * Currently active Petri net.
     */
    private PetriNet current = null;

    /**
     * Create a new snapshots object.
     */
    public PetriSnapshots() {

        PetriSnapshots.config = Singleton.getObject(PetriConfig.class);

    }

    /**
     * Create a new snapshots object and inject a custom configuration.
     * @param config Custom configuration
     */
    PetriSnapshots(final PetriConfig config) {

        PetriSnapshots.config = config;

    }

    /**
     * Add a new Petri net to the list of snapshots.
     * @param net Net to be added
     */
    public final void add(final PetriNet net) {

        this.nets.add(net);

    }

    /**
     * Create a new Petri net and add it to the snapshots.
     * @param name Name for new net
     * @return New Petri net
     */
    public final PetriNet add(final String name) {

        final PetriNet net = new PetriNet(name);
        this.nets.add(net);

        return net;

    }

    /**
     * Delete a logical net from the snapshots.
     * @param net Net to be removed
     */
    public final void delete(final PetriNet net) {

        this.nets.remove(net);

        if (net == this.current) {

            this.current = null;
            this.current = this.getCurrent();

        }

    }

    /**
     * Find a logical net by its name.
     * @param name Name
     * @return Logical net with net. NULL if no such net could be found
     */
    public final PetriNet getByName(final String name) {

        for (final PetriNet net : this.nets) {

            if (net.getName().equals(name)) {
                return net;
            }

        }

        return null;

    }

    /**
     * Get the currently active logical net.
     * @return Currently active net
     */
    public final PetriNet getCurrent() {

        if (this.nets.isEmpty()) {

            this.current = this.add(PetriSnapshots.config.getNetName());

        }

        if ((null == this.current) && (this.nets.size() > 0)) {

            this.current = this.nets.get(this.nets.size() - 1);

        }

        return this.current;

    }

    /**
     * Get the list of nets.
     * @return All snapshots
     */
    public final List<PetriNet> getNets() {

        return this.nets;

    }

    /**
     * Rename a Petri net.
     * @param net Net to be renamed
     * @param newName New name
     */
    public final void rename(final PetriNet net, final String newName) {

        net.setName(newName);

    }

    /**
     * Set a new currently active net.
     * @param net Active net
     */
    public final void setCurrent(final PetriNet net) {

        if (null != net) {
            this.current = net;
        }

    }

}
