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
package com.nequissimus.university.k1584.ui.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import com.nequissimus.library.swing.ModifierKeys;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Mouse listener that allows the user to select multiple labels by holding down
 * any of the defined connect keys.<br />
 * {@link com.nequissimus.university.k1584.logic.PetriConfig}
 * @author Tim Steinbach
 */
public class SelectListener implements MouseListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Collection for all labels selected.
     */
    private static final Set<AbstractLabel> LABELS =
        new HashSet<AbstractLabel>();

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Invoking label.
     */
    private final AbstractLabel label;

    /**
     * Create a new listener for a given label.
     * @param invoker Invoking label
     */
    public SelectListener(final AbstractLabel invoker) {

        this.label = invoker;

    }

    /**
     * Deselect all labels.
     */
    public static final void deselectLabels() {

        SelectListener.CONTROLLER.unhighlightLabels(SelectListener.LABELS);

        SelectListener.LABELS.clear();

    }

    /**
     * Get the set of currently selected labels.
     * @return Set of labels
     */
    public static final Set<AbstractLabel> getSelectedLabels() {

        return new HashSet<AbstractLabel>(SelectListener.LABELS);

    }

    /**
     * Select a given label and highlight it.
     * @param label Label to highlight
     */
    public static final void selectLabel(final AbstractLabel label) {

        SelectListener.CONTROLLER.highlightLabel(label);

        SelectListener.LABELS.add(label);

    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public final void mousePressed(final MouseEvent e) {

        if (this.isConnectKeyDown(e)) {

            SelectListener.selectLabel(this.label);

        } else if (!e.isPopupTrigger()) {

            SelectListener.deselectLabels();

        }

    }

    @Override
    public final void mouseReleased(final MouseEvent e) {
    }

    /**
     * Check whether at least one of the allowed modifier keys is pressed.
     * @param e Mouse event to check
     * @return True if at least one key was pressed when the event occurred
     */
    private boolean isConnectKeyDown(final MouseEvent e) {

        final ModifierKeys[] allowed =
            SelectListener.CONFIG.getAllowedModifierKeys();

        for (final ModifierKeys modifierKeys : allowed) {

            if (modifierKeys.isActive(e)) {
                return true;
            }

        }

        return false;

    }

}
