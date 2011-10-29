package com.nequissimus.university.k1584.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This is the main canvas on which all the editing will take place. It consists
 * of this panel that is scrollable and comes with a predefined size. Its size
 * can be changed to allow for a larger editing canvas.
 * @see PetriConfig
 * @author Tim Steinbach
 */
public class PetriCanvas extends JScrollPane {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6158181636171175402L;

    /**
     * Policy for horizontal scroll bars.
     */
    private static final int SCROLL_H = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;

    /**
     * Policy for vertical scroll bars.
     */
    private static final int SCROLL_V = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;

    /**
     * Petri net canvas.
     */
    private final JPanel canvas;

    /**
     * Configuration.
     */
    private final PetriConfig config;

    /**
     * Create a new canvas instance.
     */
    PetriCanvas() {

        super();

        this.config = PetriConfig.getInstance();

        this.canvas = new JPanel();

        this.resetCanvas();

        this.canvas.validate();
        this.canvas.repaint();

        final int visibleWidth =
            this.config.getWindowWidth() - this.config.getSidebarWidth();
        final int visibleHeight =
            this.config.getWindowHeight() - this.config.getScrollbarHeight();
        final Dimension visibleSize =
            new Dimension(visibleWidth, visibleHeight);

        this.setPreferredSize(visibleSize);
        this.setSize(visibleSize);

        this.setViewportView(canvas);

        this.setHorizontalScrollBarPolicy(SCROLL_H);
        this.setVerticalScrollBarPolicy(SCROLL_V);

    }

    /**
     * Reset the canvas to its defaults.
     */
    private void resetCanvas() {

        this.canvas.setBackground(Color.WHITE);
        this.canvas.setOpaque(true);

        final int canvasWidth = this.config.getCanvasWidth();
        final int canvasHeight = this.config.getCanvasHeight();

        final Dimension canvasSize = new Dimension(canvasWidth, canvasHeight);

        this.canvas.setPreferredSize(canvasSize);
        this.canvas.setSize(canvasSize);

        this.canvas.setLayout(null);

    }

    /**
     * Add a UI label to the canvas.
     * @param label Label to be added
     */
    public final void add(final AbstractLabel label) {

        this.canvas.add(label);
        this.canvas.validate();
        this.canvas.repaint();

    }

}
