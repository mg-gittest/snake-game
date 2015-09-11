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
 * loads appropriate bitmap, and knows if the tile should be movable
 */
public class Tile {
    private final int tileSide;
    private final TileType tileType;
    private final boolean movable;
    private Bitmap bitmap;

    public TileType getTileType() {
        return tileType;
    }

    public boolean isMovable() {
        return movable;
    }

    public Bitmap getBitmap() {
        return bitmap;
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
                drawableId = R.drawable.prizeApple;
                break;
            case CAKE:
                drawableId = R.drawable.prizeCake;
                break;
            case CHERRY:
                drawableId = R.drawable.prizeCherry;
                break;
            case SCISSORS:
                drawableId = R.drawable.prizeScissors;
                break;
            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.redstar;
                break;
        }

        loadBitmap(context, drawableId);
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
                drawableId = R.drawable.snakeBodyNorth;
                break;
            case EAST:
                drawableId = R.drawable.snakeBodyEast;
                break;
            case SOUTH:
                drawableId = R.drawable.snakeBodySouth;
                break;
            case WEST:
                drawableId = R.drawable.snakeBodyWest;
                break;
            case NORTH_TO_EAST:
                drawableId = R.drawable.snakeBodyNorthToEast;
                break;
            case NORTH_TO_WEST:
                drawableId = R.drawable.snakeBodyNorthToWest;
                break;
            case EAST_TO_NORTH:
                drawableId = R.drawable.snakeBodyEastToNorth;
                break;
            case EAST_TO_SOUTH:
                drawableId = R.drawable.snakeBodyEastToSouth;
                break;
            case SOUTH_TO_EAST:
                drawableId = R.drawable.snakeBodySouthToEast;
                break;
            case SOUTH_TO_WEST:
                drawableId = R.drawable.snakeBodySouthToWest;
                break;
            case WEST_TO_NORTH:
                drawableId = R.drawable.snakeBodyWestToNorth;
                break;
            case WEST_TO_SOUTH:
                drawableId = R.drawable.snakeBodyWestToSouth;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.redstar;
                break;
        }

        loadBitmap(context, drawableId);
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
        this.movable = false;

        int drawableId;
        switch (head) {
            case NORTH:
                drawableId = R.drawable.snakeHeadNorth;
                break;
            case EAST:
                drawableId = R.drawable.snakeHeadEast;
                break;
            case SOUTH:
                drawableId = R.drawable.snakeHeadSouth;
                break;
            case WEST:
                drawableId = R.drawable.snakeHeadWest;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.redstar;
                break;
        }

        loadBitmap(context, drawableId);
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
        this.movable = false;

        int drawableId;
        switch (tail) {
            case NORTH:
                drawableId = R.drawable.snakeTailNorth;
                break;
            case EAST:
                drawableId = R.drawable.snakeTailEast;
                break;
            case SOUTH:
                drawableId = R.drawable.snakeTailSouth;
                break;
            case WEST:
                drawableId = R.drawable.snakeTailWest;
                break;

            default:
                // a safe default, that will show logic errors
                drawableId = R.drawable.redstar;
                break;
        }

        loadBitmap(context, drawableId);
    }

    /**
     * load a bitmap given the context and a drawable id
     * @param context     context to use
     * @param drawableId  id of the drawable
     */
    private void loadBitmap(Context context, int drawableId) {

        bitmap = Bitmap.createBitmap(tileSide, tileSide, Bitmap.Config.ARGB_8888);

        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        drawable.setBounds(0, 0, tileSide, tileSide);

        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);
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
