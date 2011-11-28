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
package com.nequissimus.university.k1584.ui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.library.util.IconUtil;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.ui.MessagePool;

/**
 * Action for creating a new snapshot. Snapshots have to have unique names.
 * @author Tim Steinbach
 */
public final class CreateSnapshotAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    @Override
    public void actionPerformed(final ActionEvent e) {

        final List<String> usedNames = this.getTakenNames();

        final Icon icon =
            IconUtil.getIcon(CreateSnapshotAction.CONFIG.getImageQuestion()
                .getFile(), new Dimension(100, 100));

        boolean foundOrCancelled = false;

        String newName = null;

        while (!foundOrCancelled) {

            newName =
                (String) JOptionPane.showInputDialog(
                    CreateSnapshotAction.CONTROLLER.getWindow(),
                    CreateSnapshotAction.MSG.getSnapshotRenameDialog(),
                    CreateSnapshotAction.MSG.getSnapshotCreate(),
                    JOptionPane.QUESTION_MESSAGE, icon, null,
                    CreateSnapshotAction.CONFIG.getNetName());

            if ((null != newName) && (usedNames.contains(newName))) {

                CreateSnapshotAction.CONTROLLER
                    .reportMessage(Severity.REPORT,
                        CreateSnapshotAction.MSG
                            .getMsgErrorSnapshotNameTaken());

            } else {

                foundOrCancelled = true;

            }

        }

        if (null != newName) {

            CreateSnapshotAction.CONTROLLER.createSnapshot(newName);

        }

    }

    /**
     * Get a list of all snapshot names that have been taken so far.
     * @return List of used names
     */
    private List<String> getTakenNames() {

        final List<PetriNet> nets =
            CreateSnapshotAction.CONTROLLER.getSnapshots();
        final List<String> names = new ArrayList<String>();

        for (final PetriNet petriNet : nets) {

            names.add(petriNet.getName());

        }

        return names;

    }

}
