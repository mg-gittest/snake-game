/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.model;

/*
 * Created by mark_local on 11/09/2015.
 * Extends the TileLocation with the ability to move the location with a SnakeDirection
 */

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Specialisation of TileLocation that allows the location to be moved
 */
public class TileLocationMovable extends TileLocation {

    static final String ERR_IMMOVABLE_TILE = "ctor given non-movable tile: ";

    /**
     * ctor setting relevant Tile, and the location for this instance of the tile
     * @param x X Coord
     * @param y Y Coord
     * @param tile tile to use
     */
    protected TileLocationMovable(int x, int y, Tile tile) {

        super(x, y, tile);
        if (!tile.isMovable()) {
            throw new IllegalArgumentException(ERR_IMMOVABLE_TILE + tile.getDescription());
        }
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        TileLocationMovable that = (TileLocationMovable) other;

        return getX() == that.getX()
                && getY() == that.getY()
                && getTile().equals(that.getTile());

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "TileLocationMovable{ " + super.toString() + " }";
    }
}

