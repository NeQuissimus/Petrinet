package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriNet;

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

    @Override
    public void actionPerformed(final ActionEvent e) {

        // TODO: Message pool

        final Object[] snapshots =
            SelectSnapshotAction.CONTROLLER.getSnapshots().toArray();

        final PetriNet active =
            SelectSnapshotAction.CONTROLLER.getActiveLogic();

        final PetriNet selected =
            (PetriNet) JOptionPane.showInputDialog(
                SelectSnapshotAction.CONTROLLER.getWindow(), "title",
                "select", JOptionPane.DEFAULT_OPTION, null, snapshots,
                active);

        if ((null != selected) && (active != selected)) {

            SelectSnapshotAction.CONTROLLER.selectSnapshot(selected);

        }

    }

}
