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
     * UI canvas.
     */
    private static PetriCanvas canvas;

    /**
     * Configuration.
     */
    private final PetriConfig config;

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

        this.config = PetriConfig.getInstance();

        PetriWindow.setCanvas(new PetriCanvas());
        this.sidebar = new PetriSidebar();

        this.resetTitle();
        this.resetSize();
        this.resetLocation();
        this.resetCanvas();
        this.resetSidebar();
        this.resetMenubar();

        this.resetListeners();

        this.add(PetriWindow.canvas);
        this.add(this.sidebar);

        this.setBackground(this.config.getWindowBackgroundColor());

    }

    /**
     * Use a certain canvas to draw on.
     * @param canvas Canvas
     */
    private static void setCanvas(final PetriCanvas canvas) {
        PetriWindow.canvas = canvas;
    }

    /**
     * Get the currently used canvas.
     * @return Currently used canvas
     */
    public static PetriCanvas getCanvas() {
        return PetriWindow.canvas;
    }

    /**
     * Reset the window size.
     */
    private void resetSize() {

        final int windowWidth = this.config.getWindowWidth();
        final int windowHeight = this.config.getWindowHeight();

        final Dimension windowSize =
            new Dimension(windowWidth, windowHeight);

        this.setPreferredSize(windowSize);
        this.setSize(windowSize);

    }

    /**
     * Reset the window location.
     */
    private void resetLocation() {

        final int windowX = this.config.getWindowX();
        final int windowY = this.config.getWindowY();

        final Point windowLocation = new Point(windowX, windowY);

        this.setLocation(windowLocation);

    }

    /**
     * Reset the window title.
     */
    private void resetTitle() {

        this.setTitle(this.config.getWindowTitle());

    }

    /**
     * Reset the canvas location.
     */
    private void resetCanvas() {

        PetriWindow.canvas.setLocation(new Point(0, 0));
        PetriWindow.canvas.validate();
        PetriWindow.canvas.repaint();

    }

    /**
     * Reset the sidebar.
     */
    private void resetSidebar() {

        final Point location =
            new Point(this.config.getWindowWidth()
                - this.config.getSidebarWidth(), 0);

        this.sidebar.setLocation(location);
        this.sidebar.validate();
        this.sidebar.repaint();

    }

    /**
     * Reset all listeners for this window.
     */
    private void resetListeners() {

        final Dimension minSize =
            new Dimension(this.config.getWindowMinWidth(),
                this.config.getWindowMinHeight());

        this.addComponentListener(new ResizeCanvasListener(minSize,
            PetriWindow.canvas));
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
