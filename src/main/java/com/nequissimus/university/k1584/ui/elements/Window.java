/*******************************************************************************
 * Copyright (c) 2011 Tim Steinbach Permission is hereby granted, free of
 * charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to
 * the following conditions: The above copyright notice and this permission
 * notice shall be included in all copies or substantial portions of the
 * Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.nequissimus.university.k1584.ui.elements;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriConstants;
import com.nequissimus.university.k1584.ui.listener.CloseWindowListener;
import com.nequissimus.university.k1584.ui.listener.ResizeCanvasListener;
import com.nequissimus.university.k1584.ui.listener.ResizeSidebarListener;
import com.nequissimus.university.k1584.ui.menus.MenuBar;

/**
 * The main window that holds all the major UI elements, such as the editing
 * canvas, menus etc.
 * @author Tim Steinbach
 */
public class Window extends JFrame {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6276277357529619473L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * UI canvas.
     */
    private final Canvas canvas;

    /**
     * Sidebar UI.
     */
    private final Sidebar sidebar;

    /**
     * Instantiate a new UI window.
     */
    public Window() {

        super();

        this.setLayout(null);

        this.canvas = new Canvas();
        this.sidebar = new Sidebar();

        this.resetTitle();
        this.resetSize();
        this.resetLocation();
        this.resetCanvas();
        this.resetSidebar();
        this.resetMenubar();

        this.resetListeners();

        this.add(this.canvas);
        this.add(this.sidebar);

        this.setBackground(PetriConstants.WINDOW_BACKGROUND);

    }

    /**
     * Get the currently used canvas.
     * @return Currently used canvas
     */
    public final Canvas getCanvas() {
        return this.canvas;
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
     * Reset all listeners for this window.
     */
    private void resetListeners() {

        final Dimension minSize =
            new Dimension(Window.CONFIG.getWindowMinWidth(),
                Window.CONFIG.getWindowMinHeight());

        this.addComponentListener(new ResizeCanvasListener(minSize));
        this.addComponentListener(new ResizeSidebarListener(minSize,
            this.sidebar));
        this.addWindowListener(new CloseWindowListener());

    }

    /**
     * Reset the window location.
     */
    private void resetLocation() {

        final int windowX = Window.CONFIG.getWindowX();
        final int windowY = Window.CONFIG.getWindowY();

        final Point windowLocation = new Point(windowX, windowY);

        this.setLocation(windowLocation);

    }

    /**
     * Reset the window menubar.
     */
    private void resetMenubar() {

        final MenuBar menubar = new MenuBar();
        this.setJMenuBar(menubar);

    }

    /**
     * Reset the sidebar.
     */
    private void resetSidebar() {

        final Point location =
            new Point(Window.CONFIG.getWindowWidth()
                - Window.CONFIG.getSidebarWidth(), 0);

        this.sidebar.setLocation(location);
        this.sidebar.validate();
        this.sidebar.repaint();

    }

    /**
     * Reset the window size.
     */
    private void resetSize() {

        final int windowWidth = Window.CONFIG.getWindowWidth();
        final int windowHeight = Window.CONFIG.getWindowHeight();

        final Dimension windowSize =
            new Dimension(windowWidth, windowHeight);

        this.setPreferredSize(windowSize);
        this.setSize(windowSize);

    }

    /**
     * Reset the window title.
     */
    private void resetTitle() {

        this.setTitle(Window.CONFIG.getWindowTitle());

    }

}
