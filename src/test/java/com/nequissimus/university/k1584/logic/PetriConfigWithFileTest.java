package com.nequissimus.university.k1584.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public final class PetriConfigWithFileTest {

    private static final String PLACE_NAME = "PL_Name";
    private static final String TRANSITION_NAME = "TR_Name";
    private static final int CANVAS_HEIGHT = 512;
    private static final int CANVAS_WIDTH = 512;
    private static final String WINDOW_TITLE = "Win_Title";
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 700;
    private static final int WINDOW_X = 123;
    private static final int WINDOW_Y = 123;
    private static final String APPLICATION_NAME = "Petri_Application";
    private static final int SIDEBAR_WIDTH = 123;
    private static final int WINDOW_MIN_HEIGHT = 400;
    private static final int WINDOW_MIN_WIDTH = 400;
    private static final String PNML_EDGE_ID_PREFIX = "EDGE_";
    private static final String NET_NAME = "New_Net";
    private static final String FILE_EXTENSION = "xml";

    private static PetriConfig CONFIG;

    private static File PROP_FILE = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        PetriConfigWithFileTest.PROP_FILE =
            PetriConfigWithFileTest.createPropertiesFile();

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

        PetriConfigWithFileTest.CONFIG = null;
        PetriConfigWithFileTest.PROP_FILE = null;

    }

    private static File createPropertiesFile() throws IOException {

        final File tmp =
            File.createTempFile(UUID.randomUUID().toString(), ".tmp");

        final Properties props = new Properties();

        props.put(PetriConfig.PLACE_NAME,
            PetriConfigWithFileTest.PLACE_NAME);
        props.put(PetriConfig.TRANSITION_NAME,
            PetriConfigWithFileTest.TRANSITION_NAME);
        props.put(PetriConfig.CANVAS_HEIGHT,
            String.valueOf(PetriConfigWithFileTest.CANVAS_HEIGHT));
        props.put(PetriConfig.CANVAS_WIDTH,
            String.valueOf(PetriConfigWithFileTest.CANVAS_WIDTH));
        props.put(PetriConfig.WINDOW_TITLE,
            PetriConfigWithFileTest.WINDOW_TITLE);
        props.put(PetriConfig.WINDOW_WIDTH,
            String.valueOf(PetriConfigWithFileTest.WINDOW_WIDTH));
        props.put(PetriConfig.WINDOW_HEIGHT,
            String.valueOf(PetriConfigWithFileTest.WINDOW_HEIGHT));
        props.put(PetriConfig.WINDOW_X,
            String.valueOf(PetriConfigWithFileTest.WINDOW_X));
        props.put(PetriConfig.WINDOW_Y,
            String.valueOf(PetriConfigWithFileTest.WINDOW_Y));
        props.put(PetriConfig.APPLICATION_NAME,
            PetriConfigWithFileTest.APPLICATION_NAME);
        props.put(PetriConfig.SIDEBAR_WIDTH,
            String.valueOf(PetriConfigWithFileTest.SIDEBAR_WIDTH));
        props.put(PetriConfig.WINDOW_MIN_HEIGHT,
            String.valueOf(PetriConfigWithFileTest.WINDOW_MIN_HEIGHT));
        props.put(PetriConfig.WINDOW_MIN_WIDTH,
            String.valueOf(PetriConfigWithFileTest.WINDOW_MIN_WIDTH));
        props.put(PetriConfig.PNML_EDGE_ID_PREFIX,
            PetriConfigWithFileTest.PNML_EDGE_ID_PREFIX);
        props.put(PetriConfig.NET_NAME, PetriConfigWithFileTest.NET_NAME);
        props.put(PetriConfig.FILE_EXTENSION,
            PetriConfigWithFileTest.FILE_EXTENSION);

        props.store(new FileOutputStream(tmp), "");

        return tmp;

    }

    @Before
    public void setUp() throws Exception {

        PetriConfigWithFileTest.CONFIG =
            new PetriConfig(PetriConfigWithFileTest.PROP_FILE);

    }

    @After
    public void tearDown() throws Exception {

        PetriConfigWithFileTest.CONFIG = null;

    }

    @Test
    public final void testGetApplicationName() {

        Assert.assertEquals(PetriConfigWithFileTest.APPLICATION_NAME,
            PetriConfigWithFileTest.CONFIG.getApplicationName());

    }

    @Test
    public final void testGetCanvasHeight() {

        Assert.assertEquals(PetriConfigWithFileTest.CANVAS_HEIGHT,
            PetriConfigWithFileTest.CONFIG.getCanvasHeight());

    }

    @Test
    public final void testGetCanvasWidth() {
        Assert.assertEquals(PetriConfigWithFileTest.CANVAS_WIDTH,
            PetriConfigWithFileTest.CONFIG.getCanvasWidth());
    }

    @Test
    public final void testGetEdgeIdPrefix() {
        Assert.assertEquals(PetriConfigWithFileTest.PNML_EDGE_ID_PREFIX,
            PetriConfigWithFileTest.CONFIG.getEdgeIdPrefix());
    }

    @Test
    public final void testGetFileExtension() {
        Assert.assertEquals(PetriConfigWithFileTest.FILE_EXTENSION,
            PetriConfigWithFileTest.CONFIG.getFileExtension());
    }

    @Test
    public final void testGetNetName() {
        Assert.assertEquals(PetriConfigWithFileTest.NET_NAME,
            PetriConfigWithFileTest.CONFIG.getNetName());
    }

    @Test
    public final void testGetPlaceName() {
        Assert.assertEquals(PetriConfigWithFileTest.PLACE_NAME,
            PetriConfigWithFileTest.CONFIG.getPlaceName());
    }

    @Test
    public final void testGetSidebarWidth() {
        Assert.assertEquals(PetriConfigWithFileTest.SIDEBAR_WIDTH,
            PetriConfigWithFileTest.CONFIG.getSidebarWidth());
    }

    @Test
    public final void testGetTransitionName() {
        Assert.assertEquals(PetriConfigWithFileTest.TRANSITION_NAME,
            PetriConfigWithFileTest.CONFIG.getTransitionName());
    }

    @Test
    public final void testGetWindowHeight() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_HEIGHT,
            PetriConfigWithFileTest.CONFIG.getWindowHeight());
    }

    @Test
    public final void testGetWindowMinHeight() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_MIN_HEIGHT,
            PetriConfigWithFileTest.CONFIG.getWindowMinHeight());
    }

    @Test
    public final void testGetWindowMinWidth() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_MIN_WIDTH,
            PetriConfigWithFileTest.CONFIG.getWindowMinWidth());
    }

    @Test
    public final void testGetWindowTitle() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_TITLE,
            PetriConfigWithFileTest.CONFIG.getWindowTitle());
    }

    @Test
    public final void testGetWindowWidth() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_WIDTH,
            PetriConfigWithFileTest.CONFIG.getWindowWidth());
    }

    @Test
    public final void testGetWindowX() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_X,
            PetriConfigWithFileTest.CONFIG.getWindowX());
    }

    @Test
    public final void testGetWindowY() {
        Assert.assertEquals(PetriConfigWithFileTest.WINDOW_Y,
            PetriConfigWithFileTest.CONFIG.getWindowY());
    }

}
