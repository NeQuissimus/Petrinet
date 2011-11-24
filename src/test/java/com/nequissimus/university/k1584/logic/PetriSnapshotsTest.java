// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetriSnapshotsTest {

    private static final PetriNet PETRI_NET = new PetriNet("NET");
    private static final String NET_NAME = "NET_NAME";

    private static PetriSnapshots SNAPSHOTS = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        PetriSnapshotsTest.SNAPSHOTS = null;

    }

    @Before
    public void setUp() throws Exception {

        PetriSnapshotsTest.SNAPSHOTS = new PetriSnapshots();

    }

    @After
    public void tearDown() throws Exception {

        PetriSnapshotsTest.SNAPSHOTS = null;

    }

    @Test
    public final void testAddPetriNet() {

        PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.PETRI_NET);

        Assert.assertTrue(PetriSnapshotsTest.SNAPSHOTS.getNets().contains(
            PetriSnapshotsTest.PETRI_NET));

    }

    @Test
    public final void testAddString() {

        final PetriNet net =
            PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.NET_NAME);

        Assert.assertTrue(PetriSnapshotsTest.SNAPSHOTS.getNets().contains(
            net));

    }

    @Test
    public final void testDelete() {

        final PetriNet net =
            PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.NET_NAME);

        Assert.assertTrue(PetriSnapshotsTest.SNAPSHOTS.getNets().contains(
            net));

        PetriSnapshotsTest.SNAPSHOTS.delete(net);

        Assert.assertFalse(PetriSnapshotsTest.SNAPSHOTS.getNets().contains(
            net));

    }

    @Test
    public final void testGetByName() {

        final PetriNet net =
            PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.NET_NAME);

        final PetriNet got =
            PetriSnapshotsTest.SNAPSHOTS
                .getByName(PetriSnapshotsTest.NET_NAME);

        Assert.assertNotNull(got);
        Assert.assertEquals(net, got);

    }

    @Test
    public final void testGetCurrent() {

        final PetriNet net = PetriSnapshotsTest.SNAPSHOTS.getCurrent();

        Assert.assertNotNull(net);

    }

    @Test
    public final void testGetNets() {

        PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.PETRI_NET);

        Assert.assertNotNull(PetriSnapshotsTest.SNAPSHOTS.getNets());
        Assert
            .assertFalse(PetriSnapshotsTest.SNAPSHOTS.getNets().isEmpty());

    }

    @Test
    public final void testRename() {

        PetriSnapshotsTest.SNAPSHOTS.rename(PetriSnapshotsTest.PETRI_NET,
            PetriSnapshotsTest.NET_NAME);

        Assert.assertEquals(PetriSnapshotsTest.NET_NAME,
            PetriSnapshotsTest.PETRI_NET.getName());

    }

    @Test
    public final void testSetCurrent() {

        PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.PETRI_NET);
        final PetriNet net =
            PetriSnapshotsTest.SNAPSHOTS.add(PetriSnapshotsTest.NET_NAME);

        PetriSnapshotsTest.SNAPSHOTS.setCurrent(net);
        Assert.assertEquals(net, PetriSnapshotsTest.SNAPSHOTS.getCurrent());

        PetriSnapshotsTest.SNAPSHOTS
            .setCurrent(PetriSnapshotsTest.PETRI_NET);
        Assert.assertEquals(PetriSnapshotsTest.PETRI_NET,
            PetriSnapshotsTest.SNAPSHOTS.getCurrent());

    }

}
