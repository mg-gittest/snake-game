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
     * the location of the head
     */
    private Location headLocation;
}
