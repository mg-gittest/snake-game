/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import consulting.germain.snakegame.enums.SnakeDirection;

/**
 * Created by mark_local on 15/09/2015.
 * A snake movement has head, tail locations and a driection for the head
 * has length, and responds to requests to grow, shorten by an amount
 * had a SnakeDirection, that the head currently has, which the body and tail follow logically as it moves
 * responds to request to move
 */
public class SnakeMovement {

    /**
     * direction the head is pointing
     */
    private final SnakeDirection headDirection;

    /**
     * location of the head
     */
    private final TileLocationMovable headLocation;

    /**
     * location of the tail
     */
    private final TileLocationMovable tailLocation;

    /**
     * direction the tail is pointing
     */
    private final SnakeDirection tailDirection;

    /**
     * list of tile location for the body of the snake,
     * will start adjacent to head and finish adjacent to tail
     */
    private final BodyLocations bodyLocations;

    /**
     * @return lenght of snake measured in tiles
     */
    public int length() {
        // body plus head and tail tiles
        return bodyLocations.length() + 2;
    }

    /**
     * Models the locations of a snakes body
     * will start adjacent to head and finish adjacent to tail
     */
    static class BodyLocations {

        private final LinkedList<TileLocation> tiles;

        public BodyLocations(LinkedList<TileLocation> bodyLocations) {
            this.tiles = bodyLocations;
        }

        public int length() {
            return tiles.size();
        }

        public List<TileLocation> asUnmodifiableList() {
            return Collections.unmodifiableList(tiles);
        }

        LinkedList<TileLocation> getTiles() {
            return tiles;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            BodyLocations that = (BodyLocations) o;

            return !(getTiles() != null ? !getTiles().equals(that.getTiles()) :
                    that.getTiles() != null);

        }

        @Override
        public int hashCode() {
            return getTiles() != null ? getTiles().hashCode() : 0;
        }
    }

    /**
     * ctor
     *
     * @param headDirection head direction
     * @param headLocation  head location
     * @param tailLocation  tail location
     * @param tailDirection tail direction
     * @param bodyLocations body from head to tail
     */
    public SnakeMovement(
            SnakeDirection headDirection
            , TileLocationMovable headLocation
            , TileLocationMovable tailLocation
            , SnakeDirection tailDirection
            , BodyLocations bodyLocations) {
        this.headDirection = headDirection;
        this.headLocation = headLocation;
        this.tailLocation = tailLocation;
        this.tailDirection = tailDirection;
        this.bodyLocations = bodyLocations;
    }

    /**
     * @return direction of the head
     */
    public SnakeDirection getHeadDirection() {
        return headDirection;
    }

    /**
     * @return location of the head
     */
    public TileLocation getHeadLocation() {
        return headLocation;
    }

    /**
     * @return movable location of the head
     */
    public TileLocationMovable getHeadLocationMovable() {
        return headLocation;
    }

    /**
     * @return location of the tail
     */
    public TileLocation getTailLocation() {
        return tailLocation;
    }

    /**
     * @return movable location of the tail
     */
    public TileLocationMovable getTailLocationMovable() {
        return tailLocation;
    }

    /**
     * @return direction of the tail
     */
    public SnakeDirection getTailDirection() {
        return tailDirection;
    }

    /**
     * @return locations of the body form head to tail
     */
    public BodyLocations getBodyLocations() {
        return bodyLocations;
    }

}
