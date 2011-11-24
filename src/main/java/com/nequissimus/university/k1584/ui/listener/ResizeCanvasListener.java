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
package com.nequissimus.university.k1584.ui.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriConstants;

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
                        - PetriConstants.SCROLLBAR_HEIGHT;
                size.width =
                    component.getSize().width
                        - ResizeCanvasListener.CONFIG.getSidebarWidth();

                ResizeCanvasListener.CONTROLLER.resizeEditorWindow(size);

            }

        }

    }

}
