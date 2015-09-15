/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 15/09/2015.
 * Test SnakeMovement
 */
public class SnakeMovementTest {

    private int tileSideExpected = 45;

    // a snake heading east and 11 tiles long
    private SnakeDirection headDirectionExpected = SnakeDirection.EAST;
    private SnakeDirection tailDirectionExpected = headDirectionExpected;
    private int            bodyTileCount         = 9;
    private int            tailExpectedX         = 15;
    private int            headExpectedX         = tailExpectedX + bodyTileCount + 1;
    private int            headExpectedY         = 35;
    private int            tailExpectedY         = headExpectedY;

    private Location            headLocationExpected;
    private Tile                headTileExpected;
    private TileLocationMovable headTileLocationExpected;

    private Location            tailLocationExpected;
    private Tile                tailTileExpected;
    private TileLocationMovable tailTileLocationExpected;

    private Location bodyLocation;
    private Tile     bodyTileExpected;

    private SnakeMovement.BodyLocations bodyLocationsExpected;

    private SnakeMovement target;

    @Before
    public void Setup() {
        headLocationExpected = new Location(headExpectedX, headExpectedY);
        headTileExpected = new Tile(tileSideExpected, TileSnakeHead.EAST);
        headTileLocationExpected = new TileLocationMovable(headLocationExpected, headTileExpected);

        tailLocationExpected = new Location(tailExpectedX, tailExpectedY);
        tailTileExpected = new Tile(tileSideExpected, TileSnakeTail.EAST);
        tailTileLocationExpected = new TileLocationMovable(tailLocationExpected, tailTileExpected);

        LinkedList<TileLocation> bodyTiles = new LinkedList<>();
        bodyTileExpected = new Tile(tileSideExpected, TileSnakeBody.EAST);
        for (int idx = 1; idx <= bodyTileCount; ++idx) {
            Location locationTile = new Location(tailExpectedX + idx, tailExpectedY);
            TileLocation location = new TileLocation(locationTile, bodyTileExpected);
            bodyTiles.addLast(location);
        }
        bodyLocationsExpected = new SnakeMovement.BodyLocations(bodyTiles);

        target = new SnakeMovement(headDirectionExpected,
                headTileLocationExpected,
                tailTileLocationExpected,
                tailDirectionExpected,
                bodyLocationsExpected
        );
    }

    @Test
    public void testLength() throws Exception {
        assertEquals(bodyTileCount + 2, target.length());
    }

    @Test
    public void testGetHeadDirection() throws Exception {
        assertEquals(headDirectionExpected, target.getHeadDirection());
    }

    @Test
    public void testGetHeadLocation() throws Exception {
        assertEquals(headLocationExpected, target.getHeadLocation().getLocation());
    }

    @Test
    public void testGetHeadLocationMovable() throws Exception {
        assertEquals(headTileLocationExpected, target.getHeadLocationMovable());
    }

    @Test
    public void testGetTailLocation() throws Exception {
        assertEquals(tailLocationExpected, target.getTailLocation().getLocation());
    }

    @Test
    public void testGetTailLocationMovable() throws Exception {
        assertEquals(tailTileLocationExpected, target.getTailLocationMovable());
    }

    @Test
    public void testGetTailDirection() throws Exception {
        assertEquals(tailDirectionExpected, target.getTailDirection());
    }

    @Test
    public void testGetBodyLocations() throws Exception {
        assertEquals(bodyLocationsExpected, target.getBodyLocations());
    }
}
