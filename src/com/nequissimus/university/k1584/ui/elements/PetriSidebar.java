package com.nequissimus.university.k1584.ui.elements;

import javax.swing.JPanel;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.TextPosition;

/**
 * Sidebar UI for adding new elements to the canvas.
 * @author Tim Steinbach
 *
 */
public class PetriSidebar extends JPanel {

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -7934305863238811085L;

    /**
     * Configuration instance.
     */
    private final PetriConfig config = PetriConfig.getInstance();

    /**
     * Create a new sidebar instance.
     */
    PetriSidebar() {

        super();

        final int width = this.config.getSidebarWidth();
        final int height = this.config.getWindowHeight();

        // Use same color as window background (This prevents ugly effects when
        // resizing the window)
        this.setOpaque(false);

        this.addIcons();

        this.setSize(width, height);

    }

    /**
     * Add the default icons to the sidebar.
     * (1 place, 1 transition)
     */
    private void addIcons() {

        PetriPlaceLabel label = new PetriPlaceLabel();
        label.moveText(TextPosition.BELOW);
        label.setBounds(10, 10, 100, 100);

        this.add(label);

        PetriTransitionLabel label2 = new PetriTransitionLabel();
        label2.moveText(TextPosition.BELOW);
        label2.setBounds(0, 120, 100, 100);

        this.add(label2);

    }

}
