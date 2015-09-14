/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame;

/**
 * Created by mark_local on 11/09/2015.
 * A combination of a logical tile and a pair of coordinates for where the tile should be drawn
 */
public class TileLocation {
    /** abscissa on the field, increases down field (south) */
    protected     int  x;
    /** ordinate on the field, increases across field (east)  */
    protected     int  y;
    /** the logical tile to show at the location */
    private final Tile tile;

    /**
     * ctor setting relevant Tile, and the location for this instance of the tile
     *
     * @param x    X Coord
     * @param y    Y Coord
     * @param tile tile to use
     */
    public TileLocation(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile getTile() {
        return tile;
    }

}
