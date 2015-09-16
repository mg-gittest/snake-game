/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import consulting.germain.snakegame.enums.SnakeDirection;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 15/09/2015.
 * Test SnakeState
 */
public class SnakeStateTest {

    private SnakeState target;

    @Before
    public void Setup() {
        target = SnakeStateFactory.createDefault();
    }

    @Test
    public void testLength() throws Exception {
        // body tiles plus head and tail
        assertEquals(SnakeStateFactory.bodyTileCountDefault + 2, target.length());
    }

    @Test
    public void testGetHeadDirection() throws Exception {
        assertEquals(SnakeDirection.EAST, target.getHeadDirection());
    }

    @Test
    public void testGetHeadLocation() throws Exception {
        assertEquals(SnakeStateFactory.headLocationDefault, target.getHeadLocation().getLocation());
    }

    @Test
    public void testGetHeadLocationMovable() throws Exception {
        assertEquals(SnakeStateFactory.headTileLocationDefault, target.getHeadLocation());
    }

    @Test
    public void testGetTailLocation() throws Exception {
        assertEquals(SnakeStateFactory.tailLocationDefault, target.getTailLocation().getLocation());
    }

    @Test
    public void testGetTailLocationMovable() throws Exception {
        assertEquals(SnakeStateFactory.tailTileLocationDefault, target.getTailLocation());
    }

    @Test
    public void testGetTailDirection() throws Exception {
        assertEquals(SnakeDirection.EAST, target.getTailDirection());
    }

    @Test
    public void testGetTileLocations() throws Exception {
        LinkedList<TileLocation> tileLocations = target.getTileLocations();
        assertEquals("size", SnakeStateFactory.bodyTileCountDefault + 2, tileLocations.size());
        assertEquals("first", SnakeStateFactory.headTileLocationDefault, tileLocations.getFirst());
        assertEquals("last", SnakeStateFactory.tailTileLocationDefault, tileLocations.getLast());
        for (int idx = 1; idx <= SnakeStateFactory.bodyTileCountDefault; ++idx) {
            TileLocation tileLocation = tileLocations.get(idx);
            assertEquals(
                    "body(" + idx + ")", SnakeStateFactory.bodyTileDefault, tileLocation.getTile());
        }

    }
}
