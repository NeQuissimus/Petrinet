package com.nequissimus.university.k1584.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Container that holds all snapshots of Petri nets as well as the current one.
 * @author Tim Steinbach
 */
public class PetriSnapshots {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Petri nets.
     */
    private final List<PetriNet> nets = new ArrayList<PetriNet>();

    /**
     * Currently active Petri net.
     */
    private PetriNet current = null;

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

            this.current = this.add(PetriSnapshots.CONFIG.getNetName());

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
