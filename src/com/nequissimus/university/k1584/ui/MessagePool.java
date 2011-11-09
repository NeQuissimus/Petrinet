package com.nequissimus.university.k1584.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class hold all messages that will be displayed to the user.<br />
 * These messages will be read from /lang.properties.<br />
 * <br />
 * The following keys need to be found in this file: (with their defaults)<br />
 * MessageErrorSaveFile = "Error saving file" // Message shown when file could
 * not be saved<br />
 * IconMenuRemove = "Remove" // Menu item for removing icons<br />
 * IconMenuConnect = "Connect" // Menu item to connect an icon to another<br />
 * IconMenuDisconnect = "Disconnect" // Menu item for disconnecting an icon from
 * another<br />
 * MenuFile = "File" // File menu for the window<br />
 * MenuFileSave = "Save" // File menu for saving a file<br />
 * MenuFileClose = "Close" // File menu for closing the application<br />
 * MenuView = "View" // View menu<br />
 * MenuViewCanvasSize = "Canvas size" // Editor canvas size<br />
 * MenuViewCanvasSizeLarger = "Larger" // Larger canvas<br />
 * MenuViewCanvasSizeSmaller = "Smaller" // Smaller canvas<br />
 * MenuViewIconSize = "Icon size" // Icon size<br />
 * MenuViewIconSizeVerySmall = "Very small" // Very small icons<br />
 * MenuViewIconSizeSmall = "Small" // Small icons<br />
 * MenuViewIconSizeMedium = "Medium" // Medium icons<br />
 * MenuViewIconSizeLarge = "Large" // Large icons<br />
 * IconMenuSidebarAdd = "Add new" // Add new icon to editor<br />
 * @author Tim Steinbach
 */
public final class MessagePool extends Properties {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 4043914048672054549L;

    /**
     * Key for error message when saving a file.
     */
    public static final String MSG_ERROR_SAVE_FILE = "MessageErrorSaveFile";

    /**
     * Key for icon menu item for removal.
     */
    public static final String ICON_MENU_REMOVE = "IconMenuRemove";

    /**
     * Key for icon menu item to connect it to another one.
     */
    public static final String ICON_MENU_CONNECT = "IconMenuConnect";

    /**
     * Key for icon menu item to disconnect it from another one.
     */
    public static final String ICON_MENU_DISCONNECT = "IconMenuDisconnect";

    /**
     * Key for the window menu "File".
     */
    public static final String MENU_FILE = "MenuFile";

    /**
     * Key for the window menu to save a file.
     */
    public static final String MENU_FILE_SAVE = "MenuFileSave";

    /**
     * Key for the window menu to close the application.
     */
    public static final String MENU_FILE_CLOSE = "MenuFileClose";

    /**
     * Key for view menu.
     */
    public static final String MENU_VIEW = "MenuView";

    /**
     * Key for canvas size sub-menu.
     */
    public static final String MENU_VIEW_CANVAS_SIZE = "MenuViewCanvasSize";

    /**
     * Key for larger canvas.
     */
    public static final String MENU_VIEW_CANVAS_SIZE_LARGER =
        "MenuViewCanvasSizeLarger";

    /**
     * Key for smaller canvas.
     */
    public static final String MENU_VIEW_CANVAS_SIZE_SMALLER =
        "MenuViewCanvasSizeSmaller";

    /**
     * Key for icon size sub-menu.
     */
    public static final String MENU_VIEW_ICON_SIZE = "MenuViewIconSize";

    /**
     * Key for very small icons.
     */
    public static final String MENU_VIEW_ICON_SIZE_VERY_SMALL =
        "MenuViewIconSizeVerySmall";

    /**
     * Key for small icons.
     */
    public static final String MENU_VIEW_ICON_SIZE_SMALL =
        "MenuViewIconSizeSmall";

    /**
     * Key for medium icons.
     */
    public static final String MENU_VIEW_ICON_SIZE_MEDIUM =
        "MenuViewIconSizeMedium";

    /**
     * Key for large icons.
     */
    public static final String MENU_VIEW_ICON_SIZE_LARGE =
        "MenuViewIconSizeLarge";

    /**
     * Key for adding new icons.
     */
    public static final String ICON_MENU_SIDEBAR_ADD = "IconMenuSidebarAdd";

    /**
     * Singleton instance.
     */
    private static final MessagePool INSTANCE = new MessagePool();

    /**
     * Create a new message pool.
     */
    private MessagePool() {

        this.readProperties();

    }

    /**
     * Get a singleton instance of the message pool.
     * @return Message pool
     */
    public static MessagePool getInstance() {

        return MessagePool.INSTANCE;

    }

    /**
     * Read all entries from the lang.properties file.
     */
    private void readProperties() {

        this.getDefaults();

        InputStream is = null;

        try {

            is = new FileInputStream("lang.properties");
            this.load(is);

        } catch (final IOException e) {

            // If something went wrong with reading the properties file, the
            // default values
            // will still be in the needed in the configuration object

            System.out.println("Reading the language file failed");

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
     * Add default values to these properties.
     */
    private void getDefaults() {

        this.put(MessagePool.MSG_ERROR_SAVE_FILE, "Error saving file");
        this.put(MessagePool.ICON_MENU_CONNECT, "Connect");
        this.put(MessagePool.ICON_MENU_DISCONNECT, "Disconnect");
        this.put(MessagePool.ICON_MENU_REMOVE, "Remove");
        this.put(MessagePool.MENU_FILE, "File");
        this.put(MessagePool.MENU_FILE_CLOSE, "Close");
        this.put(MessagePool.MENU_FILE_SAVE, "Save");
        this.put(MessagePool.MENU_VIEW, "View");
        this.put(MessagePool.MENU_VIEW_CANVAS_SIZE, "Canvas size");
        this.put(MessagePool.MENU_VIEW_CANVAS_SIZE_LARGER, "Larger");
        this.put(MessagePool.MENU_VIEW_CANVAS_SIZE_SMALLER, "Smaller");
        this.put(MessagePool.MENU_VIEW_ICON_SIZE, "Icon size");
        this.put(MessagePool.MENU_VIEW_ICON_SIZE_LARGE, "Large");
        this.put(MessagePool.MENU_VIEW_ICON_SIZE_MEDIUM, "Medium");
        this.put(MessagePool.MENU_VIEW_ICON_SIZE_SMALL, "Small");
        this.put(MessagePool.MENU_VIEW_ICON_SIZE_VERY_SMALL, "Very small");
        this.put(MessagePool.ICON_MENU_SIDEBAR_ADD, "Add new");

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
     * Get error message when saving a file.
     * @return Error message
     */
    public String getMsgErrorSaveFile() {
        return this.get(MessagePool.MSG_ERROR_SAVE_FILE);
    }

    /**
     * Get menu item for a disconnect.
     * @return Menu item text
     */
    public String getIconMenuDisconnect() {
        return this.get(MessagePool.ICON_MENU_DISCONNECT);
    }

    /**
     * Get menu item for connecting.
     * @return Menu item text
     */
    public String getIconMenuConnect() {
        return this.get(MessagePool.ICON_MENU_CONNECT);
    }

    /**
     * Get menu item for removing it.
     * @return Menu item text
     */
    public String getIconMenuRemove() {
        return this.get(MessagePool.ICON_MENU_REMOVE);
    }

    /**
     * Return menu text for the file menu.
     * @return Menu text
     */
    public String getMenuFile() {
        return this.get(MessagePool.MENU_FILE);
    }

    /**
     * Return menu text for the save file menu.
     * @return Menu text
     */
    public String getMenuFileSave() {
        return this.get(MessagePool.MENU_FILE_SAVE);
    }

    /**
     * Return menu text for the close application menu.
     * @return Menu text
     */
    public String getMenuFileClose() {
        return this.get(MessagePool.MENU_FILE_CLOSE);
    }

    /**
     * Get view menu.
     * @return Menu text
     */
    public String getMenuView() {
        return this.get(MessagePool.MENU_VIEW);
    }

    /**
     * Get view menu, canvas size.
     * @return Menu text
     */
    public String getMenuViewCanvasSize() {
        return this.get(MessagePool.MENU_VIEW_CANVAS_SIZE);
    }

    /**
     * Get view menu, larger canvas.
     * @return Menu text
     */
    public String getMenuViewCanvasSizeLarger() {
        return this.get(MessagePool.MENU_VIEW_CANVAS_SIZE_LARGER);
    }

    /**
     * Get view menu, smaller canvas.
     * @return Menu text
     */
    public String getMenuViewCanvasSizeSmaller() {
        return this.get(MessagePool.MENU_VIEW_CANVAS_SIZE_SMALLER);
    }

    /**
     * Get view menu, icon size.
     * @return Menu text
     */
    public String getMenuViewIconSize() {
        return this.get(MessagePool.MENU_VIEW_ICON_SIZE);
    }

    /**
     * Get view menu, large icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeLarge() {
        return this.get(MessagePool.MENU_VIEW_ICON_SIZE_LARGE);
    }

    /**
     * Get view menu, medium icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeMedium() {
        return this.get(MessagePool.MENU_VIEW_ICON_SIZE_MEDIUM);
    }

    /**
     * Get view menu, small icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeSmall() {
        return this.get(MessagePool.MENU_VIEW_ICON_SIZE_SMALL);
    }

    /**
     * Get view menu, very small icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeVerySmall() {
        return this.get(MessagePool.MENU_VIEW_ICON_SIZE_VERY_SMALL);
    }

    /**
     * Context menu for adding new items.
     * @return Menu text
     */
    public String getIconMenuSidebarAdd() {
        return this.get(MessagePool.ICON_MENU_SIDEBAR_ADD);
    }

}