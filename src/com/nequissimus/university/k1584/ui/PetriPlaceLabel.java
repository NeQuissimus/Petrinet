package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;

import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.listener.SidebarIconMenuListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

/**
 * UI label for place objects. This label consists of an icon and a name tag.
 * @author Tim Steinbach
 */
public class PetriPlaceLabel extends AbstractLabel implements Draggable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -1093238662290231525L;

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener mouseListener;

    /**
     * Create a new label instance for a given logical place.
     * @param place Logical place
     */
    public PetriPlaceLabel(final PetriPlace place) {

        super(place);

        this.registerDraggable();

    }

    /**
     * Create a place label without connecting it to a logical place (for the
     * sidebar). Such a label has no UI behaviour (e.g. drag and drop) either.
     * It does have a context menu by default.
     */
    public PetriPlaceLabel() {

        super();

        this.addSidebarMenu();

    }

    @Override
    final AbstractIcon getPetriIcon(final Dimension size) {

        return new PetriPlaceIcon(size);

    }

    @Override
    public final void registerDraggable() {

        this.mouseListener = new DragListener(this);

        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);

    }

    @Override
    public final void unregisterDraggable() {

        this.removeMouseListener(this.mouseListener);
        this.removeMouseMotionListener(this.mouseListener);

    }

    /**
     * Add the context menu for adding new elements to the canvas.
     */
    public final void addSidebarMenu() {

        this.addMouseListener(new SidebarIconMenuListener(this));

    }

}
