/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

/**
 * Created by mark_local on 15/09/2015.
 * Trivial way to hold X,Y coordinate, and guarantee ordering rather than passing them separately
 * Coordinates are the integer location of a tile space on the field
 * Increasing X moves East, increasing Y moves South
 * Validate coordinates on construction against AssertionLimits, and throws accordingly
 * <p/>
 * Immutable, you do not move a coordiante, you move to another coordinate
 */
public class Location {

    private final int x;
    private final int y;

    public Location(int x, int y) {
        if (x < AssertionLimits.minXcoord) {
            throw new IllegalArgumentException(AssertionLimits.minXcoordFail);
        }
        if (y < AssertionLimits.minYcoord) {
            throw new IllegalArgumentException(AssertionLimits.minYcoordFail);
        }
        if (x > AssertionLimits.maxXcoord) {
            throw new IllegalArgumentException(AssertionLimits.maxXcoordFail);
        }
        if (y > AssertionLimits.maxYcoord) {
            throw new IllegalArgumentException(AssertionLimits.maxYcoordFail);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

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
        result = (1 + AssertionLimits.maxYcoord) * result + getY();
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
