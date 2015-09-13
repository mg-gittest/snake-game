/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.enums;

/**
 * Created by mark_local on 11/09/2015.
 * enum of the high level states of the game lifecycle
 */
public enum GameState {
    /** Game is initialising */
      LOADING
    /** Initialisation is done, we are ready to run */
    , READY
    /** game is actively running, field visible and updating, controls active */
    , RUNNING
    /** game is paused, field hidden and unchanging, only resume and exit control active */
    , PAUSE
    /** game is over, field is visible and unchanging, controls not active */
    , LOST
}
