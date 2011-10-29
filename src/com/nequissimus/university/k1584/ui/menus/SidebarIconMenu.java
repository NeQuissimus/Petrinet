package com.nequissimus.university.k1584.ui.menus;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.actions.AddNewPlaceAction;
import com.nequissimus.university.k1584.ui.actions.AddNewTransitionAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.elements.PetriTransitionLabel;

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
     * Create side bar instance for a given label instance.
     * @param invoker Invoker label
     */
    public SidebarIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    @Override
    final void setupMenu() {

        JMenuItem item = new JMenuItem("Add new");

        ActionListener action = null;

        if (this.getPetriLabel() instanceof PetriPlaceLabel) {
            action = new AddNewPlaceAction();
        } else if (this.getPetriLabel() instanceof PetriTransitionLabel) {
            action = new AddNewTransitionAction();
        }

        if (action != null) {

            item.addActionListener(action);
            this.add(item);

        }
    }

}
