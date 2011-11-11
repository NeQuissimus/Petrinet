package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.OccurTransitionAction;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;

/**
 * Context menu for transitions on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasTransitionMenu extends CanvasIconMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -3627078926497615342L;

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Create new context menu for a transition.
     * @param invoker Invoking label
     */
    public CanvasTransitionMenu(final TransitionLabel invoker) {
        super(invoker);
    }

    @Override
    void addCustomItems() {

        final JMenuItem item =
            new JMenuItem(CanvasTransitionMenu.MSG.getIconMenuOccur());
        item.addActionListener(new OccurTransitionAction(
            (TransitionLabel) this.getPetriLabel()));
        this.add(item);

    }

}
