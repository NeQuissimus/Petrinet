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

        final PetriController controller = PetriController.getInstance();

        controller.unhighlightLabels(SelectListener.LABELS);

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

        final PetriController controller = PetriController.getInstance();

        controller.highlightLabel(label);

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
