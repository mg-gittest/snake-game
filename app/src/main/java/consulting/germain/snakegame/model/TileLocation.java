/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.model;

/**
 * Created by mark_local on 11/09/2015.
 * A combination of a logical tile and a pair of coordinates for where the tile should be drawn
 */
public class TileLocation {
    /**
     * location of the tile
     */
    protected     Location location;
    /** the logical tile to show at the location */
    private final Tile     tile;

    /**
     * ctor setting relevant Tile, and the location for this instance of the tile
     *
     * @param x    X Coord
     * @param y    Y Coord
     * @param tile tile to use, immovable or movable allowed
     */
    public TileLocation(int x, int y, Tile tile) {
        this(new Location(x, y), tile);
    }

    /**
     * ctor taking location and tile
     *
     * @param location where in the field the tile is located
     * @param tile     tile to use, immovable or movable allowed
     */
    public TileLocation(Location location, Tile tile) {
        this.location = location;
        this.tile = tile;
    }

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public Location getLocation() {
        return location;
    }

    public Tile getTile() {
        return tile;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        TileLocation that = (TileLocation) other;

        return getLocation() == that.getLocation()
                && getTile().equals(that.getTile());

    }

    @Override
    public int hashCode() {
        int result = location.hashCode();
        result = 31 * result + getTile().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TileLocation{" +
                "location=" + location +
                ", tile=" + tile.getDescription() +
                '}';
    }

    /**
     * compares only the location and ignored the tile at the location
     *
     * @param that other TileLocation to consider
     * @return true if looation is the same
     */
    public boolean sameLocation(TileLocation that) {

        return getX() == that.getX()
                && getY() == that.getY();
    }


}
