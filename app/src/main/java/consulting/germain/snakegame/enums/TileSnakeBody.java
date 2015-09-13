/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.enums;

/**
 * Created by mark_local on 11/09/2015.
 * enum of the possible snake body parts
 * expressed as compass points on the direction the snake is moving or turning
 */
public enum TileSnakeBody {
    /** y decreasing */
      NORTH
    /** x increasing */
    , EAST
    /** y increasing */
    , SOUTH
    /** x decreasing */
    , WEST
    /** y decreasing turn to x increasing */
    , NORTH_TO_EAST
    /** y decreasing turn to x decreasing */
    , NORTH_TO_WEST
    /** x increasing turn to y decreasing */
    , EAST_TO_NORTH
    /** x increasing turn to y increasing */
    , EAST_TO_SOUTH
    /** y increasing turn to x increasing  */
    , SOUTH_TO_EAST
    /** y increasing turn to x decreasing */
    , SOUTH_TO_WEST
    /** x decreasing turn to y decreasing */
    , WEST_TO_NORTH
    /** x decreasing turn to y increasing */
    , WEST_TO_SOUTH
}
