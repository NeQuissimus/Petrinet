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
    
    private static void setController(PetriController controller) {PetriController.controller = controller;}

    @Override
    public void run() {

	ui.validate();
	ui.repaint();
	ui.setVisible(true);

    }

    public static Dimension getIconSize() {return controller.iconSize.getSize();}
    public static void setIconSize(final IconSize size) {controller.iconSize = size;}

    public static void addPlace() {

	PetriPlace place = controller.currentNet.addPlace();

	PetriPlaceLabel label = new PetriPlaceLabel(place);

	addLabel(label);

    }

    public static void addTransition() {

	PetriTransition transition = controller.currentNet.addTransition();

	PetriTransitionLabel label = new PetriTransitionLabel(transition);

	addLabel(label);

    }

    private static void addLabel(final AbstractLabel label) {

	PetriCanvas canvas = PetriWindow.getCanvas();

	label.setLocation(0, 0);
	canvas.add(label);
	
    }

    public static String getName(final PetriObject object) {

	return controller.currentNet.getName(object);

    }

    public static void setSize(final PetriObject object, final Dimension newSize) {

	controller.currentNet.setSize(object, newSize);

    }

    public static void setPosition(final PetriObject object, final Point position) {

	controller.currentNet.setPosition(object, position);

    }

}
