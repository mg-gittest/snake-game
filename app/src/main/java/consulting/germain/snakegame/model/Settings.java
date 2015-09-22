/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.model;

/**
 * Created by mark_local on 15/09/2015.
 * Holds settings for this run
 */
public class Settings {

    /**
     * default tile side
     */
    public static int tileSize = 45;

    /**
     * number of threads available in the executor pool
     */
    public static int numberExecutorThreads = 1;

    /**
     * how long the TimeBase should wait before calling for each animation
     */
    public static long timebaseStartMillis = 1024;

    /**
     * how much faster we want the animation to run after player reaches next level
     */
    public static double speedUpMultiplier = 100.0 / 95.0;

}
