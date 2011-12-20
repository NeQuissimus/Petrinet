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
 * MarkingsMenu = "Markings" // Markings menu<br />
 * MarkingsMenuSelect = "Select marking" // Select a marking<br />
 * MarkingsMenuAdd = "Create marking" // Add a new marking<br />
 * MarkingsMenuRename = "Rename marking" // Rename a marking<br />
 * MarkingsMenuDelete = "Delete marking" // Delete a marking<br />
 * NullMarking = "Null marking" // Default marking<br />
 * CreateMarkingDialog = "Enter new name" // Request for dialog window<br />
 * CreateMarkingDialogTitle = "Create marking" // Window title<br />
 * DeleteMarkingDialog = "Chose marking to be deleted" // Request for dialog
 * window<br />
 * DeleteMarkingDialogTitle = "Delete marking" // Window title<br />
 * RenameMarkingDialogChoose = "Choose marking" // Request for dialog<br />
 * RenameMarkingDialogName = "Name marking" // Request for second dialog<br />
 * RenameMarkingDialogTitle = "Rename marking" // Window title<br />
 * SelectMarkingDialog = "Select marking" // Request for dialog<br />
 * SelectMarkingDialogTitle = "Select marking" // Window title<br />
 * MenuViewTokenSize = "Token size" // Token size<br />
 * MenuViewTokenSizeSmaller = "Smaller" // Smaller token size<br />
 * MenuViewTokenSizeLarger = "Larger" // Larger token size<br />
 * @author Tim Steinbach
 */
public final class MessagePool extends Properties implements
    MessagePoolConstants {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 4043914048672054549L;

    /**
     * Create a new message pool.
     */
    public MessagePool() {

        this.readProperties();

    }

    // /**
    // * Get a singleton instance of the message pool.
    // * @return Message pool
    // */
    // public static MessagePool getInstance() {
    //
    // return MessagePool.INSTANCE;
    //
    // }

    /**
     * Get dialog request when creating a new marking.
     * @return Dialog text
     */
    public String getCreateMarkingDialog() {
        return this.get(MessagePoolConstants.CREATE_MARKING_DIALOG);
    }

    /**
     * Get dialog window title when creating a new marking.
     * @return Window title
     */
    public String getCreateMarkingDialogTitle() {
        return this.get(MessagePoolConstants.CREATE_MARKING_DIALOG_TITLE);
    }

    @Override
    public void getDefaults() {

        this.put(MessagePoolConstants.MSG_ERROR_SAVE_FILE,
            "Error saving file");
        this.put(MessagePoolConstants.MSG_EROR_LOAD_FILE,
            "The file could not be loaded");
        this.put(MessagePoolConstants.ICON_MENU_CONNECT, "Connect");
        this.put(MessagePoolConstants.ICON_MENU_DISCONNECT, "Disconnect");
        this.put(MessagePoolConstants.ICON_MENU_REMOVE, "Remove");
        this.put(MessagePoolConstants.MENU_FILE, "File");
        this.put(MessagePoolConstants.MENU_FILE_CLOSE, "Close");
        this.put(MessagePoolConstants.MENU_FILE_SAVE, "Save");
        this.put(MessagePoolConstants.MENU_FILE_LOAD, "Load");
        this.put(MessagePoolConstants.MENU_VIEW, "View");
        this.put(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE, "Canvas size");
        this.put(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE_LARGER,
            "Larger");
        this.put(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE_SMALLER,
            "Smaller");
        this.put(MessagePoolConstants.MENU_VIEW_ICON_SIZE, "Icon size");
        this.put(MessagePoolConstants.MENU_VIEW_ICON_SIZE_LARGE, "Large");
        this.put(MessagePoolConstants.MENU_VIEW_ICON_SIZE_MEDIUM, "Medium");
        this.put(MessagePoolConstants.MENU_VIEW_ICON_SIZE_SMALL, "Small");
        this.put(MessagePoolConstants.MENU_VIEW_ICON_SIZE_VERY_SMALL,
            "Very small");
        this.put(MessagePoolConstants.ICON_MENU_SIDEBAR_ADD, "Add new");
        this.put(MessagePoolConstants.ICON_MENU_INCREASE_MARKINGS,
            "Increase markings");
        this.put(MessagePoolConstants.ICON_MENU_DECREASE_MARKINGS,
            "Decrease markings");
        this.put(MessagePoolConstants.ICON_MENU_OCCUR, "Occur");
        this.put(MessagePoolConstants.ICON_MENU_RENAME, "Rename");
        this.put(MessagePoolConstants.ICON_MENU_RENAME_TITLE,
            "Enter new name");
        this.put(MessagePoolConstants.ICON_MENU_RENAME_WINDOW_TITLE,
            "Change name");
        this.put(MessagePoolConstants.SNAP_MENU, "Snapshots");
        this.put(MessagePoolConstants.SNAP_MENU_CREATE, "Create snapshot");
        this.put(MessagePoolConstants.SNAP_MENU_DELETE, "Delete snapshot");
        this.put(MessagePoolConstants.SNAP_MENU_RENAME, "Rename snapshot");
        this.put(MessagePoolConstants.SNAP_MENU_SELECT, "Select snapshot");
        this.put(MessagePoolConstants.SNAP_MENU_SELECT_TEXT,
            "Select a snapshot");
        this.put(MessagePoolConstants.SNAP_MENU_RENAME_TEXT,
            "Enter a new name");
        this.put(MessagePoolConstants.MSG_ERROR_SNAPSHOT_NAME_TAKEN,
            "Name already taken, must be unique!");
        this.put(MessagePoolConstants.MENU_FILE_NEW, "New");
        this.put(MessagePoolConstants.MARKINGS_MENU, "Markings");
        this.put(MessagePoolConstants.MARKINGS_MENU_ADD, "Add marking");
        this.put(MessagePoolConstants.MARKINGS_MENU_SELECT,
            "Select marking");
        this.put(MessagePoolConstants.MARKINGS_MENU_DELETE,
            "Delete marking");
        this.put(MessagePoolConstants.MARKINGS_MENU_RENAME,
            "Rename marking");
        this.put(MessagePoolConstants.NULL_MARKING, "Null marking");
        this.put(MessagePoolConstants.CREATE_MARKING_DIALOG,
            "Enter new name");
        this.put(MessagePoolConstants.CREATE_MARKING_DIALOG_TITLE,
            "Create marking");
        this.put(MessagePoolConstants.DELETE_MARKING_DIALOG,
            "Chose marking to be deleted");
        this.put(MessagePoolConstants.DELETE_MARKING_DIALOG_TITLE,
            "Delete marking");
        this.put(MessagePoolConstants.RENAME_MARKING_DIALOG_CHOOSE,
            "Choose marking");
        this.put(MessagePoolConstants.RENAME_MARKING_DIALOG_NAME,
            "Name marking");
        this.put(MessagePoolConstants.RENAME_MARKING_DIALOG_TITLE,
            "Rename marking");
        this.put(MessagePoolConstants.SELECT_MARKING_DIALOG,
            "Select marking");
        this.put(MessagePoolConstants.SELECT_MARKING_DIALOG_TITLE,
            "Select marking");
        this.put(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE, "Token size");
        this.put(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE_LARGER, "Larger");
        this.put(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE_SMALLER,
            "Smaller");

    }

    /**
     * Get dialog request when deleting a marking.
     * @return Dialog text
     */
    public String getDeleteMarkingDialog() {
        return this.get(MessagePoolConstants.DELETE_MARKING_DIALOG);
    }

    /**
     * Get window title when deleting a marking.
     * @return Window title
     */
    public String getDeleteMarkingDialogTitle() {
        return this.get(MessagePoolConstants.DELETE_MARKING_DIALOG_TITLE);
    }

    /**
     * Get menu item for connecting.
     * @return Menu item text
     */
    public String getIconMenuConnect() {
        return this.get(MessagePoolConstants.ICON_MENU_CONNECT);
    }

    /**
     * Context menu item for decreasing markings.
     * @return Menu text
     */
    public String getIconMenuDecreaseMarkings() {
        return this.get(MessagePoolConstants.ICON_MENU_DECREASE_MARKINGS);
    }

    /**
     * Get menu item for a disconnect.
     * @return Menu item text
     */
    public String getIconMenuDisconnect() {
        return this.get(MessagePoolConstants.ICON_MENU_DISCONNECT);
    }

    /**
     * Context menu item for increasing markings.
     * @return Menu text
     */
    public String getIconMenuIncreaseMarkings() {
        return this.get(MessagePoolConstants.ICON_MENU_INCREASE_MARKINGS);
    }

    /**
     * Context menu item for occurring an event on a transition.
     * @return Menu text
     */
    public String getIconMenuOccur() {
        return this.get(MessagePoolConstants.ICON_MENU_OCCUR);
    }

    /**
     * Get menu item for removing it.
     * @return Menu item text
     */
    public String getIconMenuRemove() {
        return this.get(MessagePoolConstants.ICON_MENU_REMOVE);
    }

    /**
     * Get menu text for renaming an object.
     * @return Menu text
     */
    public String getIconMenuRename() {
        return this.get(MessagePoolConstants.ICON_MENU_RENAME);
    }

    /**
     * Get dialog text for renaming an object.
     * @return Dialog text
     */
    public String getIconMenuRenameTitle() {
        return this.get(MessagePoolConstants.ICON_MENU_RENAME_TITLE);
    }

    /**
     * Get window title for renaming an object.
     * @return Window title
     */
    public String getIconMenuRenameWindowTitle() {
        return this.get(MessagePoolConstants.ICON_MENU_RENAME_WINDOW_TITLE);
    }

    /**
     * Context menu for adding new items.
     * @return Menu text
     */
    public String getIconMenuSidebarAdd() {
        return this.get(MessagePoolConstants.ICON_MENU_SIDEBAR_ADD);
    }

    /**
     * Add marking.
     * @return Menu text
     */
    public String getMarkingsAdd() {
        return this.get(MessagePoolConstants.MARKINGS_MENU_ADD);
    }

    /**
     * Delete marking.
     * @return Menu text
     */
    public String getMarkingsDelete() {
        return this.get(MessagePoolConstants.MARKINGS_MENU_DELETE);
    }

    /**
     * Markings menu.
     * @return Menu text
     */
    public String getMarkingsMenu() {
        return this.get(MessagePoolConstants.MARKINGS_MENU);
    }

    /**
     * Rename marking.
     * @return Menu text
     */
    public String getMarkingsRename() {
        return this.get(MessagePoolConstants.MARKINGS_MENU_RENAME);
    }

    /**
     * Select marking.
     * @return Menu text
     */
    public String getMarkingsSelect() {
        return this.get(MessagePoolConstants.MARKINGS_MENU_SELECT);
    }

    /**
     * Return menu text for the file menu.
     * @return Menu text
     */
    public String getMenuFile() {
        return this.get(MessagePoolConstants.MENU_FILE);
    }

    /**
     * Return menu text for the close application menu.
     * @return Menu text
     */
    public String getMenuFileClose() {
        return this.get(MessagePoolConstants.MENU_FILE_CLOSE);
    }

    /**
     * Get menu text for the load file menu.
     * @return Menu text
     */
    public String getMenuFileLoad() {
        return this.get(MessagePoolConstants.MENU_FILE_LOAD);
    }

    /**
     * Get the menu text for resetting the application.
     * @return Menu text
     */
    public String getMenuFileNew() {
        return this.get(MessagePoolConstants.MENU_FILE_NEW);
    }

    /**
     * Return menu text for the save file menu.
     * @return Menu text
     */
    public String getMenuFileSave() {
        return this.get(MessagePoolConstants.MENU_FILE_SAVE);
    }

    /**
     * Get view menu.
     * @return Menu text
     */
    public String getMenuView() {
        return this.get(MessagePoolConstants.MENU_VIEW);
    }

    /**
     * Get view menu, canvas size.
     * @return Menu text
     */
    public String getMenuViewCanvasSize() {
        return this.get(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE);
    }

    /**
     * Get view menu, larger canvas.
     * @return Menu text
     */
    public String getMenuViewCanvasSizeLarger() {
        return this.get(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE_LARGER);
    }

    /**
     * Get view menu, smaller canvas.
     * @return Menu text
     */
    public String getMenuViewCanvasSizeSmaller() {
        return this.get(MessagePoolConstants.MENU_VIEW_CANVAS_SIZE_SMALLER);
    }

    /**
     * Get view menu, icon size.
     * @return Menu text
     */
    public String getMenuViewIconSize() {
        return this.get(MessagePoolConstants.MENU_VIEW_ICON_SIZE);
    }

    /**
     * Get view menu, large icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeLarge() {
        return this.get(MessagePoolConstants.MENU_VIEW_ICON_SIZE_LARGE);
    }

    /**
     * Get view menu, medium icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeMedium() {
        return this.get(MessagePoolConstants.MENU_VIEW_ICON_SIZE_MEDIUM);
    }

    /**
     * Get view menu, small icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeSmall() {
        return this.get(MessagePoolConstants.MENU_VIEW_ICON_SIZE_SMALL);
    }

    /**
     * Get view menu, very small icons.
     * @return Menu text
     */
    public String getMenuViewIconSizeVerySmall() {
        return this
            .get(MessagePoolConstants.MENU_VIEW_ICON_SIZE_VERY_SMALL);
    }

    /**
     * Get token size menu text.
     * @return Menu text
     */
    public String getMenuViewTokenSize() {
        return this.get(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE);
    }

    /**
     * Get larger token size menu text.
     * @return Menu text
     */
    public String getMenuViewTokenSizeLarger() {
        return this.get(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE_LARGER);
    }

    /**
     * Get smaller token size menu text.
     * @return Menu text
     */
    public String getMenuViewTokenSizeSmaller() {
        return this.get(MessagePoolConstants.MENU_VIEW_TOKEN_SIZE_SMALLER);
    }

    /**
     * Get error message when loading a file.
     * @return Error message
     */
    public String getMsgErrorLoadFile() {
        return this.get(MessagePoolConstants.MSG_EROR_LOAD_FILE);
    }

    /**
     * Get error message when saving a file.
     * @return Error message
     */
    public String getMsgErrorSaveFile() {
        return this.get(MessagePoolConstants.MSG_ERROR_SAVE_FILE);
    }

    /**
     * Get error message when naming a snapshot.
     * @return Error message
     */
    public String getMsgErrorSnapshotNameTaken() {
        return this.get(MessagePoolConstants.MSG_ERROR_SNAPSHOT_NAME_TAKEN);
    }

    /**
     * Get name for null marking.
     * @return Name
     */
    public String getNullMarking() {
        return this.get(MessagePoolConstants.NULL_MARKING);
    }

    /**
     * Get dialog text when choosing a marking to be renamed.
     * @return Dialog text
     */
    public String getRenameMarkingDialogChoose() {
        return this.get(MessagePoolConstants.RENAME_MARKING_DIALOG_CHOOSE);
    }

    /**
     * Get dialog text when choosing a new name for a marking.
     * @return Dialog text
     */
    public String getRenameMarkingDialogName() {
        return this.get(MessagePoolConstants.RENAME_MARKING_DIALOG_NAME);
    }

    /**
     * Get window title for dialog when renaming a marking.
     * @return Window title
     */
    public String getRenameMarkingDialogTitle() {
        return this.get(MessagePoolConstants.RENAME_MARKING_DIALOG_TITLE);
    }

    /**
     * Dialog message when selecting a marking.
     * @return Dialog text
     */
    public String getSelectMarkingDialog() {
        return this.get(MessagePoolConstants.SELECT_MARKING_DIALOG);
    }

    /**
     * Window title for dialog when selecting a marking.
     * @return Window title
     */
    public String getSelectMarkingDialogTitle() {
        return this.get(MessagePoolConstants.SELECT_MARKING_DIALOG_TITLE);
    }

    /**
     * Get the menu text for creating a snapshot.
     * @return Menu text
     */
    public String getSnapshotCreate() {
        return this.get(MessagePoolConstants.SNAP_MENU_CREATE);
    }

    /**
     * Get the menu text for deleting a snapshot.
     * @return Menu text
     */
    public String getSnapshotDelete() {
        return this.get(MessagePoolConstants.SNAP_MENU_DELETE);
    }

    /**
     * Get the menu text for the snapshot menu.
     * @return Menu text
     */
    public String getSnapshotMenu() {
        return this.get(MessagePoolConstants.SNAP_MENU);
    }

    /**
     * Get the menu text for renaming a snapshot.
     * @return Menu text
     */
    public String getSnapshotRename() {
        return this.get(MessagePoolConstants.SNAP_MENU_RENAME);
    }

    /**
     * Get the dialog text for renaming a snapshot.
     * @return Dialog text
     */
    public String getSnapshotRenameDialog() {
        return this.get(MessagePoolConstants.SNAP_MENU_RENAME_TEXT);
    }

    /**
     * Get the menu text for selecting a snapshot.
     * @return Menu text
     */
    public String getSnapshotSelect() {
        return this.get(MessagePoolConstants.SNAP_MENU_SELECT);
    }

    /**
     * Get the dialog text for selecting a snapshot.
     * @return Dialog text
     */
    public String getSnapshotSelectDialog() {
        return this.get(MessagePoolConstants.SNAP_MENU_SELECT_TEXT);
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
