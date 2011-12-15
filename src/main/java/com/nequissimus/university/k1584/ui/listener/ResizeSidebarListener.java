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

package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.ui.elements.Sidebar;

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
    private final Sidebar sidebar;

    /**
     * Create new sidebar resize listener.
     * @param minSize Minimum size
     * @param sidebar Sidebar
     */
    public ResizeSidebarListener(final Dimension minSize,
        final Sidebar sidebar) {

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
