/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.enums;

/**
 * Created by mark_local on 11/09/2015.
 * enumeration of the possible behaviours when snake hits edge of field
 */
public enum EdgeRollBehaviour {
    /**
     * hit any side and reappear other side
     */
    ROLL_X_Y_ALLOWED
    /** hit top/bottom and reappear other side, hit left/right and stop */
    , ROLL_Y_ALLOWED
    /** hit left/right and reappear other side, hit top/bottom and stop */
    , ROLL_X_ALLOWED
    /** hit any side and stop */
    , ROLL_BLOCKED

}
