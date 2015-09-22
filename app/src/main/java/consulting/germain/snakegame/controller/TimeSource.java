/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

/**
 * Created by mark_local on 22/09/2015.
 * Interface for classes which provides a time. The purpose of this interface is
 * enable dependency injection such that a real-time clock can be used in
 * production deployments but a deterministic clock can be used for unit testing
 * purposes.
 */
public interface TimeSource {

    /**
     * @return current time in milliseconds since java epoch
     */
    long currentTimeMillis();

    /**
     * @param initialTime Some point in time (milliseconds since java epoch)
     * @return Milliseconds between the supplied tme and the current time.
     */
    long timeSince(final long initialTime);
}
