// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetriObjectTest {

    private static final String OBJECT_NAME = "OBJ_NAME";
    private static final String OBJECT_ID = "OBJ_ID";

    private static final String DIFFERENT_NAME = "OBJ_NAME2";

    private static final Point OBJECT_POS = new Point(123, 456);
    private static final Dimension OBJECT_SIZE = new Dimension(111, 222);

    private static PetriObject OBJECT = null;

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        PetriObjectTest.OBJECT = null;

    }

    @Before
    public void setUp() throws Exception {

        PetriObjectTest.OBJECT =
            new PetriObjectImpl(PetriObjectTest.OBJECT_NAME,
                PetriObjectTest.OBJECT_ID);

    }

    @Test
    public final void testPetriObjectStringString() {

        Assert.assertNotNull(PetriObjectTest.OBJECT);

        Assert.assertEquals(PetriObjectTest.OBJECT_ID,
            PetriObjectTest.OBJECT.getId());
        Assert.assertEquals(PetriObjectTest.OBJECT_NAME,
            PetriObjectTest.OBJECT.getName());

    }

    @Test
    public final void testSetName() {

        PetriObjectTest.OBJECT.setName(PetriObjectTest.DIFFERENT_NAME);

        Assert.assertEquals(PetriObjectTest.DIFFERENT_NAME,
            PetriObjectTest.OBJECT.getName());

    }

    @Test
    public final void testSetPosition() {

        PetriObjectTest.OBJECT.setPosition(PetriObjectTest.OBJECT_POS);

        Assert.assertEquals(PetriObjectTest.OBJECT_POS,
            PetriObjectTest.OBJECT.getPosition());

    }

    @Test
    public final void testSetSize() {

        PetriObjectTest.OBJECT.setSize(PetriObjectTest.OBJECT_SIZE);

        Assert.assertEquals(PetriObjectTest.OBJECT_SIZE,
            PetriObjectTest.OBJECT.getSize());

    }

}
