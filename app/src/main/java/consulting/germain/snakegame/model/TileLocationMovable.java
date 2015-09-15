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
     *
     * @param location where in the field the tile is located
     * @param tile     tile to use, must be movable
     */
    public TileLocationMovable(final Location location, final Tile tile) {
        super(location, tile);
        if (!tile.isMovable()) {
            throw new IllegalArgumentException(ERR_IMMOVABLE_TILE + tile.getDescription());
        }
    }

    /**
     * Move the tile location as requested, location class validates against internal not field limits
     * @param snakeDirection the relevant direction in which to move
     */
    public void move(final SnakeDirection snakeDirection) {
        // set the number of moves we make
        final int numberMoves = 1;
        location =
                location.getProjectedLocation(numberMoves, snakeDirection, Settings.edgeRollBehaviour);
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

