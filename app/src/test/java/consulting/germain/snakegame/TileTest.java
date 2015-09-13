/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame;

import junit.framework.TestCase;

import org.junit.Test;

import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;
import consulting.germain.snakegame.enums.TileType;

/**
 * Created by mark_local on 13/09/2015.
 * Tests the various constructors for tile behave correctly
 */
public class TileTest extends TestCase {

    private Tile target;
    private final int expectSide = 50;

    public void testGetTileSide() throws Exception {
        target = new Tile(expectSide, TilePrize.APPLE);
        int actual = target.getTileSide();
        assertEquals("tileSide", expectSide, actual);
    }

    public void testGetTileTypePrize() throws Exception {
        for (TilePrize type : TilePrize.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.PRIZE, actual);
        }
    }

    public void testGetTileTypeHead() throws Exception {
        for (TileSnakeHead type : TileSnakeHead.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_HEAD, actual);
        }
    }

    public void testGetTileTypeBody() throws Exception {
        for (TileSnakeBody type : TileSnakeBody.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_BODY, actual);
        }
    }

    public void testGetTileTypeTail() throws Exception {
        for (TileSnakeTail type : TileSnakeTail.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_TAIL, actual);
        }
    }

    public void testIsMovable() throws Exception {

        for (TilePrize type : TilePrize.values()) {
            target = new Tile(expectSide, type);
            assertFalse("isMovable", target.isMovable());
        }

        for (TileSnakeHead type : TileSnakeHead.values()) {
            target = new Tile(expectSide, type);
            assertTrue("isMovable", target.isMovable());
        }

        for (TileSnakeBody type : TileSnakeBody.values()) {
            target = new Tile(expectSide, type);
            assertFalse("isMovable", target.isMovable());
        }

        for (TileSnakeTail type : TileSnakeTail.values()) {
            target = new Tile(expectSide, type);
            assertTrue("isMovable", target.isMovable());
        }

    }

    public void testBadSide() throws Exception {

        target = new Tile(expectSide, TileSnakeBody.EAST_TO_NORTH);

        try {
            target = new Tile(expectSide + 1, TileSnakeBody.EAST_TO_NORTH);
        } catch (IllegalArgumentException e) {
            String msg = e.getMessage();
            assertTrue(msg.startsWith("inconsistent tileSide"));
        }

    }
}
