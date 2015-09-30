/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import consulting.germain.snakegame.model.Settings;

/**
 * Created by mark_local on 22/09/2015.
 * Combines an animator with a time source to set the progress of the animator
 * Allowing a test time source to be substituted for the system time source.
 */
public class TimeBaseAnimator {

    /**
     * Executor service to use
     */
    final   ExecutorService executor;
    /**
     * our animator
     * runable that will do the animation
     */
    private Animator        animator;

    /**
     * default ctor using system time source
     */
    public TimeBaseAnimator() {
        this(new SystemTimeSource());
    }

    /**
     * ctor for testing, allowing a simulated time source
     *
     * @param timeSource allows a simulated timesource
     */
    public TimeBaseAnimator(TimeSource timeSource) {
        this(timeSource, Executors.newFixedThreadPool(Settings.numberExecutorThreads));

    }

    /**
     * ctor
     *
     * @param timeSource      allows a simulated time source
     * @param executorService allow a choice of executor service
     */
    public TimeBaseAnimator(TimeSource timeSource, ExecutorService executorService) {
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
