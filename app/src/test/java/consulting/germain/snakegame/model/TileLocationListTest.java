/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.enums.TilePrize;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 22/09/2015.
 * Testing TileLocationList methods not delegated to underlying class
 */
public class TileLocationListTest {

    private final Tile tile = Tile.get(TilePrize.SCISSORS);
    private TileLocationList target;
    private TileLocationList testList;
    private TileLocation     tileLocation;
    private int xMin = 2;
    private int xMax = 15;
    private int yMin = 7;
    private int yMax = 19;

    @Before
    public void setUp() throws Exception {
        target = new TileLocationList();

        for (int x = xMin; x <= xMax; ++x) {
            TileLocation tileLoc = new TileLocation(new Location(x, yMin), tile);
            target.addFirst(tileLoc);
        }

        for (int y = yMin + 1; y <= yMax; y++) {
            TileLocation tileLoc = new TileLocation(new Location(xMax, y), tile);
            target.addFirst(tileLoc);
        }

        testList = new TileLocationList();
    }

    @Test
    public void testIntersectsXminYMax() throws Exception {
        tileLocation = new TileLocation(new Location(xMin, yMax), tile);
        testList.addFirst(tileLocation);

        assertFalse(target.intersects(testList));
    }

    @Test
    public void testOneByOne() throws Exception {

        for (int x = xMin; x <= xMax; ++x) {
            testList = new TileLocationList();
            tileLocation = new TileLocation(new Location(x, yMin), tile);
            testList.addFirst(tileLocation);
            assertTrue(testList.toString(), target.intersects(testList));
        }

        for (int y = yMin + 1; y <= yMax; y++) {
            testList = new TileLocationList();
            tileLocation = new TileLocation(new Location(xMax, y), tile);
            testList.addFirst(tileLocation);
            assertTrue(testList.toString(), target.intersects(testList));
        }

    }

    @Test
    public void testHorizList() throws Exception {

        for (int x = xMin; x <= xMax; ++x) {
            tileLocation = new TileLocation(new Location(x, yMax), tile);
            testList.addFirst(tileLocation);
        }

        assertTrue(testList.toString(), target.intersects(testList));

    }

    @Test
    public void testVertList() throws Exception {

        for (int y = yMin; y <= yMax; y++) {
            tileLocation = new TileLocation(new Location(xMin, y), tile);
            testList.addFirst(tileLocation);
        }

        assertTrue(testList.toString(), target.intersects(testList));
    }

    @Test
    public void testGap() throws Exception {

        for (int y = yMin + 1; y <= yMax; y++) {
            for (int x = xMin; x < xMax; ++x) {
                tileLocation = new TileLocation(new Location(x, y), tile);
                testList.addFirst(tileLocation);
            }
        }

        assertFalse(target.intersects(testList));
    }
}
