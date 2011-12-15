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
     * Font colour for an active transition.
     */
    public static final Color ACTIVE_TRANSITION_COLOUR = new Color(0, 200,
        0);

    /**
     * Font for an active transition.
     */
    public static final Font ACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.BOLD, 12);

    /**
     * Allowed modifier keys for collecting multiple drag and drop objects.
     */
    public static final ModifierKeys[] ALLOWED_MODIFIER_KEYS = {
        ModifierKeys.CTRL, ModifierKeys.SHIFT
    };

    /**
     * Stretching factor for arrow head.
     */
    public static final double ARROW_HEAD_FACTOR = 0.6;

    /**
     * Arrow head size.
     */
    public static final int ARROW_HEAD_SIZE = 10;

    /**
     * Color for highlighted icons.
     */
    public static final Color COLOR_HIGHLIGHT = new Color(255, 255, 0, 60);

    /**
     * Color for non-highlighted icons.
     */
    public static final Color COLOR_NORMAL = new Color(0, 0, 0, 0);

    /**
     * Value for file extension description.
     */
    public static final String FILE_EXTENSION_DESCRIPTION = "PetriNet";

    /**
     * Array of all images required by the UI.
     */
    public static final String[] IMAGES = {
        PetriConstants.IMG_PLACE_ICON, PetriConstants.IMG_QUESTION_ICON,
        PetriConstants.IMG_TRANSITION_ICON
    };

    /**
     * Application icon image.
     */
    public static final String IMG_APPLICATION_ICON =
        "/img/sphere_icon.png";

    /**
     * Place icon image.
     */
    public static final String IMG_PLACE_ICON = "/img/circle.png";

    /**
     * Question mark icon.
     */
    public static final String IMG_QUESTION_ICON = "/img/question.png";

    /**
     * Transition icon image.
     */
    public static final String IMG_TRANSITION_ICON = "/img/square.png";

    /**
     * Font colour for an inactive transition.
     */
    public static final Color INACTIVE_TRANSITION_COLOUR = new Color(0, 0,
        0);

    /**
     * Font for an inactive transition.
     */
    public static final Font INACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.PLAIN, 12);

    /**
     * Value for scrollbar height.
     */
    public static final int SCROLLBAR_HEIGHT = 41;

    /**
     * Value for window background.
     */
    public static final Color WINDOW_BACKGROUND = Color.LIGHT_GRAY;

    /**
     * Hide constructor.
     */
    private PetriConstants() {
    }

}
