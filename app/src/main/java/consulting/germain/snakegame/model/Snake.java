/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.LinkedList;
import java.util.List;

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
    private SnakeMovement currentMovement;

    public Snake(SnakeMovement currentMovement) {
        this.currentMovement = currentMovement;
    }

    /**
     * @return the tile locations for the whole snake, head, body, tail order, not null
     */
    public List<TileLocation> getTileLocations() {

        LinkedList<TileLocation> list =
                new LinkedList<>(currentMovement.getBodyLocations().asUnmodifiableList());
        list.addFirst(getHeadLocation());
        list.addLast(getTailLocation());

        return list;
    }

    /**
     * @return the location of the snake Head
     */
    public TileLocation getHeadLocation() {
        return currentMovement.getHeadLocation();
    }

    /**
     * @return the location of the snake Tail
     */
    public TileLocation getTailLocation() {
        return currentMovement.getTailLocation();
    }

    /**
     * @return direction of the head
     */
    public SnakeDirection getHeadDirection() {
        return currentMovement.getHeadDirection();
    }

    /**
     * @return direction of the tail
     */
    public SnakeDirection getTailDirection() {
        return currentMovement.getTailDirection();
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
        return currentMovement.length();
    }
}
