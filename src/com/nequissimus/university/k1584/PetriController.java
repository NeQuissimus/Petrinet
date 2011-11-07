package com.nequissimus.university.k1584;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.Map.Entry;
import java.util.Set;

import javax.print.attribute.standard.Severity;

import com.nequissimus.university.k1584.data.BiMap;
import com.nequissimus.university.k1584.data.DoubleKeyMap;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;
import com.nequissimus.university.k1584.ui.PetriUi;
import com.nequissimus.university.k1584.ui.PetriUiImpl;
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
public enum PetriController implements Runnable {

    /**
     * Controller instance.
     */
    INSTANCE;

    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    private final PetriUi ui;

    private final PetriNet logic;

    private final PetriSnapshots snapshots;

    private final BiMap<PetriObject, AbstractLabel> objects =
        new BiMap<PetriObject, AbstractLabel>();

    private final DoubleKeyMap<PetriPlaceLabel, PetriTransitionLabel, Arrow> arrows =
        new DoubleKeyMap<PetriPlaceLabel, PetriTransitionLabel, Arrow>();

    private AbstractLabel connectTmp = null;

    private AbstractLabel disconnectTmp = null;

    private PetriController() {

        this.ui = new PetriUiImpl();
        this.snapshots = new PetriSnapshots();

        this.logic = this.snapshots.getCurrent();

    }

    /**
     * Get a singleton instance of the controller.
     * @return Controller
     */
    public static PetriController getInstance() {

        return INSTANCE;

    }

    public final void addPlace() {

        PetriPlaceLabel label = this.ui.addPlace();
        PetriPlace place = this.logic.addPlace();

        this.objects.put(place, label);

    }

    public final void addTransition() {

        PetriTransitionLabel label = this.ui.addTransition();
        PetriTransition transition = this.logic.addTransition();

        this.objects.put(transition, label);

    }

    public final void setIconSize(final IconSize size) {

        this.ui.setIconSize(size);
        this.logic.setSize(size.getSize());

    }

    public final IconSize getIconSize() {

        return this.ui.getIconSize();

    }

    public final void removeObject(final AbstractLabel label) {

        PetriObject object = this.findObject(label);

        this.ui.removeLabel(label);
        this.logic.remove(object);

    }

    public final void reportMessage(final Severity severity,
        final String message) {
        this.ui.reportMessage(severity, message);
    }

    public final PetriWindow getWindow() {
        return this.ui.getWindow();
    }

    public final void redrawCanvas() {
        this.ui.redrawCanvas();
    }

    public final void moveLabel(final AbstractLabel label,
        final Point location) {

        PetriObject object = this.findObject(label);

        this.ui.moveLabel(label, location);
        this.logic.setPosition(object, location);

    }

    public final void resizeCanvas(final Dimension size) {

        this.ui.resizeCanvas(size);
        this.ui.resizeArrowCanvas(size);

    }

    /**
     * Save the current Petri net to a given file.
     * @param file File to save to
     * @throws PnmlException Error turning nets into PNML
     */
    public void save(final File file) throws PnmlException {

        PetriMarkup.savePnmlFile(file, this.snapshots);

    }

    /**
     * Connect arrow to this label. If this is the first label chosen, save it
     * for further use. If this is the second label chosen, create a connection
     * with the first one selected.
     * @param label Selected label
     */
    public void arrowConnect(final AbstractLabel label) {

        if (null == this.connectTmp) {
            this.connectTmp = label;
        } else {
            if (null != label) {
                this.arrowConnect(this.connectTmp, label);
                this.connectTmp = null;
            }
        }

    }

    /**
     * Connect two labels with an arrow and make a logical connection.
     * @param from Label to connect from
     * @param to Label to connect to
     */
    private void arrowConnect(final AbstractLabel from,
        final AbstractLabel to) {

        if ((from == null) || (to == null)) {
            return;
        }

        PetriCanvas canvas = this.ui.getCanvas();

        PetriObject fromObject = this.findObject(from);
        PetriObject toObject = this.findObject(to);

        Arrow arrow = new Arrow(from, to);

        if ((from instanceof PetriPlaceLabel)
            && (to instanceof PetriTransitionLabel)) {

            PetriPlaceLabel fromL = (PetriPlaceLabel) from;
            PetriTransitionLabel toL = (PetriTransitionLabel) to;
            PetriPlace fromO = (PetriPlace) fromObject;
            PetriTransition toO = (PetriTransition) toObject;

            this.logic.connect(fromO, toO);
            this.arrows.put(fromL, toL, arrow);

        } else if ((from instanceof PetriTransitionLabel)
            && (to instanceof PetriPlaceLabel)) {

            PetriTransitionLabel fromL = (PetriTransitionLabel) from;
            PetriPlaceLabel toL = (PetriPlaceLabel) to;
            PetriTransition fromO = (PetriTransition) fromObject;
            PetriPlace toO = (PetriPlace) toObject;

            this.logic.connect(fromO, toO);
            this.arrows.put(toL, fromL, arrow);

        } else {
            return;
        }

        arrow.setBounds(canvas.getBounds());
        canvas.getCanvas().add(arrow);
        arrow.repaint();

        this.redrawCanvas();

        this.checkForActive();

    }

    /**
     * Disconnect this label from the one chosen next. If a label was chosen
     * before, it will be used to disconnect from the current one.
     * @param label Label to disconnect
     */
    public void arrowDisconnect(final AbstractLabel label) {
        if (null == this.disconnectTmp) {
            this.disconnectTmp = label;
        } else {
            if (null != label) {
                this.arrowDisconnect(this.disconnectTmp, label);
                this.disconnectTmp = null;
            }
        }
    }

    /**
     * Disconnect two labels.
     * @param from Disconnect from this label
     * @param to Disconnect from this target label
     */
    private void arrowDisconnect(final AbstractLabel from,
        final AbstractLabel to) {

        if ((from == null) || (to == null)) {
            return;
        }

        PetriObject fromObject = this.findObject(from);
        PetriObject toObject = this.findObject(to);

        Arrow arrow = null;

        if ((from instanceof PetriTransitionLabel)
            && (to instanceof PetriPlaceLabel)) {

            PetriTransitionLabel fromL = (PetriTransitionLabel) from;
            PetriPlaceLabel toL = (PetriPlaceLabel) to;
            PetriTransition fromO = (PetriTransition) fromObject;
            PetriPlace toO = (PetriPlace) toObject;

            arrow = this.arrows.get(toL, fromL);

            this.logic.removeOutput(fromO, toO);

        } else if ((from instanceof PetriPlaceLabel)
            && (to instanceof PetriTransitionLabel)) {

            PetriPlaceLabel fromL = (PetriPlaceLabel) from;
            PetriTransitionLabel toL = (PetriTransitionLabel) to;
            PetriPlace fromO = (PetriPlace) fromObject;
            PetriTransition toO = (PetriTransition) toObject;

            arrow = this.arrows.get(fromL, toL);

            this.logic.removeInput(toO, fromO);

        } else {
            return;
        }

        this.ui.removeArrow(arrow);

        this.redrawCanvas();

        this.checkForActive();

    }

    /**
     * Check all transitions whether they are active and change their font
     * colours accordingly.
     */
    public void checkForActive() {

        Set<Entry<PetriObject, AbstractLabel>> entries =
            this.objects.entrySet();

        for (Entry<PetriObject, AbstractLabel> entry : entries) {

            PetriObject object = entry.getKey();
            AbstractLabel label = entry.getValue();

            if (object instanceof PetriTransition) {

                if (this.logic.isActive((PetriTransition) object)) {

                    label.setForeground(CONFIG.getActiveTransitionColour());
                    label.setFont(CONFIG.getActiveTransitionFont());

                } else {

                    label.setForeground(CONFIG
                        .getInactiveTransitionColour());
                    label.setFont(CONFIG.getInactiveTransitionFont());

                }

            }

        }

    }

    @Override
    public void run() {
        // TODO: Auto-generated method stub

    }

    /**
     * Get a Petri object represented by a given UI component.
     * @param label Label component
     * @return Logical object
     */
    private PetriObject findObject(final AbstractLabel label) {
        return this.objects.getKey(label);
    }

}
