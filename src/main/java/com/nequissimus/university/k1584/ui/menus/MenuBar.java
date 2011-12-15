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

package com.nequissimus.university.k1584.ui.menus;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.actions.CanvasLargerAction;
import com.nequissimus.university.k1584.ui.actions.CanvasSmallerAction;
import com.nequissimus.university.k1584.ui.actions.ChangeIconSizeAction;
import com.nequissimus.university.k1584.ui.actions.CloseWindowAction;
import com.nequissimus.university.k1584.ui.actions.CreateMarkingAction;
import com.nequissimus.university.k1584.ui.actions.CreateSnapshotAction;
import com.nequissimus.university.k1584.ui.actions.DeleteMarkingAction;
import com.nequissimus.university.k1584.ui.actions.DeleteSnapshotAction;
import com.nequissimus.university.k1584.ui.actions.LoadFileAction;
import com.nequissimus.university.k1584.ui.actions.RenameMarkingAction;
import com.nequissimus.university.k1584.ui.actions.RenameSnapshotAction;
import com.nequissimus.university.k1584.ui.actions.ResetApplicationAction;
import com.nequissimus.university.k1584.ui.actions.SaveFileAction;
import com.nequissimus.university.k1584.ui.actions.SelectMarkingAction;
import com.nequissimus.university.k1584.ui.actions.SelectSnapshotAction;
import com.nequissimus.university.k1584.ui.enums.IconSize;

/**
 * Menu bar for the application window. This menu bar automatically provides all
 * essential options, such as "save", "close", "load" etc.
 * @author Tim Steinbach
 */
public class MenuBar extends JMenuBar {

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -415488481991662662L;

    /**
     * Create a new menu bar instance for a given window.
     */
    public MenuBar() {

        super();

        this.resetMenu();

    }

    /**
     * Add all essential menu items to the menu bar.
     */
    public final void resetMenu() {

        this.resetFileMenu();
        this.resetViewMenu();
        this.resetNetMenu();
        this.resetMarkingMenu();

    }

    /**
     * Add all items found under "View"-&gt;"Canvas Size".
     * @param menu View menu
     */
    private void resetCanvasSizeViewMenu(final JMenu menu) {

        final JMenu subMenu =
            new JMenu(MenuBar.MSG.getMenuViewCanvasSize());

        final JMenuItem itemSmaller =
            new JMenuItem(MenuBar.MSG.getMenuViewCanvasSizeSmaller());
        final JMenuItem itemLarger =
            new JMenuItem(MenuBar.MSG.getMenuViewCanvasSizeLarger());

        itemSmaller.addActionListener(new CanvasSmallerAction());
        itemLarger.addActionListener(new CanvasLargerAction());

        subMenu.add(itemSmaller);
        subMenu.add(itemLarger);

        menu.add(subMenu);

    }

    /**
     * Add all items found under "file".
     */
    private void resetFileMenu() {

        final JMenu menu = new JMenu(MenuBar.MSG.getMenuFile());

        JMenuItem item = new JMenuItem(MenuBar.MSG.getMenuFileNew());
        item.addActionListener(new ResetApplicationAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuFileSave());
        item.addActionListener(new SaveFileAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuFileLoad());
        item.addActionListener(new LoadFileAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuFileClose());
        item.addActionListener(new CloseWindowAction());
        menu.add(item);

        this.add(menu);

    }

    /**
     * Add all items found under "View"-&gt;"Icon Size".
     * @param menu View menu to add to
     */
    private void resetIconSizeViewMenu(final JMenu menu) {

        final JMenu subMenu = new JMenu(MenuBar.MSG.getMenuViewIconSize());

        JMenuItem item =
            new JMenuItem(MenuBar.MSG.getMenuViewIconSizeLarge());
        item.addActionListener(new ChangeIconSizeAction(IconSize.LARGE));
        subMenu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuViewIconSizeMedium());
        item.addActionListener(new ChangeIconSizeAction(IconSize.MEDIUM));
        subMenu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuViewIconSizeSmall());
        item.addActionListener(new ChangeIconSizeAction(IconSize.SMALL));
        subMenu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMenuViewIconSizeVerySmall());
        item.addActionListener(new ChangeIconSizeAction(IconSize.VERY_SMALL));
        subMenu.add(item);

        menu.add(subMenu);

    }

    /**
     * Add all items found under "Markings".
     */
    private void resetMarkingMenu() {

        final JMenu menu = new JMenu(MenuBar.MSG.getMarkingsMenu());

        JMenuItem item = new JMenuItem(MenuBar.MSG.getMarkingsSelect());
        item.addActionListener(new SelectMarkingAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMarkingsAdd());
        item.addActionListener(new CreateMarkingAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMarkingsRename());
        item.addActionListener(new RenameMarkingAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getMarkingsDelete());
        item.addActionListener(new DeleteMarkingAction());
        menu.add(item);

        this.add(menu);

    }

    /**
     * Add all items found under "nets".
     */
    private void resetNetMenu() {

        final JMenu menu = new JMenu(MenuBar.MSG.getSnapshotMenu());

        JMenuItem item = new JMenuItem(MenuBar.MSG.getSnapshotSelect());
        item.addActionListener(new SelectSnapshotAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getSnapshotCreate());
        item.addActionListener(new CreateSnapshotAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getSnapshotRename());
        item.addActionListener(new RenameSnapshotAction());
        menu.add(item);

        item = new JMenuItem(MenuBar.MSG.getSnapshotDelete());
        item.addActionListener(new DeleteSnapshotAction());
        menu.add(item);

        this.add(menu);

    }

    /**
     * Add all items found under "view".
     */
    private void resetViewMenu() {

        final JMenu menu = new JMenu(MenuBar.MSG.getMenuView());

        this.resetIconSizeViewMenu(menu);
        this.resetCanvasSizeViewMenu(menu);

        this.add(menu);

    }

}
