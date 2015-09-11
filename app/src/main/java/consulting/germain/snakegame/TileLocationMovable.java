package consulting.germain.snakegame;

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Created by mark_local on 11/09/2015.
 *Extends the TileLocation with the ability to move the location with a SnakeDirection
 */
public class TileLocationMovable extends TileLocation{

    protected TileLocationMovable(int x, int y, Tile tile) {
        super(x, y, tile);
    }

    /**
     * Move the tile location as requested, makes no attempt to validate the new location
     * @param snakeDirection the relevant direction in which to move
     */
    public void move(SnakeDirection snakeDirection) {
        switch (snakeDirection) {
            case NORTH:
                --y;
                break;
            case EAST:
                ++x;
                break;
            case SOUTH:
                ++y;
                break;
            case WEST:
                --x;
                break;
        }
    }
}
