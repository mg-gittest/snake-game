/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.enums.SnakeDirection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by mark_local on 15/09/2015.
 * Test for Snake
 */
public class SnakeTest {

    private Snake target;

    @Before
    public void setup() {
        target = new Snake(SnakeStateFactory.createDefault());
    }


    @Test
    public void testGetTileLocations() throws Exception {

        TileLocationList tileLocations = target.getTileLocations();
        assertEquals("size", SnakeStateFactory.bodyTileCountDefault + 2, tileLocations.size());
        assertEquals("first", SnakeStateFactory.headTileLocationDefault, tileLocations.getFirst());
        assertEquals("last", SnakeStateFactory.tailTileLocationDefault, tileLocations.getLast());
        for (int idx = 1; idx <= SnakeStateFactory.bodyTileCountDefault; ++idx) {
            TileLocation tileLocation = tileLocations.get(idx);
            assertEquals(
                    "body(" + idx + ")", SnakeStateFactory.bodyTileDefault, tileLocation.getTile());
        }
    }

    @Test
    public void testGetHeadLocation() throws Exception {
        assertEquals(SnakeStateFactory.headLocationDefault, target.getHeadLocation().getLocation());
    }

    @Test
    public void testGetHeadTileLocation() throws Exception {
        assertEquals(SnakeStateFactory.headTileLocationDefault, target.getHeadLocation());
    }

    @Test
    public void testGetTailLocation() throws Exception {
        assertEquals(SnakeStateFactory.tailLocationDefault, target.getTailLocation().getLocation());
    }

    @Test
    public void testGetHeadDirection() throws Exception {
        assertEquals(SnakeDirection.EAST, target.getHeadDirection());

    }

    @Test
    public void testGetTailDirection() throws Exception {
        assertEquals(SnakeDirection.EAST, target.getTailDirection());
    }

    @Test
    public void testMove() throws Exception {
        fail("not yet implemented");
    }

    @Test
    public void testGrow() throws Exception {
        fail("not yet implemented");
    }

    @Test
    public void testGetLength() throws Exception {
        // body tiles plus head and tail
        assertEquals(SnakeStateFactory.bodyTileCountDefault + 2, target.getLength());
    }

    @Test
    public void testNonAdjacent() throws Exception {

    }
}
