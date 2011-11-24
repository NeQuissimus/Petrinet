package com.nequissimus.university.k1584.logic;

import java.awt.Color;
import java.awt.Font;

import com.nequissimus.library.swing.ModifierKeys;

/**
 * Constants used throughout the application.
 * @author Tim Steinbach
 */
public final class PetriConstants {

    /**
     * Allowed modifier keys for collecting multiple drag and drop objects.
     */
    public static final ModifierKeys[] ALLOWED_MODIFIER_KEYS = {
        ModifierKeys.CTRL, ModifierKeys.SHIFT
    };

    /**
     * Color for highlighted icons.
     */
    public static final Color COLOR_HIGHLIGHT = new Color(255, 255, 0, 60);

    /**
     * Color for non-highlighted icons.
     */
    public static final Color COLOR_NORMAL = new Color(0, 0, 0, 0);

    /**
     * Place icon image.
     */
    public static final String IMG_PLACE_ICON = "/img/circle.png";

    /**
     * Transition icon image.
     */
    public static final String IMG_TRANSITION_ICON = "/img/square.png";

    /**
     * Question mark icon.
     */
    public static final String IMG_QUESTION_ICON = "/img/question.png";

    /**
     * Array of all images required by the UI.
     */
    public static final String[] IMAGES = {
        PetriConstants.IMG_PLACE_ICON, PetriConstants.IMG_QUESTION_ICON,
        PetriConstants.IMG_TRANSITION_ICON
    };

    /**
     * Arrow head size.
     */
    public static final int ARROW_HEAD_SIZE = 10;

    /**
     * Stretching factor for arrow head.
     */
    public static final double ARROW_HEAD_FACTOR = 0.6;

    /**
     * Font for an active transition.
     */
    public static final Font ACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.BOLD, 12);

    /**
     * Font for an inactive transition.
     */
    public static final Font INACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.PLAIN, 12);

    /**
     * Font colour for an active transition.
     */
    public static final Color ACTIVE_TRANSITION_COLOUR = new Color(0, 200,
        0);

    /**
     * Font colour for an inactive transition.
     */
    public static final Color INACTIVE_TRANSITION_COLOUR = new Color(0, 0,
        0);

    /**
     * Value for scrollbar height.
     */
    public static final int SCROLLBAR_HEIGHT = 41;

    /**
     * Value for window background.
     */
    public static final Color WINDOW_BACKGROUND = Color.LIGHT_GRAY;

    /**
     * Value for file extension description.
     */
    public static final String FILE_EXTENSION_DESCRIPTION = "PetriNet";

    /**
     * Hide constructor.
     */
    private PetriConstants() {
    }

}
