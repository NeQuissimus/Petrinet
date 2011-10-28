package com.nequissimus.university.k1584;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;
import com.nequissimus.university.k1584.ui.AbstractLabel;
import com.nequissimus.university.k1584.ui.PetriCanvas;
import com.nequissimus.university.k1584.ui.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.PetriWindow;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Main controller for the Petri net application
 * that is implemented as a singleton to make sure
 * no application has more than one controller/UI/logic.
 * @author Tim Steinbach
 *
 */
public final class PetriController implements Runnable {

    /**
     * Singleton instance of controller.
     */
    private static final PetriController CONTROLLER =
        new PetriController();

    /**
     * Graphical user interface.
     */
    private PetriWindow ui;

    /**
     * Snapshots of all logical Petri nets.
     */
    private final PetriSnapshots logic;

    /**
     * Configuration.
     */
    private final PetriConfig config = PetriConfig.getInstance();

    /**
     * The Petri net that is currently being edited.
     */
    private PetriNet currentNet = null;

    /**
     * Size for all icons.
     */
    private IconSize iconSize = IconSize.LARGE;

    /**
     * Instantiate the controller.
     */
    private PetriController() {

        this.logic = new PetriSnapshots();

        this.logic.add(this.config.getNetName());
        this.currentNet = this.logic.getCurrent();

    }

    /**
     * Get the singleton instance of PetriController.
     * @return Controller instance
     */
    public static PetriController getInstance() {
        return PetriController.CONTROLLER;
    }

    @Override
    public void run() {

        Component ui = this.getWindow();

        ui.validate();
        ui.repaint();
        ui.setVisible(true);

    }

    /**
     * Get the current size for all icons.
     * @return Current size
     */
    public Dimension getIconSize() {
        return this.iconSize.getSize();
    }

    /**
     * Set the new size for all icons.
     * @param size New size
     */
    public void setIconSize(final IconSize size) {
        this.iconSize = size;
    }

    /**
     * Create a new place object and add it to both, the canvas and logical net.
     */
    public void addPlace() {

        PetriPlace place = this.currentNet.addPlace();

        PetriPlaceLabel label = new PetriPlaceLabel(place);

        addLabel(label);

    }

    /**
     * Create a new transition object and add it to both, the canvas and logical
     * net.
     */
    public void addTransition() {

        PetriTransition transition = this.currentNet.addTransition();

        PetriTransitionLabel label = new PetriTransitionLabel(transition);

        addLabel(label);

    }

    /**
     * Add a new label to the canvas.
     * @param label Label to be added
     */
    private void addLabel(final AbstractLabel label) {

        PetriCanvas canvas = PetriWindow.getCanvas();

        label.setLocation(0, 0);
        canvas.add(label);

    }

    /**
     * Get a Petri object's name.
     * @param object Object
     * @return Object's name
     */
    public String getName(final PetriObject object) {

        return this.currentNet.getName(object);

    }

    /**
     * Resize a Petri object.
     * @param object Object to be resized
     * @param newSize New size
     */
    public void setSize(final PetriObject object, final Dimension newSize) {

        this.currentNet.setSize(object, newSize);

    }

    /**
     * Reposition a Petri object.
     * @param object Object to be moved
     * @param position New position
     */
    public void
        setPosition(final PetriObject object, final Point position) {

        this.currentNet.setPosition(object, position);

    }

    /**
     * Save the current Petri net to a given file.
     * @param file File to save to
     * @throws PnmlException Error turning nets into PNML
     */
    public void save(final File file) throws PnmlException {

        PetriMarkup.toPnmlFile(file, this.logic);

    }

    /**
     * Lazily initialize UI singleton and return it.
     * @return PetriWindow singleton
     */
    public PetriWindow getWindow() {

        if (null == this.ui) {

            this.ui = new PetriWindow();

        }

        return this.ui;

    }

    /**
     * Report a message to the UI/user.
     * @param severity Severity of message
     * @param message Message text
     */
    public void
        reportMessage(final Severity severity, final String message) {

        int messageType = JOptionPane.INFORMATION_MESSAGE;

        if (severity.equals(Severity.ERROR)) {
            messageType = JOptionPane.ERROR_MESSAGE;
        } else if (severity.equals(Severity.WARNING)) {
            messageType = JOptionPane.WARNING_MESSAGE;
        }

        JOptionPane.showMessageDialog(this.ui, message, null, messageType);

    }

}
