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
    public TileLocation getHeadLocation() {
        return snakeState.getHeadLocation();
    }

    /**
     * @return the location of the snake Tail
     */
    public TileLocation getTailLocation() {
        return snakeState.getTailLocation();
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
     * ask the snake to move in a particular direction by moving its head
     * @param direction which way to move head, body and tail will be dragged behind
     */
    public void move(SnakeDirection direction) {
    }

    /**
     * ask the snake to grow in a particular direction by streching its head
     * @param direction which way to move the head as it grows, body stays in place and extends to head
     */
    public void grow(SnakeDirection direction) {
    }

    /**
     * @return the current length in tiles
     */
    public int getLength() {
        // head + tail + body lengt
        return snakeState.length();
    }
}
