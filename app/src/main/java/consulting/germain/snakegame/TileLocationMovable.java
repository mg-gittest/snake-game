/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame;

/*
 * Created by mark_local on 11/09/2015.
 * Extends the TileLocation with the ability to move the location with a SnakeDirection
 */

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Specialisation of Tile Location that allows the location to be moved
 */
public class TileLocationMovable extends TileLocation {

    /**
     * ctor setting relevant Tile, and the location for this instance of the tile
     * @param x X Coord
     * @param y Y Coord
     * @param tile tile to use
     */
    protected TileLocationMovable(int x, int y, Tile tile) {
        super(x, y, tile);
    }

    /**
     * Move the tile location as requested, makes no attempt to validate the new location
     * @param snakeDirection the relevant direction in which to move
     */
    public void move(SnakeDirection snakeDirection) {
        switch (snakeDirection) {
            case NORTH:
                --y;
                break;
            case EAST:
                ++x;
                break;
            case SOUTH:
                ++y;
                break;
            case WEST:
                --x;
                break;
            default:
                throw new IllegalArgumentException("unexpected SnakeDirection: " + snakeDirection);
        }
    }
}

