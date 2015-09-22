/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import consulting.germain.snakegame.model.Settings;

/**
 * Created by mark_local on 22/09/2015.
 * Sets the rate of progress of the game, advances all animations one move after each tick
 * ticks can get faster as the player progresses
 */
public class TimeBase {

    /**
     * Executor service to use
     */
    private final ExecutorService executor;
    /**
     * our animator
     */
    private       Animator        animator;

    /**
     * runable that will do the animation
     * private final Animator animator;
     * <p/>
     * /**
     * default ctor using system time source
     */
    public TimeBase() {
        this(new SystemTimeSource());
    }

    /**
     * ctor for testing, allowing a simulated time source
     *
     * @param timeSource allows a simulated timesource
     */
    public TimeBase(TimeSource timeSource) {
        this(timeSource, Executors.newFixedThreadPool(Settings.numberExecutorThreads));

    }

    /**
     * ctor
     *
     * @param timeSource      allows a simulated time source
     * @param executorService allow a choice of executor service
     */
    public TimeBase(TimeSource timeSource, ExecutorService executorService) {
        this.executor = executorService;
        this.animator = new Animator(timeSource);
        executor.submit(animator);
    }

    /**
     * @return animator, test method
     */
    Animator getAnimator() {
        return animator;
    }
}
