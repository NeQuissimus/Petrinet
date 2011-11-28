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

import javax.swing.Icon;
import javax.swing.JOptionPane;

import com.nequissimus.library.data.Singleton;
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
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

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
            IconUtil.getIcon(RenameObjectAction.CONFIG.getImageQuestion()
                .getFile(), new Dimension(100, 100));

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
