package com.nequissimus.university.k1584.ui;

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
 * MessageErrorLoadFile = "The file could not be loaded" // Message shown when
 * the file could not be loaded<br />
 * IconMenuRemove = "Remove" // Menu item for removing icons<br />
 * IconMenuConnect = "Connect" // Menu item to connect an icon to another<br />
 * IconMenuDisconnect = "Disconnect" // Menu item for disconnecting an icon from
 * another<br />
 * MenuFile = "File" // File menu for the window<br />
 * MenuFileLoad = "Load" // File menu for loading a file<br />
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
 * IconMenuIncreaseMarkings = "Increase markings" // Increase markings for a
 * place<br />
 * IconMenuDecreaseMarkings = "Decrease markings" // Decrease markings for a
 * place<br />
 * IconMenuOccur = "Occur" // Let a transition occur<br />
 * IconMenuRename = "Rename" // Rename an object<br />
 * IconMenuRenameWindowTitle = "Change name" // Window title for dialog<br />
 * IconMenuRenameTitle = "Enter new name" // Request in rename dialog<br />
 * SnapshotMenu = "Snapshots" // Snapshot menu<br />
 * SnapshotMenuCreate = "Create snapshot" // Create a snapshot<br />
 * SnapshotMenuRename = "Rename snapshot" // Rename a snapshot<br />
 * SnapshotMenuSelect = "Select snapshot" // Select a snapshot<br />
 * SnapshotMenuDelete = "Delete snapshot" // Delete a snapshot<br />
 * SnapshotMenuSelectText = "Select a snapshot" // Dialog text<br />
 * SnapshotMenuRenameText = "Enter a new name" // Dialog text<br />
 * MessageErrorSnapshotNameTaken = "Name already taken, must be unique!" //
 * Error message when choosing snapshot name <br />
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
    private static final String MSG_ERROR_SAVE_FILE =
        "MessageErrorSaveFile";

    /**
     * Key for error message when loading a file.
     */
    private static final String MSG_EROR_LOAD_FILE = "MessageErrorLoadFile";

    /**
     * Key for error message when duplicate name has been entered.
     */
    private static final String MSG_ERROR_SNAPSHOT_NAME_TAKEN =
        "MessageErrorSnapshotNameTaken";

    /**
     * Key for icon menu item for removal.
     */
    private static final String ICON_MENU_REMOVE = "IconMenuRemove";

    /**
     * Key for icon menu item to connect it to another one.
     */
    private static final String ICON_MENU_CONNECT = "IconMenuConnect";

    /**
     * Key for icon menu item to disconnect it from another one.
     */
    private static final String ICON_MENU_DISCONNECT = "IconMenuDisconnect";

    /**
     * Key for icon menu item to increase the number of markings.
     */
    private static final String ICON_MENU_INCREASE_MARKINGS =
        "IconMenuIncreaseMarkings";

    /**
     * Key for icon menu item to decrease the number of markings.
     */
    private static final String ICON_MENU_DECREASE_MARKINGS =
        "IconMenuDecreaseMarkings";

    /**
     * Key for icon menu item to occur event on a transition.
     */
    private static final String ICON_MENU_OCCUR = "IconMenuOccur";

    /**
     * Key for the window menu "File".
     */
    private static final String MENU_FILE = "MenuFile";

    /**
     * Key for the window menu to reset the application.
     */
    private static final String MENU_FILE_NEW = "MenuFileNew";

    /**
     * Key for the window menu to save a file.
     */
    private static final String MENU_FILE_SAVE = "MenuFileSave";

    /**
     * Key for the window menu to load a file.
     */
    private static final String MENU_FILE_LOAD = "MenuFileLoad";

    /**
     * Key for the window menu to close the application.
     */
    private static final String MENU_FILE_CLOSE = "MenuFileClose";

    /**
     * Key for view menu.
     */
    private static final String MENU_VIEW = "MenuView";

    /**
     * Key for canvas size sub-menu.
     */
    private static final String MENU_VIEW_CANVAS_SIZE =
        "MenuViewCanvasSize";

    /**
     * Key for larger canvas.
     */
    private static final String MENU_VIEW_CANVAS_SIZE_LARGER =
        "MenuViewCanvasSizeLarger";

    /**
     * Key for smaller canvas.
     */
    private static final String MENU_VIEW_CANVAS_SIZE_SMALLER =
        "MenuViewCanvasSizeSmaller";

    /**
     * Key for icon size sub-menu.
     */
    private static final String MENU_VIEW_ICON_SIZE = "MenuViewIconSize";

    /**
     * Key for very small icons.
     */
    private static final String MENU_VIEW_ICON_SIZE_VERY_SMALL =
        "MenuViewIconSizeVerySmall";

    /**
     * Key for small icons.
     */
    private static final String MENU_VIEW_ICON_SIZE_SMALL =
        "MenuViewIconSizeSmall";

    /**
     * Key for medium icons.
     */
    private static final String MENU_VIEW_ICON_SIZE_MEDIUM =
        "MenuViewIconSizeMedium";

    /**
     * Key for large icons.
     */
    private static final String MENU_VIEW_ICON_SIZE_LARGE =
        "MenuViewIconSizeLarge";

    /**
     * Key for adding new icons.
     */
    private static final String ICON_MENU_SIDEBAR_ADD =
        "IconMenuSidebarAdd";

    /**
     * Key for renaming an object.
     */
    private static final String ICON_MENU_RENAME = "IconMenuRename";

    /**
     * Key for window title when renaming an object.
     */
    private static final String ICON_MENU_RENAME_WINDOW_TITLE =
        "IconMenuRenameWindowTitle";

    /**
     * Key for window text when renaming an object.
     */
    private static final String ICON_MENU_RENAME_TITLE =
        "IconMenuRenameTitle";

    /**
     * Key for snapshot menu.
     */
    private static final String SNAP_MENU = "SnapshotMenu";

    /**
     * Key for selecting a snapshot.
     */
    private static final String SNAP_MENU_SELECT = "SnapshotMenuSelect";

    /**
     * Key for dialog text when selecting a snapshot.
     */
    private static final String SNAP_MENU_SELECT_TEXT =
        "SnapshotMenuSelectText";

    /**
     * Key for creating a snapshot.
     */
    private static final String SNAP_MENU_CREATE = "SnapshotMenuCreate";

    /**
     * Key for deleting a snapshot.
     */
    private static final String SNAP_MENU_DELETE = "SnapshotMenuDelete";

    /**
     * Key for renaming a snapshot.
     */
    private static final String SNAP_MENU_RENAME = "SnapshotMenuRename";

    /**
     * Key for dialog text when renaming a snapshot.
     */
    private static final String SNAP_MENU_RENAME_TEXT =
        "SnapshotMenuRenameText";

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
     * Get menu item for connecting.
     * @return Menu item text
     */
    public String getIconMenuConnect() {
        return this.get(MessagePool.ICON_MENU_CONNECT);
    }

    /**
     * Context menu item for decreasing markings.
     * @return Menu text
     */
    public String getIconMenuDecreaseMarkings() {
        return this.get(MessagePool.ICON_MENU_DECREASE_MARKINGS);
    }

    /**
     * Get menu item for a disconnect.
     * @return Menu item text
     */
    public String getIconMenuDisconnect() {
        return this.get(MessagePool.ICON_MENU_DISCONNECT);
    }

    /**
     * Context menu item for increasing markings.
     * @return Menu text
     */
    public String getIconMenuIncreaseMarkings() {
        return this.get(MessagePool.ICON_MENU_INCREASE_MARKINGS);
    }

    /**
     * Context menu item for occurring an event on a transition.
     * @return Menu text
     */
    public String getIconMenuOccur() {
        return this.get(MessagePool.ICON_MENU_OCCUR);
    }

    /**
     * Get menu item for removing it.
     * @return Menu item text
     */
    public String getIconMenuRemove() {
        return this.get(MessagePool.ICON_MENU_REMOVE);
    }

    /**
     * Get menu text for renaming an object.
     * @return Menu text
     */
    public String getIconMenuRename() {
        return this.get(MessagePool.ICON_MENU_RENAME);
    }

    /**
     * Get dialog text for renaming an object.
     * @return Dialog text
     */
    public String getIconMenuRenameTitle() {
        return this.get(MessagePool.ICON_MENU_RENAME_TITLE);
    }

    /**
     * Get window title for renaming an object.
     * @return Window title
     */
    public String getIconMenuRenameWindowTitle() {
        return this.get(MessagePool.ICON_MENU_RENAME_WINDOW_TITLE);
    }

    /**
     * Context menu for adding new items.
     * @return Menu text
     */
    public String getIconMenuSidebarAdd() {
        return this.get(MessagePool.ICON_MENU_SIDEBAR_ADD);
    }

    /**
     * Return menu text for the file menu.
     * @return Menu text
     */
    public String getMenuFile() {
        return this.get(MessagePool.MENU_FILE);
    }

    /**
     * Return menu text for the close application menu.
     * @return Menu text
     */
    public String getMenuFileClose() {
        return this.get(MessagePool.MENU_FILE_CLOSE);
    }

    /**
     * Get menu text for the load file menu.
     * @return Menu text
     */
    public String getMenuFileLoad() {
        return this.get(MessagePool.MENU_FILE_LOAD);
    }

    /**
     * Get the menu text for resetting the application.
     * @return Menu text
     */
    public String getMenuFileNew() {
        return this.get(MessagePool.MENU_FILE_NEW);
    }

    /**
     * Return menu text for the save file menu.
     * @return Menu text
     */
    public String getMenuFileSave() {
        return this.get(MessagePool.MENU_FILE_SAVE);
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
     * Get error message when loading a file.
     * @return Error message
     */
    public String getMsgErrorLoadFile() {
        return this.get(MessagePool.MSG_EROR_LOAD_FILE);
    }

    /**
     * Get error message when saving a file.
     * @return Error message
     */
    public String getMsgErrorSaveFile() {
        return this.get(MessagePool.MSG_ERROR_SAVE_FILE);
    }

    /**
     * Get error message when naming a snapshot.
     * @return Error message
     */
    public String getMsgErrorSnapshotNameTaken() {
        return this.get(MessagePool.MSG_ERROR_SNAPSHOT_NAME_TAKEN);
    }

    /**
     * Get the menu text for creating a snapshot.
     * @return Menu text
     */
    public String getSnapshotCreate() {
        return this.get(MessagePool.SNAP_MENU_CREATE);
    }

    /**
     * Get the menu text for deleting a snapshot.
     * @return Menu text
     */
    public String getSnapshotDelete() {
        return this.get(MessagePool.SNAP_MENU_DELETE);
    }

    /**
     * Get the menu text for the snapshot menu.
     * @return Menu text
     */
    public String getSnapshotMenu() {
        return this.get(MessagePool.SNAP_MENU);
    }

    /**
     * Get the menu text for renaming a snapshot.
     * @return Menu text
     */
    public String getSnapshotRename() {
        return this.get(MessagePool.SNAP_MENU_RENAME);
    }

    /**
     * Get the dialog text for renaming a snapshot.
     * @return Dialog text
     */
    public String getSnapshotRenameDialog() {
        return this.get(MessagePool.SNAP_MENU_RENAME_TEXT);
    }

    /**
     * Get the menu text for selecting a snapshot.
     * @return Menu text
     */
    public String getSnapshotSelect() {
        return this.get(MessagePool.SNAP_MENU_SELECT);
    }

    /**
     * Get the dialog text for selecting a snapshot.
     * @return Dialog text
     */
    public String getSnapshotSelectDialog() {
        return this.get(MessagePool.SNAP_MENU_SELECT_TEXT);
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
     * Add default values to these properties.
     */
    private void getDefaults() {

        this.put(MessagePool.MSG_ERROR_SAVE_FILE, "Error saving file");
        this.put(MessagePool.MSG_EROR_LOAD_FILE,
            "The file could not be loaded");
        this.put(MessagePool.ICON_MENU_CONNECT, "Connect");
        this.put(MessagePool.ICON_MENU_DISCONNECT, "Disconnect");
        this.put(MessagePool.ICON_MENU_REMOVE, "Remove");
        this.put(MessagePool.MENU_FILE, "File");
        this.put(MessagePool.MENU_FILE_CLOSE, "Close");
        this.put(MessagePool.MENU_FILE_SAVE, "Save");
        this.put(MessagePool.MENU_FILE_LOAD, "Load");
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
        this.put(MessagePool.ICON_MENU_INCREASE_MARKINGS,
            "Increase markings");
        this.put(MessagePool.ICON_MENU_DECREASE_MARKINGS,
            "Decrease markings");
        this.put(MessagePool.ICON_MENU_OCCUR, "Occur");
        this.put(MessagePool.ICON_MENU_RENAME, "Rename");
        this.put(MessagePool.ICON_MENU_RENAME_TITLE, "Enter new name");
        this.put(MessagePool.ICON_MENU_RENAME_WINDOW_TITLE, "Change name");
        this.put(MessagePool.SNAP_MENU, "Snapshots");
        this.put(MessagePool.SNAP_MENU_CREATE, "Create snapshot");
        this.put(MessagePool.SNAP_MENU_DELETE, "Delete snapshot");
        this.put(MessagePool.SNAP_MENU_RENAME, "Rename snapshot");
        this.put(MessagePool.SNAP_MENU_SELECT, "Select snapshot");
        this.put(MessagePool.SNAP_MENU_SELECT_TEXT, "Select a snapshot");
        this.put(MessagePool.SNAP_MENU_RENAME_TEXT, "Enter a new name");
        this.put(MessagePool.MSG_ERROR_SNAPSHOT_NAME_TAKEN,
            "Name already taken, must be unique!");
        this.put(MessagePool.MENU_FILE_NEW, "New");

    }

    /**
     * Read all entries from the lang.properties file.
     */
    private void readProperties() {

        this.getDefaults();

        InputStream is = null;

        try {

            is = this.getClass().getResourceAsStream("/lang.properties");
            this.load(is);

        } catch (final Exception e) {

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

}
