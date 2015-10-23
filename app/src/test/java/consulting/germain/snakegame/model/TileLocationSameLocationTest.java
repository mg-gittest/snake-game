/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import consulting.germain.snakegame.enums.TilePrize;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 14/09/2015.
 * Test Tile Location
 */
@RunWith(Parameterized.class)
public class TileLocationSameLocationTest {

    private static TileLocation target;
    private static int          xExpect;
    private static int          yExpect;
    private static Location     locationExpect;
    private static Tile         tileExpect;
    /**
     * context message
     */
    private final  String       msg;
    /**
     * x difference to use
     */
    private final  int          xDiff;
    /**
     * y different to use
     */
    private final  int          yDiff;
    /**
     * return from call to test with Same or different Tile
     */
    private final  boolean      expectResult;

    /**
     * test the same location logic with differeing values of x and y offset
     *
     * @param xDiff  x difference to use
     * @param yDiff  y different to use
     * @param expect return from call to test with Same or different Tile
     * @throws Exception
     */
    public TileLocationSameLocationTest(
            final int xDiff,
            final int yDiff,
            final boolean expect) throws Exception {
        this.msg = String.valueOf(xDiff) + ", " + yDiff;
        this.xDiff = xDiff;
        this.yDiff = yDiff;
        this.expectResult = expect;
    }

    @BeforeClass
    public static void setUp() throws Exception {
        xExpect = 12;
        yExpect = 15;
        locationExpect = new Location(xExpect, yExpect);

        tileExpect = Tile.get(TilePrize.CAKE);
        target = new TileLocation(locationExpect, tileExpect);
    }

    @Parameterized.Parameters
    public static List data() {
        return Arrays.asList(new Object[][]{
                {0, 0, true},
                {0, 1, false},
                {0, -1, false},
                {1, 0, false},
                {1, 1, false},
                {1, -1, false},
                {-1, 0, false},
                {-1, 1, false},
                {-1, -1, false}
        });
    }

    @Test
    public void testSameLocationSameTile() throws Exception {

        TileLocation candidate =
                new TileLocation(new Location(xExpect + xDiff, yExpect + yDiff), tileExpect);

        assertEquals(msg, expectResult, target.sameLocation(candidate));
    }

    @Test
    public void testSameLocationDifferentTile() throws Exception {

        Tile tileApple = Tile.get(TilePrize.APPLE);
        TileLocation candidate =
                new TileLocation(new Location(xExpect + xDiff, yExpect + yDiff), tileApple);

        assertEquals(msg, expectResult, target.sameLocation(candidate));
    }

}
