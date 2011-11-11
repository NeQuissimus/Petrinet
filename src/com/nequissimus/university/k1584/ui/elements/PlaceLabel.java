package com.nequissimus.university.k1584.ui.elements;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.listener.CanvasPlaceListener;
import com.nequissimus.university.k1584.ui.listener.DragListener;

/**
 * UI label for place objects. This label consists of an icon and a name tag.
 * @author Tim Steinbach
 */
public class PlaceLabel extends AbstractLabel {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -1093238662290231525L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Number of markings.
     */
    private int markings = 0;

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener mouseListener;

    /**
     * Create a new label instance for a given logical place.
     * @param name Place name
     */
    public PlaceLabel(final String name) {

        super(name);

        this.registerDraggable();
        this.addCanvasMenu();

    }

    /**
     * Create a place label without connecting it to a logical place (for the
     * sidebar). Such a label has no UI behaviour (e.g. drag and drop) either.
     * It does have a context menu by default.
     */
    public PlaceLabel() {

        super();

        this.setText(PlaceLabel.CONFIG.getPlaceName());

        this.addSidebarMenu();

    }

    /**
     * Get number of markings.
     * @return Markings
     */
    public final int getMarkings() {
        return this.markings;
    }

    /**
     * Set new number of markings.
     * @param value Markings
     */
    public final void setMarkings(final int value) {

        this.markings = value;

        final PlaceIcon icon = ((PlaceIcon) this.getIcon());
        icon.setMarkings(this.markings);
        icon.draw();

    }

    @Override
    public final AbstractIcon getPetriIcon(final IconSize size) {

        return new PlaceIcon(size);

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

    @Override
    public final void addCanvasMenu() {

        this.addMouseListener(new CanvasPlaceListener(this));

    }

}
