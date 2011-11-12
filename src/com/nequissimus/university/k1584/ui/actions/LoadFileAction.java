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
import com.nequissimus.university.k1584.ui.elements.Window;

/**
 * Action called when trying to load a file. A dialog will be opened to select
 * the file.
 * @author Tim Steinbach
 */
public final class LoadFileAction implements ActionListener {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = PetriConfig.getInstance();

    /**
     * Message pool.
     */
    private static final MessagePool MSG = MessagePool.getInstance();

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        final JFileChooser fileChooser =
            new JFileChooser(User.getUserHome());

        final Window window = PetriController.getInstance().getWindow();

        fileChooser.setFileFilter(new ExtensionFileFilter(
            LoadFileAction.CONFIG.getFileExtension()));

        fileChooser.showOpenDialog(window);

        final File file = fileChooser.getSelectedFile();

        if (null != file) {

            try {

                PetriController.getInstance().load(file);

            } catch (final PnmlException e) {

                e.printStackTrace();

                PetriController.getInstance().reportMessage(Severity.ERROR,
                    LoadFileAction.MSG.getMsgErrorLoadFile());

            }

        }

    }
}
