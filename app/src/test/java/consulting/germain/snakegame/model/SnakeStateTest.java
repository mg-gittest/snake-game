/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.enums.EdgeRollBehaviour;
import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    public void testValidateMessageEmpty() throws Exception {
        String msg = target.validateTileLocations();
        assertEquals("initial state message length", 0, msg.length());
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
    public void testGetTailTileLocation() throws Exception {
        assertEquals(SnakeStateFactory.tailTileLocationDefault, target.getTailTileLocation());
    }

    @Test
    public void testGetTailDirection() throws Exception {
        assertEquals(SnakeDirection.EAST, target.getTailDirection());
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
    public void testValidateTileLocationsNotAdjacent() throws Exception {
        // remove first tile, confirm it is the head
        TileLocation headTileLocation = target.getTileLocations().removeFirst();
        assertEquals("removed Head", SnakeStateFactory.headTileLocationDefault, headTileLocation);

        // establish a new head location
        Location headLocation = headTileLocation.getLocation();
        Tile headTile = headTileLocation.getTile();
        int numMoves = 1;
        SnakeDirection headDirection = target.getHeadDirection();
        EdgeRollBehaviour edgeRole = Settings.edgeRollBehaviour;
        Location nextLocation =
                headLocation.getProjectedLocation(numMoves, headDirection, edgeRole);

        // add the new location as first element of list
        TileLocation nextHeadTileLocation = new TileLocation(nextLocation, headTile);
        target.getTileLocations().addFirst(nextHeadTileLocation);

        // validate the list is good, expect to fail
        String msg = target.validateTileLocations();
        assertTrue("non adjacent", msg.startsWith(SnakeState.SNAKE_LOCATIONS_NOT_ADJACENT));
    }

    @Test
    public void testValidateTileLocationsNotAligned() throws Exception {
        // remove first tile, confirm it is the head
        TileLocation headTileLocation = target.getTileLocations().removeFirst();
        assertEquals("removed Head", SnakeStateFactory.headTileLocationDefault, headTileLocation);

        // establish a new head direction
        Location headLocation = headTileLocation.getLocation();
        Tile newHeadTile = new Tile(SnakeStateFactory.tileSideDefault, TileSnakeHead.SOUTH);
        assertNotEquals("direction check", newHeadTile.getDirectionTo(), headTileLocation.getTile()
                .getDirectionTo());

        // add the new location as first element of list
        TileLocation nextHeadTileLocation =
                new TileLocation(headTileLocation.getLocation(), newHeadTile);
        target.getTileLocations().addFirst(nextHeadTileLocation);

        // validate the list is good, expect to fail
        String msg = target.validateTileLocations();
        assertTrue("non adjacent", msg.startsWith(SnakeState.SNAKE_LOCATIONS_NOT_ALIGNED));
    }

    @Test
    public void testBadFirst() throws Exception {
        target.getTileLocations().removeFirst();
        String msg = target.validateTileLocations();
        assertTrue(msg.startsWith(SnakeState.SNAKE_FIRST_NOT_HEAD));
    }

    @Test
    public void testBadLast() throws Exception {
        target.getTileLocations().removeLast();
        String msg = target.validateTileLocations();
        assertTrue(msg.startsWith(SnakeState.SNAKE_LAST_NOT_TAIL));
    }

    @Test
    public void testBadSecond() throws Exception {
        // remove first tile, confirm it is the head
        TileLocation headTileLocation = target.getHeadTileLocation();

        // establish a new head location
        Location headLocation = headTileLocation.getLocation();
        Tile headTile = headTileLocation.getTile();
        int numMoves = 1;
        SnakeDirection headDirection = target.getHeadDirection();
        EdgeRollBehaviour edgeRole = Settings.edgeRollBehaviour;
        Location nextLocation =
                headLocation.getProjectedLocation(numMoves, headDirection, edgeRole);

        // add the new location as first element of list, so we now have two successive heads
        TileLocation nextHeadTileLocation = new TileLocation(nextLocation, headTile);
        target.getTileLocations().addFirst(nextHeadTileLocation);

        String msg = target.validateTileLocations();
        assertTrue(msg.startsWith(SnakeState.SNAKE_MID_NOT_BODY));
    }

    @Test
    public void testSelfCollide() throws Exception {
        TileLocation third = target.getTileLocations().get(3);
        int side = SnakeStateFactory.tileSideDefault;

        // now build a loop
        Location loc0 = third.getLocation();

        Location loc1 = new Location(loc0.getX() - 1, loc0.getY());
        Location loc2 = new Location(loc0.getX() - 1, loc0.getY() - 1);
        Location loc3 = new Location(loc0.getX(), loc0.getY() - 1);
        Location loc4 = new Location(loc0.getX(), loc0.getY());

        Tile t1 = new Tile(side, TileSnakeBody.SOUTH_TO_EAST);
        Tile t2 = new Tile(side, TileSnakeBody.WEST_TO_SOUTH);
        Tile t3 = new Tile(side, TileSnakeBody.NORTH_TO_WEST);
        Tile t4 = new Tile(side, TileSnakeBody.EAST_TO_NORTH);

        TileLocation tLoc1 = new TileLocation(loc1, t1);
        TileLocation tLoc2 = new TileLocation(loc2, t2);
        TileLocation tLoc3 = new TileLocation(loc3, t3);
        TileLocation tLoc4 = new TileLocation(loc4, t4);

        target.getTileLocations().add(4, tLoc1);
        target.getTileLocations().add(5, tLoc2);
        target.getTileLocations().add(6, tLoc3);
        target.getTileLocations().add(7, tLoc4);

        String msg = target.validateTileLocations();
        assertTrue(msg.startsWith(SnakeState.SNAKE_SELF_COLLISION));

    }
}
