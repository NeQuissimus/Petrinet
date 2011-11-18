package com.nequissimus.university.k1584.ui.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.nequissimus.library.util.IconUtil;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.MessagePool;
import com.nequissimus.university.k1584.ui.elements.AbstractLabel;

/**
 * Action that allows the user to rename a given label object.
 * @author Tim Steinbach
 */
public final class RenameObjectAction implements ActionListener {

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

    /**
     * Invoking object.
     */
    private final AbstractLabel invoker;

    /**
     * Create a new action allowing the user to rename the invoking object.
     * @param invoker Invoking object
     */
    public RenameObjectAction(final AbstractLabel invoker) {

        this.invoker = invoker;

    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        final Icon icon =
            IconUtil.getIcon(RenameObjectAction.CONFIG.getImageQuestion(),
                new Dimension(100, 100));

        final String newName =
            (String) JOptionPane.showInputDialog(
                RenameObjectAction.CONTROLLER.getWindow(),
                RenameObjectAction.MSG.getIconMenuRenameTitle(),
                RenameObjectAction.MSG.getIconMenuRenameWindowTitle(),
                JOptionPane.QUESTION_MESSAGE, icon, null,
                this.invoker.getText());

        if (newName != null) {

            RenameObjectAction.CONTROLLER
                .renameLabel(this.invoker, newName);

        }

    }

}
