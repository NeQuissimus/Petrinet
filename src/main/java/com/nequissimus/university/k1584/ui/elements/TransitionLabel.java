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

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.listener.CanvasTransitionListener;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.listener.SelectListener;
import com.nequissimus.university.k1584.ui.traits.Draggable;

/**
 * UI label for transition objects. This label consists of the transition icon
 * and a name tag.
 * @author Tim Steinbach
 */
public class TransitionLabel extends AbstractLabel implements Draggable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5447519082475950763L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener dragDropListener;

    /**
     * Mouse listener for selecting multiple labels.
     */
    private SelectListener selectListener;

    /**
     * Create a transition label without connecting it to a logical transition
     * (for the sidebar). Such a label has no UI behaviour (e.g. drag and drop)
     * either. It does have a context menu by default.
     */
    public TransitionLabel() {

        super();

        this.addSidebarMenu();
        this.setText(TransitionLabel.CONFIG.getTransitionName());

    }

    /**
     * Create a new label for a logical transition.
     * @param name Transition name
     */
    public TransitionLabel(final String name) {

        super(name);

        this.registerDraggable();
        this.addCanvasMenu();

    }

    @Override
    public final void addCanvasMenu() {

        this.addMouseListener(new CanvasTransitionListener(this));

    }

    /**
     * Get icon of particular size.
     * @param size Size
     * @return Icon
     */
    @Override
    public final AbstractIcon getPetriIcon(final IconSize size) {

        return new TransitionIcon(size);

    }

    @Override
    public final void registerDraggable() {

        this.selectListener = new SelectListener(this);
        this.dragDropListener = new DragListener(this);

        this.addMouseListener(this.selectListener);
        this.addMouseListener(this.dragDropListener);
        this.addMouseMotionListener(this.dragDropListener);

    }

    @Override
    public final void unregisterDraggable() {

        this.removeMouseListener(this.selectListener);
        this.removeMouseListener(this.dragDropListener);
        this.removeMouseMotionListener(this.dragDropListener);

    }

}
