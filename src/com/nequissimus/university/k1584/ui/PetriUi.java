package com.nequissimus.university.k1584.ui;

import java.awt.Dimension;
import java.awt.Point;

import javax.print.attribute.standard.Severity;

import com.nequissimus.university.k1584.ui.elements.AbstractLabel;
import com.nequissimus.university.k1584.ui.elements.Arrow;
import com.nequissimus.university.k1584.ui.elements.PetriCanvas;
import com.nequissimus.university.k1584.ui.elements.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.elements.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.elements.PetriWindow;
import com.nequissimus.university.k1584.ui.enums.IconSize;

public interface PetriUi {

    public PetriWindow getWindow();
    public IconSize getIconSize();
    public void setIconSize(final IconSize size);
    public void redrawCanvas();
    public PetriPlaceLabel addPlace();
    public PetriTransitionLabel addTransition();
    public void removeLabel(final AbstractLabel label);
    public void reportMessage(final Severity severity, final String message);
    public void moveLabel(final AbstractLabel label, final Point location);
    public void resizeArrowCanvas(final Dimension size);
    public void resizeCanvas(final Dimension size);
    public PetriCanvas getCanvas();
    public void removeArrow(final Arrow arrow);
    
}
