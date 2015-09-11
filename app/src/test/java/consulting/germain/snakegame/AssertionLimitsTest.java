/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame;

import junit.framework.TestCase;

import static consulting.germain.snakegame.AssertionLimits.*;
/**
 * Created by mark_local on 11/09/2015.
 */
public class AssertionLimitsTest extends TestCase {

    public void testMinTileSide() throws Exception {
        assertEquals("minTileSide", 10, minTileSide);
    }

    public void testMaxTileSide() throws Exception {
        assertEquals("maxTileSide", 200, maxTileSide);
    }

    public void testSideMinAgainstMax() throws Exception {
        assertTrue("sideMin <= sideMax", minTileSide <= maxTileSide);
    }


    public void testMinTileSideFail() throws Exception {
        String msgLower = minTileSideFail.toLowerCase();

        assertTrue(msgLower.contains("min"));
        assertTrue(msgLower.contains("tile"));
        assertTrue(msgLower.contains("side"));
        assertTrue(msgLower.contains("below"));
    }

    public void testMaxTileSideFail() throws Exception {
        String msgLower = maxTileSideFail.toLowerCase();

        assertTrue(msgLower.contains("max"));
        assertTrue(msgLower.contains("tile"));
        assertTrue(msgLower.contains("side"));
        assertTrue(msgLower.contains("above"));
    }
}
