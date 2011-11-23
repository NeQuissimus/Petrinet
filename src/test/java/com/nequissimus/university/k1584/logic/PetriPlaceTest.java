// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetriPlaceTest {

    private static final String PLACE_NAME = "PLACE_NAME";
    private static final String PLACE_ID = "PLACE_ID";
    private static final int PLACE_MARKINGS = 5;

    private static PetriPlace PLACE = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        PetriPlaceTest.PLACE = null;

    }

    @Before
    public void setUp() throws Exception {

        PetriPlaceTest.PLACE =
            new PetriPlace(PetriPlaceTest.PLACE_NAME,
                PetriPlaceTest.PLACE_ID);

    }

    @After
    public void tearDown() throws Exception {

        PetriPlaceTest.PLACE = null;

    }

    @Test
    public final void testClone() {

        final PetriPlace place = PetriPlaceTest.PLACE.clone();

        Assert.assertNotSame(place, PetriPlaceTest.PLACE);
        Assert.assertNotSame(PetriPlaceTest.PLACE.getId(), place.getId());
        Assert.assertEquals(PetriPlaceTest.PLACE.getMarkings(),
            place.getMarkings());
        Assert
            .assertEquals(PetriPlaceTest.PLACE.getName(), place.getName());
        Assert.assertEquals(PetriPlaceTest.PLACE.getPosition(),
            place.getPosition());
        Assert
            .assertEquals(PetriPlaceTest.PLACE.getSize(), place.getSize());

    }

    @Test
    public final void testDecreaseMarkings() {

        PetriPlaceTest.PLACE.setMarkings(PetriPlaceTest.PLACE_MARKINGS);
        PetriPlaceTest.PLACE.decreaseMarkings();

        Assert.assertEquals(PetriPlaceTest.PLACE_MARKINGS - 1,
            PetriPlaceTest.PLACE.getMarkings());

        PetriPlaceTest.PLACE.setMarkings(0);
        PetriPlaceTest.PLACE.decreaseMarkings();

        Assert.assertEquals(0, PetriPlaceTest.PLACE.getMarkings());

        PetriPlaceTest.PLACE.setMarkings(-1);
        PetriPlaceTest.PLACE.decreaseMarkings();

        Assert.assertEquals(0, PetriPlaceTest.PLACE.getMarkings());

    }

    @Test
    public final void testGetMarkings() {

        PetriPlaceTest.PLACE.setMarkings(PetriPlaceTest.PLACE_MARKINGS);

        Assert.assertEquals(PetriPlaceTest.PLACE_MARKINGS,
            PetriPlaceTest.PLACE.getMarkings());

    }

    @Test
    public final void testIncreaseMarkings() {

        PetriPlaceTest.PLACE.setMarkings(PetriPlaceTest.PLACE_MARKINGS);
        PetriPlaceTest.PLACE.increaseMarkings();

        Assert.assertEquals(PetriPlaceTest.PLACE_MARKINGS + 1,
            PetriPlaceTest.PLACE.getMarkings());

        PetriPlaceTest.PLACE.setMarkings(Integer.MAX_VALUE);
        PetriPlaceTest.PLACE.increaseMarkings();

        Assert.assertEquals(Integer.MAX_VALUE,
            PetriPlaceTest.PLACE.getMarkings());

    }

    @Test
    public final void testPetriPlaceStringString() {

        Assert.assertNotNull(PetriPlaceTest.PLACE);

        Assert.assertEquals(PetriPlaceTest.PLACE_NAME,
            PetriPlaceTest.PLACE.getName());
        Assert.assertEquals(PetriPlaceTest.PLACE_ID,
            PetriPlaceTest.PLACE.getId());

    }

    @Test
    public final void testSetMarkings() {

        PetriPlaceTest.PLACE.setMarkings(PetriPlaceTest.PLACE_MARKINGS);
        Assert.assertEquals(PetriPlaceTest.PLACE_MARKINGS,
            PetriPlaceTest.PLACE.getMarkings());

    }

}
