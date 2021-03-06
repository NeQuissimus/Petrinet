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

package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.ui.elements.PlaceLabel;

/**
 * Action for increasing the number of markings on a place.
 * @author Tim Steinbach
 */
public final class IncreaseMarkingsAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Invoking place label.
     */
    private final PlaceLabel invoker;

    /**
     * Create a new action to increase the markings.
     * @param invoker Invoking label
     */
    public IncreaseMarkingsAction(final PlaceLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        if (null != this.invoker) {
            IncreaseMarkingsAction.CONTROLLER.increaseTokens(this.invoker);
        }

    }

}
