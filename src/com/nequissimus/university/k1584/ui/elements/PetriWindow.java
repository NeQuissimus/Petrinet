package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.listener.CloseWindowListener;
import com.nequissimus.university.k1584.ui.listener.ResizeCanvasListener;
import com.nequissimus.university.k1584.ui.listener.ResizeSidebarListener;

/**
 * The main window that holds all the major UI elements, such as the editing
 * canvas, menus etc.
 * @author Tim Steinbach
 */
public class PetriWindow extends JFrame {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6276277357529619473L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * UI canvas.
     */
    private final PetriCanvas canvas;

    /**
     * Sidebar UI.
     */
    private final PetriSidebar sidebar;

    /**
     * Instantiate a new UI window.
     */
    public PetriWindow() {

        super();

        this.setLayout(null);

        this.canvas = new PetriCanvas();
        this.sidebar = new PetriSidebar();

        this.resetTitle();
        this.resetSize();
        this.resetLocation();
        this.resetCanvas();
        this.resetSidebar();
        this.resetMenubar();

        this.resetListeners();

        this.add(this.canvas);
        this.add(this.sidebar);

        this.setBackground(CONFIG.getWindowBackgroundColor());

    }

    /**
     * Get the currently used canvas.
     * @return Currently used canvas
     */
    public final PetriCanvas getCanvas() {
        return this.canvas;
    }

    /**
     * Reset the window size.
     */
    private void resetSize() {

        final int windowWidth = CONFIG.getWindowWidth();
        final int windowHeight = CONFIG.getWindowHeight();

        final Dimension windowSize =
            new Dimension(windowWidth, windowHeight);

        this.setPreferredSize(windowSize);
        this.setSize(windowSize);

    }

    /**
     * Reset the window location.
     */
    private void resetLocation() {

        final int windowX = CONFIG.getWindowX();
        final int windowY = CONFIG.getWindowY();

        final Point windowLocation = new Point(windowX, windowY);

        this.setLocation(windowLocation);

    }

    /**
     * Reset the window title.
     */
    private void resetTitle() {

        this.setTitle(CONFIG.getWindowTitle());

    }

    /**
     * Reset the canvas location.
     */
    private void resetCanvas() {

        this.canvas.setLocation(new Point(0, 0));
        this.canvas.validate();
        this.canvas.repaint();

    }

    /**
     * Reset the sidebar.
     */
    private void resetSidebar() {

        final Point location =
            new Point(CONFIG.getWindowWidth() - CONFIG.getSidebarWidth(), 0);

        this.sidebar.setLocation(location);
        this.sidebar.validate();
        this.sidebar.repaint();

    }

    /**
     * Reset all listeners for this window.
     */
    private void resetListeners() {

        final Dimension minSize =
            new Dimension(CONFIG.getWindowMinWidth(),
                CONFIG.getWindowMinHeight());

        this.addComponentListener(new ResizeCanvasListener(minSize));
        this.addComponentListener(new ResizeSidebarListener(minSize,
            this.sidebar));
        this.addWindowListener(new CloseWindowListener());

    }

    /**
     * Reset the window menubar.
     */
    private void resetMenubar() {

        PetriMenuBar menubar = new PetriMenuBar(this);
        this.setJMenuBar(menubar);

    }

}
