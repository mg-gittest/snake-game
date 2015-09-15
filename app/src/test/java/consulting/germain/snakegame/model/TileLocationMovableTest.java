/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 14/09/2015.
 * Testing TileLocationMovable
 */
public class TileLocationMovableTest {

    private TileLocationMovable target;
    private int                 xExpect;
    private int                 yExpect;
    private Location locationExpect;
    private Tile                tileExpect;
    private int                 sideExpect;

    @Before
    public void setUp() throws Exception {

        xExpect = 125;
        yExpect = 135;
        locationExpect = new Location(xExpect, yExpect);
        sideExpect = 38;

        tileExpect = new Tile(sideExpect, TileSnakeHead.NORTH);
        target = new TileLocationMovable(locationExpect, tileExpect);
    }

    @Test
    public void testMoveNorth() throws Exception {

        target.move(SnakeDirection.NORTH);
        assertEquals("X", xExpect, target.getX());
        assertEquals("Y", yExpect - 1, target.getY());
        assertEquals("Tile", tileExpect, target.getTile());
    }

    @Test
    public void testMoveSouth() throws Exception {

        target.move(SnakeDirection.SOUTH);
        assertEquals("X", xExpect, target.getX());
        assertEquals("Y", yExpect + 1, target.getY());
        assertEquals("Tile", tileExpect, target.getTile());
    }

    @Test
    public void testMoveEast() throws Exception {

        target.move(SnakeDirection.EAST);
        assertEquals("X", xExpect + 1, target.getX());
        assertEquals("Y", yExpect, target.getY());
        assertEquals("Tile", tileExpect, target.getTile());
    }

    @Test
    public void testMoveWest() throws Exception {

        target.move(SnakeDirection.WEST);
        assertEquals("X", xExpect - 1, target.getX());
        assertEquals("Y", yExpect, target.getY());
        assertEquals("Tile", tileExpect, target.getTile());
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testImmovableThrowsCtor() throws Exception {

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(TileLocationMovable.ERR_IMMOVABLE_TILE);

        Tile tileCake = new Tile(sideExpect, TilePrize.CAKE);
        target = new TileLocationMovable(locationExpect, tileCake);

    }

    @Test
    public void testSameLocationTileLocation() throws Exception {
        Tile tileCake = new Tile(sideExpect, TilePrize.CAKE);
        TileLocation sameXsameY = new TileLocation(new Location(xExpect, yExpect), tileCake);
        TileLocation sameXdiffY = new TileLocation(new Location(xExpect, yExpect + 1), tileCake);
        TileLocation diffXsameY = new TileLocation(new Location(xExpect + 1, yExpect), tileCake);
        TileLocation diffXdiffY =
                new TileLocation(new Location(xExpect + 1, yExpect + 1), tileCake);

        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));

    }

    @Test
    public void testSameLocationTileLocationMovable() throws Exception {
        Tile tileCake = new Tile(sideExpect, TileSnakeTail.SOUTH);
        TileLocationMovable sameXsameY =
                new TileLocationMovable(new Location(xExpect, yExpect), tileCake);
        TileLocationMovable sameXdiffY =
                new TileLocationMovable(new Location(xExpect, yExpect + 1), tileCake);
        TileLocationMovable diffXsameY =
                new TileLocationMovable(new Location(xExpect + 1, yExpect), tileCake);
        TileLocationMovable diffXdiffY =
                new TileLocationMovable(new Location(xExpect + 1, yExpect + 1), tileCake);

        assertTrue("target", target.sameLocation(target));
        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));

    }

}
