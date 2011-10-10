package com.nequissimus.university.k1584.logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetriNetTest {
    
    PetriNet net;
    PetriPlace place1;
    PetriPlace place2;
    PetriPlace place3;
    PetriPlace place4;
    PetriPlace place5;
    PetriTransition trans1;
    PetriTransition trans2;

    @Before
    public void setUp() throws Exception {
	
	this.net = new PetriNet();
	
	this.place1 = this.net.addPlace();
	this.net.rename(this.place1, "Place1");

	this.place2 = this.net.addPlace();
	this.net.rename(this.place2, "Place2");
	
	this.place3 = this.net.addPlace();
	this.net.rename(this.place3, "Place3");
	
	this.place4 = this.net.addPlace();
	this.net.rename(this.place4, "Place4");
	
	this.place5 = this.net.addPlace();
	this.net.rename(this.place5, "Place5");

	this.trans1 = this.net.addTransition();
	this.trans2 = this.net.addTransition();
	
	this.net.connect(place5, trans2);
	this.net.connect(trans2, place4);
	
    }

    @After
    public void tearDown() throws Exception {
	
    }

    @Test
    public final void testAddPlace() {
	
	Assert.assertNotNull(this.place1);
	Assert.assertNotNull(this.place2);
	Assert.assertNotNull(this.place3);
	Assert.assertNotNull(this.place4);

	Assert.assertTrue(this.net.places.contains(place1));
	Assert.assertTrue(this.net.places.contains(place2));
	Assert.assertTrue(this.net.places.contains(place3));
	Assert.assertTrue(this.net.places.contains(place4));
	
    }

    @Test
    public final void testAddTransition() {

	Assert.assertNotNull(this.trans1);
	Assert.assertNotNull(this.trans2);

	Assert.assertTrue(this.net.transitions.contains(trans1));
	Assert.assertTrue(this.net.transitions.contains(trans2));
	
    }

    @Test
    public final void testConnectPetriPlacePetriTransition() {
	
	this.net.connect(this.place1, this.trans1);
	this.net.connect(this.place2, this.trans1);

	Assert.assertTrue(this.trans1.input.contains(this.place1));
	Assert.assertTrue(this.trans1.input.contains(this.place2));
	
    }

    @Test
    public final void testConnectPetriTransitionPetriPlace() {
	
	this.net.connect(trans1, place3);
	
	Assert.assertTrue(this.trans1.output.contains(place3));
	
    }
    
    @Test
    public final void testIncreaseMarkings() {
	
	this.net.increaseMarkings(place1);
	this.net.increaseMarkings(place1);
	this.net.increaseMarkings(place2);
	
	Assert.assertEquals(2, this.net.getMarkings(place1));
	Assert.assertEquals(1, this.net.getMarkings(place2));
	
    }

    @Test
    public final void testDecreaseMarkings() {
	
	this.net.decreaseMarkings(place3);
	
	Assert.assertEquals(0, this.net.getMarkings(place3));
	
	this.net.increaseMarkings(place3);
	
	Assert.assertEquals(1, this.net.getMarkings(place3));
	
    }

    @Test
    public final void testRemovePetriPlace() {

	Assert.assertTrue(this.net.places.contains(place4));	
	
	this.net.remove(place4);
	
	Assert.assertFalse(this.net.places.contains(place4));
	
    }

    @Test
    public final void testRemovePetriTransition() {

	Assert.assertTrue(this.net.transitions.contains(trans2));	
	
	this.net.remove(trans2);
	
	Assert.assertFalse(this.net.transitions.contains(trans2));
	
    }

    @Test
    public final void testOccur() {
	
	int in = this.net.getMarkings(place5);
	int out = this.net.getMarkings(place4);
	
	if (in == 0) {
	
	    in = 1;
	    this.net.increaseMarkings(place5);
	
	}
	    
	this.net.occur(trans2);
	
	Assert.assertEquals(out + 1, this.net.getMarkings(place4));
	Assert.assertEquals(in - 1, this.net.getMarkings(place5));
	
    }

    @Test
    public final void testIsActive() {
	
	int in = this.net.getMarkings(place5);
	
	if (in == 0)
	    this.net.increaseMarkings(place5);
	
	if (trans2.input.size() == 1) {
	    
	    Assert.assertTrue(this.net.isActive(trans2));
	    
	}
	
    }


    @Test
    public final void testRename() {
	
	String oldName = this.net.getName(place5);
	
	this.net.rename(place5, "1" + oldName);
	
	Assert.assertEquals("1" + oldName, this.net.getName(place5));
	
    }

}
