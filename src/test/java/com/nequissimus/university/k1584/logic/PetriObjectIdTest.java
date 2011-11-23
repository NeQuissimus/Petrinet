// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetriObjectIdTest {

    private static final String USED_ID =
        "487ecb46-94e1-43b4-b790-e6abd9e232b3";

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

        PetriObjectId.resetUsedIds();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testAddUsedId() {

        PetriObjectId.addUsedId(PetriObjectIdTest.USED_ID);
        Assert.assertTrue(PetriObjectId.isUsed(PetriObjectIdTest.USED_ID));

    }

    @Test
    public final void testGetId() {

        final List<String> ids = new ArrayList<String>();

        for (int i = 0; i < 10000; i++) {

            final String id = PetriObjectId.getId();

            if (ids.contains(id)) {
                Assert.fail();
            } else {
                ids.add(id);
            }

        }

    }

    @Test
    public final void testResetUsedIds() {

        PetriObjectId.addUsedId(PetriObjectIdTest.USED_ID);
        Assert.assertTrue(PetriObjectId.isUsed(PetriObjectIdTest.USED_ID));

        PetriObjectId.resetUsedIds();
        Assert.assertFalse(PetriObjectId.isUsed(PetriObjectIdTest.USED_ID));

    }

}
