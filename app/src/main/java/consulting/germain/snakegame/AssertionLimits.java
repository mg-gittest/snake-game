/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame;

/**
 * Created by mark_local on 11/09/2015.
 * assertions so internal use only, hence not read from resources
 */
public class AssertionLimits {

    public static final int minTileSide = 10;
    public static final int maxTileSide = 200;

    public static final String minTileSideFail = "Tile Side below minimum: " + minTileSide;
    public static final String maxTileSideFail = "Tile Side above maximum: " + maxTileSide;

}
