/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Created by mark_local on 15/09/2015.
 * A snake model, has head, tail, body
 * has length, and responds to requests to grow, shorten by an amount
 * had a SnakeDirection, that the head currently has, which the body and tail follow logically as it moves
 * responds to request to move
 */
public class Snake {

    /**
     * total length of the snake, measured in tiles, it has a continuos body that may have turns
     */
    private int length;

    /**
     * the direction the head is currently pointing
     */
    private SnakeDirection headDirection;

    /**
     * the current location of the head
     */
    private TileLocationMovable headLocation;

    /**
     * the current location of the tail
     */
    private TileLocationMovable tailLocation;

    /**
     * the direction the head will be pointing when current move completes
     */
    private SnakeDirection headMovedDirection;

    /**
     * where the head will be after current move completes
     */
    private TileLocationMovable headMovedLocation;

    /**
     * where the tail will be when the current move completes
     */
    private TileLocationMovable tailMovedLocation;

    /**
     * the string of locations for the body
     */
}
