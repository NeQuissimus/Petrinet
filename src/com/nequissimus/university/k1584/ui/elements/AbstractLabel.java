package com.nequissimus.university.k1584.ui.elements;

import javax.swing.JLabel;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.enums.TextPosition;
import com.nequissimus.university.k1584.ui.listener.CanvasIconMenuListener;
import com.nequissimus.university.k1584.ui.listener.SidebarIconMenuListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

/**
 * Abstract class defining essential properties for each label component.
 * @author Tim Steinbach
 */
public abstract class AbstractLabel extends JLabel implements Draggable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6558385524618595255L;

    /**
     * Application controller.
     */
    private final PetriController controller;

    /**
     * Create a new UI label for a given logical component.
     * @param name Label name
     */
    protected AbstractLabel(final String name) {

        this();

        this.setIcon(getPetriIcon(this.controller.getIconSize()));

        this.setText(name);

    }

    /**
     * Create a new UI label without it being associated to a logical component.
     */
    protected AbstractLabel() {

        this.controller = PetriController.getInstance();

        this.setIcon(getPetriIcon(IconSize.MEDIUM));
        this.setText("");
        this.setOpaque(false);

        this.moveText(TextPosition.RIGHT);

    }

    /**
     * Get the application controller.
     * @return Application controller
     */
    final PetriController getController() {

        return this.controller;

    }

    /**
     * Move the text to a different position relative to its icon.
     * @param pos Text position
     */
    final void moveText(final TextPosition pos) {

        this.setHorizontalTextPosition(pos.getX());
        this.setVerticalTextPosition(pos.getY());

    }

    /**
     * Return the associated icon.
     * @param size Icon size
     * @return Icon
     */
    public abstract AbstractIcon getPetriIcon(final IconSize size);

    @Override
    public final void setText(final String text) {

        super.setText(text);
        this.setSize(this.getPreferredSize());

    }

    /**
     * Resize label icon.
     * @param size New size
     */
    public final void resizeIcon(final IconSize size) {

        this.setIcon(this.getPetriIcon(size));
        this.setSize(this.getPreferredSize());

    }

    /**
     * Add the context menu for adding new elements to the canvas.
     */
    public final void addSidebarMenu() {

        this.addMouseListener(new SidebarIconMenuListener(this));

    }

    /**
     * Add the context menu for elements on the canvas.
     */
    public final void addCanvasMenu() {

        this.addMouseListener(new CanvasIconMenuListener(this));

    }

}
