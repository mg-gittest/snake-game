/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import consulting.germain.snakegame.R;
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
    private Location locationExpect;
    private Tile         tileExpect;

    @Before
    public void setUp() throws Exception {
        xExpect = 21;
        yExpect = 13;
        locationExpect = new Location(xExpect, yExpect);

        tileExpect = Tile.get(TilePrize.CAKE);
        target = new TileLocation(locationExpect, tileExpect);
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
    public void testGetLocation() throws Exception {
        assertEquals("location", locationExpect, target.getLocation());
    }

    @Test
    public void testGetTile() throws Exception {
        Tile tileActual = target.getTile();
        assertEquals("Tile", tileExpect, tileActual);

        Assert.assertEquals("ID", R.drawable.prize_cake, tileActual.getDrawableId());
    }

}
