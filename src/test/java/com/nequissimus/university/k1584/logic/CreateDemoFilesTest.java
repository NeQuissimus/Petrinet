// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nequissimus.library.data.Singleton;
import com.nequissimus.university.k1584.logic.pnml.PetriMarkup;
import com.nequissimus.university.k1584.logic.pnml.PnmlException;

public class CreateDemoFilesTest {

    private static File DEMO1 = new File("./examples/demo1.pnml");
    private static File DEMO2 = new File("./examples/demo2.pnml");
    private static File DEMO3 = new File("./examples/keks.pnml");
    private static File DEMO4 = new File("./examples/bestellung.pnml");
    private static File DEMO5 = new File("./examples/fotoautomat.pnml");
    private static File DEMO6 = new File("./examples/bleche.pnml");

    private static final List<File> ALL_FILES = Arrays.asList(new File[] {
        CreateDemoFilesTest.DEMO1, CreateDemoFilesTest.DEMO2,
        CreateDemoFilesTest.DEMO3, CreateDemoFilesTest.DEMO4,
        CreateDemoFilesTest.DEMO5, CreateDemoFilesTest.DEMO6
    });

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        final PetriConfig config =
            (PetriConfig) new PetriConfig().getDefaults();

        Singleton.addObject(config);

        // Remove old example files and create new empty ones
        for (final File file : CreateDemoFilesTest.ALL_FILES) {

            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();

        }

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    @Test
    public final void createDemo1() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet before = snapshots.add("Before");
        before.createNewMarking("Null");

        final Dimension iconSize = new Dimension(138, 100);

        final PetriPlace place1 = before.addPlace();
        place1.setTokens(1);
        place1.setPosition(new Point(120, 375));
        place1.setSize(iconSize);

        final PetriPlace place2 = before.addPlace();
        place2.setTokens(0);
        place2.setPosition(new Point(320, 375));
        place2.setSize(iconSize);

        final PetriPlace place3 = before.addPlace();
        place3.setTokens(0);
        place3.setPosition(new Point(515, 375));
        place3.setSize(iconSize);

        final PetriPlace place4 = before.addPlace();
        place4.setTokens(2);
        place4.setPosition(new Point(410, 10));
        place4.setSize(iconSize);

        final PetriPlace place5 = before.addPlace();
        place5.setTokens(1);
        place5.setPosition(new Point(230, 10));
        place5.setSize(iconSize);

        final PetriTransition transition = before.addTransition();
        transition.setPosition(new Point(320, 175));
        transition.setSize(iconSize);

        before.connect(transition, place1);
        before.connect(transition, place2);
        before.connect(transition, place3);
        before.connect(place4, transition);
        before.connect(place5, transition);

        final PetriNet after = before.clone();
        after.createNewMarking("Null");

        after.setName("After");

        final PetriTransition trans =
            after.getTransitions().iterator().next();
        after.occur(trans);

        snapshots.add(after);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO1, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Test
    public final void createDemo2() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet empty = snapshots.add("Empty");
        empty.createNewMarking("Null");

        final Dimension iconSize = new Dimension(83, 45);

        final PetriPlace reqRefill = empty.addPlace("request for refill");
        reqRefill.setSize(iconSize);
        reqRefill.setPosition(new Point(160, 240));

        final PetriPlace holdCoin = empty.addPlace("holding coin");
        holdCoin.setSize(iconSize);
        holdCoin.setPosition(new Point(725, 130));

        final PetriPlace readyDisp = empty.addPlace("ready to dispense");
        readyDisp.setSize(iconSize);
        readyDisp.setPosition(new Point(335, 225));

        final PetriPlace readyIns = empty.addPlace("ready for insertion");
        readyIns.setSize(iconSize);
        readyIns.setPosition(new Point(330, 30));
        readyIns.setTokens(1);

        final PetriPlace candy = empty.addPlace("candy storage");
        candy.setSize(iconSize);
        candy.setPosition(new Point(180, 20));
        candy.setTokens(4);

        final PetriTransition insCoin = empty.addTransition("insert coin");
        insCoin.setSize(iconSize);
        insCoin.setPosition(new Point(600, 35));

        final PetriTransition accCoin = empty.addTransition("accept coin");
        accCoin.setSize(iconSize);
        accCoin.setPosition(new Point(600, 245));

        final PetriTransition rejCoin = empty.addTransition("reject coin");
        rejCoin.setSize(iconSize);
        rejCoin.setPosition(new Point(465, 140));

        final PetriTransition refill = empty.addTransition("refill");
        refill.setSize(iconSize);
        refill.setPosition(new Point(85, 125));

        final PetriTransition dispense =
            empty.addTransition("dispense candy");
        dispense.setSize(iconSize);
        dispense.setPosition(new Point(245, 135));

        empty.connect(reqRefill, refill);
        empty.connect(refill, candy);
        empty.connect(candy, dispense);
        empty.connect(dispense, reqRefill);
        empty.connect(dispense, readyIns);
        empty.connect(readyIns, insCoin);
        empty.connect(insCoin, holdCoin);
        empty.connect(holdCoin, rejCoin);
        empty.connect(rejCoin, readyIns);
        empty.connect(holdCoin, accCoin);
        empty.connect(accCoin, readyDisp);
        empty.connect(readyDisp, dispense);

        final PetriNet fig12 = empty.clone();
        fig12.createNewMarking("Null");

        fig12.setName("Fig1.2");

        snapshots.add(fig12);

        empty.occur(insCoin);
        empty.occur(accCoin);
        empty.occur(dispense);

        final PetriNet fig13 = empty.clone();
        fig13.createNewMarking("Null");

        fig13.setName("Fig1.3");

        snapshots.add(fig13);

        reqRefill.setTokens(0);
        candy.setTokens(0);
        readyIns.setTokens(0);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO2, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Test
    public final void createDemo3() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet net = snapshots.add("Default");
        final PetriMarking marking1 = net.createNewMarking("Markierung 1");

        final Dimension iconSize = new Dimension(83, 45);

        final PetriPlace container = net.addPlace("Vorratsbehälter");
        container.setSize(iconSize);
        container.setPosition(new Point(100, 10));

        final PetriPlace ready = net.addPlace("bereit");
        ready.setSize(iconSize);
        ready.setPosition(new Point(300, 10));

        final PetriPlace checkCoin = net.addPlace("Münze prüfen");
        checkCoin.setSize(iconSize);
        checkCoin.setPosition(new Point(650, 100));

        final PetriPlace release = net.addPlace("bereit zur Ausgabe");
        release.setSize(iconSize);
        release.setPosition(new Point(300, 200));

        final PetriPlace refillReq = net.addPlace("Bitte auffüllen!");
        refillReq.setSize(iconSize);
        refillReq.setPosition(new Point(100, 200));

        final PetriTransition refill = net.addTransition("auffüllen");
        refill.setSize(iconSize);
        refill.setPosition(new Point(10, 100));

        final PetriTransition cookie = net.addTransition("Keks ausgeben");
        cookie.setSize(iconSize);
        cookie.setPosition(new Point(200, 100));

        final PetriTransition insertCoin =
            net.addTransition("Münze einwerfen");
        insertCoin.setSize(iconSize);
        insertCoin.setPosition(new Point(450, 10));

        final PetriTransition returnCoin =
            net.addTransition("Münze zurückgeben");
        returnCoin.setSize(iconSize);
        returnCoin.setPosition(new Point(450, 100));

        final PetriTransition acceptCoin =
            net.addTransition("Münze akzeptieren");
        acceptCoin.setSize(iconSize);
        acceptCoin.setPosition(new Point(450, 200));

        net.connect(container, cookie);
        net.connect(cookie, refillReq);
        net.connect(refillReq, refill);
        net.connect(refill, container);
        net.connect(cookie, ready);
        net.connect(ready, insertCoin);
        net.connect(insertCoin, checkCoin);
        net.connect(checkCoin, returnCoin);
        net.connect(returnCoin, ready);
        net.connect(checkCoin, acceptCoin);
        net.connect(acceptCoin, release);
        net.connect(release, cookie);

        marking1.setTokens(container, 4);
        marking1.setTokens(ready, 1);

        final PetriMarking marking2 = net.createNewMarking("Markierung 2");

        marking2.setTokens(container, 3);
        marking2.setTokens(ready, 0);
        marking2.setTokens(release, 1);
        marking2.setTokens(refillReq, 1);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO3, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Test
    public final void createDemo4() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet net = snapshots.add("Default");
        final PetriMarking marking = net.createNewMarking("Markierung 1");

        final Dimension iconSize = new Dimension(83, 45);

        final PetriPlace order = net.addPlace("Bestellung");
        order.setSize(iconSize);
        order.setPosition(new Point(100, 200));

        final PetriPlace deliveryReq = net.addPlace("Lieferauftrag");
        deliveryReq.setSize(iconSize);
        deliveryReq.setPosition(new Point(300, 200));

        final PetriPlace prodReq = net.addPlace("Produktionsauftrag");
        prodReq.setSize(iconSize);
        prodReq.setPosition(new Point(200, 100));

        final PetriPlace storage = net.addPlace("Lager");
        storage.setSize(iconSize);
        storage.setPosition(new Point(400, 100));

        final PetriTransition customer = net.addTransition("Kunde");
        customer.setSize(iconSize);
        customer.setPosition(new Point(10, 200));

        final PetriTransition confirmOrder =
            net.addTransition("Bestellannahme");
        confirmOrder.setSize(iconSize);
        confirmOrder.setPosition(new Point(200, 200));

        final PetriTransition production = net.addTransition("Produktion");
        production.setSize(iconSize);
        production.setPosition(new Point(300, 100));

        final PetriTransition delivery = net.addTransition("Lieferung");
        delivery.setSize(iconSize);
        delivery.setPosition(new Point(400, 10));

        final PetriTransition finish = net.addTransition("Auslieferung");
        finish.setSize(iconSize);
        finish.setPosition(new Point(400, 200));

        marking.setTokens(order, 1);
        marking.setTokens(storage, 1);

        net.connect(customer, order);
        net.connect(order, confirmOrder);
        net.connect(confirmOrder, prodReq);
        net.connect(confirmOrder, deliveryReq);
        net.connect(prodReq, production);
        net.connect(deliveryReq, finish);
        net.connect(production, storage);
        net.connect(delivery, storage);
        net.connect(storage, finish);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO4, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Test
    public final void createDemo5() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet net = snapshots.add("Default");
        final PetriMarking marking = net.createNewMarking("Markierung 1");

        final Dimension iconSize = new Dimension(83, 45);

        final PetriPlace wait = net.addPlace("warten");
        wait.setPosition(new Point(10, 120));
        wait.setSize(iconSize);

        final PetriPlace before = net.addPlace("vorher");
        before.setPosition(new Point(200, 120));
        before.setSize(iconSize);

        final PetriPlace free = net.addPlace("frei");
        free.setPosition(new Point(200, 10));
        free.setSize(iconSize);

        final PetriPlace busy = net.addPlace("besetzt");
        busy.setPosition(new Point(200, 250));
        busy.setSize(iconSize);

        final PetriPlace after = net.addPlace("nachher");
        after.setPosition(new Point(400, 120));
        after.setSize(iconSize);

        final PetriPlace gone = net.addPlace("weg");
        gone.setPosition(new Point(600, 120));
        gone.setSize(iconSize);

        final PetriTransition enter = net.addTransition("eintreten");
        enter.setSize(iconSize);
        enter.setPosition(new Point(100, 120));

        final PetriTransition takePic = net.addTransition("mache Foto");
        takePic.setSize(iconSize);
        takePic.setPosition(new Point(300, 120));

        final PetriTransition leave = net.addTransition("verlassen");
        leave.setPosition(new Point(500, 120));
        leave.setSize(iconSize);

        marking.setTokens(wait, 4);
        marking.setTokens(free, 1);

        net.connect(wait, enter);
        net.connect(enter, before);
        net.connect(before, takePic);
        net.connect(takePic, after);
        net.connect(after, leave);
        net.connect(leave, gone);
        net.connect(leave, free);
        net.connect(free, enter);
        net.connect(enter, busy);
        net.connect(busy, leave);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO5, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Test
    public final void createDemo6() {

        final PetriSnapshots snapshots = new PetriSnapshots();
        final PetriNet net = snapshots.add("Default");
        final PetriMarking marking = net.createNewMarking("Markierung 1");

        final Dimension iconSize = new Dimension(83, 45);

        final PetriPlace screws = net.addPlace("Schauben");
        screws.setPosition(new Point(100, 30));
        screws.setSize(iconSize);

        final PetriPlace nuts = net.addPlace("Muttern");
        nuts.setPosition(new Point(200, 10));
        nuts.setSize(iconSize);

        final PetriPlace sheets = net.addPlace("Bleche");
        sheets.setPosition(new Point(300, 30));
        sheets.setSize(iconSize);

        final PetriPlace driver = net.addPlace("Schraubenzieher");
        driver.setPosition(new Point(400, 100));
        driver.setSize(iconSize);

        final PetriPlace wrench = net.addPlace("Schraubenschlüssel");
        wrench.setPosition(new Point(10, 100));
        wrench.setSize(iconSize);

        final PetriPlace done = net.addPlace("verschraubte Bleche");
        done.setPosition(new Point(200, 200));
        done.setSize(iconSize);

        final PetriTransition work = net.addTransition("verbinden");
        work.setPosition(new Point(200, 100));
        work.setSize(iconSize);

        marking.setTokens(screws, 4);
        marking.setTokens(nuts, 4);
        marking.setTokens(sheets, 4);
        marking.setTokens(wrench, 1);
        marking.setTokens(driver, 1);

        net.connect(screws, work);
        net.connect(nuts, work);
        net.connect(sheets, work);
        net.connect(driver, work);
        net.connect(work, driver);
        net.connect(wrench, work);
        net.connect(work, wrench);
        net.connect(work, done);

        try {
            PetriMarkup.savePnmlFile(CreateDemoFilesTest.DEMO6, snapshots);
        } catch (final PnmlException e) {
            Assert.fail();
        }

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
