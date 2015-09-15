/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.LinkedList;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

/**
 * Created by mark_local on 15/09/2015.
 */
public class SnakeMovementFactory {

    private int tileSideDefault = 45;

    // a snake heading east and 11 tiles long
    private SnakeDirection headDirectionDefault = SnakeDirection.EAST;
    private SnakeDirection tailDirectionDefault = headDirectionDefault;

    private int bodyTileCountDefault = 9;
    private int tailDefaultX         = 15;
    private int headDefaultX         = tailDefaultX + bodyTileCountDefault + 1;
    private int headDefaultY         = 35;
    private int tailDefaultY         = headDefaultY;

    private Location headLocationDefault;
    private Location tailLocationDefault;

    private Tile headTileDefault;
    private Tile tailTileDefault;
    private Tile bodyTileDefault;

    private TileLocationMovable headTileLocationDefault;
    private TileLocationMovable tailTileLocationDefault;

    private Location                    bodyLocation;
    private SnakeMovement.BodyLocations bodyLocationsDefault;

    private SnakeMovement target;

    public SnakeMovement createDefault() {
        headLocationDefault = new Location(headDefaultX, headDefaultY);
        headTileDefault = new Tile(tileSideDefault, TileSnakeHead.EAST);
        headTileLocationDefault = new TileLocationMovable(headLocationDefault, headTileDefault);

        tailLocationDefault = new Location(tailDefaultX, tailDefaultY);
        tailTileDefault = new Tile(tileSideDefault, TileSnakeTail.EAST);
        tailTileLocationDefault = new TileLocationMovable(tailLocationDefault, tailTileDefault);

        LinkedList<TileLocation> bodyTiles = new LinkedList<>();
        bodyTileDefault = new Tile(tileSideDefault, TileSnakeBody.EAST);
        for (int idx = 1; idx <= bodyTileCountDefault; ++idx) {
            Location locationTile = new Location(tailDefaultX + idx, tailDefaultY);
            TileLocation location = new TileLocation(locationTile, bodyTileDefault);
            bodyTiles.addLast(location);
        }
        bodyLocationsDefault = new SnakeMovement.BodyLocations(bodyTiles);

        target = new SnakeMovement(headDirectionDefault,
                headTileLocationDefault,
                tailTileLocationDefault,
                tailDirectionDefault,
                bodyLocationsDefault
        );

        return target;
    }

    public int getTileSideDefault() {
        return tileSideDefault;
    }

    public SnakeDirection getHeadDirectionDefault() {
        return headDirectionDefault;
    }

    public SnakeDirection getTailDirectionDefault() {
        return tailDirectionDefault;
    }

    public int getBodyTileCountDefault() {
        return bodyTileCountDefault;
    }

    public int getTailDefaultX() {
        return tailDefaultX;
    }

    public int getHeadDefaultX() {
        return headDefaultX;
    }

    public int getHeadDefaultY() {
        return headDefaultY;
    }

    public int getTailDefaultY() {
        return tailDefaultY;
    }

    public Location getHeadLocationDefault() {
        return headLocationDefault;
    }

    public Location getTailLocationDefault() {
        return tailLocationDefault;
    }

    public Tile getHeadTileDefault() {
        return headTileDefault;
    }

    public Tile getTailTileDefault() {
        return tailTileDefault;
    }

    public Tile getBodyTileDefault() {
        return bodyTileDefault;
    }

    public TileLocationMovable getHeadTileLocationDefault() {
        return headTileLocationDefault;
    }

    public TileLocationMovable getTailTileLocationDefault() {
        return tailTileLocationDefault;
    }

    public Location getBodyLocation() {
        return bodyLocation;
    }

    public SnakeMovement.BodyLocations getBodyLocationsDefault() {
        return bodyLocationsDefault;
    }

    public SnakeMovement getTarget() {
        return target;
    }
}
