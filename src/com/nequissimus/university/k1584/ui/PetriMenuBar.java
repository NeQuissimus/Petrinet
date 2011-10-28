package com.nequissimus.university.k1584.ui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.nequissimus.university.k1584.ui.actions.CloseWindowAction;
import com.nequissimus.university.k1584.ui.actions.SaveFileAction;

/**
 * Menu bar for the application window. This menu bar automatically provides all
 * essential options, such as "save", "close", "load" etc.
 * @author Tim Steinbach
 */
public class PetriMenuBar extends JMenuBar {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -415488481991662662L;

    /**
     * Application window.
     */
    private final JFrame window;

    /**
     * Create a new menu bar instance for a given window.
     * @param window Application window
     */
    public PetriMenuBar(final JFrame window) {

        super();

        this.window = window;

        this.resetMenu();

    }

    /**
     * Add all essential menu items to the menu bar.
     */
    public final void resetMenu() {

        JMenu menu = new JMenu("File");

        JMenuItem item = new JMenuItem("Save");
        item.addActionListener(new SaveFileAction());
        menu.add(item);

        item = new JMenuItem("Close");
        item.addActionListener(new CloseWindowAction(this.window));
        menu.add(item);

        this.add(menu);

    }

}
