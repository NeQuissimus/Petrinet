/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.nequissimus.university.k1584.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.Set;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriConstants;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.Canvas;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;
import com.nequissimus.university.k1584.ui.elements.TransitionLabel;
import com.nequissimus.university.k1584.ui.elements.Window;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Main class that holds all information and provides all necessary methods for
 * working with the Petri net UI.
 * @author Tim Steinbach
 */
public final class PetriUiImpl implements PetriUi {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Graphical user interface.
     */
    private Window window;

    /**
     * Main canvas.
     */
    private Canvas canvas;

    /**
     * Editor canvas panel.
     */
    private JPanel canvasPanel;

    /**
     * Size for all icons.
     */
    private IconSize iconSize = IconSize.LARGE;

    /**
     * All arrows on the canvas. Hold them in this set for convenience.
     */
    private final Set<Arrow> arrows = new HashSet<Arrow>();

    /**
     * Create a new UI Implementation object.<br />
     * The constructor checks for the presence of all images needed to display
     * UI elements.
     */
    public PetriUiImpl() {

        this.checkForRequiredImages();

    }

    @Override
    public void addArrow(final Arrow arrow) {

        arrow.setBounds(this.canvas.getBounds());
        this.canvas.getCanvas().add(arrow);
        arrow.repaint();

        this.arrows.add(arrow);

    }

    @Override
    public PlaceLabel addPlace() {

        return this.addPlace(PetriUiImpl.CONFIG.getPlaceName());

    }

    @Override
    public PlaceLabel addPlace(final String name) {

        final PlaceLabel label = new PlaceLabel(name);
        label.setLocation(0, 0);
        this.canvas.add(label);

        return label;

    }

    @Override
    public TransitionLabel addTransition() {

        return this.addTransition(PetriUiImpl.CONFIG.getTransitionName());

    }

    @Override
    public TransitionLabel addTransition(final String name) {

        final TransitionLabel label = new TransitionLabel(name);
        label.setLocation(0, 0);
        this.canvas.add(label);

        return label;

    }

    @Override
    public Dimension calculateCanvasSize(final Dimension minimum) {

        final int larger =
            (minimum.height > minimum.width) ? minimum.height
                : minimum.width;

        final int newSize = ((larger / 500) + 1) * 500;

        return new Dimension(newSize, newSize);

    }

    @Override
    public void clean() {

        this.canvasPanel.removeAll();

    }

    @Override
    public void decreaseMarkings(final PlaceLabel label) {

        final int markings = label.getMarkings() - 1;
        if (markings >= 0) {
            label.setMarkings(markings);
        }

    }

    @Override
    public Canvas getCanvas() {

        return this.canvas;

    }

    @Override
    public Dimension getCanvasSize() {

        return this.canvasPanel.getSize();

    }

    @Override
    public IconSize getIconSize() {

        return this.iconSize;

    }

    @Override
    public Dimension getMinCanvasSize() {

        final Component[] components = this.canvasPanel.getComponents();

        int minX = 0;
        int minY = 0;

        for (final Component component : components) {

            final int compX = component.getX() + component.getWidth();
            final int compY = component.getY() + component.getHeight();

            if (minX < compX) {
                minX = compX;
            }

            if (minY < compY) {
                minY = compY;
            }

        }

        return new Dimension(minX, minY);

    }

    @Override
    public Window getWindow() {

        if (null == this.window) {

            this.window = new Window();
            this.canvas = this.window.getCanvas();
            this.canvasPanel = this.canvas.getCanvas();

        }

        return this.window;

    }

    @Override
    public void hideWindow() {

        this.window.dispatchEvent(new WindowEvent(this.window,
            WindowEvent.WINDOW_CLOSING));

    }

    @Override
    public void highlightLabel(final AbstractLabel label) {

        label.setBackground(PetriConstants.COLOR_HIGHLIGHT);

    }

    @Override
    public void increaseMarkings(final PlaceLabel label) {

        final int markings = label.getMarkings();
        if (markings < Integer.MAX_VALUE) {
            label.setMarkings(markings + 1);
        }

    }

    @Override
    public void markTransitionActive(final TransitionLabel label,
        final boolean active) {

        if (active) {

            label.setForeground(PetriConstants.ACTIVE_TRANSITION_COLOUR);
            label.setFont(PetriConstants.ACTIVE_TRANSITION_FONT);

        } else {

            label.setForeground(PetriConstants.INACTIVE_TRANSITION_COLOUR);
            label.setFont(PetriConstants.INACTIVE_TRANSITION_FONT);

        }

    }

    @Override
    public void moveLabel(final AbstractLabel label, final Point location) {

        label.setLocation(location);

    }

    @Override
    public void redrawCanvas() {
        this.canvasPanel.revalidate();
        this.canvasPanel.repaint();
        this.canvas.revalidate();
        this.canvas.repaint();
    }

    @Override
    public void removeArrow(final Arrow arrow) {

        this.arrows.remove(arrow);
        this.canvasPanel.remove(arrow);

    }

    @Override
    public void removeLabel(final AbstractLabel label) {

        this.canvasPanel.remove(label);

    }

    @Override
    public void renameLabel(final AbstractLabel label, final String name) {

        label.setText(name);

    }

    @Override
    public void
        reportMessage(final Severity severity, final String message) {

        int messageType = JOptionPane.INFORMATION_MESSAGE;

        if (severity.equals(Severity.ERROR)) {
            messageType = JOptionPane.ERROR_MESSAGE;
        } else if (severity.equals(Severity.WARNING)) {
            messageType = JOptionPane.WARNING_MESSAGE;
        }

        JOptionPane.showMessageDialog(this.window, message, null,
            messageType);

    }

    @Override
    public void resizeArrowCanvas(final Dimension size) {

        for (final Arrow arrow : this.arrows) {

            arrow.setSize(size);

        }

    }

    @Override
    public boolean resizeCanvas(final Dimension size) {

        boolean result = false;

        final Dimension minimumSize = this.getMinCanvasSize();

        if ((size.height >= minimumSize.height)
            && (size.width >= minimumSize.width)) {

            this.canvasPanel.setSize(size);
            this.canvasPanel.setPreferredSize(size);
            result = true;

        }

        return result;

    }

    @Override
    public boolean resizeCanvas(final int difference) {

        final Dimension size = this.canvasPanel.getSize();
        size.setSize(size.width + difference, size.height + difference);

        return this.resizeCanvas(size);

    }

    @Override
    public void resizeEditorWindow(final Dimension size) {

        this.canvas.setSize(size);

    }

    @Override
    public void setIconSize(final IconSize size) {

        if (size != this.iconSize) {

            this.iconSize = size;

            final Set<AbstractLabel> labels = this.canvas.getLabels();

            for (final AbstractLabel label : labels) {
                label.resizeIcon(size);
            }

        }

    }

    @Override
    public void showWindow() {

        this.getWindow().validate();
        this.getWindow().repaint();
        this.getWindow().setVisible(true);

    }

    @Override
    public void unhighlightLabel(final AbstractLabel label) {

        label.setBackground(PetriConstants.COLOR_NORMAL);

    }

    @Override
    public void updateMarkings(final Map<PlaceLabel, Integer> places) {

        final Set<Entry<PlaceLabel, Integer>> entrySet = places.entrySet();

        for (final Entry<PlaceLabel, Integer> entry : entrySet) {

            final PlaceLabel label = entry.getKey();
            final Integer value = entry.getValue();

            label.setMarkings(value);

        }

    }

    /**
     * Check if all required images can be found.
     */
    private void checkForRequiredImages() {

        final List<String> list = Arrays.asList(PetriConstants.IMAGES);
        for (final String string : list) {
            final URL location = this.getClass().getResource(string);
            if (null == location) {
                throw new MissingResourceException(string + " is missing",
                    this.getClass().getName(), string);
            }
        }

    }

}
