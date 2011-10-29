package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.ui.PetriSidebar;

/**
 * This listener automatically resizes and moves a sidebar component. (It will
 * always have the same size as the window and be moved to the right side of the
 * window)
 * @author Tim Steinbach
 */
public class ResizeSidebarListener extends ResizeListener {

    /**
     * Sidebar UI.
     */
    private final PetriSidebar sidebar;

    /**
     * Create new sidebar resize listener.
     * @param minSize Minimum size
     * @param sidebar Sidebar
     */
    public ResizeSidebarListener(final Dimension minSize,
        final PetriSidebar sidebar) {

        super(minSize);
        this.sidebar = sidebar;

    }

    @Override
    public final void componentResized(final ComponentEvent e) {

        super.componentResized(e);

        if (this.sidebar != null) {

            final Dimension size = this.sidebar.getSize();

            final Component component = (Component) e.getSource();

            size.height = component.getSize().height;

            this.sidebar.setSize(size);

            this.sidebar.setLocation(
                component.getWidth() - this.sidebar.getWidth(), 0);

            this.sidebar.validate();
            this.sidebar.repaint();

        }

    }

}
