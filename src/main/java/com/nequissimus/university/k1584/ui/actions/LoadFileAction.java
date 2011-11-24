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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.print.attribute.standard.Severity;
import javax.swing.JFileChooser;

import com.nequissimus.library.io.ExtensionFileFilter;
import com.nequissimus.library.os.User;
import com.nequissimus.university.k1584.PetriController;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriConstants;
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
    public void actionPerformed(final ActionEvent arg0) {

        final JFileChooser fileChooser =
            new JFileChooser(User.getUserHome());

        final Window window = LoadFileAction.CONTROLLER.getWindow();

        final ExtensionFileFilter filter =
            new ExtensionFileFilter(
                LoadFileAction.CONFIG.getFileExtension());

        filter.setDescription(PetriConstants.FILE_EXTENSION_DESCRIPTION);

        fileChooser.setFileFilter(filter);

        fileChooser.showOpenDialog(window);

        final File file = fileChooser.getSelectedFile();

        if (null != file) {

            try {

                LoadFileAction.CONTROLLER.load(file);

            } catch (final PnmlException e) {

                e.printStackTrace();

                LoadFileAction.CONTROLLER.reportMessage(Severity.ERROR,
                    LoadFileAction.MSG.getMsgErrorLoadFile());

            }

        }

    }
}
