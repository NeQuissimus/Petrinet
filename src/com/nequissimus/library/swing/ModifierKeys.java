package com.nequissimus.library.swing;

import java.awt.event.MouseEvent;

/**
 * Modifier key enumeration that can check an event for whether they keys are
 * pressed.
 * @author Tim Steinbach
 */
public enum ModifierKeys {

    /**
     * CTRL key.
     */
    CTRL,

    /**
     * Alt key.
     */
    ALT,

    /**
     * Shift key.
     */
    SHIFT;

    /**
     * Check whether the currently chosen key is pressed.
     * @param event Mouse event
     * @return Whether key was pressed when event occurred
     */
    public boolean isActive(final MouseEvent event) {

        switch (this) {
            case CTRL:
                return event.isControlDown();
            case ALT:
                return event.isAltDown();
            case SHIFT:
            default:
                return event.isShiftDown();
        }

    }

}
