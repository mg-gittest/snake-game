/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import consulting.germain.snakegame.enums.EdgeRollBehaviour;
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
     * project location after number of moves in given direction, limited by boundaries
     *
     * @param numberMoves       how many moves, if < 1 then don't move
     * @param snakeDirection    which way to move
     * @param edgeRollBehaviour how to hadnle reaching the edge
     * @return the projected positon with those movements
     */
    public Location getProjectedLocation(
            int numberMoves,
            SnakeDirection snakeDirection,
            EdgeRollBehaviour edgeRollBehaviour) {

        // don't move with a non-positive number of moves
        if (numberMoves < 1) {
            return this;
        }

        Location newLocation;

        switch (snakeDirection) {
            case NORTH:
                newLocation = new Location(x, calcNewY(-numberMoves, edgeRollBehaviour));
                break;

            case SOUTH:
                newLocation = new Location(x, calcNewY(+numberMoves, edgeRollBehaviour));
                break;

            case EAST:
                newLocation = new Location(calcNewX(+numberMoves, edgeRollBehaviour), y);
                break;

            case WEST:
                newLocation = new Location(calcNewX(-numberMoves, edgeRollBehaviour), y);
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
     * @param edgeRollBehaviour how to roll movement at edge of field
     * @return new Y for given move
     */
    private int calcNewY(int numberMoves, EdgeRollBehaviour edgeRollBehaviour) {
        int newY = y + numberMoves;

        switch (edgeRollBehaviour) {
            case ROLL_BLOCKED: // fall through
            case ROLL_X_ALLOWED:
                // not allowed to roll top/bottom so trap at limits
                if (newY < Limits.minYcoord) {
                    newY = Limits.minYcoord;
                } else if (newY > Limits.maxYcoord) {
                    newY = Limits.maxYcoord;
                }
                break;

            default:
                // we can roll, so check sign to decide logic
                if (numberMoves > 0) {
                    // restrict so that we don't wrap multiple times
                    numberMoves = Math.min(numberMoves, +Limits.maxMoveY);
                    newY = y + numberMoves;
                    final int diff = newY - Limits.maxYcoord;
                    if (diff > 0) {
                        // went beyond the limit, so wrap by overshoot
                        newY = Limits.minYcoord + diff;
                    }
                } else {
                    // restrict so that we don't wrap multiple times
                    numberMoves = Math.max(numberMoves, -Limits.maxMoveY);
                    newY = y + numberMoves;
                    final int diff = Limits.maxYcoord - newY;
                    if (diff > 0) {
                        // went beyond the limit, so wrap by overshoot
                        newY = Limits.maxYcoord - diff;
                    }
                }
                break;

        }

        return newY;
    }

    /**
     * calculate a new X coordinate given the number of moves to make, and roll behaviour
     *
     * @param numberMoves       how many tiles to move, restricted to maxMoveY
     * @param edgeRollBehaviour how to roll movement at edge of field
     * @return new X for given move
     */
    private int calcNewX(int numberMoves, EdgeRollBehaviour edgeRollBehaviour) {
        int newX = x + numberMoves;

        switch (edgeRollBehaviour) {
            case ROLL_BLOCKED: // fall through
            case ROLL_Y_ALLOWED:
                // not allowed to roll top/bottom so trap at limits
                if (newX < Limits.minXcoord) {
                    newX = Limits.minXcoord;
                } else if (newX > Limits.maxXcoord) {
                    newX = Limits.maxXcoord;
                }
                break;

            default:
                // we can roll, so check sign to decide logic
                if (numberMoves > 0) {
                    // restrict so that we don't wrap multiple times
                    numberMoves = Math.min(numberMoves, +Limits.maxMoveX);
                    newX = x + numberMoves;
                    final int diff = newX - Limits.maxXcoord;
                    if (diff > 0) {
                        // went beyond the limit, so wrap by overshoot
                        newX = Limits.minXcoord + diff;
                    }
                } else {
                    // restrict so that we don't wrap multiple times
                    numberMoves = Math.max(numberMoves, -Limits.maxMoveX);
                    newX = x + numberMoves;
                    final int diff = Limits.maxXcoord - newX;
                    if (diff > 0) {
                        // went beyond the limit, so wrap by overshoot
                        newX = Limits.maxXcoord - diff;
                    }
                }
                break;

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

        Location location = (Location) other;

        return getX() == location.getX() && getY() == location.getY();

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
