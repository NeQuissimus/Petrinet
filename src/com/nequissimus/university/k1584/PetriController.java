package com.nequissimus.university.k1584;

import java.awt.Dimension;
import java.awt.Point;

import com.nequissimus.university.k1584.logic.PetriConfig;
import com.nequissimus.university.k1584.logic.PetriNet;
import com.nequissimus.university.k1584.logic.PetriObject;
import com.nequissimus.university.k1584.logic.PetriPlace;
import com.nequissimus.university.k1584.logic.PetriSnapshots;
import com.nequissimus.university.k1584.logic.PetriTransition;
import com.nequissimus.university.k1584.ui.AbstractLabel;
import com.nequissimus.university.k1584.ui.PetriCanvas;
import com.nequissimus.university.k1584.ui.PetriPlaceLabel;
import com.nequissimus.university.k1584.ui.PetriTransitionLabel;
import com.nequissimus.university.k1584.ui.PetriWindow;
import com.nequissimus.university.k1584.ui.enums.IconSize;

public class PetriController implements Runnable {

    // Always the controller that was created last (There can only be one really)
    private static PetriController controller;

    final PetriWindow ui;
    final PetriSnapshots logic;

    private final PetriConfig config = PetriConfig.getInstance();

    private PetriNet currentNet = null;

    private IconSize iconSize = IconSize.VERY_SMALL;

    public PetriController() {

	PetriController.setController(this);

	this.ui = new PetriWindow();
	this.logic = new PetriSnapshots();

	this.logic.add(this.config.getNetName());
	this.currentNet = this.logic.getCurrent();

    }
    
    private static void setController(PetriController controller) {PetriController.controller = (null != controller) ? controller : PetriController.getInstance();}
    
    public static PetriController getInstance() {return PetriController.controller;}
    
    @Override
    public void run() {

	ui.validate();
	ui.repaint();
	ui.setVisible(true);

    }

    public Dimension getIconSize() {return this.iconSize.getSize();}
    public void setIconSize(final IconSize size) {this.iconSize = size;}

    public void addPlace() {

	PetriPlace place = this.currentNet.addPlace();

	PetriPlaceLabel label = new PetriPlaceLabel(place);

	addLabel(label);

    }

    public void addTransition() {

	PetriTransition transition = this.currentNet.addTransition();

	PetriTransitionLabel label = new PetriTransitionLabel(transition);

	addLabel(label);

    }

    private void addLabel(final AbstractLabel label) {

	PetriCanvas canvas = PetriWindow.getCanvas();

	label.setLocation(0, 0);
	canvas.add(label);
	
    }

    public String getName(final PetriObject object) {

	return this.currentNet.getName(object);

    }

    public void setSize(final PetriObject object, final Dimension newSize) {

	this.currentNet.setSize(object, newSize);

    }

    public void setPosition(final PetriObject object, final Point position) {

	this.currentNet.setPosition(object, position);

    }

}
