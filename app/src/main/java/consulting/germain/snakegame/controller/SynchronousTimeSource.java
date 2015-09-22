/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import java.util.concurrent.TimeUnit;

/**
 * Created by mark_local on 22/09/2015.
 * Implementation of a {@link TimeSource} that allows the user to manipulate it.
 * <p/>
 * Intended to be used for unit testing purposes only!
 */
public class SynchronousTimeSource implements TimeSource {

    /**
     * our simulated time
     */
    long time;

    /**
     * default ctor
     */
    public SynchronousTimeSource() {
        this(0);
    }

    /**
     * ctor taking an initialisation value
     *
     * @param initialValue what time to start with
     */
    public SynchronousTimeSource(final long initialValue) {
        time = initialValue;
    }

    @Override
    public long currentTimeMillis() {
        return time;
    }

    @Override
    public long timeSince(long initialTime) {
        return time - initialTime;
    }

    /**
     * advance time simulation by a number of milliseconds
     *
     * @param advanceTime how many milliseconds to advance
     */
    public void advance(final long advanceTime) {
        time += advanceTime;
    }

    /**
     * advance simulated time by a period other than milliseconds
     *
     * @param period period to advance by
     * @param unit   what unit of time the period is counting
     */
    public void advance(final long period, final TimeUnit unit) {
        advance(TimeUnit.MILLISECONDS.convert(period, unit));
    }

    /**
     * set the time to simulated
     *
     * @param time
     */
    public void set(final long time) {
        this.time = time;
    }

}
