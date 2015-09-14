/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame;

import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.enums.TilePrize;

import static org.junit.Assert.assertEquals;

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
        assertEquals("Tile", tileExpect, target.getTile());
    }
}
