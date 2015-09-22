/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

/**
 * Created by mark_local on 22/09/2015.
 * Implementation of a {@link TimeSource} that calls the System time, intended for all but Unit Tests
 */
public class SystemTimeSource implements TimeSource {
    /**
     * @return current time in milliseconds in milliseconds since java epoch
     */
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * @param initialTime Some point in time (milliseconds since java epoch)
     * @return Milliseconds between the supplied tme and the current time.
     */
    @Override
    public long timeSince(long initialTime) {

        return currentTimeMillis() - initialTime;
    }
}
