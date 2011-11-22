package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.ui.MessagePool;

/**
 * Action for deleting a snapshot.
 * @author Tim Steinbach
 */
public final class DeleteSnapshotAction implements ActionListener {

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
    public void actionPerformed(final ActionEvent e) {

        final Object[] snapshots =
            DeleteSnapshotAction.CONTROLLER.getSnapshots().toArray();

        final PetriNet selected =
            (PetriNet) JOptionPane.showInputDialog(
                DeleteSnapshotAction.CONTROLLER.getWindow(),
                DeleteSnapshotAction.MSG.getSnapshotSelectDialog(),
                DeleteSnapshotAction.MSG.getSnapshotSelect(),
                JOptionPane.DEFAULT_OPTION, null, snapshots, snapshots[0]);

        if (null != selected) {

            DeleteSnapshotAction.CONTROLLER.deleteSnapshot(selected);

        }

    }

}
