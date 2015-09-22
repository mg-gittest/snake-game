/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TileSnakeBody;
import consulting.germain.snakegame.enums.TileSnakeHead;
import consulting.germain.snakegame.enums.TileSnakeTail;

/**
 * Created by mark_local on 15/09/2015.
 * A snake model, has head, tail, body
 * has length, and responds to requests to growHead, shorten by an amount
 * had a SnakeDirection, that the head currently has, which the body and tail follow logically as it moves
 * responds to request to move
 */
public class Snake {

    public static final String BAD_NECK_REQUEST = "Requested bad neck from/to: ";
    public static final String GROW_HEAD_FAIL   = "Failure to Grow Head correctly: ";
    public static final String TAIL_SHRINK_FAIL = "Failure to Shrink Tail correctly: ";

    /**
     * current movement state of the snake ends
     */
    private SnakeState snakeState;

    public Snake(SnakeState startState) {
        this.snakeState = startState;
    }

    /**
     * @return current snake state
     */
    public SnakeState getSnakeState() {
        return snakeState;
    }

    /**
     * @return the location of the snake Head
     */
    public TileLocation getHeadTileLocation() {
        return snakeState.getHeadTileLocation();
    }

    /**
     * @return the location of the snake Tail
     */
    public TileLocation getTailTileLocation() {
        return snakeState.getTailTileLocation();
    }

    /**
     * @return direction of the head
     */
    public SnakeDirection getHeadDirection() {
        return snakeState.getHeadDirection();
    }

    /**
     * @return direction of the tail
     */
    public SnakeDirection getTailDirection() {
        return snakeState.getTailDirection();
    }

    /**
     * examine a suplied list of TileLocation and see if any intersect with the TileLocations of the snake
     *
     * @param targetList A list of locations to check
     * @return ture is there is an intersection false otherwise
     */
    public boolean intersects(final TileLocationList targetList) {
        return getTileLocations().intersects(targetList);
    }

    /**
     * ask the snake to move in a particular direction by moving its head
     * inserting appropriate body section, and moving tail
     * @param direction which way to move head
     * @return resulting location of the head
     * @throws Exception if final state is unexpected
     * @throws IllegalArgumentException if cannot turn head in direction requested
     */
    public TileLocation move(SnakeDirection direction) throws Exception {
        // first grow the head, then shrink the tail
        growHead(direction);
        shrinkTail();

        return getHeadTileLocation();
    }

    /**
     * Reduce the size of the snake by removing one rear body section and advancing the tail to that location
     * rotating tail to align with next section if needed
     *
     * @throws Exception if the final state fails to validate
     */
    public void shrinkTail() throws Exception {

        TileLocationList list = getTileLocations();
        list.removeLast(); // get rid of old tail
        // remove last body segment, keep ref so we can build tail in same location
        TileLocation lastBody = list.removeLast();
        //set tail direction based on what it needs to join to
        SnakeDirection tailDirection = lastBody.getTile().getDirectionTo();
        Tile tailTile = chooseTailTile(tailDirection);
        // set location to match the removed body section and add the new tail
        TileLocation tailTileLocation = new TileLocation(lastBody.getLocation(), tailTile);
        list.addLast(tailTileLocation);

        String msg = getSnakeState().validateTileLocations();
        if (msg.length() > 0) {
            throw new Exception(TAIL_SHRINK_FAIL + msg);
        }
    }

    /**
     * ask the snake to grow in a particular direction by streching its head out
     * inserting appropriate body section, not moving tail
     * @param direction which way to move the head as it grows, body stays in place and extends to head
     * @return resulting location of the head
     * @throws Exception if final state is unexpected
     * @throws IllegalArgumentException if cannot turn head in direction requested
     */
    public TileLocation growHead(SnakeDirection direction) throws Exception {

        Tile neckTile = chooseNeckTile(direction);
        Tile headTile = chooseHeadTile(direction);

        Location neckLocation = getHeadTileLocation().getLocation();
        Location headLocation = neckLocation.getProjectedLocation(direction);

        TileLocation neckTileLocation = new TileLocation(neckLocation, neckTile);
        TileLocation headTileLocation = new TileLocation(headLocation, headTile);

        TileLocationList list = getTileLocations();
        list.removeFirst();
        list.addFirst(neckTileLocation);
        list.addFirst(headTileLocation);

        String msg = getSnakeState().validateTileLocations();
        if (msg.length() > 0) {
            throw new Exception(GROW_HEAD_FAIL + msg);
        }

        return getHeadTileLocation();
    }

    /**
     * choose a new head tile given the requested direction
     *
     * @param direction where we want the snake to be heading
     * @return Chosen Tile
     */
    private Tile chooseHeadTile(SnakeDirection direction) {
        switch (direction) {
            case NORTH:
                return new Tile(TileSnakeHead.NORTH);

            case EAST:
                return new Tile(TileSnakeHead.EAST);

            case SOUTH:
                return new Tile(TileSnakeHead.SOUTH);

            case WEST:
                return new Tile(TileSnakeHead.WEST);

        }
        return null;
    }

    /**
     * choose a new neck tile (joins exiting body to head in selected direction
     * assuming we are starting from a valid state, so does not check again.
     *
     * @param direction requested new head direction
     * @return appropriate tile to deal with new direction joining to body
     */
    private Tile chooseNeckTile(SnakeDirection direction) {
        // assuming we are starting from a valid state, so neck (first body section) is aligned with head
        final SnakeDirection currentDirection = getHeadDirection();

        switch (currentDirection) {
            case NORTH:
                switch (direction) {
                    case NORTH:
                        return new Tile(TileSnakeBody.NORTH);

                    case EAST:
                        return new Tile(TileSnakeBody.NORTH_TO_EAST);

                    case SOUTH:
                        throw new IllegalArgumentException(buildBadNeckMessage(currentDirection, direction));

                    case WEST:
                        return new Tile(TileSnakeBody.NORTH_TO_WEST);
                }

            case EAST:
                switch (direction) {
                    case NORTH:
                        return new Tile(TileSnakeBody.EAST_TO_NORTH);

                    case EAST:
                        return new Tile(TileSnakeBody.EAST);

                    case SOUTH:
                        return new Tile(TileSnakeBody.EAST_TO_SOUTH);

                    case WEST:
                        throw new IllegalArgumentException(buildBadNeckMessage(currentDirection, direction));
                }

            case SOUTH:
                switch (direction) {
                    case NORTH:
                        throw new IllegalArgumentException(buildBadNeckMessage(currentDirection, direction));

                    case EAST:
                        return new Tile(TileSnakeBody.SOUTH_TO_EAST);

                    case SOUTH:
                        return new Tile(TileSnakeBody.SOUTH);

                    case WEST:
                        return new Tile(TileSnakeBody.SOUTH_TO_WEST);
                }

            case WEST:
                switch (direction) {
                    case NORTH:
                        return new Tile(TileSnakeBody.WEST_TO_NORTH);

                    case EAST:
                        throw new IllegalArgumentException(buildBadNeckMessage(currentDirection, direction));

                    case SOUTH:
                        return new Tile(TileSnakeBody.WEST_TO_SOUTH);

                    case WEST:
                        return new Tile(TileSnakeBody.WEST);
                }

        }

        return null;
    }

    /**
     * choose a new tail tile given the requested direction
     *
     * @param tailDirection we want the snake tail to be heading
     * @return Chosen Tile
     */
    private Tile chooseTailTile(SnakeDirection tailDirection) {
        switch (tailDirection) {

            case NORTH:
                return new Tile(TileSnakeTail.NORTH);

            case EAST:
                return new Tile(TileSnakeTail.EAST);

            case SOUTH:
                return new Tile(TileSnakeTail.SOUTH);

            case WEST:
                return new Tile(TileSnakeTail.WEST);

        }
        return null;
    }

    private String buildBadNeckMessage(SnakeDirection currentDirection, SnakeDirection direction) {
        return BAD_NECK_REQUEST + currentDirection + direction;
    }

    /**
     * @return the current length in tiles
     */
    public int getLength() {
        // head + tail + body length
        return snakeState.length();
    }

    /**
     * @return the underlying list of tile locations
     */
    public TileLocationList getTileLocations() {
        return snakeState.getTileLocations();
    }

    public String validateState() {
        return getSnakeState().validateTileLocations();
    }

}