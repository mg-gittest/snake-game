/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame;

import android.content.Context;
import android.graphics.Bitmap;


import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;
import consulting.germain.snakegame.enums.TileType;

/**
 * Created by mark_local on 11/09/2015.
 * holds appropriate drawableId, and knows the type type and if the tile should be movable
 */
public class Tile {
    private final int tileSide;
    private final TileType tileType;
    private final boolean movable;
    private final int drawableId;

    public int getTileSide() {
        return tileSide;
    }

    public TileType getTileType() {
        return tileType;
    }

    public boolean isMovable() {
        return movable;
    }

    public int getDrawableId() {
        return drawableId;
    }

    /**
     * ctor for a prize tile
     * @param tileSide the size of one side of a square tile
     * @param prize the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final TilePrize prize) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.PRIZE;
        this.movable = false;

        switch (prize) {
            case APPLE:
                this.drawableId = R.drawable.prize_apple;
                break;
            case CAKE:
                this.drawableId = R.drawable.prize_cake;
                break;
            case CHERRY:
                this.drawableId = R.drawable.prize_cherry;
                break;
            case SCISSORS:
                this.drawableId = R.drawable.prize_scissors;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                break;
        }

    }

    /**
     * ctor for a snake body tile
     * @param tileSide the size of one side of a square tile
     * @param body the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final TileSnakeBody body) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_BODY;
        this.movable = false;

        switch (body) {
            case NORTH:
                this.drawableId = R.drawable.snake_body_north;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_body_east;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_body_south;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_body_west;
                break;
            case NORTH_TO_EAST:
                this.drawableId = R.drawable.snake_body_north_to_east;
                break;
            case NORTH_TO_WEST:
                this.drawableId = R.drawable.snake_body_north_to_west;
                break;
            case EAST_TO_NORTH:
                this.drawableId = R.drawable.snake_body_east_to_north;
                break;
            case EAST_TO_SOUTH:
                this.drawableId = R.drawable.snake_body_east_to_south;
                break;
            case SOUTH_TO_EAST:
                this.drawableId = R.drawable.snake_body_south_to_east;
                break;
            case SOUTH_TO_WEST:
                this.drawableId = R.drawable.snake_body_south_to_west;
                break;
            case WEST_TO_NORTH:
                this.drawableId = R.drawable.snake_body_west_to_north;
                break;
            case WEST_TO_SOUTH:
                this.drawableId = R.drawable.snake_body_west_to_south;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                break;
        }

    }

    /**
     * ctor for a snake head tile
     * @param tileSide the size of one side of a square tile
     * @param head the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final TileSnakeHead head) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_HEAD;
        this.movable = true;

        switch (head) {
            case NORTH:
                this.drawableId = R.drawable.snake_head_north;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_head_east;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_head_south;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_head_west;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                break;
        }
    }


    /**
     * ctor for a snake tail tile
     * @param tileSide the size of one side of a square tile
     * @param tail the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final TileSnakeTail tail) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_TAIL;
        this.movable = true;

        switch (tail) {
            case NORTH:
                this.drawableId = R.drawable.snake_tail_north;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_tail_east;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_tail_south;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_tail_west;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                break;
        }
    }

    /** allows check that we have consistent tile side on all constructor calls */
    private static int tileSideCheck = 0;

    /**
     * validates supplied tile side against AssertionLimits
     * @param tileSide what to validate
     */
    private void validateTileSide(int tileSide) {

        if (tileSideCheck == 0) {
            tileSideCheck = tileSide;
        } else {
            if (tileSide != tileSideCheck) {
                throw new IllegalArgumentException("inconsistent tileSide. Expected: "
                                                    + tileSideCheck + " actual: " + tileSide);
            }
        }

        if (tileSide < AssertionLimits.minTileSide) {
            throw new IllegalArgumentException(AssertionLimits.minTileSideFail);
        }
        if (tileSide > AssertionLimits.maxTileSide) {
            throw new IllegalArgumentException(AssertionLimits.maxTileSideFail);
        }
    }
}
