/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.R;
import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;
import consulting.germain.snakegame.enums.TileType;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 13/09/2015.
 * Tests the various constructors for tile behave correctly
 */
public class TileTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    private Tile target;

    @Test
    public void testGetTileTypePrize() throws Exception {
        for (TilePrize type : TilePrize.values()) {
            target = new Tile(type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.PRIZE, actual);
        }
    }

    @Test
    public void testGetTileTypeHead() throws Exception {
        for (TileSnakeHead type : TileSnakeHead.values()) {
            target = new Tile(type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_HEAD, actual);
        }
    }

    @Test
    public void testGetTileTypeBody() throws Exception {
        for (TileSnakeBody type : TileSnakeBody.values()) {
            target = new Tile(type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_BODY, actual);
        }
    }

    @Test
    public void testGetTileTypeTail() throws Exception {
        for (TileSnakeTail type : TileSnakeTail.values()) {
            target = new Tile(type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_TAIL, actual);
        }
    }


    @Test
    public void testGetDrawableId() throws Exception {

        int drawableIdExpected = R.drawable.snake_body_east_to_north;
        target = new Tile(TileSnakeBody.EAST_TO_NORTH);
        assertEquals("drawableId snake_body_east_to_north", drawableIdExpected, target.getDrawableId());

        drawableIdExpected = R.drawable.snake_head_east;
        target = new Tile(TileSnakeHead.EAST);
        assertEquals("drawableId snake_head_east", drawableIdExpected, target.getDrawableId());
    }

}
