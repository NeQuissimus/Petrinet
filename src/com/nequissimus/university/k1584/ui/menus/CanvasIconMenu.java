package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.ConnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.DisconnectArrowAction;
import com.nequissimus.university.k1584.ui.actions.RemoveObjectAction;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Context menu that is displayed for Petri objects on the canvas.
 * @author Tim Steinbach
 */
public abstract class CanvasIconMenu extends AbstractContextMenu {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -906932943870192331L;

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Create a new context menu for a label.
     * @param invoker Label
     */
    public CanvasIconMenu(final AbstractLabel invoker) {

        super(invoker);

    }

    @Override
    final void setupMenu() {

        JMenuItem item =
            new JMenuItem(CanvasIconMenu.MSG.getIconMenuRemove());
        item.addActionListener(new RemoveObjectAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem(CanvasIconMenu.MSG.getIconMenuConnect());
        item.addActionListener(new ConnectArrowAction(this.getPetriLabel()));
        this.add(item);

        item = new JMenuItem(CanvasIconMenu.MSG.getIconMenuDisconnect());
        item.addActionListener(new DisconnectArrowAction(this
            .getPetriLabel()));
        this.add(item);

        this.addCustomItems();

    }

    /**
     * Add custom menu entries in sub-classes.
     */
    abstract void addCustomItems();

}
