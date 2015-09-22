/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.enums.SnakeDirection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 15/09/2015.
 * Test for Snake
 */
public class SnakeTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
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
        assertEquals(SnakeStateFactory.headLocationDefault, target.getHeadTileLocation()
                .getLocation());
    }

    @Test
    public void testGetHeadTileLocation() throws Exception {
        assertEquals(SnakeStateFactory.headTileLocationDefault, target.getHeadTileLocation());
    }

    @Test
    public void testGetTailLocation() throws Exception {
        assertEquals(SnakeStateFactory.tailLocationDefault, target.getTailTileLocation()
                .getLocation());
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
        SnakeDirection headDirection = target.getHeadDirection();
        TileLocation currentHeadLocation = target.getHeadTileLocation();
        TileLocation currentTailLocation = target.getTailTileLocation();

        Location expectedHead =
                currentHeadLocation.getLocation().getProjectedLocation(headDirection);
        Location expectedTail =
                currentTailLocation.getLocation().getProjectedLocation(headDirection);

        target.move(headDirection);

        TileLocation actualHeadLocation = target.getHeadTileLocation();
        TileLocation actualTailLocation = target.getTailTileLocation();

        assertEquals("head", expectedHead, actualHeadLocation.getLocation());
        assertEquals("tail", expectedTail, actualTailLocation.getLocation());

        String msg = target.validateState();
        assertEquals(msg, 0, msg.length());
    }

    //TODO need negative tests for move and grow

    @Test
    public void testGrowCurrentDir() throws Exception {

        SnakeDirection headDirection = target.getHeadDirection();
        TileLocation currentLocation = target.getHeadTileLocation();
        TileLocation newHeadLocation = target.growHead(headDirection);

        assertTrue("adjacent", currentLocation.isAdjacentLocation(newHeadLocation));

        String msg = target.validateState();
        assertEquals(msg, 0, msg.length());

        Location expectedLocation =
                currentLocation.getLocation().getProjectedLocation(headDirection);
        assertEquals("location", expectedLocation, newHeadLocation.getLocation());

        Tile expectedTile = currentLocation.getTile();
        assertEquals("tile", expectedTile, newHeadLocation.getTile());

        msg = target.validateState();
        assertEquals(msg, 0, msg.length());

    }

    @Test
    public void testShrinkTail() throws Exception {

        TileLocation oldHeadLocation = target.getHeadTileLocation();
        TileLocation oldTailLocation = target.getTailTileLocation();
        int oldLength = target.getLength();

        Location expectedTail =
                oldTailLocation.getLocation().getProjectedLocation(target.getTailDirection());

        target.shrinkTail();

        TileLocation newHeadLocation = target.getHeadTileLocation();
        TileLocation newTailLocation = target.getTailTileLocation();
        int newLength = target.getLength();

        assertEquals("head", oldHeadLocation, newHeadLocation);
        assertEquals("length", oldLength - 1, newLength);
        assertEquals("tailLocation", expectedTail, newTailLocation.getLocation());
        assertEquals("tail tile", oldTailLocation.getTile(), newTailLocation.getTile());

    }

    @Test
    public void testGetLength() throws Exception {
        // body tiles plus head and tail
        assertEquals(SnakeStateFactory.bodyTileCountDefault + 2, target.getLength());
    }

    @Test
    public void testMoveEastToWest() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Snake.BAD_NECK_REQUEST);

        target.move(SnakeDirection.WEST);
    }
}
