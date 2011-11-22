package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetriObjectTest {

    private static final String OBJECT_NAME = "OBJ_NAME";
    private static final String OBJECT_ID = "OBJ_ID";

    private static final String DIFFERENT_NAME = "OBJ_NAME2";

    private static final Point OBJECT_POS = new Point(123, 456);
    private static final Dimension OBJECT_SIZE = new Dimension(111, 222);

    private PetriObject object = null;

    @Before
    public void setUp() throws Exception {

        this.object =
            new PetriObjectImpl(PetriObjectTest.OBJECT_NAME,
                PetriObjectTest.OBJECT_ID);

    }

    @Test
    public final void testPetriObjectStringString() {

        Assert.assertNotNull(this.object);

        Assert.assertEquals(PetriObjectTest.OBJECT_ID, this.object.getId());
        Assert.assertEquals(PetriObjectTest.OBJECT_NAME,
            this.object.getName());

    }

    @Test
    public final void testSetName() {

        this.object.setName(PetriObjectTest.DIFFERENT_NAME);

        Assert.assertEquals(PetriObjectTest.DIFFERENT_NAME,
            this.object.getName());

    }

    @Test
    public final void testSetPosition() {

        this.object.setPosition(PetriObjectTest.OBJECT_POS);

        Assert.assertEquals(PetriObjectTest.OBJECT_POS,
            this.object.getPosition());

    }

    @Test
    public final void testSetSize() {

        this.object.setSize(PetriObjectTest.OBJECT_SIZE);

        Assert.assertEquals(PetriObjectTest.OBJECT_SIZE,
            this.object.getSize());

    }

}
