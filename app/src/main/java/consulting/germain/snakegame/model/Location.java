/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Created by mark_local on 15/09/2015.
 * Trivial way to hold X,Y coordinate, and guarantee ordering rather than passing them separately
 * Coordinates are the integer location of a tile space on the field
 * Increasing X moves East, increasing Y moves South
 * Validate coordinates on construction against Limits, and throws accordingly
 * <p/>
 * Immutable, you do not move a coordiante, you move to another coordinate
 */
public class Location {

    private final int x;
    private final int y;

    /**
     * ctor from X/Y coordinates
     *
     * @param x X coord
     * @param y Y coord
     */
    public Location(int x, int y) {
        if (x < Limits.minXcoord) {
            throw new IllegalArgumentException(Limits.minXcoordFail);
        }
        if (y < Limits.minYcoord) {
            throw new IllegalArgumentException(Limits.minYcoordFail);
        }
        if (x > Limits.maxXcoord) {
            throw new IllegalArgumentException(Limits.maxXcoordFail);
        }
        if (y > Limits.maxYcoord) {
            throw new IllegalArgumentException(Limits.maxYcoordFail);
        }
        this.x = x;
        this.y = y;
    }

    /**
     * copy ctor
     *
     * @param otherLocation what to copy
     */
    public Location(Location otherLocation) {
        // no need to validate as previous ctor will have done that for us
        x = otherLocation.x;
        y = otherLocation.y;
    }

    /**
     * project location after one move in given direction, roll behaviour from Settings
     *
     * @param snakeDirection    which way to move
     * @return the projected positon with those movements
     */
    public Location getProjectedLocation(SnakeDirection snakeDirection) {
        return getProjectedLocation(1, snakeDirection);
    }

    /**
     * project location after number of moves in given direction, roll behaviour from Settings
     *
     * @param numberMoves       how many moves, if < 1 then don't move
     * @param snakeDirection    which way to move
     * @return the projected positon with those movements
     */
    public Location getProjectedLocation(int numberMoves, SnakeDirection snakeDirection) {

        // don't move with a non-positive number of moves
        if (numberMoves < 1) {
            return this;
        }

        Location newLocation;

        switch (snakeDirection) {
            case NORTH:
                newLocation = new Location(x, calcNewY(-numberMoves));
                break;

            case SOUTH:
                newLocation = new Location(x, calcNewY(+numberMoves));
                break;

            case EAST:
                newLocation = new Location(calcNewX(+numberMoves), y);
                break;

            case WEST:
                newLocation = new Location(calcNewX(-numberMoves), y);
                break;

            default:
                throw new IllegalArgumentException("unexpected SnakeDirection: " + snakeDirection);
        }

        return newLocation;
    }

    /**
     * calculate a new Y coordinate given the number of moves to make, and roll behaviour
     *
     * @param numberMoves       how many tiles to move, restricted to maxMoveY
     * @return new Y for given move
     */
    private int calcNewY(int numberMoves) {
        int newY = y;
        if (numberMoves > 0) {
            // restrict so that we don't wrap multiple times
            numberMoves = Math.min(numberMoves, +Limits.maxMoveY);
            newY = y + numberMoves;
            final int diff = newY - Limits.maxYcoord;
            if (diff > 0) {
                // went beyond the limit, so wrap by overshoot
                newY = Limits.minYcoord + diff;
            }
        } else if (numberMoves < 0) {
            // restrict so that we don't wrap multiple times
            numberMoves = Math.max(numberMoves, -Limits.maxMoveY);
            newY = y + numberMoves;
            final int diff = Limits.maxYcoord - newY;
            if (diff > 0) {
                // went beyond the limit, so wrap by overshoot
                newY = Limits.maxYcoord - diff;
            }
        }

        return newY;
    }

    /**
     * calculate a new X coordinate given the number of moves to make, and roll behaviour
     *
     * @param numberMoves how many tiles to move, restricted to maxMoveY
     * @return new X for given move
     */
    private int calcNewX(int numberMoves) {

        int newX = x;
        if (numberMoves > 0) {
            // restrict so that we don't wrap multiple times
            numberMoves = Math.min(numberMoves, +Limits.maxMoveX);
            newX = x + numberMoves;
            final int diff = newX - Limits.maxXcoord;
            if (diff > 0) {
                // went beyond the limit, so wrap by overshoot
                newX = Limits.minXcoord + diff;
            }
        } else if (numberMoves < 0) {
            // restrict so that we don't wrap multiple times
            numberMoves = Math.max(numberMoves, -Limits.maxMoveX);
            newX = x + numberMoves;
            final int diff = Limits.maxXcoord - newX;
            if (diff > 0) {
                // went beyond the limit, so wrap by overshoot
                newX = Limits.maxXcoord - diff;
            }
        }

        return newX;
    }

    /**
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return Y coordinate
     */
    public int getY() { return y; }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Location that = (Location) other;

        return this.getX() == that.getX() && this.getY() == that.getY();

    }

    @Override
    public int hashCode() {
        int result = getX();
        result = (1 + Limits.maxYcoord) * result + getY();
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
