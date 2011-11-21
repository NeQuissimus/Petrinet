package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.swing.JOptionPane;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.elements.Window;

/**
 * Action that lets the user choose the snapshot to be renamed and then opens a
 * dialog for the user to enter the new name.
 * @author Tim Steinbach
 */
public class RenameSnapshotAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    @Override
    public final void actionPerformed(final ActionEvent e) {

        final Window window = RenameSnapshotAction.CONTROLLER.getWindow();

        final Object[] snapshots =
            RenameSnapshotAction.CONTROLLER.getSnapshots().toArray();

        final PetriNet active =
            RenameSnapshotAction.CONTROLLER.getActiveLogic();

        final PetriNet selected =
            (PetriNet) JOptionPane.showInputDialog(window,
                RenameSnapshotAction.MSG.getSnapshotSelectDialog(),
                RenameSnapshotAction.MSG.getSnapshotRename(),
                JOptionPane.DEFAULT_OPTION, null, snapshots, active);

        if (null != selected) {

            final List<String> takenNames = this.getTakenNames();

            boolean foundOrCancelled = false;

            final String oldName = selected.getName();

            while (!foundOrCancelled) {

                final String newName =
                    (String) JOptionPane.showInputDialog(window,
                        RenameSnapshotAction.MSG.getSnapshotRenameDialog(),
                        RenameSnapshotAction.MSG.getSnapshotRename(),
                        JOptionPane.QUESTION_MESSAGE, null, null, oldName);

                if ((null != newName) && (takenNames.contains(newName))) {

                    RenameSnapshotAction.CONTROLLER.reportMessage(
                        Severity.REPORT, RenameSnapshotAction.MSG
                            .getMsgErrorSnapshotNameTaken());

                } else {

                    foundOrCancelled = true;

                    if (null != newName) {
                        selected.setName(newName);
                    }

                }

            }

        }

    }

    /**
     * Get a list of all snapshot names that have been taken so far.
     * @return List of used names
     */
    private List<String> getTakenNames() {

        final List<PetriNet> nets =
            RenameSnapshotAction.CONTROLLER.getSnapshots();
        final List<String> names = new ArrayList<String>();

        for (final PetriNet petriNet : nets) {

            names.add(petriNet.getName());

        }

        return names;

    }

}
