package com.nequissimus.university.k1584.ui.elements;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
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
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener dragDropListener;

    /**
     * Create a new label for a logical transition.
     * @param name Transition name
     */
    public PetriTransitionLabel(final String name) {

        super(name);

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
        this.setText(CONFIG.getTransitionName());

    }

    /**
     * Get icon of particular size.
     * @param size Size
     * @return Icon
     */
    @Override
    public final AbstractIcon getPetriIcon(final IconSize size) {

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

}
