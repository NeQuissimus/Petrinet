package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

/**
 * Abstract class defining essential properties for each label component.
 * @author Tim Steinbach
 */
public abstract class AbstractLabel extends JLabel {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6558385524618595255L;

    /**
     * Logical object associated with label.
     */
    private PetriObject object = null;

    /**
     * Application controller.
     */
    private final PetriController controller;

    /**
     * Create a new UI label for a given logical component.
     * @param object Logical component
     */
    protected AbstractLabel(final PetriObject object) {

        this();

        this.object = object;

        this.setIcon(getPetriIcon(this.controller.getIconSize()));

        this.setText(this.controller.getName(object));

    }

    /**
     * Create a new UI label without it being associated to a logical component.
     */
    protected AbstractLabel() {

        this.controller = PetriController.getInstance();

        this.setIcon(getPetriIcon(IconSize.MEDIUM.getSize()));
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
    public abstract AbstractIcon getPetriIcon(final Dimension size);

    @Override
    public final void setText(final String text) {

        super.setText(text);
        this.setSize(this.getPreferredSize());

    }

    @Override
    public final void setSize(final Dimension size) {

        super.setSize(size);

        if (null != this.object) {
            this.controller.setSize(object, size);
        }

    }

    @Override
    public final void setBounds(final int x, final int y, final int width,
        final int height) {

        super.setBounds(x, y, width, height);

        if (null != this.object) {

            this.controller.setSize(object, new Dimension(width, height));
            this.controller.setPosition(object, new Point(x, y));

        }

    }

    @Override
    public final void setLocation(final Point location) {

        super.setLocation(location);

        if (null != this.object) {
            this.controller.setPosition(object, location);
        }

    }

    /**
     * Resize label icon.
     * @param size New size
     */
    public final void resizeIcon(final IconSize size) {

        this.setIcon(this.getPetriIcon(size.getSize()));
        this.setSize(this.getPreferredSize());

    }

}
