package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

/**
 * UI label for transition objects. This label consists of the transition icon
 * and a name tag.
 * @author Tim Steinbach
 */
public class PetriTransitionLabel extends AbstractLabel implements
    Draggable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5447519082475950763L;

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener dragDropListener;

    /**
     * Incoming arrows.
     */
    private final Set<Arrow> inputArrows = new HashSet<Arrow>();

    /**
     * Outgoing arrows.
     */
    private final Set<Arrow> outputArrows = new HashSet<Arrow>();

    /**
     * Create a new label for a logical transition.
     * @param transition Logical transition
     */
    public PetriTransitionLabel(final PetriTransition transition) {

        super(transition);

        this.registerDraggable();
        this.addCanvasMenu();

    }

    /**
     * Create a transition label without connecting it to a logical transition
     * (for the sidebar). Such a label has no UI behaviour (e.g. drag and drop)
     * either. It does have a context menu by default.
     */
    public PetriTransitionLabel() {

        super();

        this.addSidebarMenu();

    }

    /**
     * Get icon of particular size.
     * @param size Size
     * @return Icon
     */
    @Override
    public final AbstractIcon getPetriIcon(final Dimension size) {

        return new PetriTransitionIcon(size);

    }

    @Override
    public final void registerDraggable() {

        this.dragDropListener = new DragListener(this);

        this.addMouseListener(this.dragDropListener);
        this.addMouseMotionListener(this.dragDropListener);

    }

    @Override
    public final void unregisterDraggable() {

        this.removeMouseListener(this.dragDropListener);
        this.removeMouseMotionListener(this.dragDropListener);

    }

    /**
     * Add an incoming arrow.
     * @param arrow Incoming arrow
     * @return Whether arrow was added
     */
    public final boolean addInputArrow(final Arrow arrow) {
        return this.inputArrows.add(arrow);
    }

    /**
     * Add an outgoing arrow.
     * @param arrow Outgoing arrow
     * @return Whether arrow was added
     */
    public final boolean addOutputArrow(final Arrow arrow) {
        return this.outputArrows.add(arrow);
    }

    /**
     * Remove an incoming arrow.
     * @param arrow Arrow
     * @return Whether arrow was removed
     */
    public final boolean removeInputArrow(final Arrow arrow) {
        return this.inputArrows.remove(arrow);
    }

    /**
     * Remove an outgoing arrow.
     * @param arrow Arrow
     * @return Whether arrow was removed
     */
    public final boolean removeOutputArrow(final Arrow arrow) {
        return this.outputArrows.remove(arrow);
    }

    /**
     * Get an input arrow from the given place to this transition.
     * @param label Place label
     * @return Arrow if one could be found, NULL if not
     */
    public final Arrow getInputArrow(final PetriPlaceLabel label) {
        for (Arrow arrow : this.inputArrows) {
            if (arrow.getFrom().equals(label)) {
                return arrow;
            }
        }
        return null;
    }

    /**
     * Get an input arrow from this transition to the given place.
     * @param label Place label
     * @return Arrow if one could be found, NULL if not
     */
    public final Arrow getOutputArrow(final PetriPlaceLabel label) {
        for (Arrow arrow : this.outputArrows) {
            if (arrow.getFrom().equals(label)) {
                return arrow;
            }
        }
        return null;
    }

}
