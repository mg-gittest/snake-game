/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import org.junit.Assert;
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

    private Tile target;
    private final int expectSide = 50;

    @Test
    public void testGetTileSide() throws Exception {
        target = new Tile(expectSide, TilePrize.APPLE);
        int actual = target.getTileSide();
        assertEquals("tileSide", expectSide, actual);
    }

    @Test
    public void testGetTileTypePrize() throws Exception {
        for (TilePrize type : TilePrize.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.PRIZE, actual);
        }
    }

    @Test
    public void testGetTileTypeHead() throws Exception {
        for (TileSnakeHead type : TileSnakeHead.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_HEAD, actual);
        }
    }

    @Test
    public void testGetTileTypeBody() throws Exception {
        for (TileSnakeBody type : TileSnakeBody.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_BODY, actual);
        }
    }

    @Test
    public void testGetTileTypeTail() throws Exception {
        for (TileSnakeTail type : TileSnakeTail.values()) {
            target = new Tile(expectSide, type);
            TileType actual = target.getTileType();
            assertEquals("tileType", TileType.SNAKE_TAIL, actual);
        }
    }

    @Test
    public void testIsMovable() throws Exception {

        for (TilePrize type : TilePrize.values()) {
            target = new Tile(expectSide, type);
            Assert.assertFalse("isMovable", target.isMovable());
        }

        for (TileSnakeHead type : TileSnakeHead.values()) {
            target = new Tile(expectSide, type);
            Assert.assertTrue("isMovable", target.isMovable());
        }

        for (TileSnakeBody type : TileSnakeBody.values()) {
            target = new Tile(expectSide, type);
            Assert.assertFalse("isMovable", target.isMovable());
        }

        for (TileSnakeTail type : TileSnakeTail.values()) {
            target = new Tile(expectSide, type);
            Assert.assertTrue("isMovable", target.isMovable());
        }

    }

    @Test
    public void testGetDrawableId() throws Exception {

        int drawableIdExpected = R.drawable.snake_body_east_to_north;
        target = new Tile(expectSide, TileSnakeBody.EAST_TO_NORTH);
        assertEquals("drawableId snake_body_east_to_north", drawableIdExpected, target.getDrawableId());

        drawableIdExpected = R.drawable.snake_head_east;
        target = new Tile(expectSide, TileSnakeHead.EAST);
        assertEquals("drawableId snake_head_east", drawableIdExpected, target.getDrawableId());
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBadSideMin() throws Exception {

        final int badSide = AssertionLimits.minTileSide - 1;
        String message = AssertionLimits.minTileSideFail;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        target = new Tile(badSide, TileSnakeBody.EAST_TO_NORTH);
    }

    @Test
    public void testBadSideMax() throws Exception {

        final int badSide = AssertionLimits.maxTileSide + 1;
        String message = AssertionLimits.maxTileSideFail;

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(message);
        target = new Tile(badSide, TileSnakeBody.EAST_TO_NORTH);
    }

}
