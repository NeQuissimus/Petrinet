package com.nequissimus.university.k1584.ui.elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

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
public class Canvas extends JScrollPane {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 6158181636171175402L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Policy for horizontal scroll bars.
     */
    private static final int SCROLL_H =
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;

    /**
     * Policy for vertical scroll bars.
     */
    private static final int SCROLL_V =
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;

    /**
     * Petri net canvas.
     */
    private final JPanel canvas;

    /**
     * Create a new canvas instance.
     */
    Canvas() {

        super();

        this.canvas = new JPanel();

        this.resetCanvas();

        this.canvas.validate();
        this.canvas.repaint();

        final int visibleWidth =
            Canvas.CONFIG.getWindowWidth()
                - Canvas.CONFIG.getSidebarWidth();
        final int visibleHeight =
            Canvas.CONFIG.getWindowHeight()
                - Canvas.CONFIG.getScrollbarHeight();
        final Dimension visibleSize =
            new Dimension(visibleWidth, visibleHeight);

        this.setPreferredSize(visibleSize);
        this.setSize(visibleSize);

        this.setViewportView(this.canvas);

        this.setHorizontalScrollBarPolicy(Canvas.SCROLL_H);
        this.setVerticalScrollBarPolicy(Canvas.SCROLL_V);

    }

    /**
     * Reset the canvas to its defaults.
     */
    private void resetCanvas() {

        this.canvas.setBackground(Color.WHITE);
        this.canvas.setOpaque(true);

        final int canvasWidth = Canvas.CONFIG.getCanvasWidth();
        final int canvasHeight = Canvas.CONFIG.getCanvasHeight();

        final Dimension canvasSize =
            new Dimension(canvasWidth, canvasHeight);

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

    /**
     * Get all labels currently displayed on the canvas.
     * @return Set of labels
     */
    public final Set<AbstractLabel> getLabels() {

        final Component[] components = this.canvas.getComponents();

        final Set<AbstractLabel> labels = new HashSet<AbstractLabel>();

        for (final Component component : components) {

            if (component instanceof AbstractLabel) {
                labels.add((AbstractLabel) component);
            }

        }

        return labels;

    }

    /**
     * Get canvas.
     * @return Canvas
     */
    public final JPanel getCanvas() {

        return this.canvas;

    }

    @Override
    public final void repaint() {
        this.revalidate();
        super.repaint();

        if (null != this.canvas) {
            this.canvas.revalidate();
            this.canvas.repaint();
        }

    }

}
