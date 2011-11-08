package com.nequissimus.university.k1584;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.Map.Entry;
import java.util.Set;

import javax.print.attribute.standard.Severity;

import com.nequissimus.university.k1584.data.BiMap;
import com.nequissimus.university.k1584.data.TwoKeyMap;
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
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.elements.Window;
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

    /**
     * User interface master.
     */
    private final PetriUi ui;

    /**
     * Currently active logical net.
     */
    private final PetriNet logic;

    /**
     * Logic master.
     */
    private final PetriSnapshots snapshots;

    /**
     * Bidirectional map for logical objects and UI components.
     */
    private final BiMap<PetriObject, AbstractLabel> objects =
        new BiMap<PetriObject, AbstractLabel>();

    /**
     * Map with two keys holding all arrow components and their paths.
     */
    private final TwoKeyMap<PlaceLabel, TransitionLabel, Arrow> arrows =
        new TwoKeyMap<PlaceLabel, TransitionLabel, Arrow>();

    /**
     * Temporary element for connecting it to another one.
     */
    private AbstractLabel connectTmp = null;

    /**
     * Temporary element for disconnecting an arrow from it.
     */
    private AbstractLabel disconnectTmp = null;

    /**
     * Instantiate the controller.
     */
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

    /**
     * Add a new place to the logical net and the UI.
     */
    public final void addPlace() {

        final PlaceLabel label = this.ui.addPlace();
        final PetriPlace place = this.logic.addPlace();

        this.logic.setSize(place, label.getSize());

        this.objects.put(place, label);

        this.checkForActive();

    }

    /**
     * Add a new transition to the logical net and the UI.
     */
    public final void addTransition() {

        final TransitionLabel label = this.ui.addTransition();
        final PetriTransition transition = this.logic.addTransition();

        this.logic.setSize(transition, label.getSize());

        this.objects.put(transition, label);

        this.checkForActive();

    }

    /**
     * Set the new size for all icons representing Petri net components.
     * @param size New size
     */
    public final void setIconSize(final IconSize size) {

        this.ui.setIconSize(size);
        this.logic.setSize(size.getSize());

    }

    /**
     * Get the current icon size for Petri net components.
     * @return Icon size
     */
    public final IconSize getIconSize() {

        return this.ui.getIconSize();

    }

    /**
     * Remove a Petri object from the logical net and the UI.
     * @param label Label object to remove
     */
    public final void removeObject(final AbstractLabel label) {

        final PetriObject object = this.findObject(label);

        this.ui.removeLabel(label);
        this.logic.remove(object);

        this.checkForActive();

    }

    /**
     * Report a message to the user.
     * @param severity Message severity
     * @param message Message text
     */
    public final void reportMessage(final Severity severity,
        final String message) {
        this.ui.reportMessage(severity, message);
    }

    /**
     * Get the UI window.
     * @return UI window
     */
    public final Window getWindow() {
        return this.ui.getWindow();
    }

    /**
     * Redraw the entire UI and its components.
     */
    public final void redrawCanvas() {
        this.ui.redrawCanvas();
    }

    /**
     * Move a label component to a new position.
     * @param label Label component to move
     * @param location New location
     */
    public final void moveLabel(final AbstractLabel label,
        final Point location) {

        final PetriObject object = this.findObject(label);

        this.ui.moveLabel(label, location);
        this.logic.setPosition(object, location);

    }

    /**
     * Resize the canvas and all arrow canvases.
     * @param size New size
     */
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

        final PetriObject fromObject = this.findObject(from);
        final PetriObject toObject = this.findObject(to);

        final Arrow arrow = new Arrow(from, to);

        if ((from instanceof PlaceLabel) && (to instanceof TransitionLabel)) {

            final PlaceLabel fromL = (PlaceLabel) from;
            final TransitionLabel toL = (TransitionLabel) to;
            final PetriPlace fromO = (PetriPlace) fromObject;
            final PetriTransition toO = (PetriTransition) toObject;

            this.logic.connect(fromO, toO);
            this.arrows.put(fromL, toL, arrow);

        } else if ((from instanceof TransitionLabel)
            && (to instanceof PlaceLabel)) {

            final TransitionLabel fromL = (TransitionLabel) from;
            final PlaceLabel toL = (PlaceLabel) to;
            final PetriTransition fromO = (PetriTransition) fromObject;
            final PetriPlace toO = (PetriPlace) toObject;

            this.logic.connect(fromO, toO);
            this.arrows.put(toL, fromL, arrow);

        } else {
            return;
        }

        this.ui.addArrow(arrow);

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

        final PetriObject fromObject = this.findObject(from);
        final PetriObject toObject = this.findObject(to);

        Arrow arrow = null;

        if ((from instanceof TransitionLabel) && (to instanceof PlaceLabel)) {

            final TransitionLabel fromL = (TransitionLabel) from;
            final PlaceLabel toL = (PlaceLabel) to;
            final PetriTransition fromO = (PetriTransition) fromObject;
            final PetriPlace toO = (PetriPlace) toObject;

            arrow = this.arrows.remove(toL, fromL);

            this.logic.removeOutput(fromO, toO);

        } else if ((from instanceof PlaceLabel)
            && (to instanceof TransitionLabel)) {

            final PlaceLabel fromL = (PlaceLabel) from;
            final TransitionLabel toL = (TransitionLabel) to;
            final PetriPlace fromO = (PetriPlace) fromObject;
            final PetriTransition toO = (PetriTransition) toObject;

            arrow = this.arrows.remove(fromL, toL);

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

        final Set<Entry<PetriObject, AbstractLabel>> entries =
            this.objects.entrySet();

        for (final Entry<PetriObject, AbstractLabel> entry : entries) {

            final PetriObject object = entry.getKey();
            final AbstractLabel label = entry.getValue();

            if (object instanceof PetriTransition) {

                final PetriTransition transition = (PetriTransition) object;
                final TransitionLabel transLabel = (TransitionLabel) label;

                this.ui.markTransitionActive(transLabel,
                    this.logic.isActive(transition));

            }

        }

    }

    /**
     * Close the application.
     */
    public void exit() {

        this.ui.hideWindow();

    }

    /**
     * Get the UI master.
     * @return UI master
     */
    public PetriUi getUi() {
        return this.ui;
    }

    /**
     * Get the objects map.
     * @return Objects map
     */
    public BiMap<PetriObject, AbstractLabel> getObjects() {

        return this.objects;

    }

    /**
     * Get all arrows.
     * @return Arrow map
     */
    public TwoKeyMap<PlaceLabel, TransitionLabel, Arrow> getArrows() {

        return this.arrows;

    }

    @Override
    public void run() {

        this.ui.showWindow();

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
