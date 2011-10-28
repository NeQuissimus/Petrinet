package com.nequissimus.university.k1584.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.print.attribute.standard.Severity;
import javax.swing.JFileChooser;

import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;

/**
 * Action called when trying to save a file. A dialog window will be opened to
 * choose the file's location.
 * @author Tim Steinbach
 */
public class SaveFileAction implements ActionListener {

    /**
     * Configuration.
     */
    private final PetriConfig config;

    /**
     * Create a new action instance.
     */
    public SaveFileAction() {

        this.config = PetriConfig.getInstance();

    }

    @Override
    public final void actionPerformed(final ActionEvent arg0) {

        final JFileChooser fileChooser = new JFileChooser();

        fileChooser.showSaveDialog(PetriController.getInstance()
            .getWindow());

        final File file = fileChooser.getSelectedFile();

        if (null != file) {

            try {

                PetriController.getInstance().save(file);

            } catch (PnmlException e) {

                PetriController.getInstance().reportMessage(Severity.ERROR,
                    this.config.getMsgErrorSaveFile());

            }

        }

    }

}
