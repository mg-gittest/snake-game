/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.LinkedList;

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Created by mark_local on 15/09/2015.
 * A snake movement has head, tail locations and a driection for the head
 * has length, and responds to requests to grow, shorten by an amount
 * had a SnakeDirection, that the head currently has, which the body and tail follow logically as it moves
 * responds to request to move
 */
public class SnakeState {

    /**
     * list of tile location for the body of the snake,
     * will start adjacent to head and finish adjacent to tail
     */
    private final LinkedList<TileLocation> tileLocations;

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
     * @return lenght of snake measured in tiles
     */
    public int length() {
        return tileLocations.size();
    }

    /**
     * ctor from supplied tile locations, that will be validated
     * @param tileLocations body from head to tail inclusive, ownership passes to SnakeState
     */
    public SnakeState(final LinkedList<TileLocation> tileLocations) {
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

    private SnakeState(
            final LinkedList<TileLocation> tileLocations,
            boolean validateTileLocations) {
        this.tileLocations = tileLocations;
        if (validateTileLocations) {
            // TODO; validate tile locations for adjacency and continuity of direction
        }
        headLocation = tileLocations.getFirst();
        tailLocation = tileLocations.getLast();
        headDirection = headLocation.getTile().getDirectionTo();
        tailDirection = tailLocation.getTile().getDirectionFrom();
    }

    /**
     * @return locations of the body from head to tail
     */
    public LinkedList<TileLocation> getTileLocations() {
        return tileLocations;
    }

    /**
     * @return location of the head
     */
    public TileLocation getHeadLocation() {
        return headLocation;
    }

    /**
     * @return location of the tail
     */
    public TileLocation getTailLocation() {
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
