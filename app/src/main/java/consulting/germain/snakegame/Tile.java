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
 * loads appropriate bitmap, and knows the type type and if the tile should be movable
 */
public class Tile {
    private final int tileSide;
    private final TileType tileType;
    private final boolean movable;
    private final Bitmap bitmap;

    public TileType getTileType() {
        return tileType;
    }

    public boolean isMovable() {
        return movable;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getTileSide() {
        return tileSide;
    }

    /**
     * ctor for a prize tile
     * @param tileSide the size of one side of a square tile
     * @param context context in which to do drawing
     * @param prize the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final Context context, final TilePrize prize) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.PRIZE;
        this.movable = false;

        int drawableId;
        switch (prize) {
            case APPLE:
                drawableId = R.drawable.prize_apple;
                break;
            case CAKE:
                drawableId = R.drawable.prize_cake;
                break;
            case CHERRY:
                drawableId = R.drawable.prize_cherry;
                break;
            case SCISSORS:
                drawableId = R.drawable.prize_scissors;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.red_star;
                break;
        }

        this.bitmap = loadBitmap(context, drawableId);
    }

    /**
     * ctor for a snake body tile
     * @param tileSide the size of one side of a square tile
     * @param context context in which to do drawing
     * @param body the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final Context context, final TileSnakeBody body) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_BODY;
        this.movable = false;

        int drawableId;
        switch (body) {
            case NORTH:
                drawableId = R.drawable.snake_body_north;
                break;
            case EAST:
                drawableId = R.drawable.snake_body_east;
                break;
            case SOUTH:
                drawableId = R.drawable.snake_body_south;
                break;
            case WEST:
                drawableId = R.drawable.snake_body_west;
                break;
            case NORTH_TO_EAST:
                drawableId = R.drawable.snake_body_north_to_east;
                break;
            case NORTH_TO_WEST:
                drawableId = R.drawable.snake_body_north_to_west;
                break;
            case EAST_TO_NORTH:
                drawableId = R.drawable.snake_body_east_to_north;
                break;
            case EAST_TO_SOUTH:
                drawableId = R.drawable.snake_body_east_to_south;
                break;
            case SOUTH_TO_EAST:
                drawableId = R.drawable.snake_body_south_to_east;
                break;
            case SOUTH_TO_WEST:
                drawableId = R.drawable.snake_body_south_to_west;
                break;
            case WEST_TO_NORTH:
                drawableId = R.drawable.snake_body_west_to_north;
                break;
            case WEST_TO_SOUTH:
                drawableId = R.drawable.snake_body_west_to_south;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.red_star;
                break;
        }

        this.bitmap = loadBitmap(context, drawableId);
    }

    /**
     * ctor for a snake head tile
     * @param tileSide the size of one side of a square tile
     * @param context context in which to do drawing
     * @param head the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final Context context, final TileSnakeHead head) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_BODY;
        this.movable = true;

        int drawableId;
        switch (head) {
            case NORTH:
                drawableId = R.drawable.snake_head_north;
                break;
            case EAST:
                drawableId = R.drawable.snake_head_east;
                break;
            case SOUTH:
                drawableId = R.drawable.snake_head_south;
                break;
            case WEST:
                drawableId = R.drawable.snake_head_west;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.red_star;
                break;
        }

        this.bitmap = loadBitmap(context, drawableId);
    }


    /**
     * ctor for a snake tail tile
     * @param tileSide the size of one side of a square tile
     * @param context context in which to do drawing
     * @param tail the enumeration specifying which tile this is.
     */
    public Tile(final int tileSide, final Context context, final TileSnakeTail tail) {

        validateTileSide(tileSide);
        this.tileSide = tileSide;
        this.tileType = TileType.SNAKE_BODY;
        this.movable = true;

        int drawableId;
        switch (tail) {
            case NORTH:
                drawableId = R.drawable.snake_tail_north;
                break;
            case EAST:
                drawableId = R.drawable.snake_tail_east;
                break;
            case SOUTH:
                drawableId = R.drawable.snake_tail_south;
                break;
            case WEST:
                drawableId = R.drawable.snake_tail_west;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.red_star;
                break;
        }

        this.bitmap = loadBitmap(context, drawableId);
    }

    /**
     * load a bitmap given the context and a drawable id
     * @param context     context to use
     * @param drawableId  id of the drawable
     */
    private Bitmap loadBitmap(Context context, int drawableId) {

        Bitmap bm = Bitmap.createBitmap(tileSide, tileSide, Bitmap.Config.ARGB_8888);

        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        drawable.setBounds(0, 0, tileSide, tileSide);

        Canvas canvas = new Canvas(bm);
        drawable.draw(canvas);

        return bm;
    }

    /**
     * validates supplied tile side against AssertionLimits
     * @param tileSide what to validate
     */
    private void validateTileSide(int tileSide) {

        if (tileSide < AssertionLimits.minTileSide) {
            throw new IllegalArgumentException(AssertionLimits.minTileSideFail);
        }
        if (tileSide < AssertionLimits.maxTileSide) {
            throw new IllegalArgumentException(AssertionLimits.maxTileSideFail);
        }
    }
}
