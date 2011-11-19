package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;

/**
 * This Listener extends {@link ResizeListener} by the feature of resizing the
 * canvas and all canvases used by connecting arrows in height according to the
 * original source. The slave component will always be the same height as the
 * original source component.
 * @author Tim Steinbach
 */
public class ResizeCanvasListener extends ResizeListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Create a new listener. Only allow resizing larger than a minimum size.
     * Automatically resize a slave component.
     * @param minSize Minimum size
     */
    public ResizeCanvasListener(final Dimension minSize) {

        super(minSize);

    }

    @Override
    public final void componentResized(final ComponentEvent e) {

        super.componentResized(e);

        if (null != e) {

            final Dimension size = new Dimension(0, 0);

            final Component component = (Component) e.getSource();

            if (null != component) {

                size.height =
                    component.getSize().height
                        - ResizeCanvasListener.CONFIG.getScrollbarHeight();
                size.width =
                    component.getSize().width
                        - ResizeCanvasListener.CONFIG.getSidebarWidth();

                ResizeCanvasListener.CONTROLLER.resizeEditorWindow(size);

            }

        }

    }

}
