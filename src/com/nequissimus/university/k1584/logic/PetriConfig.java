package com.nequissimus.university.k1584.logic;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load the properties file into this configuration object.<br />
 * This class has default values, in case the configuration file cannot be
 * loaded<br />
 * <br />
 * Default values:<br />
 * PlaceName = "" // Initial name for a place<br />
 * TransitionName = "" // Initial name for a transition<br />
 * CanvasWidth = 1000 // Width for the editing canvas<br />
 * CanvasHeight = 1000 // Height for the editing canvas<br />
 * WindowTitle = "Tim Steinbach - q7485417" // Window title<br />
 * WindowWidth = 500 // Window width<br />
 * WindowHeight = 400 // Window height<br />
 * WindowX = 100 // Initial X coordinate for window<br />
 * WindowY = 100 // Initial Y coordinate for window<br />
 * ApplicationName = "Petrinet" // Application name<br />
 * SidebarWidth = 120 // Sidebar width<br />
 * WindowMinHeight = 200 // Minimum window height<br />
 * WindowMinWidth = 300 // Minimum window width (Must be >SidebarWidth)<br />
 * PnmlEdgeIdPrefix = e // Prefix for edge ids in PNML markup<br />
 * NetName = "DefaultNet" // Default name for a new Petrinet<br />
 * FileExtension = "pnml" // File extension for nets<br />
 * MessageErrorSaveFile = "Error saving file" // Error message when saving file
 * fails<br />
 * @author Tim Steinbach
 */
public final class PetriConfig extends Properties {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -6320268661029188694L;

    /**
     * Arrow head size.
     */
    private static final int VALUE_ARROW_HEAD_SIZE = 10;

    /**
     * Stretching factor for arrow head.
     */
    private static final double VALUE_ARROW_HEAD_FACTOR = 0.6;

    /**
     * Font for an active transition.
     */
    private static final Font VALUE_ACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.BOLD, 12);

    /**
     * Font for an inactive transition.
     */
    private static final Font VALUE_INACTIVE_TRANSITION_FONT = new Font(
        Font.SANS_SERIF, Font.PLAIN, 12);

    /**
     * Font colour for an active transition.
     */
    private static final Color VALUE_ACTIVE_TRANSITION_COLOUR = new Color(
        0, 200, 0);

    /**
     * Font colour for an inactive transition.
     */
    private static final Color VALUE_INACTIVE_TRANSITION_COLOUR =
        new Color(0, 0, 0);

    /**
     * Value for scrollbar height.
     */
    private static final int VALUE_SCROLLBAR_HEIGHT = 41;

    /**
     * Value for window background.
     */
    private static final Color VALUE_WINDOW_BACKGROUND = Color.LIGHT_GRAY;

    /**
     * Value for file extension description.
     */
    private static final String VALUE_FILE_EXTENSION_DESCRIPTION =
        "PetriNet";

    /**
     * Key for default place name.
     */
    public static final String PLACE_NAME = "PlaceName";

    /**
     * Key for default transition name.
     */
    public static final String TRANSITION_NAME = "TransitionName";

    /**
     * Key for default canvas width.
     */
    public static final String CANVAS_WIDTH = "CanvasWidth";

    /**
     * Key for default canvas height.
     */
    public static final String CANVAS_HEIGHT = "CanvasHeight";

    /**
     * Key for window title.
     */
    public static final String WINDOW_TITLE = "WindowTitle";

    /**
     * Key for default window width.
     */
    public static final String WINDOW_WIDTH = "WindowWidth";

    /**
     * Key for default window height.
     */
    public static final String WINDOW_HEIGHT = "WindowHeight";

    /**
     * Key for window's default x-position.
     */
    public static final String WINDOW_X = "WindowX";

    /**
     * Key for window's default y-position.
     */
    public static final String WINDOW_Y = "WindowY";

    /**
     * Key for application name.
     */
    public static final String APPLICATION_NAME = "ApplicationName";

    /**
     * Key for sidebar width.
     */
    public static final String SIDEBAR_WIDTH = "SidebarWidth";

    /**
     * Key for minimum window height.
     */
    public static final String WINDOW_MIN_HEIGHT = "WindowMinHeight";

    /**
     * Key for minimum window width.
     */
    public static final String WINDOW_MIN_WIDTH = "WindowMinWidth";

    /**
     * Key for PNML edge id prefix.
     */
    public static final String PNML_EDGE_ID_PREFIX = "PnmlEdgeIdPrefix";

    /**
     * Key for Petri net name.
     */
    public static final String NET_NAME = "NetName";

    /**
     * Key for file extension.
     */
    public static final String FILE_EXTENSION = "FileExtension";

    /**
     * Key for error message when saving a file.
     */
    public static final String MSG_ERROR_SAVE_FILE = "MessageErrorSaveFile";

    /**
     * Configuration.
     */
    private static PetriConfig config = null;

    /**
     * Instantiate new configuration. Hide this constructor to enforce a
     * singleton.
     */
    private PetriConfig() {

        super();

        this.readProperties();

    }

    /**
     * Instead of using the constructor, this method is used to make this a
     * singleton. This is to make sure the properties file is only read once.
     * @return Configuration instance
     */
    public static PetriConfig getInstance() {

        if (config == null) {
            config = new PetriConfig();
        }

        return config;

    }

    /**
     * Fill the configuration object with default values.
     */
    private void getDefaults() {

        this.put(PLACE_NAME, "");
        this.put(TRANSITION_NAME, "");
        this.put(CANVAS_HEIGHT, 1000);
        this.put(CANVAS_WIDTH, 1000);
        this.put(WINDOW_TITLE, "Tim Steinbach - q7485417");
        this.put(WINDOW_WIDTH, 500);
        this.put(WINDOW_HEIGHT, 400);
        this.put(WINDOW_X, 100);
        this.put(WINDOW_Y, 100);
        this.put(APPLICATION_NAME, "Petrinet");
        this.put(SIDEBAR_WIDTH, 120);
        this.put(WINDOW_MIN_HEIGHT, 200);
        this.put(WINDOW_MIN_WIDTH, 300);
        this.put(PNML_EDGE_ID_PREFIX, "e");
        this.put(NET_NAME, "DefaultNet");
        this.put(FILE_EXTENSION, "pnml");
        this.put(MSG_ERROR_SAVE_FILE, "Error saving file");

    }

    /**
     * Read the properties file 'config.properties'. If reading that file fails,
     * a default configuration will be used
     */
    private void readProperties() {

        InputStream is = null;

        try {

            is = new FileInputStream("config.properties");
            this.load(is);

        } catch (final IOException e) {

            // If something went wrong with reading the properties file, the
            // default values
            // will still be in the needed in the configuration object
            this.getDefaults();

        } finally {

            if (null != is) {
                try {
                    is.close();
                } catch (final IOException e) {
                    System.out.println("Could not close config stream");
                }
            }

        }

    }

    /**
     * Return a property value as String.
     * @param key Key to look for
     * @return Value as String
     */
    public String get(final String key) {

        return String.valueOf(super.get(key));

    }

    /**
     * Get default name for a place.
     * @return Name
     */
    public String getPlaceName() {
        return this.get(PLACE_NAME);
    }

    /**
     * Get default name for a transition.
     * @return Name
     */
    public String getTransitionName() {
        return this.get(TRANSITION_NAME);
    }

    /**
     * Get default canvas width.
     * @return Width
     */
    public int getCanvasWidth() {
        return new Integer(this.get(CANVAS_WIDTH));
    }

    /**
     * Get default canvas height.
     * @return Canvas height
     */
    public int getCanvasHeight() {
        return new Integer(this.get(CANVAS_HEIGHT));
    }

    /**
     * Get window title.
     * @return Window title
     */
    public String getWindowTitle() {
        return this.get(WINDOW_TITLE);
    }

    /**
     * Get default window width.
     * @return Window width
     */
    public int getWindowWidth() {
        return new Integer(this.get(WINDOW_WIDTH));
    }

    /**
     * Get default window height.
     * @return Window height
     */
    public int getWindowHeight() {
        return new Integer(this.get(WINDOW_HEIGHT));
    }

    /**
     * Get default window x-position.
     * @return X-position
     */
    public int getWindowX() {
        return new Integer(this.get(WINDOW_X));
    }

    /**
     * Get default window y-position.
     * @return Y-position
     */
    public int getWindowY() {
        return new Integer(this.get(WINDOW_Y));
    }

    /**
     * Get application name.
     * @return Application name
     */
    public String getApplicationName() {
        return this.get(APPLICATION_NAME);
    }

    /**
     * Get sidebar width.
     * @return Sidebar width
     */
    public int getSidebarWidth() {
        return new Integer(this.get(SIDEBAR_WIDTH));
    }

    /**
     * Get minimum height for window.
     * @return Minimum height
     */
    public int getWindowMinHeight() {
        return new Integer(this.get(WINDOW_MIN_HEIGHT));
    }

    /**
     * Get minimum width for window.
     * @return Minimum width
     */
    public int getWindowMinWidth() {

        final int minWidth = new Integer(this.get(WINDOW_MIN_WIDTH));

        return (this.getSidebarWidth() > minWidth) ? this.getSidebarWidth()
            : minWidth;

    }

    /**
     * Get scroll bar height.
     * @return Scroll bar height
     */
    public int getScrollbarHeight() {
        return VALUE_SCROLLBAR_HEIGHT;
    }

    /**
     * Get prefix for edge ids in PNML.
     * @return Edge prefix
     */
    public String getEdgeIdPrefix() {
        return this.get(PNML_EDGE_ID_PREFIX);
    }

    /**
     * Get color for window background.
     * @return Color
     */
    public Color getWindowBackgroundColor() {
        return VALUE_WINDOW_BACKGROUND;
    }

    /**
     * Get default Petri net name.
     * @return Name
     */
    public String getNetName() {
        return this.get(NET_NAME);
    }

    /**
     * Get file extension.
     * @return File extension
     */
    public String getFileExtension() {
        return this.get(FILE_EXTENSION);
    }

    /**
     * Get value for file extension description.
     * @return File extension description
     */
    public String getFileExtensionDescription() {
        return VALUE_FILE_EXTENSION_DESCRIPTION;
    }

    /**
     * Get error message when saving a file.
     * @return Error message
     */
    public String getMsgErrorSaveFile() {
        return this.get(MSG_ERROR_SAVE_FILE);
    }

    /**
     * Get font for inactive transitions.
     * @return Font for inactive transitions.
     */
    public Font getInactiveTransitionFont() {
        return VALUE_INACTIVE_TRANSITION_FONT;
    }

    /**
     * Get font for active transitions.
     * @return Font for active transitions
     */
    public Font getActiveTransitionFont() {
        return VALUE_ACTIVE_TRANSITION_FONT;
    }

    /**
     * Get font colour for inactive transitions.
     * @return Font colour for inactive transitions
     */
    public Color getInactiveTransitionColour() {
        return VALUE_INACTIVE_TRANSITION_COLOUR;
    }

    /**
     * Get font colour for active transitions.
     * @return Font colour for active transitions
     */
    public Color getActiveTransitionColour() {
        return VALUE_ACTIVE_TRANSITION_COLOUR;
    }

    /**
     * Get arrow head size.
     * @return Arrow head size
     */
    public int getArrowHeadSize() {
        return VALUE_ARROW_HEAD_SIZE;
    }

    /**
     * Get stretching factor for arrow head.
     * @return Stretching factor
     */
    public double getArrowHeadFactor() {
        return VALUE_ARROW_HEAD_FACTOR;
    }

}
