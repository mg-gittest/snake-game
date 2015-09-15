/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame;

import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 14/09/2015.
 * Test Tile Location
 */
public class TileLocationTest {

    private TileLocation target;
    private int          xExpect;
    private int          yExpect;
    private Tile         tileExpect;
    private int          sideExpect;

    @Before
    public void setUp() throws Exception {
        xExpect = 120;
        yExpect = 130;
        sideExpect = 30;

        tileExpect = new Tile(sideExpect, TilePrize.CAKE);
        target = new TileLocation(xExpect, yExpect, tileExpect);
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

        assertEquals("ID", R.drawable.prize_cake, tileActual.getDrawableId());
    }

    @Test
    public void testSameLocationSameTile() throws Exception {

        TileLocation sameXsameY = new TileLocation(xExpect, yExpect, tileExpect);
        TileLocation sameXdiffY = new TileLocation(xExpect, yExpect + 1, tileExpect);
        TileLocation diffXsameY = new TileLocation(xExpect + 1, yExpect, tileExpect);
        TileLocation diffXdiffY = new TileLocation(xExpect + 1, yExpect + 1, tileExpect);

        assertTrue("target", target.sameLocation(target));
        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));
    }

    @Test
    public void testSameLocationDifferentTile() throws Exception {

        Tile tileCake = new Tile(sideExpect, TilePrize.APPLE);
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
    public void testSameLocationAgainstMovable() throws Exception {

        Tile tileSnakeTail = new Tile(sideExpect, TileSnakeTail.SOUTH);
        TileLocationMovable sameXsameY = new TileLocationMovable(xExpect, yExpect, tileSnakeTail);
        TileLocationMovable sameXdiffY =
                new TileLocationMovable(xExpect, yExpect + 1, tileSnakeTail);
        TileLocationMovable diffXsameY =
                new TileLocationMovable(xExpect + 1, yExpect, tileSnakeTail);
        TileLocationMovable diffXdiffY =
                new TileLocationMovable(xExpect + 1, yExpect + 1, tileSnakeTail);

        assertTrue("target", target.sameLocation(target));
        assertTrue("sameXsameY", target.sameLocation(sameXsameY));

        assertFalse("sameXdiffY", target.sameLocation(sameXdiffY));
        assertFalse("diffXsameY", target.sameLocation(diffXsameY));
        assertFalse("diffXdiffY", target.sameLocation(diffXdiffY));


    }

    @Test
    public void testMovableTileToCtor() throws Exception {
        // need to ensure we can pass movable head and tail tiles to constructor with no problems

        for (TileSnakeHead value : TileSnakeHead.values()) {
            Tile tile = new Tile(sideExpect, value);
            assertTrue("isMovable", tile.isMovable());
            target = new TileLocation(xExpect, yExpect, tile);
        }

        for (TileSnakeTail value : TileSnakeTail.values()) {
            Tile tile = new Tile(sideExpect, value);
            assertTrue("isMovable", tile.isMovable());
            target = new TileLocation(xExpect, yExpect, tile);
        }

    }


}
