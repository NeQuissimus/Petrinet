// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

package com.nequissimus.university.k1584;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.print.attribute.standard.Severity;

import com.nequissimus.library.data.BiMap;
import com.nequissimus.library.data.TwoKeyMap;
import com.nequissimus.library.util.ParamUtil;
import com.nequissimus.university.k1584.logic.PetriMarking;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;
import com.nequissimus.university.k1584.ui.LogicToUi;
import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.PetriUi;
import com.nequissimus.university.k1584.ui.PetriUiImpl;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.elements.Window;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.enums.TokenSize;

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
     * Currently active logical net.
     */
    private PetriNet logic;

    /**
     * Bidirectional map for logical objects and UI components.
     */
    private final BiMap<PetriObject, AbstractLabel> objects =
        new BiMap<PetriObject, AbstractLabel>();

    /**
     * Logic master.
     */
    private PetriSnapshots snapshots;

    /**
     * User interface master.
     */
    private final PetriUi ui;

    /**
     * Instantiate the controller.
     */
    private PetriController() {

        this.ui = new PetriUiImpl();

        this.resetController();

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

        this.redrawCanvas();

    }

    /**
     * Add a new transition to the logical net and the UI.
     */
    public final void addTransition() {

        final TransitionLabel label = this.ui.addTransition();
        final PetriTransition transition = this.logic.addTransition();

        this.logic.setSize(transition, label.getSize());

        this.objects.put(transition, label);

        this.redrawCanvas();

    }

    /**
     * Connect arrow to this label. If this is the first label chosen, save it
     * for further use. If this is the second label chosen, create a connection
     * with the first one selected.
     * @param label Selected label
     */
    public void arrowConnect(@Nullable final AbstractLabel label) {

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
     * Disconnect this label from the one chosen next. If a label was chosen
     * before, it will be used to disconnect from the current one.
     * @param label Label to disconnect
     */
    public void arrowDisconnect(@Nullable final AbstractLabel label) {

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
     * Create a new marking with the given name.
     * @param name Name
     */
    public final void createMarking(@Nonnull final String name) {

        ParamUtil.checkNotNull(name);

        this.logic.createNewMarking(name);

    }

    /**
     * Create a new snapshot and display it on the UI.
     * @param newName New snapshot name
     */
    public void createSnapshot(@Nonnull final String newName) {

        ParamUtil.checkNotNull(newName);

        final PetriNet net = this.logic.clone();
        net.setName(newName);

        this.snapshots.add(net);

        this.selectSnapshot(net);

    }

    /**
     * Decrease the number of tokens for a place.
     * @param label Place
     */
    public void decreaseTokens(@Nonnull final PlaceLabel label) {

        ParamUtil.checkNotNull(label);

        final PetriPlace place = (PetriPlace) this.findObject(label);

        this.ui.decreaseTokens(label);
        this.logic.decreaseTokens(place);

        this.redrawCanvas();

    }

    /**
     * Decrease the size of all tokens.<br />
     * This action is only executed if the tokens have not already reached the
     * smallest defined size.
     */
    public void decreaseTokenSize() {

        final TokenSize currentSize = this.ui.getTokenSize();

        final List<TokenSize> sizes = Arrays.asList(TokenSize.values());

        final int sizeIndex = sizes.indexOf(currentSize);
        final int minIndex = 0;

        if (sizeIndex > minIndex) {

            final TokenSize newSize = sizes.get(sizeIndex - 1);

            this.ui.setTokenSize(newSize);

            this.updateTokens();

        }

        this.redrawCanvas();

    }

    /**
     * Delete the selected marking.<br />
     * If there is no marking left, a new null marking will be created.
     * @param marking Marking to be removed
     */
    public final void deleteMarking(@Nonnull final PetriMarking marking) {

        ParamUtil.checkNotNull(marking);

        this.logic.deleteMarking(marking);

        if (this.logic.getMarkings().isEmpty()) {
            this.createNullMarking();
        }

        this.switchMarking(this.getActiveMarking());

    }

    /**
     * Delete a snapshot. An empty default net will be created if the last
     * snapshot has been deleted.
     * @param snapshot Snapshot to be deleted.
     */
    public void deleteSnapshot(@Nonnull final PetriNet snapshot) {

        ParamUtil.checkNotNull(snapshot);

        this.snapshots.delete(snapshot);

        if (snapshot == this.logic) {

            this.selectSnapshot(this.snapshots.getCurrent());

        }

    }

    /**
     * Close the application.
     */
    public void exit() {

        this.ui.hideWindow();

    }

    /**
     * Get the currently active logical net.
     * @return Logical net
     */
    public PetriNet getActiveLogic() {
        return this.logic;
    }

    /**
     * Get the currently active marking.
     * @return Active marking
     */
    public final PetriMarking getActiveMarking() {

        return this.logic.getActiveMarking();

    }

    /**
     * Get all arrows.
     * @return Arrow map
     */
    public TwoKeyMap<PlaceLabel, TransitionLabel, Arrow> getArrows() {

        return this.arrows;

    }

    /**
     * Get the current icon size for Petri net components.
     * @return Icon size
     */
    public final IconSize getIconSize() {

        return this.ui.getIconSize();

    }

    /**
     * Get all markings for the current net.
     * @return All markings
     */
    public final Set<PetriMarking> getMarkings() {

        return this.logic.getMarkings();

    }

    /**
     * Get the objects map.
     * @return Objects map
     */
    public BiMap<PetriObject, AbstractLabel> getObjects() {

        return this.objects;

    }

    /**
     * Get all snapshots.
     * @return Snapshot list, empty list if no snapshots available
     */
    public List<PetriNet> getSnapshots() {

        final List<PetriNet> result = new ArrayList<PetriNet>();
        final List<PetriNet> nets = this.snapshots.getNets();

        if (null != nets) {
            result.addAll(nets);
        }

        return result;

    }

    /**
     * Get the globally set token size.
     * @return Token size
     */
    public TokenSize getTokenSize() {

        return this.ui.getTokenSize();

    }

    /**
     * Get the UI master.
     * @return UI master
     */
    public PetriUi getUi() {
        return this.ui;
    }

    /**
     * Get the UI window.
     * @return UI window
     */
    public final Window getWindow() {
        return this.ui.getWindow();
    }

    /**
     * Highlight a label by setting a coloured background.
     * @param label Label to highlight
     */
    public final void highlightLabel(@Nonnull final AbstractLabel label) {

        ParamUtil.checkNotNull(label);

        this.ui.highlightLabel(label);
        this.redrawCanvas();

    }

    /**
     * Increase the number of tokens for a place.
     * @param label Place
     */
    public void increaseTokens(@Nonnull final PlaceLabel label) {

        ParamUtil.checkNotNull(label);

        final PetriPlace place = (PetriPlace) this.findObject(label);

        this.ui.increaseTokens(label);
        this.logic.increaseTokens(place);

        this.redrawCanvas();

    }

    /**
     * Increase the size of all tokens.<br />
     * This action is only executed if the tokens have not already reached the
     * maximum defined size.
     */
    public void increaseTokenSize() {

        final TokenSize currentSize = this.ui.getTokenSize();

        final List<TokenSize> sizes = Arrays.asList(TokenSize.values());

        final int sizeIndex = sizes.indexOf(currentSize);
        final int maxIndex = sizes.size() - 1;

        if (sizeIndex < maxIndex) {

            this.ui.setTokenSize(sizes.get(sizeIndex + 1));
            this.updateTokens();

        }

        this.redrawCanvas();

    }

    /**
     * Load a given file and draw it onto the UI.
     * @param file File to load
     * @throws PnmlException Error loading PNML file
     */
    public void load(@Nonnull final File file) throws PnmlException {

        ParamUtil.checkNotNull(file);

        final PetriSnapshots snapshots = PetriMarkup.loadPnmlFile(file);

        if (null != snapshots) {
            this.snapshots = snapshots;
        }

        this.logic = this.snapshots.getCurrent();

        // Draw the logical net onto the canvas
        LogicToUi.convert(this.logic);

        this.redrawCanvas();

    }

    /**
     * Move a label component to a new position.
     * @param label Label component to move
     * @param location New location
     */
    public final void moveLabel(@Nonnull final AbstractLabel label,
        @Nonnull final Point location) {

        ParamUtil.checkNotNull(label);
        ParamUtil.checkNotNull(location);

        final PetriObject object = this.findObject(label);

        this.ui.moveLabel(label, location);
        this.logic.setPosition(object, location);

    }

    /**
     * Make a transition occur.
     * @param label Transition label
     */
    public final void occur(@Nonnull final TransitionLabel label) {

        ParamUtil.checkNotNull(label);

        final PetriTransition transition =
            (PetriTransition) this.findObject(label);

        final Set<PetriPlace> changed = this.logic.occur(transition);

        final Map<PlaceLabel, Integer> changeLabels =
            new HashMap<PlaceLabel, Integer>(changed.size());

        for (final PetriPlace place : changed) {

            final Integer newValue = this.logic.getTokens(place);
            final PlaceLabel placeLabel =
                (PlaceLabel) this.findLabel(place);

            changeLabels.put(placeLabel, newValue);

        }

        this.ui.updateTokens(changeLabels);
        this.redrawCanvas();

    }

    /**
     * Redraw the entire UI and its components.
     */
    public final void redrawCanvas() {
        this.checkForActive();
        this.ui.redrawCanvas();
    }

    /**
     * Remove a Petri object from the logical net and the UI.
     * @param label Label object to remove
     */
    public final void removeObject(@Nonnull final AbstractLabel label) {

        ParamUtil.checkNotNull(label);

        final PetriObject object = this.findObject(label);

        if (label instanceof TransitionLabel) {
            this.removeAllArrows((TransitionLabel) label);
        }

        this.ui.removeLabel(label);
        this.logic.remove(object);

        this.redrawCanvas();

    }

    /**
     * Rename a label and its corresponding logical object.<br />
     * If the new name is empty or null, do not do anything to preserve the old
     * name.
     * @param label Label component
     * @param name New name
     */
    public void renameLabel(@Nonnull final AbstractLabel label,
        @Nonnull final String name) {

        ParamUtil.checkNotNull(label);
        ParamUtil.checkNotNull(name);

        if (name.length() > 0) {

            final PetriObject object = this.findObject(label);

            this.ui.renameLabel(label, name);
            this.logic.rename(object, name);

        }

    }

    /**
     * Rename a marking.
     * @param marking Marking to be renamed
     * @param name New name
     */
    public final void renameMarking(@Nonnull final PetriMarking marking,
        @Nonnull final String name) {

        ParamUtil.checkNotNull(marking);
        ParamUtil.checkNotNull(name);

        this.logic.renameMarking(marking, name);

    }

    /**
     * Report a message to the user.
     * @param severity Message severity
     * @param message Message text
     */
    public final void reportMessage(@Nonnull final Severity severity,
        @Nonnull final String message) {

        ParamUtil.checkNotNull(severity);
        ParamUtil.checkNotNull(message);

        this.ui.reportMessage(severity, message);

    }

    /**
     * Reset the application.
     */
    public void resetApplication() {

        this.resetController();

        LogicToUi.convert(this.logic);

        this.redrawCanvas();

    }

    /**
     * Resize the canvas and all arrow canvases.
     * @param size New size
     */
    public final void resizeCanvas(@Nonnull final Dimension size) {

        ParamUtil.checkNotNull(size);

        this.ui.resizeCanvas(size);
        this.ui.resizeArrowCanvas(size);

    }

    /**
     * Resize canvas by a certain amount of pixels.
     * @param difference Pixels to be added to size
     */
    public void resizeCanvas(final int difference) {

        final boolean isResized = this.ui.resizeCanvas(difference);

        final Dimension canvasSize = this.ui.getCanvasSize();

        if (isResized) {

            this.ui.resizeArrowCanvas(canvasSize);
            this.redrawCanvas();

        }

    }

    /**
     * Resize the visible editor window.
     * @param size New size
     */
    public void resizeEditorWindow(@Nonnull final Dimension size) {

        ParamUtil.checkNotNull(size);

        final Dimension canvasSize = this.ui.getCanvasSize();

        this.ui.resizeEditorWindow(size);

        if ((canvasSize.width < size.width)
            || (canvasSize.height < size.height)) {
            this.resizeCanvas(size);
        }

    }

    @Override
    public void run() {

        this.ui.showWindow();

    }

    /**
     * Save the current Petri net to a given file.
     * @param file File to save to
     * @throws PnmlException Error turning nets into PNML
     */
    public void save(@Nonnull final File file) throws PnmlException {

        ParamUtil.checkNotNull(file);

        PetriMarkup.savePnmlFile(file, this.snapshots);

    }

    /**
     * Select a snapshot, draw it onto the UI.
     * @param net Petri net
     */
    public void selectSnapshot(@Nonnull final PetriNet net) {

        ParamUtil.checkNotNull(net);

        this.snapshots.setCurrent(net);
        this.logic = net;

        LogicToUi.convert(net);

        this.redrawCanvas();

    }

    /**
     * Set the new size for all icons representing Petri net components.
     * @param size New size
     */
    public final void setIconSize(@Nonnull final IconSize size) {

        ParamUtil.checkNotNull(size);

        this.ui.setIconSize(size);
        this.logic.setSize(size.getSize());

        this.redrawCanvas();

    }

    /**
     * Switch to a different marking.
     * @param marking Marking
     */
    public final void switchMarking(@Nonnull final PetriMarking marking) {

        this.logic.switchMarking(marking);

        final Set<PetriPlace> places = this.logic.getPlaces();

        for (final PetriPlace petriPlace : places) {

            final PlaceLabel label =
                (PlaceLabel) this.findLabel(petriPlace);

            final int tokens = this.logic.getTokens(petriPlace);

            label.setTokens(tokens);

        }

        this.redrawCanvas();

    }

    /**
     * Remove the highlighted background from a set of labels.
     * @param labels Labels to remove background from
     */
    public final void unhighlightLabels(
        @Nonnull final Set<AbstractLabel> labels) {

        ParamUtil.checkNotNull(labels);

        for (final AbstractLabel abstractLabel : labels) {

            this.ui.unhighlightLabel(abstractLabel);

        }

        this.redrawCanvas();

    }

    /**
     * Connect two labels with an arrow and make a logical connection.
     * @param from Label to connect from
     * @param to Label to connect to
     */
    private void arrowConnect(@Nonnull final AbstractLabel from,
        @Nonnull final AbstractLabel to) {

        ParamUtil.checkNotNull(from);
        ParamUtil.checkNotNull(to);

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

    }

    /**
     * Disconnect two labels.
     * @param from Disconnect from this label
     * @param to Disconnect from this target label
     */
    private void arrowDisconnect(@Nonnull final AbstractLabel from,
        @Nonnull final AbstractLabel to) {

        ParamUtil.checkNotNull(from);
        ParamUtil.checkNotNull(to);

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

    }

    /**
     * Create a new null marking.
     */
    private void createNullMarking() {

        final MessagePool messagePool = MessagePool.getInstance();

        this.createMarking(messagePool.getNullMarking());

    }

    /**
     * Get a UI component for a Petri object.
     * @param object Logical object
     * @return Label component
     */
    private AbstractLabel findLabel(@Nonnull final PetriObject object) {
        return this.objects.get(object);
    }

    /**
     * Get a Petri object represented by a given UI component.
     * @param label Label component
     * @return Logical object
     */
    private PetriObject findObject(final AbstractLabel label) {

        return this.objects.getKey(label);

    }

    /**
     * Remove all arrows attached to a transition.
     * @param label Transition
     */
    private void removeAllArrows(final TransitionLabel label) {

        final PetriTransition transition =
            (PetriTransition) this.findObject(label);

        final Set<PetriPlace> inPlaces =
            this.logic.getInputEdges(transition);

        for (final PetriPlace place : inPlaces) {

            final PlaceLabel placeLabel =
                (PlaceLabel) this.findLabel(place);
            this.arrowDisconnect(placeLabel, label);

        }

        final Set<PetriPlace> ourPlaces =
            this.logic.getOutputEdges(transition);

        for (final PetriPlace place : ourPlaces) {

            final PlaceLabel placeLabel =
                (PlaceLabel) this.findLabel(place);
            this.arrowDisconnect(label, placeLabel);

        }

    }

    /**
     * Reset the controller.
     */
    private void resetController() {

        this.snapshots = new PetriSnapshots();

        this.logic = this.snapshots.getCurrent();

        this.createNullMarking();

    }

    /**
     * Update the token displayed for each place.
     */
    private void updateTokens() {

        final Set<PetriPlace> places = this.logic.getPlaces();

        for (final PetriPlace petriPlace : places) {

            final PlaceLabel label =
                (PlaceLabel) this.findLabel(petriPlace);

            label.setTokens(label.getTokens());

        }

    }

}
