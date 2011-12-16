package com.nequissimus.university.k1584.ui;

/**
 * Configuration keys for {@link MessagePool}.
 * @author Tim Steinbach
 */
interface MessagePoolConstants {

    /**
     * Key for request message shown in create marking dialog.
     */
    String CREATE_MARKING_DIALOG = "CreateMarkingDialog";

    /**
     * Key for create marking dialog title.
     */
    String CREATE_MARKING_DIALOG_TITLE = "CreateMarkingDialogTitle";

    /**
     * Key for request message shown in delete marking dialog.
     */
    String DELETE_MARKING_DIALOG = "DeleteMarkingDialog";

    /**
     * Key for delete marking dialog title.
     */
    String DELETE_MARKING_DIALOG_TITLE = "DeleteMarkingDialogTitle";

    /**
     * Key for icon menu item to connect it to another one.
     */
    String ICON_MENU_CONNECT = "IconMenuConnect";

    /**
     * Key for icon menu item to decrease the number of markings.
     */
    String ICON_MENU_DECREASE_MARKINGS = "IconMenuDecreaseMarkings";

    /**
     * Key for icon menu item to disconnect it from another one.
     */
    String ICON_MENU_DISCONNECT = "IconMenuDisconnect";

    /**
     * Key for icon menu item to increase the number of markings.
     */
    String ICON_MENU_INCREASE_MARKINGS = "IconMenuIncreaseMarkings";

    /**
     * Key for icon menu item to occur event on a transition.
     */
    String ICON_MENU_OCCUR = "IconMenuOccur";

    /**
     * Key for icon menu item for removal.
     */
    String ICON_MENU_REMOVE = "IconMenuRemove";

    /**
     * Key for renaming an object.
     */
    String ICON_MENU_RENAME = "IconMenuRename";

    /**
     * Key for window text when renaming an object.
     */
    String ICON_MENU_RENAME_TITLE = "IconMenuRenameTitle";

    /**
     * Key for window title when renaming an object.
     */
    String ICON_MENU_RENAME_WINDOW_TITLE = "IconMenuRenameWindowTitle";

    /**
     * Key for adding new icons.
     */
    String ICON_MENU_SIDEBAR_ADD = "IconMenuSidebarAdd";

    /**
     * Key for the markings menu.
     */
    String MARKINGS_MENU = "MarkingsMenu";

    /**
     * Key for adding a marking.
     */
    String MARKINGS_MENU_ADD = "MarkingsMenuAdd";

    /**
     * Key for deleting a marking.
     */
    String MARKINGS_MENU_DELETE = "MarkingsMenuDelete";

    /**
     * Key for renaming a marking.
     */
    String MARKINGS_MENU_RENAME = "MarkingsMenuRename";

    /**
     * Key for selecting a marking.
     */
    String MARKINGS_MENU_SELECT = "MarkingsMenuSelect";

    /**
     * Key for the window menu "File".
     */
    String MENU_FILE = "MenuFile";

    /**
     * Key for the window menu to close the application.
     */
    String MENU_FILE_CLOSE = "MenuFileClose";

    /**
     * Key for the window menu to load a file.
     */
    String MENU_FILE_LOAD = "MenuFileLoad";

    /**
     * Key for the window menu to reset the application.
     */
    String MENU_FILE_NEW = "MenuFileNew";

    /**
     * Key for the window menu to save a file.
     */
    String MENU_FILE_SAVE = "MenuFileSave";

    /**
     * Key for view menu.
     */
    String MENU_VIEW = "MenuView";

    /**
     * Key for canvas size sub-menu.
     */
    String MENU_VIEW_CANVAS_SIZE = "MenuViewCanvasSize";

    /**
     * Key for larger canvas.
     */
    String MENU_VIEW_CANVAS_SIZE_LARGER = "MenuViewCanvasSizeLarger";

    /**
     * Key for smaller canvas.
     */
    String MENU_VIEW_CANVAS_SIZE_SMALLER = "MenuViewCanvasSizeSmaller";

    /**
     * Key for icon size sub-menu.
     */
    String MENU_VIEW_ICON_SIZE = "MenuViewIconSize";

    /**
     * Key for large icons.
     */
    String MENU_VIEW_ICON_SIZE_LARGE = "MenuViewIconSizeLarge";

    /**
     * Key for medium icons.
     */
    String MENU_VIEW_ICON_SIZE_MEDIUM = "MenuViewIconSizeMedium";

    /**
     * Key for small icons.
     */
    String MENU_VIEW_ICON_SIZE_SMALL = "MenuViewIconSizeSmall";

    /**
     * Key for very small icons.
     */
    String MENU_VIEW_ICON_SIZE_VERY_SMALL = "MenuViewIconSizeVerySmall";

    /**
     * Key for token size menu.
     */
    String MENU_VIEW_TOKEN_SIZE = "MenuViewTokenSize";

    /**
     * Key for larger token size.
     */
    String MENU_VIEW_TOKEN_SIZE_LARGER = "MenuViewTokenSizeLarger";

    /**
     * Key for smaller token size.
     */
    String MENU_VIEW_TOKEN_SIZE_SMALLER = "MenuViewTokenSizeSmaller";

    /**
     * Key for error message when loading a file.
     */
    String MSG_EROR_LOAD_FILE = "MessageErrorLoadFile";

    /**
     * Key for error message when saving a file.
     */
    String MSG_ERROR_SAVE_FILE = "MessageErrorSaveFile";

    /**
     * Key for error message when duplicate name has been entered.
     */
    String MSG_ERROR_SNAPSHOT_NAME_TAKEN = "MessageErrorSnapshotNameTaken";

    /**
     * Name for empty marking.
     */
    String NULL_MARKING = "NullMarking";

    /**
     * Key for request message shown when choosing a marking to be renamed.
     */
    String RENAME_MARKING_DIALOG_CHOOSE = "RenameMarkingDialogChoose";

    /**
     * Key for request message shown when renaming a chosen marking.
     */
    String RENAME_MARKING_DIALOG_NAME = "RenameMarkingDialogName";

    /**
     * Key for window title when renaming a marking.
     */
    String RENAME_MARKING_DIALOG_TITLE = "RenameMarkingDialogTitle";

    /**
     * Key for request message shown when selecting a marking.
     */
    String SELECT_MARKING_DIALOG = "SelectMarkingDialog";

    /**
     * Key for window title when selecting a marking.
     */
    String SELECT_MARKING_DIALOG_TITLE = "SelectMarkingDialogTitle";

    /**
     * Key for snapshot menu.
     */
    String SNAP_MENU = "SnapshotMenu";

    /**
     * Key for creating a snapshot.
     */
    String SNAP_MENU_CREATE = "SnapshotMenuCreate";

    /**
     * Key for deleting a snapshot.
     */
    String SNAP_MENU_DELETE = "SnapshotMenuDelete";

    /**
     * Key for renaming a snapshot.
     */
    String SNAP_MENU_RENAME = "SnapshotMenuRename";

    /**
     * Key for dialog text when renaming a snapshot.
     */
    String SNAP_MENU_RENAME_TEXT = "SnapshotMenuRenameText";

    /**
     * Key for selecting a snapshot.
     */
    String SNAP_MENU_SELECT = "SnapshotMenuSelect";

    /**
     * Key for dialog text when selecting a snapshot.
     */
    String SNAP_MENU_SELECT_TEXT = "SnapshotMenuSelectText";

    /**
     * Add default values to these properties.
     */
    void getDefaults();

}
