package com.nequissimus.university.k1584.ui.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.AddNewPlaceAction;
import com.nequissimus.university.k1584.ui.actions.AddNewTransitionAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;

/**
 * Context menu for sidebar icons.
 * @author Tim Steinbach
 */
public class SidebarIconMenu extends AbstractContextMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 8479743006947699772L;

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Create side bar instance for a given label instance.
     * @param invoker Invoker label
     */
    public SidebarIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    @Override
    final void setupMenu() {

        final JMenuItem item =
            new JMenuItem(SidebarIconMenu.MSG.getIconMenuSidebarAdd());

        ActionListener action = null;

        if (this.getPetriLabel() instanceof PlaceLabel) {
            action = new AddNewPlaceAction();
        } else if (this.getPetriLabel() instanceof TransitionLabel) {
            action = new AddNewTransitionAction();
        }

        if (action != null) {

            item.addActionListener(action);
            this.add(item);

        }
    }

}
