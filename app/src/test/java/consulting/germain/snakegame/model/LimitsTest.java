/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.model;

import org.junit.Test;

import static consulting.germain.snakegame.model.Limits.maxTileSide;
import static consulting.germain.snakegame.model.Limits.maxTileSideFail;
import static consulting.germain.snakegame.model.Limits.minTileSide;
import static consulting.germain.snakegame.model.Limits.minTileSideFail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 11/09/2015.
 * Exercise the Limits class
 */
public class LimitsTest {

    @Test
    public void testMinTileSide() throws Exception {
        assertEquals("minTileSide", 10, minTileSide);
    }

    @Test
    public void testMaxTileSide() throws Exception {
        assertEquals("maxTileSide", 200, maxTileSide);
    }

    @Test
    public void testSideMinAgainstMax() throws Exception {
        assertTrue("sideMin <= sideMax", minTileSide <= maxTileSide);
    }

    @Test
    public void testMinTileSideFail() throws Exception {
        String msgLower = minTileSideFail.toLowerCase();

        assertTrue(msgLower.contains("min"));
        assertTrue(msgLower.contains("tile"));
        assertTrue(msgLower.contains("side"));
        assertTrue(msgLower.contains("below"));
    }

    @Test
    public void testMaxTileSideFail() throws Exception {
        String msgLower = maxTileSideFail.toLowerCase();

        assertTrue(msgLower.contains("max"));
        assertTrue(msgLower.contains("tile"));
        assertTrue(msgLower.contains("side"));
        assertTrue(msgLower.contains("above"));
    }
}
