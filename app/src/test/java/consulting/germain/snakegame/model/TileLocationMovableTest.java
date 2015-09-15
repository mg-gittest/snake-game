/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.R;
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
    private Tile                tileExpect;
    private int                 sideExpect;

    @Before
    public void setUp() throws Exception {

        xExpect = 125;
        yExpect = 135;
        sideExpect = 38;

        tileExpect = new Tile(sideExpect, TileSnakeHead.NORTH);
        target = new TileLocationMovable(xExpect, yExpect, tileExpect);
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals("X", xExpect, target.getX());
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals("Y", yExpect, target.getY());
    }

    @Test
    public void testGetTile() throws Exception {
        Tile tileActual = target.getTile();
        assertEquals("Tile", tileExpect, tileActual);

        Assert.assertEquals("ID", R.drawable.snake_head_north, tileActual.getDrawableId());
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
        target = new TileLocationMovable(xExpect, yExpect, tileCake);

    }

    @Test
    public void testSameLocationTileLocation() throws Exception {
        Tile tileCake = new Tile(sideExpect, TilePrize.CAKE);
        TileLocation sameXsameY = new TileLocation(xExpect, yExpect, tileCake);
        TileLocation sameXdiffY = new TileLocation(xExpect, yExpect + 1, tileCake);
        TileLocation diffXsameY = new TileLocation(xExpect + 1, yExpect, tileCake);
        TileLocation diffXdiffY = new TileLocation(xExpect + 1, yExpect + 1, tileCake);

        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));

    }

    @Test
    public void testSameLocationTileLocationMovable() throws Exception {
        Tile tileCake = new Tile(sideExpect, TileSnakeTail.SOUTH);
        TileLocationMovable sameXsameY = new TileLocationMovable(xExpect, yExpect, tileCake);
        TileLocationMovable sameXdiffY = new TileLocationMovable(xExpect, yExpect + 1, tileCake);
        TileLocationMovable diffXsameY = new TileLocationMovable(xExpect + 1, yExpect, tileCake);
        TileLocationMovable diffXdiffY =
                new TileLocationMovable(xExpect + 1, yExpect + 1, tileCake);

        assertTrue("target", target.sameLocation(target));
        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));

    }

}
