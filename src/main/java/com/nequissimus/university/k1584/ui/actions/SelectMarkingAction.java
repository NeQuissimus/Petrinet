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

import javax.swing.JOptionPane;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriMarking;
import com.nequissimus.university.k1584.ui.MessagePool;

/**
 * Select a marking for the current net.
 * @author Tim Steinbach
 */
public final class SelectMarkingAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Message pool.
     */
    private static final MessagePool MSG = Singleton
        .getObject(MessagePool.class);

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Object[] markings =
            SelectMarkingAction.CONTROLLER.getMarkings().toArray();

        final PetriMarking active =
            SelectMarkingAction.CONTROLLER.getActiveMarking();

        final PetriMarking selected =
            (PetriMarking) JOptionPane.showInputDialog(
                SelectMarkingAction.CONTROLLER.getWindow(),
                SelectMarkingAction.MSG.getSelectMarkingDialog(),
                SelectMarkingAction.MSG.getSelectMarkingDialogTitle(),
                JOptionPane.DEFAULT_OPTION, null, markings, active);

        if ((null != selected) && (active != selected)) {

            SelectMarkingAction.CONTROLLER.switchMarking(selected);

        }

    }
}
