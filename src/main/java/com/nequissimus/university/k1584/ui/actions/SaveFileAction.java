package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.print.attribute.standard.Severity;
import javax.swing.JFileChooser;

import com.nequissimus.library.io.ExtensionFileFilter;
import com.nequissimus.library.os.User;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;
import com.nequissimus.university.k1584.ui.MessagePool;

/**
 * Action called when trying to save a file. A dialog window will be opened to
 * choose the file's location.
 * @author Tim Steinbach
 */
public class SaveFileAction implements ActionListener {

    /**
     * Controller.
     */
    private static final PetriController CONTROLLER = PetriController
        .getInstance();

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Create a new action instance.
     */
    public SaveFileAction() {
    }

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        final JFileChooser fileChooser =
            new JFileChooser(User.getUserHome());

        final ExtensionFileFilter filter =
            new ExtensionFileFilter(
                SaveFileAction.CONFIG.getFileExtension());

        filter.setDescription(SaveFileAction.CONFIG
            .getFileExtensionDescription());

        fileChooser.setFileFilter(filter);

        fileChooser.showSaveDialog(SaveFileAction.CONTROLLER.getWindow());

        final File file = fileChooser.getSelectedFile();

        if (null != file) {

            try {

                SaveFileAction.CONTROLLER.save(file);

            } catch (final PnmlException e) {

                SaveFileAction.CONTROLLER.reportMessage(Severity.ERROR,
                    SaveFileAction.MSG.getMsgErrorSaveFile());

            }

        }

    }

}
