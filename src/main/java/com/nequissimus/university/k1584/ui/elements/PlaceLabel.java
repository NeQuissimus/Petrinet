// @formatter:off
// CHECKSTYLE:OFF
/******************************************************************************* 
 * Copyright (c) 2011 Tim Steinbach
 * 
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit 
 * persons to whom the Software is furnished to do so, subject 
 * to the following conditions:
 * 
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY 
 * OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS 
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO 
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN 
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE 
 * OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 ******************************************************************************/
// @formatter:on
// CHECKSTYLE:ON

package com.nequissimus.university.k1584.ui.elements;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.ui.enums.IconSize;
import com.nequissimus.university.k1584.ui.listener.CanvasPlaceListener;
import com.nequissimus.university.k1584.ui.listener.DragListener;
import com.nequissimus.university.k1584.ui.listener.SelectListener;

/**
 * UI label for place objects. This label consists of an icon and a name tag.
 * @author Tim Steinbach
 */
public class PlaceLabel extends AbstractLabel {

    /**
     * Configuration.
     */
    private static final PetriConfig CONFIG = Singleton
        .getObject(PetriConfig.class);

    /**
     * Serializable UID.
     */
    private static final long serialVersionUID = -1093238662290231525L;

    /**
     * Mouse listener for drag and drop.
     */
    private DragListener mouseListener;

    /**
     * Mouse listener for selecting multiple labels.
     */
    private SelectListener selectListener;

    /**
     * Number of tokens.
     */
    private int tokens = 0;

    /**
     * Create a place label without connecting it to a logical place (for the
     * sidebar). Such a label has no UI behaviour (e.g. drag and drop) either.
     * It does have a context menu by default.
     */
    public PlaceLabel() {

        super();

        this.setText(PlaceLabel.CONFIG.getPlaceName());

        this.addSidebarMenu();

    }

    /**
     * Create a new label instance for a given logical place.
     * @param name Place name
     */
    public PlaceLabel(final String name) {

        super(name);

        this.registerDraggable();
        this.addCanvasMenu();

    }

    @Override
    public final void addCanvasMenu() {

        this.addMouseListener(new CanvasPlaceListener(this));

    }

    @Override
    public final AbstractIcon getPetriIcon(final IconSize size) {

        final PlaceIcon icon = new PlaceIcon(size);
        icon.setTokens(this.tokens);

        return icon;

    }

    /**
     * Get number of tokens.
     * @return Tokens
     */
    public final int getTokens() {
        return this.tokens;
    }

    @Override
    public final void registerDraggable() {

        this.selectListener = new SelectListener(this);
        this.mouseListener = new DragListener(this);

        this.addMouseListener(this.selectListener);
        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);

    }

    /**
     * Set new number of tokens.
     * @param value Tokens
     */
    public final void setTokens(final int value) {

        this.tokens = value;

        final PlaceIcon icon = ((PlaceIcon) this.getIcon());
        icon.setTokens(this.tokens);
        icon.draw();

    }

    @Override
    public final void unregisterDraggable() {

        this.removeMouseListener(this.selectListener);
        this.removeMouseListener(this.mouseListener);
        this.removeMouseMotionListener(this.mouseListener);

    }

}
