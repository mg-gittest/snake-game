/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 15/09/2015.
 * Tests the limits of Location
 */
public class LocationTest {

    private final int xExpect = 10;
    private final int yExpect = 15;

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


}
