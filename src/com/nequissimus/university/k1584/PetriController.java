package com.nequissimus.university.k1584;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.Set;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.PetriCanvas;
import com.nequissimus.university.k1584.ui.elements.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.elements.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.elements.PetriWindow;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Main controller for the Petri net application that is implemented as a
 * singleton to make sure no application has more than one controller/UI/logic.
 * @author Tim Steinbach
 */
public final class PetriController implements Runnable {

    /**
     * Singleton instance of controller.
     */
    private static final PetriController CONTROLLER = new PetriController();

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

        this.drawArrow();

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

        if (size != this.iconSize) {

            this.iconSize = size;

            PetriCanvas canvas = PetriWindow.getCanvas();

            Set<AbstractLabel> labels = canvas.getLabels();

            for (AbstractLabel label : labels) {
                label.resizeIcon(size);
            }

            canvas.revalidate();
            canvas.repaint();

        }

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

        this.checkForActive();

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
    public void setPosition(final PetriObject object, final Point position) {

        this.currentNet.setPosition(object, position);

    }

    /**
     * Save the current Petri net to a given file.
     * @param file File to save to
     * @throws PnmlException Error turning nets into PNML
     */
    public void save(final File file) throws PnmlException {

        PetriMarkup.savePnmlFile(file, this.logic);

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

    /**
     * Remove an object and its label from the logical net and the canvas,
     * respectively.
     * @param label Label/object to be removed
     */
    public void removeObject(final AbstractLabel label) {

        PetriObject object = label.getObject();

        if (object instanceof PetriPlace) {
            this.currentNet.remove((PetriPlace) object);
        } else if (object instanceof PetriTransition) {
            this.currentNet.remove((PetriTransition) object);
        }

        final JPanel canvas = PetriWindow.getCanvas().getCanvas();

        canvas.remove(label);
        canvas.invalidate();
        canvas.repaint();

    }

    /**
     * Check all transitions whether they are active and change their font
     * colours accordingly.
     */
    public void checkForActive() {

        Set<AbstractLabel> labels = PetriWindow.getCanvas().getLabels();

        for (AbstractLabel label : labels) {

            PetriObject object = label.getObject();

            if (object instanceof PetriTransition) {

                if (this.currentNet.isActive((PetriTransition) object)) {

                    label.setForeground(this.config
                        .getActiveTransitionColour());
                    label.setFont(this.config.getActiveTransitionFont());

                } else {

                    label.setForeground(this.config
                        .getInactiveTransitionColour());
                    label.setFont(this.config.getInactiveTransitionFont());

                }

            }
        }

    }

    /**
     * Set all arrow canvases to the main canvas size.
     */
    public void resizeArrowCanvas() {

        JPanel panel = PetriWindow.getCanvas().getCanvas();

        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof Arrow) {
                ((Arrow) component).setSize(panel.getSize());
            }
        }

    }

    /**
     * Test.
     */
    private void drawArrow() {

        // TODO: Remove this method.

        PetriCanvas canvas = PetriWindow.getCanvas();

        this.addPlace();
        this.addTransition();

        Set<AbstractLabel> labels = canvas.getLabels();

        Object[] arr = labels.toArray();

        ((AbstractLabel) arr[0]).setLocation(new Point(150, 100));

        Arrow arrow =
            new Arrow((AbstractLabel) arr[0], (AbstractLabel) arr[1]);
        arrow.setBounds(0, 0, 100, 100);

        // canvas.add(arrow);
        canvas.getCanvas().add(arrow);

        arrow.revalidate();
        canvas.getCanvas().revalidate();
        canvas.revalidate();

        arrow.repaint();
        canvas.getCanvas().repaint();
        canvas.repaint();

    }

}
