package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.DecreaseMarkingsAction;
import com.nequissimus.university.k1584.ui.actions.IncreaseMarkingsAction;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;

/**
 * Context menu for Petri place icons on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasPlaceMenu extends CanvasIconMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6468863481232638652L;

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Create a new menu for places.
     * @param invoker Invoking place
     */
    public CanvasPlaceMenu(final PlaceLabel invoker) {
        super(invoker);
    }

    @Override
    void addCustomItems() {

        JMenuItem item =
            new JMenuItem(CanvasPlaceMenu.MSG.getIconMenuIncreaseMarkings());
        item.addActionListener(new IncreaseMarkingsAction((PlaceLabel) this
            .getPetriLabel()));
        this.add(item);

        item =
            new JMenuItem(CanvasPlaceMenu.MSG.getIconMenuDecreaseMarkings());
        item.addActionListener(new DecreaseMarkingsAction((PlaceLabel) this
            .getPetriLabel()));
        this.add(item);

    }

}
