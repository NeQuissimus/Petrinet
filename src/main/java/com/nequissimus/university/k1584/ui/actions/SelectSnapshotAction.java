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
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.ui.MessagePool;

/**
 * Action for selecting an active snapshot.
 * @author Tim Steinbach
 */
public final class SelectSnapshotAction implements ActionListener {

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

        final Object[] snapshots =
            SelectSnapshotAction.CONTROLLER.getSnapshots().toArray();

        final PetriNet active =
            SelectSnapshotAction.CONTROLLER.getActiveLogic();

        final PetriNet selected =
            (PetriNet) JOptionPane.showInputDialog(
                SelectSnapshotAction.CONTROLLER.getWindow(),
                SelectSnapshotAction.MSG.getSnapshotSelectDialog(),
                SelectSnapshotAction.MSG.getSnapshotSelect(),
                JOptionPane.DEFAULT_OPTION, null, snapshots, active);

        if ((null != selected) && (active != selected)) {

            SelectSnapshotAction.CONTROLLER.selectSnapshot(selected);

        }

    }

}
