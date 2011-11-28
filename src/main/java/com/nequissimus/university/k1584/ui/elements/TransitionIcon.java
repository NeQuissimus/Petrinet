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

import javax.swing.ImageIcon;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.traits.Resizable;

/**
 * Transition icon that is resizable.
 * @author Tim Steinbach
 */
public class TransitionIcon extends AbstractIcon implements Resizable {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = 5314485571108672835L;

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Create new transition icon with a certain size.
     * @param size Icon size
     */
    protected TransitionIcon(final IconSize size) {

        super(size);

        this.setDefaultImage(new ImageIcon(TransitionIcon.CONFIG
            .getImageTransition()));

        this.draw();

    }

    @Override
    public final void resize(final Dimension newSize) {

        this.setSize(newSize);
        this.draw();

    }

}
