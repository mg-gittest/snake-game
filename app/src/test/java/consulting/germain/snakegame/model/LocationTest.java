/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.enums.EdgeRollBehaviour;
import consulting.germain.snakegame.enums.SnakeDirection;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 15/09/2015.
 * Tests the limits of Location
 */
public class LocationTest {

    private final int xExpect = 10;
    private final int yExpect = 15;
    private SnakeDirection    snakeDirection    = SnakeDirection.SOUTH;
    private EdgeRollBehaviour edgeRollBehaviour = EdgeRollBehaviour.ROLL_BLOCKED;

    Location target;

    @Before
    public void setUp() throws Exception {
        target = new Location(xExpect, yExpect);
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals("X", xExpect, target.getX());
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals("Y", yExpect, target.getY());
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLowX() throws Exception {
        int val = Limits.minXcoord - 1;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Limits.minXcoordFail);

        target = new Location(val, yExpect);
    }

    @Test
    public void testHighX() throws Exception {
        int val = Limits.maxXcoord + 1;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Limits.maxXcoordFail);

        target = new Location(val, yExpect);
    }

    @Test
    public void testLowY() throws Exception {
        int val = Limits.minYcoord - 1;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Limits.minYcoordFail);

        target = new Location(xExpect, val);
    }

    @Test
    public void testHighY() throws Exception {
        int val = Limits.maxYcoord + 1;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(Limits.maxYcoordFail);

        target = new Location(xExpect, val);
    }

    @Test
    public void testProjectedLocationBadNumberMoves() throws Exception {

        Location expected = target;

        int numMoves = 0;
        assertEquals(expected, target.getProjectedLocation(numMoves, snakeDirection, edgeRollBehaviour));

        numMoves = -1;
        assertEquals(expected, target.getProjectedLocation(numMoves, snakeDirection, edgeRollBehaviour));
    }

    @Test
    public void testProjectedLocationMoveOne() throws Exception {
        int numMoves = 1;

        Location expectedSouth = new Location(xExpect, yExpect + numMoves);
        Location expectedNorth = new Location(xExpect, yExpect - numMoves);
        Location expectedEast = new Location(xExpect + numMoves, yExpect);
        Location expectedWest = new Location(xExpect - numMoves, yExpect);

        assertEquals(expectedSouth, target.getProjectedLocation(numMoves, SnakeDirection.SOUTH, edgeRollBehaviour));
        assertEquals(expectedNorth, target.getProjectedLocation(numMoves, SnakeDirection.NORTH, edgeRollBehaviour));
        assertEquals(expectedEast, target.getProjectedLocation(numMoves, SnakeDirection.EAST, edgeRollBehaviour));
        assertEquals(expectedWest, target.getProjectedLocation(numMoves, SnakeDirection.WEST, edgeRollBehaviour));
    }

    @Test
    public void testProjectedLocationMoveMany() throws Exception {

        for (int numMoves = 2; numMoves < 10; ++numMoves) {
            Location expectedSouth = new Location(xExpect, yExpect + numMoves);
            Location expectedNorth = new Location(xExpect, yExpect - numMoves);
            Location expectedEast = new Location(xExpect + numMoves, yExpect);
            Location expectedWest = new Location(xExpect - numMoves, yExpect);

            assertEquals("numMoves: " + numMoves,
                    expectedSouth,
                    target.getProjectedLocation(numMoves, SnakeDirection.SOUTH, edgeRollBehaviour));
            assertEquals("numMoves: " + numMoves,
                    expectedNorth,
                    target.getProjectedLocation(numMoves, SnakeDirection.NORTH, edgeRollBehaviour));
            assertEquals("numMoves: " + numMoves,
                    expectedEast,
                    target.getProjectedLocation(numMoves, SnakeDirection.EAST, edgeRollBehaviour));
            assertEquals("numMoves: " + numMoves,
                    expectedWest,
                    target.getProjectedLocation(numMoves, SnakeDirection.WEST, edgeRollBehaviour));
        }
    }


}
