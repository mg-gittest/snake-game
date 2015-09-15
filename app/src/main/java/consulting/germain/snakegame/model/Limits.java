/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.model;

/**
 * Created by mark_local on 11/09/2015.
 * assertions so internal use only, hence not read from resources
 */
public class Limits {

    public static final int minTileSide = 10;
    public static final int maxTileSide = 200;

    public static final String minTileSideFail = "Tile Side below minimum: " + minTileSide;
    public static final String maxTileSideFail = "Tile Side above maximum: " + maxTileSide;

    public static final int minXcoord = 0;
    public static final int minYcoord = 0;

    public static final String minXcoordFail = "X coordinate below minimum: " + minXcoord;
    public static final String minYcoordFail = "Y coordinate below minimum: " + minYcoord;

    public static final int maxXcoord = 255;
    public static final int maxYcoord = 255;

    public static final String maxXcoordFail = "X coordinate above maximium: " + maxXcoord;
    public static final String maxYcoordFail = "Y coordinate above maximium: " + maxYcoord;

    public static final int maxMoveY = maxYcoord - minYcoord - 1;
    public static final int maxMoveX = maxXcoord - minXcoord - 1;

}
