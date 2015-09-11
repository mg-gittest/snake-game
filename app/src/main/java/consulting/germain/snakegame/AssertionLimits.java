package consulting.germain.snakegame;

import android.content.res.Resources;

/**
 * Created by mark_local on 11/09/2015.
 * assertions so internal use only, hence not read from resources
 */
public class AssertionLimits {

    public static int minTileSide = 10;
    public static int maxTileSide = 200;

    public static String minTileSideFail = "Tile Side below minimum: " + minTileSide;
    public static String maxTileSideFail = "Tile Side above maximum: " + maxTileSide;

}
