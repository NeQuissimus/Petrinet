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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Load the properties file into this configuration object.<br />
 * This class has default values, in case the configuration file cannot be
 * loaded<br />
 * <br />
 * Default values:<br />
 * PlaceName = "Place" // Initial name for a place<br />
 * TransitionName = "Transition" // Initial name for a transition<br />
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
     * Key for application name.
     */
    static final String APPLICATION_NAME = "ApplicationName";

    /**
     * Key for default canvas height.
     */
    static final String CANVAS_HEIGHT = "CanvasHeight";

    /**
     * Key for default canvas width.
     */
    static final String CANVAS_WIDTH = "CanvasWidth";

    /**
     * Key for file extension.
     */
    static final String FILE_EXTENSION = "FileExtension";

    /**
     * Key for Petri net name.
     */
    static final String NET_NAME = "NetName";

    /**
     * Key for default place name.
     */
    static final String PLACE_NAME = "PlaceName";

    /**
     * Key for PNML edge id prefix.
     */
    static final String PNML_EDGE_ID_PREFIX = "PnmlEdgeIdPrefix";

    /**
     * Key for sidebar width.
     */
    static final String SIDEBAR_WIDTH = "SidebarWidth";

    /**
     * Key for default transition name.
     */
    static final String TRANSITION_NAME = "TransitionName";

    /**
     * Key for default window height.
     */
    static final String WINDOW_HEIGHT = "WindowHeight";

    /**
     * Key for minimum window height.
     */
    static final String WINDOW_MIN_HEIGHT = "WindowMinHeight";

    /**
     * Key for minimum window width.
     */
    static final String WINDOW_MIN_WIDTH = "WindowMinWidth";

    /**
     * Key for window title.
     */
    static final String WINDOW_TITLE = "WindowTitle";

    /**
     * Key for default window width.
     */
    static final String WINDOW_WIDTH = "WindowWidth";

    /**
     * Key for window's default x-position.
     */
    static final String WINDOW_X = "WindowX";

    /**
     * Key for window's default y-position.
     */
    static final String WINDOW_Y = "WindowY";

    /**
     * Instantiate new configuration. Hide this constructor to enforce a
     * singleton.
     */
    public PetriConfig() {

        super();

        this.readProperties();

    }

    /**
     * Constructor for testing.
     * @param loadFile Whether to load the properties file
     */
    PetriConfig(final File loadFile) {

        super();

        if (null != loadFile) {
            try {
                this.readProperties(loadFile);
            } catch (final IOException e) {
                this.getDefaults();
            }
        }

    }

    /**
     * Get the application icon.
     * @return Image path
     */
    public URL getApplicationIcon() {
        return this.getClass().getResource(PetriConstants.IMG_APPLICATION_ICON);
    }

    /**
     * Get application name.
     * @return Application name
     */
    public String getApplicationName() {
        return this.get(PetriConfig.APPLICATION_NAME);
    }

    /**
     * Get default canvas height.
     * @return Canvas height
     */
    public int getCanvasHeight() {
        return new Integer(this.get(PetriConfig.CANVAS_HEIGHT));
    }

    /**
     * Get default canvas width.
     * @return Width
     */
    public int getCanvasWidth() {
        return new Integer(this.get(PetriConfig.CANVAS_WIDTH));
    }

    /**
     * Get prefix for edge ids in PNML.
     * @return Edge prefix
     */
    public String getEdgeIdPrefix() {
        return this.get(PetriConfig.PNML_EDGE_ID_PREFIX);
    }

    /**
     * Get file extension.
     * @return File extension
     */
    public String getFileExtension() {
        return this.get(PetriConfig.FILE_EXTENSION);
    }

    /**
     * Get the Petri place icon image.
     * @return Image path
     */
    public URL getImagePlace() {
        return this.getClass().getResource(PetriConstants.IMG_PLACE_ICON);
    }

    /**
     * Get the question mark image.
     * @return Image path
     */
    public URL getImageQuestion() {
        return this.getClass()
            .getResource(PetriConstants.IMG_QUESTION_ICON);
    }

    /**
     * Get the Petri transition image.
     * @return Image path
     */
    public URL getImageTransition() {
        return this.getClass().getResource(
            PetriConstants.IMG_TRANSITION_ICON);
    }

    /**
     * Get default Petri net name.
     * @return Name
     */
    public String getNetName() {
        return this.get(PetriConfig.NET_NAME);
    }

    /**
     * Get default name for a place.
     * @return Name
     */
    public String getPlaceName() {
        return this.get(PetriConfig.PLACE_NAME);
    }

    /**
     * Get sidebar width.
     * @return Sidebar width
     */
    public int getSidebarWidth() {
        return new Integer(this.get(PetriConfig.SIDEBAR_WIDTH));
    }

    /**
     * Get default name for a transition.
     * @return Name
     */
    public String getTransitionName() {
        return this.get(PetriConfig.TRANSITION_NAME);
    }

    /**
     * Get default window height.
     * @return Window height
     */
    public int getWindowHeight() {
        return new Integer(this.get(PetriConfig.WINDOW_HEIGHT));
    }

    /**
     * Get minimum height for window.
     * @return Minimum height
     */
    public int getWindowMinHeight() {
        return new Integer(this.get(PetriConfig.WINDOW_MIN_HEIGHT));
    }

    /**
     * Get minimum width for window.
     * @return Minimum width
     */
    public int getWindowMinWidth() {

        final int minWidth =
            new Integer(this.get(PetriConfig.WINDOW_MIN_WIDTH));

        return (this.getSidebarWidth() > minWidth) ? this.getSidebarWidth()
            : minWidth;

    }

    /**
     * Get window title.
     * @return Window title
     */
    public String getWindowTitle() {
        return this.get(PetriConfig.WINDOW_TITLE);
    }

    /**
     * Get default window width.
     * @return Window width
     */
    public int getWindowWidth() {
        return new Integer(this.get(PetriConfig.WINDOW_WIDTH));
    }

    /**
     * Get default window x-position.
     * @return X-position
     */
    public int getWindowX() {
        return new Integer(this.get(PetriConfig.WINDOW_X));
    }

    /**
     * Get default window y-position.
     * @return Y-position
     */
    public int getWindowY() {
        return new Integer(this.get(PetriConfig.WINDOW_Y));
    }

    /**
     * Return a property value as String.
     * @param key Key to look for
     * @return Value as String
     */
    private String get(final String key) {

        return String.valueOf(super.get(key));

    }

    /**
     * Read the properties file 'config.properties'. If reading that file fails,
     * a default configuration will be used
     */
    private void readProperties() {

        this.getDefaults();

        InputStream is = null;

        try {

            is = this.getClass().getResourceAsStream("/config.properties");

            this.load(is);

        } catch (final Exception e) {

            // If something went wrong with reading the properties file, the
            // default values
            // will still be in the needed in the configuration object

            System.out.println("Reading the config file failed");

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
     * Read a specific properties file.
     * @param file Properties file
     * @throws IOException Error while reading the file
     */
    private void readProperties(final File file) throws IOException {

        this.getDefaults();

        InputStream is = null;

        is = new FileInputStream(file);

        this.load(is);

        is.close();

    }

    /**
     * Fill the configuration object with default values.
     * @return Default properties
     */
    Properties getDefaults() {

        this.put(PetriConfig.PLACE_NAME, "Place");
        this.put(PetriConfig.TRANSITION_NAME, "Transition");
        this.put(PetriConfig.CANVAS_HEIGHT, 1000);
        this.put(PetriConfig.CANVAS_WIDTH, 1000);
        this.put(PetriConfig.WINDOW_TITLE, "Tim Steinbach - q7485417");
        this.put(PetriConfig.WINDOW_WIDTH, 500);
        this.put(PetriConfig.WINDOW_HEIGHT, 400);
        this.put(PetriConfig.WINDOW_X, 100);
        this.put(PetriConfig.WINDOW_Y, 100);
        this.put(PetriConfig.APPLICATION_NAME, "Petrinet");
        this.put(PetriConfig.SIDEBAR_WIDTH, 120);
        this.put(PetriConfig.WINDOW_MIN_HEIGHT, 200);
        this.put(PetriConfig.WINDOW_MIN_WIDTH, 300);
        this.put(PetriConfig.PNML_EDGE_ID_PREFIX, "e");
        this.put(PetriConfig.NET_NAME, "DefaultNet");
        this.put(PetriConfig.FILE_EXTENSION, "pnml");

        return this;

    }

}
