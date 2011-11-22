package com.nequissimus.university.k1584.logic;

import java.util.Properties;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PetriConfigWithoutFileTest {

    private static PetriConfig CONFIG = null;
    private static Properties DEFAULTS = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        PetriConfigWithoutFileTest.CONFIG = PetriConfig.getInstance();
        PetriConfigWithoutFileTest.DEFAULTS =
            PetriConfigWithoutFileTest.CONFIG.getDefaults();

    }

    @Before
    public void setUp() throws Exception {

        PetriConfigWithoutFileTest.CONFIG = PetriConfig.getInstance();

    }

    @After
    public void tearDown() throws Exception {

        PetriConfigWithoutFileTest.CONFIG = null;

    }

    @Test
    public final void testGetApplicationName() {

        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.APPLICATION_NAME),
            PetriConfigWithoutFileTest.CONFIG.getApplicationName());

    }

    @Test
    public final void testGetCanvasHeight() {

        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.CANVAS_HEIGHT),
            PetriConfigWithoutFileTest.CONFIG.getCanvasHeight());

    }

    @Test
    public final void testGetCanvasWidth() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.CANVAS_WIDTH),
            PetriConfigWithoutFileTest.CONFIG.getCanvasWidth());
    }

    @Test
    public final void testGetEdgeIdPrefix() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.PNML_EDGE_ID_PREFIX),
            PetriConfigWithoutFileTest.CONFIG.getEdgeIdPrefix());
    }

    @Test
    public final void testGetFileExtension() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.FILE_EXTENSION),
            PetriConfigWithoutFileTest.CONFIG.getFileExtension());
    }

    @Test
    public final void testGetNetName() {
        Assert.assertEquals(
            PetriConfigWithoutFileTest.DEFAULTS.get(PetriConfig.NET_NAME),
            PetriConfigWithoutFileTest.CONFIG.getNetName());
    }

    @Test
    public final void testGetPlaceName() {
        Assert
            .assertEquals(PetriConfigWithoutFileTest.DEFAULTS
                .get(PetriConfig.PLACE_NAME),
                PetriConfigWithoutFileTest.CONFIG.getPlaceName());
    }

    @Test
    public final void testGetSidebarWidth() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.SIDEBAR_WIDTH),
            PetriConfigWithoutFileTest.CONFIG.getSidebarWidth());
    }

    @Test
    public final void testGetTransitionName() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.TRANSITION_NAME),
            PetriConfigWithoutFileTest.CONFIG.getTransitionName());
    }

    @Test
    public final void testGetWindowHeight() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.WINDOW_HEIGHT),
            PetriConfigWithoutFileTest.CONFIG.getWindowHeight());
    }

    @Test
    public final void testGetWindowMinHeight() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.WINDOW_MIN_HEIGHT),
            PetriConfigWithoutFileTest.CONFIG.getWindowMinHeight());
    }

    @Test
    public final void testGetWindowMinWidth() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.WINDOW_MIN_WIDTH),
            PetriConfigWithoutFileTest.CONFIG.getWindowMinWidth());
    }

    @Test
    public final void testGetWindowTitle() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.WINDOW_TITLE),
            PetriConfigWithoutFileTest.CONFIG.getWindowTitle());
    }

    @Test
    public final void testGetWindowWidth() {
        Assert.assertEquals(PetriConfigWithoutFileTest.DEFAULTS
            .get(PetriConfig.WINDOW_WIDTH),
            PetriConfigWithoutFileTest.CONFIG.getWindowWidth());
    }

    @Test
    public final void testGetWindowX() {
        Assert.assertEquals(
            PetriConfigWithoutFileTest.DEFAULTS.get(PetriConfig.WINDOW_X),
            PetriConfigWithoutFileTest.CONFIG.getWindowX());
    }

    @Test
    public final void testGetWindowY() {
        Assert.assertEquals(
            PetriConfigWithoutFileTest.DEFAULTS.get(PetriConfig.WINDOW_Y),
            PetriConfigWithoutFileTest.CONFIG.getWindowY());
    }

}
