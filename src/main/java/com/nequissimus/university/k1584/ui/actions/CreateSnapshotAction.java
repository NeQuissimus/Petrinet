package com.nequissimus.university.k1584.ui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.swing.Icon;
import javax.swing.JOptionPane;

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
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

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
