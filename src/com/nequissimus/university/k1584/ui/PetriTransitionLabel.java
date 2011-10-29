package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;

import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.listener.SidebarIconMenuListener;
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
     * Create a new label for a logical transition.
     * @param transition Logical transition
     */
    public PetriTransitionLabel(final PetriTransition transition) {

        super(transition);

        this.registerDraggable();

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
    final AbstractIcon getPetriIcon(final Dimension size) {

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
     * Add sidebar menu to this label.
     */
    public final void addSidebarMenu() {

        this.addMouseListener(new SidebarIconMenuListener(this));

    }

}
