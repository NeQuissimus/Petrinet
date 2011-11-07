package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.actions.ConnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.DisconnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.RemoveObjectAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Context menu that is displayed for Petri objects on the canvas.
 * @author Tim Steinbach
 */
public final class CanvasIconMenu extends AbstractContextMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -906932943870192331L;

    /**
     * Create a new context menu for a label.
     * @param invoker Label
     */
    public CanvasIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    @Override
    void setupMenu() {

        JMenuItem item = new JMenuItem("Remove");
        item.addActionListener(new RemoveObjectAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem("Connect");
        item.addActionListener(new ConnectArrowAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem("Disconnect");
        item.addActionListener(new DisconnectArrowAction(this
            .getPetriLabel()));
        this.add(item);

    }

}
