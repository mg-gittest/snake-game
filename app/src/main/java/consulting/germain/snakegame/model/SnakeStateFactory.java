/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.LinkedList;

import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

/**
 * Created by mark_local on 15/09/2015.
 * Manufactures SnakeState in different styles
 */
public class SnakeStateFactory {

    public static final int tileSideDefault = 45;

    // a snake heading east and 11 tiles long
    public static final int bodyTileCountDefault = 9;
    public static final int tailDefaultX         = 15;
    public static final int headDefaultX         = tailDefaultX + bodyTileCountDefault + 1;
    public static final int headDefaultY         = 35;
    public static final int tailDefaultY         = headDefaultY;

    public static final Location headLocationDefault = new Location(headDefaultX, headDefaultY);
    public static final Location tailLocationDefault = new Location(tailDefaultX, tailDefaultY);

    public static final Tile headTileDefault = new Tile(tileSideDefault, TileSnakeHead.EAST);
    public static final Tile tailTileDefault = new Tile(tileSideDefault, TileSnakeTail.EAST);
    public static final Tile bodyTileDefault = new Tile(tileSideDefault, TileSnakeBody.EAST);

    public static final TileLocation headTileLocationDefault =
            new TileLocation(headLocationDefault, headTileDefault);
    public static final TileLocation tailTileLocationDefault =
            new TileLocation(tailLocationDefault, tailTileDefault);

    public static SnakeState createDefault() {

        LinkedList<TileLocation> bodyLocations = new LinkedList<>();
        bodyLocations.addFirst(headTileLocationDefault);

        for (int idx = 1; idx <= bodyTileCountDefault; ++idx) {
            Location bodyLocation = new Location(tailDefaultX + idx, tailDefaultY);
            TileLocation bodyTileLocation = new TileLocation(bodyLocation, bodyTileDefault);
            bodyLocations.addLast(bodyTileLocation);
        }
        bodyLocations.addLast(tailTileLocationDefault);

        SnakeState target = new SnakeState(bodyLocations);

        return target;
    }

}
