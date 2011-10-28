package com.nequissimus.university.k1584.ui.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.nequissimus.university.k1584.ui.AbstractLabel;
import com.nequissimus.university.k1584.ui.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.actions.AddNewPlaceAction;
import com.nequissimus.university.k1584.ui.actions.AddNewTransitionAction;

/**
 * Context menu for sidebar icons.
 * @author Tim Steinbach
 *
 */
public class SidebarIconMenu extends JPopupMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 8479743006947699772L;

    /**
     * Invoker source label.
     */
    private final AbstractLabel invoker;

    /**
     * Create side bar instance for a given label instance.
     * @param invoker Invoker label
     */
    public SidebarIconMenu(final AbstractLabel invoker) {

        super();
        this.invoker = invoker;

        this.setupMenu();

    }

    /**
     * Set menu up with all items and actions.
     */
    private void setupMenu() {

        JMenuItem item = new JMenuItem("Add new");

        ActionListener action = null;

        if (invoker instanceof PetriPlaceLabel) {
            action = new AddNewPlaceAction();
        } else if (invoker instanceof PetriTransitionLabel) {
            action = new AddNewTransitionAction();
        }

        if (action != null) {

            item.addActionListener(action);
            this.add(item);

        }
    }

}
