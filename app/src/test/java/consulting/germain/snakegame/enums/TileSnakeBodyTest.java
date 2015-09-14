/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 11/09/2015.
 * test for Tile
 */
public class TileSnakeBodyTest {

    @Test
    public void testEnumCount() throws Exception {
        int count = 0;
        for (TileSnakeBody val: TileSnakeBody.values() ) {
            ++count;
        }
        // if the test fails,
        // then look to see that any switch values deal with all cases before fixing here
        assertEquals("count of enum values", 12, count);
    }

}
