package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JPopupMenu;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Abstract menu that defines all methods for context menus.
 * @author Tim Steinbach
 */
public abstract class AbstractContextMenu extends JPopupMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -7674897734685610675L;

    /**
     * Invoking component.
     */
    private final AbstractLabel invoker;

    /**
     * Create a new context menu for a label.
     * @param invoker Label
     */
    public AbstractContextMenu(final AbstractLabel invoker) {

        super();
        this.invoker = invoker;

        this.setupMenu();

    }

    /**
     * Return invoking Petri label.
     * @return Petri label
     */
    final AbstractLabel getPetriLabel() {

        return this.invoker;

    }

    /**
     * Add menu items and actions.
     */
    abstract void setupMenu();

}
