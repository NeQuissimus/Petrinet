//
//
//import java.awt.Component;
//import java.awt.Dimension;
//import java.awt.Point;
//import java.io.File;
//import java.util.Set;
//
//import javax.print.attribute.standard.Severity;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//import com.nequissimus.university.k1584.logic.PetriConfig;
//import com.nequissimus.university.k1584.logic.PetriNet;
//import com.nequissimus.university.k1584.logic.PetriObject;
//import com.nequissimus.university.k1584.logic.PetriPlace;
//import com.nequissimus.university.k1584.logic.PetriSnapshots;
//import com.nequissimus.university.k1584.logic.PetriTransition;
//import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
//import com.nequissimus.university.k1584.logic.pnml.PnmlException;
//import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
//import com.nequissimus.university.k1584.ui.elements.Arrow;
//import com.nequissimus.university.k1584.ui.elements.PetriCanvas;
//import com.nequissimus.university.k1584.ui.elements.PetriPlaceLabel;
//import com.nequissimus.university.k1584.ui.elements.PetriTransitionLabel;
//import com.nequissimus.university.k1584.ui.elements.PetriWindow;
//import com.nequissimus.university.k1584.ui.enums.IconSize;
//
///**
// * Main controller for the Petri net application that is implemented as a
// * singleton to make sure no application has more than one controller/UI/logic.
// * @author Tim Steinbach
// */
//public final class PetriController implements Runnable {
//
//    /**
//     * Singleton instance of controller.
//     */
//    private static final PetriController CONTROLLER = new PetriController();
//
//    /**
//     * Configuration.
//     */
//    private static final PetriConfig CONFIG = PetriConfig.getInstance();
//
//    /**
//     * Graphical user interface.
//     */
//    private PetriWindow ui;
//
//    /**
//     * Snapshots of all logical Petri nets.
//     */
//    private final PetriSnapshots logic;
//
//    /**
//     * The Petri net that is currently being edited.
//     */
//    private PetriNet currentNet = null;
//
//    /**
//     * Size for all icons.
//     */
//    private IconSize iconSize = IconSize.LARGE;
//
//    /**
//     * Temporarily hold a label until the user connects it to another one.
//     */
//    private AbstractLabel connectTmp = null;
//
//    /**
//     * Temporarily hold a label until the user chooses the disconnect target.
//     */
//    private AbstractLabel disconnectTmp = null;
//
//    /**
//     * Instantiate the controller.
//     */
//    private PetriController() {
//
//        this.logic = new PetriSnapshots();
//
//        this.logic.add(PetriConfig.getInstance().getNetName());
//        this.currentNet = this.logic.getCurrent();
//
//    }
//
//    /**
//     * Get the singleton instance of PetriController.
//     * @return Controller instance
//     */
//    public static PetriController getInstance() {
//        return PetriController.CONTROLLER;
//    }
//
//    @Override
//    public void run() {
//
//        Component ui = this.getWindow();
//
//        this.drawArrow();
//
//        ui.validate();
//        ui.repaint();
//        ui.setVisible(true);
//
//    }
//
//    /**
//     * Get the current size for all icons.
//     * @return Current size
//     */
//    public Dimension getIconSize() {
//        return this.iconSize.getSize();
//    }
//
//    /**
//     * Set the new size for all icons.
//     * @param size New size
//     */
//    public void setIconSize(final IconSize size) {
//
//        if (size != this.iconSize) {
//
//            this.iconSize = size;
//
//            PetriCanvas canvas = PetriWindow.getCanvas();
//
//            Set<AbstractLabel> labels = canvas.getLabels();
//
//            for (AbstractLabel label : labels) {
//                label.resizeIcon(size);
//            }
//
//            this.redrawCanvas();
//
//        }
//
//    }
//
//    /**
//     * Create a new place object and add it to both, the canvas and logical net.
//     */
//    public void addPlace() {
//
//        PetriPlace place = this.currentNet.addPlace();
//
//        PetriPlaceLabel label = new PetriPlaceLabel(place);
//
//        addLabel(label);
//
//    }
//
//    /**
//     * Create a new transition object and add it to both, the canvas and logical
//     * net.
//     */
//    public void addTransition() {
//
//        PetriTransition transition = this.currentNet.addTransition();
//
//        PetriTransitionLabel label = new PetriTransitionLabel(transition);
//
//        addLabel(label);
//
//    }
//
//    /**
//     * Add a new label to the canvas.
//     * @param label Label to be added
//     */
//    public void addLabel(final AbstractLabel label) {
//
//        PetriCanvas canvas = PetriWindow.getCanvas();
//
//        label.setLocation(0, 0);
//        canvas.add(label);
//
//        this.checkForActive();
//
//    }
//
//    /**
//     * Get a Petri object's name.
//     * @param object Object
//     * @return Object's name
//     */
//    public String getName(final PetriObject object) {
//
//        return this.currentNet.getName(object);
//
//    }
//
//    /**
//     * Resize a Petri object.
//     * @param object Object to be resized
//     * @param newSize New size
//     */
//    public void setSize(final PetriObject object, final Dimension newSize) {
//
//        this.currentNet.setSize(object, newSize);
//
//    }
//
//    /**
//     * Reposition a Petri object.
//     * @param object Object to be moved
//     * @param position New position
//     */
//    public void setPosition(final PetriObject object, final Point position) {
//
//        this.currentNet.setPosition(object, position);
//
//    }
//
//    /**
//     * Save the current Petri net to a given file.
//     * @param file File to save to
//     * @throws PnmlException Error turning nets into PNML
//     */
//    public void save(final File file) throws PnmlException {
//
//        PetriMarkup.savePnmlFile(file, this.logic);
//
//    }
//
//    /**
//     * Lazily initialize UI singleton and return it.
//     * @return PetriWindow singleton
//     */
//    public PetriWindow getWindow() {
//
//        if (null == this.ui) {
//
//            this.ui = new PetriWindow();
//
//        }
//
//        return this.ui;
//
//    }
//
//    /**
//     * Report a message to the UI/user.
//     * @param severity Severity of message
//     * @param message Message text
//     */
//    public void
//        reportMessage(final Severity severity, final String message) {
//
//        int messageType = JOptionPane.INFORMATION_MESSAGE;
//
//        if (severity.equals(Severity.ERROR)) {
//            messageType = JOptionPane.ERROR_MESSAGE;
//        } else if (severity.equals(Severity.WARNING)) {
//            messageType = JOptionPane.WARNING_MESSAGE;
//        }
//
//        JOptionPane.showMessageDialog(this.ui, message, null, messageType);
//
//    }
//
//    /**
//     * Remove an object and its label from the logical net and the canvas,
//     * respectively.
//     * @param label Label/object to be removed
//     */
//    public void removeObject(final AbstractLabel label) {
//
//        PetriObject object = label.getObject();
//
//        if (object instanceof PetriPlace) {
//            this.currentNet.remove((PetriPlace) object);
//        } else if (object instanceof PetriTransition) {
//            this.currentNet.remove((PetriTransition) object);
//        }
//
//        final JPanel canvas = PetriWindow.getCanvas().getCanvas();
//
//        canvas.remove(label);
//
//        this.redrawCanvas();
//
//    }
//
//    /**
//     * Check all transitions whether they are active and change their font
//     * colours accordingly.
//     */
//    public void checkForActive() {
//
//        Set<AbstractLabel> labels = PetriWindow.getCanvas().getLabels();
//
//        for (AbstractLabel label : labels) {
//
//            PetriObject object = label.getObject();
//
//            if (object instanceof PetriTransition) {
//
//                if (this.currentNet.isActive((PetriTransition) object)) {
//
//                    label.setForeground(CONFIG.getActiveTransitionColour());
//                    label.setFont(CONFIG.getActiveTransitionFont());
//
//                } else {
//
//                    label.setForeground(CONFIG
//                        .getInactiveTransitionColour());
//                    label.setFont(CONFIG.getInactiveTransitionFont());
//
//                }
//
//            }
//        }
//
//    }
//
//    /**
//     * Set all arrow canvases to the main canvas size.
//     */
//    public void resizeArrowCanvas() {
//
//        JPanel panel = PetriWindow.getCanvas().getCanvas();
//
//        Component[] components = panel.getComponents();
//
//        for (Component component : components) {
//            if (component instanceof Arrow) {
//                ((Arrow) component).setSize(panel.getSize());
//            }
//        }
//
//    }
//
//    /**
//     * Connect arrow to this label. If this is the first label chosen, save it
//     * for further use. If this is the second label chosen, create a connection
//     * with the first one selected.
//     * @param label Selected label
//     */
//    public void arrowConnect(final AbstractLabel label) {
//
//        if (null == this.connectTmp) {
//            this.connectTmp = label;
//        } else {
//            if (null != label) {
//                this.arrowConnect(this.connectTmp, label);
//                this.connectTmp = null;
//            }
//        }
//
//    }
//
//    /**
//     * Connect two labels with an arrow and make a logical connection.
//     * @param from Label to connect from
//     * @param to Label to connect to
//     */
//    private void arrowConnect(final AbstractLabel from,
//        final AbstractLabel to) {
//
//        if ((from == null) || (to == null)) {
//            return;
//        }
//
//        PetriCanvas canvas = PetriWindow.getCanvas();
//
//        PetriObject fromObject = from.getObject();
//        PetriObject toObject = to.getObject();
//
//        Arrow arrow = new Arrow(from, to);
//
//        if ((from instanceof PetriPlaceLabel)
//            && (to instanceof PetriTransitionLabel)) {
//
//            this.currentNet.connect((PetriPlace) fromObject,
//                (PetriTransition) toObject);
//            ((PetriTransitionLabel) to).addInputArrow(arrow);
//
//        } else if ((from instanceof PetriTransitionLabel)
//            && (to instanceof PetriPlaceLabel)) {
//
//            this.currentNet.connect((PetriTransition) fromObject,
//                (PetriPlace) toObject);
//            ((PetriTransitionLabel) from).addOutputArrow(arrow);
//
//        } else {
//            return;
//        }
//
//        arrow.setBounds(canvas.getBounds());
//
//        canvas.getCanvas().add(arrow);
//
//        arrow.repaint();
//
//        this.redrawCanvas();
//
//        this.checkForActive();
//
//    }
//
//    /**
//     * Disconnect this label from the one chosen next. If a label was chosen
//     * before, it will be used to disconnect from the current one.
//     * @param label Label to disconnect
//     */
//    public void arrowDisconnect(final AbstractLabel label) {
//        if (null == this.disconnectTmp) {
//            this.disconnectTmp = label;
//        } else {
//            if (null != label) {
//                this.arrowDisconnect(this.disconnectTmp, label);
//                this.disconnectTmp = null;
//            }
//        }
//    }
//
//    /**
//     * Disconnect two labels.
//     * @param from Disconnect from this label
//     * @param to Disconnect from this target label
//     */
//    private void arrowDisconnect(final AbstractLabel from,
//        final AbstractLabel to) {
//
//        if ((from == null) || (to == null)) {
//            return;
//        }
//
//        PetriObject fromO = from.getObject();
//        PetriObject toO = to.getObject();
//
//        Arrow arrow = null;
//
//        if ((fromO instanceof PetriTransition)
//            && (toO instanceof PetriPlace)) {
//
//            PetriTransitionLabel transition = ((PetriTransitionLabel) from);
//            arrow = transition.getOutputArrow((PetriPlaceLabel) to);
//
//            this.currentNet.removeOutput((PetriTransition) fromO,
//                (PetriPlace) toO);
//            transition.removeOutputArrow(arrow);
//
//        } else if ((fromO instanceof PetriPlace)
//            && (toO instanceof PetriTransition)) {
//
//            PetriTransitionLabel transition = ((PetriTransitionLabel) to);
//            arrow = transition.getInputArrow((PetriPlaceLabel) from);
//
//            this.currentNet.removeInput((PetriTransition) toO,
//                (PetriPlace) fromO);
//            transition.removeInputArrow(arrow);
//
//        } else {
//            return;
//        }
//
//        PetriWindow.getCanvas().getCanvas().remove(arrow);
//
//        this.redrawCanvas();
//
//        this.checkForActive();
//
//    }
//
//    /**
//     * Test.
//     */
//    private void drawArrow() {
//
//        // TODO: Remove this method.
//
//        PetriCanvas canvas = PetriWindow.getCanvas();
//
//        this.addPlace();
//        this.addTransition();
//        this.addPlace();
//
//        Set<AbstractLabel> labels = canvas.getLabels();
//
//        Object[] arr = labels.toArray();
//
//        PetriTransitionLabel label = null;
//        PetriPlaceLabel[] labelsA = new PetriPlaceLabel[2];
//
//        int i = 0;
//        for (Object object : arr) {
//            if (object instanceof PetriTransitionLabel) {
//                label = (PetriTransitionLabel) object;
//            } else {
//                labelsA[i++] = (PetriPlaceLabel) object;
//            }
//        }
//
//        label.setLocation(new Point(150, 100));
//
//        this.arrowConnect(labelsA[0]);
//        this.arrowConnect(label);
//        // this.arrowConnect(label);
//        // this.arrowConnect(labelsA[1]);
//
//    }
//
//    /**
//     * Get the currently active Petri net.
//     * @return Petri net
//     */
//    public PetriNet getCurrentNet() {
//
//        return this.currentNet;
//
//    }
//
//    /**
//     * Redraw the canvas.
//     */
//    public void redrawCanvas() {
//        PetriWindow.getCanvas().repaint();
//    }
//
//}
