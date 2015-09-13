/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.enums;

import junit.framework.TestCase;

/**
 * Created by mark_local on 11/09/2015.
 */
public class GameStateTest extends TestCase {
    public void testEnumCount() throws Exception {
        int count = 0;
        for (GameState val: GameState.values() ) {
            ++count;
        }
        // if the test fails,
        // then look to see that any switch values deal with all cases before fixing here
        assertEquals("count of enum values", 5, count);
    }

}
