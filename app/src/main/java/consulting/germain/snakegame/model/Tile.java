/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.model;

import java.util.HashMap;
import java.util.Map;

import consulting.germain.snakegame.R;
import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TilePrize;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;
import consulting.germain.snakegame.enums.TileType;

/**
 * Created by mark_local on 11/09/2015.
 * holds appropriate drawableId, and can describe itself
 * also has a to/from direction to help with aligning the snake tiles
 * expect multiple instances of most tiles
 */
public class Tile {

    /**
     * for factory
     */
    private static Map<TilePrize, Tile>     prizeTileMap = new HashMap<>();
    /**
     * for factory
     */
    private static Map<TileSnakeHead, Tile> headTileMap  = new HashMap<>();
    /**
     * for factory
     */
    private static Map<TileSnakeBody, Tile> bodyTileMap  = new HashMap<>();
    /**
     * for factory
     */
    private static Map<TileSnakeTail, Tile> tailTileMap  = new HashMap<>();

    /**
     * tile type
     */
    private final TileType       tileType;
    /**
     * name within the tile type
     */
    private final String         name;
    /**
     * helps toString
     */
    private final String         description;
    /**
     * the drawble to be used
     */
    private final int            drawableId;
    /**
     * where to connect to
     */
    private final SnakeDirection directionTo;
    /**
     * where to connect from
     */
    private final SnakeDirection directionFrom;

    /**
     * ctor for a prize tile
     *
     * @param prize the enumeration specifying which tile this is.
     */
    private Tile(final TilePrize prize) {

        this.tileType = TileType.PRIZE;
        this.name = prize.name();
        this.description = tileType.toString() + ":" + prize.toString();
        this.directionFrom = SnakeDirection.NORTH; // meaningless here, but need something
        this.directionTo = SnakeDirection.NORTH; // meaningless here, but need something

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
     *
     * @param body the enumeration specifying which tile this is.
     */
    private Tile(final TileSnakeBody body) {

        this.tileType = TileType.SNAKE_BODY;
        this.name = body.name();
        this.description = tileType.toString() + ":" + body.toString();

        switch (body) {
            case NORTH:
                this.drawableId = R.drawable.snake_body_north;
                this.directionFrom = this.directionTo = SnakeDirection.NORTH;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_body_east;
                this.directionFrom = this.directionTo = SnakeDirection.EAST;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_body_south;
                this.directionFrom = this.directionTo = SnakeDirection.SOUTH;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_body_west;
                this.directionFrom = this.directionTo = SnakeDirection.WEST;
                break;
            case NORTH_TO_EAST:
                this.drawableId = R.drawable.snake_body_north_to_east;
                this.directionFrom = SnakeDirection.NORTH;
                this.directionTo = SnakeDirection.EAST;
                break;
            case NORTH_TO_WEST:
                this.drawableId = R.drawable.snake_body_north_to_west;
                this.directionFrom = SnakeDirection.NORTH;
                this.directionTo = SnakeDirection.WEST;
                break;
            case EAST_TO_NORTH:
                this.drawableId = R.drawable.snake_body_east_to_north;
                this.directionFrom = SnakeDirection.EAST;
                this.directionTo = SnakeDirection.NORTH;
                break;
            case EAST_TO_SOUTH:
                this.drawableId = R.drawable.snake_body_east_to_south;
                this.directionFrom = SnakeDirection.EAST;
                this.directionTo = SnakeDirection.SOUTH;
                break;
            case SOUTH_TO_EAST:
                this.drawableId = R.drawable.snake_body_south_to_east;
                this.directionFrom = SnakeDirection.SOUTH;
                this.directionTo = SnakeDirection.EAST;
                break;
            case SOUTH_TO_WEST:
                this.drawableId = R.drawable.snake_body_south_to_west;
                this.directionFrom = SnakeDirection.SOUTH;
                this.directionTo = SnakeDirection.WEST;
                break;
            case WEST_TO_NORTH:
                this.drawableId = R.drawable.snake_body_west_to_north;
                this.directionFrom = SnakeDirection.WEST;
                this.directionTo = SnakeDirection.NORTH;
                break;
            case WEST_TO_SOUTH:
                this.drawableId = R.drawable.snake_body_west_to_south;
                this.directionFrom = SnakeDirection.WEST;
                this.directionTo = SnakeDirection.SOUTH;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                this.directionFrom = this.directionTo =
                        SnakeDirection.NORTH; // meaningless here, but need something
                break;
        }

    }

    /**
     * ctor for a snake head tile
     *
     * @param head the enumeration specifying which tile this is.
     */
    private Tile(final TileSnakeHead head) {

        this.tileType = TileType.SNAKE_HEAD;
        this.name = head.name();
        this.description = tileType.toString() + ":" + head.toString();

        switch (head) {
            case NORTH:
                this.drawableId = R.drawable.snake_head_north;
                this.directionFrom = this.directionTo = SnakeDirection.NORTH;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_head_east;
                this.directionFrom = this.directionTo = SnakeDirection.EAST;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_head_south;
                this.directionFrom = this.directionTo = SnakeDirection.SOUTH;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_head_west;
                this.directionFrom = this.directionTo = SnakeDirection.WEST;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                this.directionFrom = this.directionTo =
                        SnakeDirection.NORTH; // meaningless here, but need something
                break;
        }
    }

    /**
     * ctor for a snake tail tile
     * @param tail the enumeration specifying which tile this is.
     */
    private Tile(final TileSnakeTail tail) {

        this.tileType = TileType.SNAKE_TAIL;
        this.name = tail.name();
        this.description = tileType.toString() + ":" + tail.toString();

        switch (tail) {
            case NORTH:
                this.drawableId = R.drawable.snake_tail_north;
                this.directionFrom = this.directionTo = SnakeDirection.NORTH;
                break;
            case EAST:
                this.drawableId = R.drawable.snake_tail_east;
                this.directionFrom = this.directionTo = SnakeDirection.EAST;
                break;
            case SOUTH:
                this.drawableId = R.drawable.snake_tail_south;
                this.directionFrom = this.directionTo = SnakeDirection.SOUTH;
                break;
            case WEST:
                this.drawableId = R.drawable.snake_tail_west;
                this.directionFrom = this.directionTo = SnakeDirection.WEST;
                break;

            default:
                // a safe default, that will show logic errors
                this.drawableId = R.drawable.red_star;
                this.directionFrom = this.directionTo =
                        SnakeDirection.NORTH; // meaningless here, but need something
                break;
        }
    }

    /**
     * factory for a prize tile
     *
     * @param prize    the enumeration specifying which tile this is.
     */
    public static Tile get(final TilePrize prize) {
        Tile ret = prizeTileMap.get(prize);
        if (ret == null) {
            ret = new Tile(prize);
            prizeTileMap.put(prize, ret);
        }
        return ret;
    }

    /**
     * factory for a body tile
     *
     * @param body    the enumeration specifying which tile this is.
     */
    public static Tile get(final TileSnakeBody body) {
        Tile ret = bodyTileMap.get(body);
        if (ret == null) {
            ret = new Tile(body);
            bodyTileMap.put(body, ret);
        }
        return ret;
    }

    /**
     * factory for a head tile
     *
     * @param head    the enumeration specifying which tile this is.
     */
    public static Tile get(final TileSnakeHead head) {
        Tile ret = headTileMap.get(head);
        if (ret == null) {
            ret = new Tile(head);
            headTileMap.put(head, ret);
        }
        return ret;
    }

    /**
     * factory for a tail tile
     *
     * @param tail    the enumeration specifying which tile this is.
     */
    public static Tile get(final TileSnakeTail tail) {
        Tile ret = tailTileMap.get(tail);
        if (ret == null) {
            ret = new Tile(tail);
            tailTileMap.put(tail, ret);
        }
        return ret;
    }

    /**
     * factory from type and name
     * @param type tile type
     * @param name name of the tile within type
     * @return relevant tile
     */
    public static Tile get(final TileType type, final String name) {
        switch (type) {
            case PRIZE:
                return get(TilePrize.valueOf(name));

            case SNAKE_HEAD:
                return get(TileSnakeHead.valueOf(name));

            case SNAKE_BODY:
                return get(TileSnakeBody.valueOf(name));

            case SNAKE_TAIL:
                return get(TileSnakeTail.valueOf(name));
        }
        return null;
    }

    /**
     * @return type of the tile (Prize, head .... )
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * @return helper for toString
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return drawable to use
     */
    public int getDrawableId() {
        return drawableId;
    }

    /**
     * @return where this tile connects to
     */
    public SnakeDirection getDirectionTo() {
        return directionTo;
    }

    /**
     * @return where this tile connects from
     */
    public SnakeDirection getDirectionFrom() {
        return directionFrom;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Tile that = (Tile) other;

        return getTileType() == that.getTileType()
                && getDrawableId() == that.getDrawableId()
                ;
    }

    @Override
    public int hashCode() {
        int result = getTileType().hashCode();
        result = 31 * result + getDrawableId();
        return result;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tileType=" + tileType +
                ", description=" + description +
                '}';
    }

    /**
     * @return the name of the tile enum within its type
     */
    public String getName() {
        return name;
    }
}
