/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.enums.TileType;

/**
 * Created by mark_local on 15/09/2015.
 * A snake movement has head, tail locations and a driection for the head
 * has length, and responds to requests to grow, shorten by an amount
 * had a SnakeDirection, that the head currently has, which the body and tail follow logically as it moves
 * responds to request to move
 */
public class SnakeState {

    public static final String SNAKE_LOCATIONS_NOT_ADJACENT =
            "Connected SnakeLocations not adjacent: ";
    public static final String SNAKE_LOCATIONS_NOT_ALIGNED  =
            "Connected SnakeLocations not aligned: ";
    public static final String SNAKE_FIRST_NOT_HEAD         = "First element not a head tile: ";
    public static final String SNAKE_LAST_NOT_TAIL          = "Last element not a tail tile: ";
    public static final String SNAKE_MID_NOT_BODY           = "Middle element not a body tile: ";
    public static final String SNAKE_SELF_COLLISION         = "Snake collides with itself: ";

    /**
     * list of tile location for the body of the snake,
     * will start adjacent to head and finish adjacent to tail
     */
    private final TileLocationList tileLocations;

    /**
     * location of the head
     */
    private final TileLocation headLocation;

    /**
     * location of the tail
     */
    private final TileLocation tailLocation;

    /**
     * direction the head is pointing
     */
    private final SnakeDirection headDirection;

    /**
     * direction the tail is pointing
     */
    private final SnakeDirection tailDirection;

    /**
     * ctor from supplied tile locations, that will be validated
     * @param tileLocations body from head to tail inclusive, ownership passes to SnakeState
     */
    public SnakeState(final TileLocationList tileLocations) {
        this(tileLocations, true);
    }

    /**
     * copy ctor
     *
     * @param that will not validate tile locations, as assume the previous instance has already done so.
     */
    public SnakeState(SnakeState that) {
        this(that.tileLocations, false);
    }

    /**
     * private common constructor that can choose to validate before setting final variable
     *
     * @param tileLocations         list of tile locations
     * @param validateTileLocations flag for whether to validate that list
     */
    private SnakeState(
            final TileLocationList tileLocations,
            boolean validateTileLocations) {

        this.tileLocations = tileLocations;
        if (validateTileLocations) {
            String msg = validateTileLocations();
            if (msg.length() > 0) {
                throw new IllegalArgumentException(msg);
            }
        }
        headLocation = tileLocations.getFirst();
        tailLocation = tileLocations.getLast();
        headDirection = headLocation.getTile().getDirectionTo();
        tailDirection = tailLocation.getTile().getDirectionFrom();
    }

    /**
     * validate the tile locations for consistency
     *
     * @return notnull, string describing errors found, zero length for no errors
     */
    String validateTileLocations() {
        final String separator = "\n";
        StringBuilder msg = new StringBuilder();

        validateConnections(separator, msg);
        validateHeadFirst(separator, msg);
        validateBodyMiddle(separator, msg);
        validateTailLast(separator, msg);
        validateNoSelfCollision(separator, msg);

        return msg.toString();
    }

    private void validateNoSelfCollision(String separator, StringBuilder msg) {
        for (int idx1 = 0; idx1 < tileLocations.size(); idx1++) {
            TileLocation outer = tileLocations.get(idx1);
            for (int idx2 = 1 + idx1; idx2 < tileLocations.size(); idx2++) {
                TileLocation inner = tileLocations.get(idx2);
                if (outer.sameLocation(inner)) {
                    msg.append(SNAKE_SELF_COLLISION)
                            .append(idx1)
                            .append("->")
                            .append(outer)
                            .append(" hits ")
                            .append(idx2)
                            .append("->")
                            .append(inner)
                            .append(separator);
                }
            }
        }
    }

    private void validateTailLast(String separator, StringBuilder msg) {
        TileLocation last = tileLocations.getLast();
        if (last.getTile().getTileType() != TileType.SNAKE_TAIL) {
            msg.append(SNAKE_LAST_NOT_TAIL).append(last).append(separator);
        }
    }

    private void validateBodyMiddle(String separator, StringBuilder msg) {
        for (int idx = 1; idx < tileLocations.size() - 1; ++idx) {
            TileLocation curr = tileLocations.get(idx);
            if (curr.getTile().getTileType() != TileType.SNAKE_BODY) {
                msg.append(SNAKE_MID_NOT_BODY)
                        .append(curr)
                        .append(" idx: ")
                        .append(idx)
                        .append(separator);
            }
        }
    }

    private void validateHeadFirst(String separator, StringBuilder msg) {
        TileLocation first = tileLocations.getFirst();
        if (first.getTile().getTileType() != TileType.SNAKE_HEAD) {
            msg.append(SNAKE_FIRST_NOT_HEAD).append(first).append(separator);
        }
    }

    private void validateConnections(String separator, StringBuilder msg) {
        TileLocation prev = null;
        for (TileLocation curr : tileLocations) {
            if (null != prev) {
                if (prev.isAdjacentLocation(curr)) {
                    SnakeDirection dirPrev = prev.getTile().getDirectionFrom();
                    SnakeDirection dirCurr = curr.getTile().getDirectionTo();
                    if (dirPrev != dirCurr) {
                        msg.append(SNAKE_LOCATIONS_NOT_ALIGNED)
                                .append(prev.getTile()).append(curr.getTile()).append(separator);
                    }
                } else {
                    msg.append(SNAKE_LOCATIONS_NOT_ADJACENT)
                            .append(prev)
                            .append(curr)
                            .append(separator);
                }
            }
            prev = curr;
        }
    }

    /**
     * @return lenght of snake measured in tiles
     */
    public int length() {
        return tileLocations.size();
    }

    /**
     * @return locations of the body from head to tail
     */
    public TileLocationList getTileLocations() {
        return tileLocations;
    }

    /**
     * @return location of the head
     */
    public TileLocation getHeadTileLocation() {
        return headLocation;
    }

    /**
     * @return location of the tail
     */
    public TileLocation getTailTileLocation() {
        return tailLocation;
    }

    /**
     * @return direction of the head
     */
    public SnakeDirection getHeadDirection() {
        return headDirection;
    }

    /**
     * @return direction of the tail
     */
    public SnakeDirection getTailDirection() {
        return tailDirection;
    }

}
