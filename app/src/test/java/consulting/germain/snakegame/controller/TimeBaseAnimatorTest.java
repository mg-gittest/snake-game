/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.model.Location;
import consulting.germain.snakegame.model.Settings;
import consulting.germain.snakegame.model.TileLocation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 22/09/2015.
 * Uses TimeBase to test animation on separate thread
 */
public class TimeBaseAnimatorTest {

    private long                  timeInterval;
    private TimeBase              timeBase;
    private SynchronousTimeSource timeSource;
    private Animator              target;

    @Before
    public void setUp() throws Exception {
        timeInterval = Settings.timebaseStartMillis;
        timeSource = new SynchronousTimeSource();
        timeBase = new TimeBase(timeSource);
        target = timeBase.getAnimator();
    }

    @Test
    public void testAnimatorTimeSource() throws Exception {
        assertEquals(timeSource, target.getTimeSource());
    }

    @Test
    public void testTargetIsRunning() throws Exception {
        assertTrue(target.isRunningRequested());
    }

    @Test
    public void testStepCountZero() throws Exception {
        assertEquals(0, target.getStepCount());
    }

    @Test
    public void testTimeInterval() throws Exception {
        assertEquals(timeInterval, target.getStepTimeIntervalMillis());
    }

    @Test
    public void testAdvanceShortOfStep() throws Exception {
        target.setStepTimeIntervalMillis(10);
        timeInterval = target.getStepTimeIntervalMillis();
        TimeUnit.MILLISECONDS.sleep(100); // allow time for threadpool to start

        long startTime = timeSource.currentTimeMillis();

        timeSource.advance(timeInterval);
        assertEquals(startTime + timeInterval, timeSource.currentTimeMillis());

        TimeUnit.MILLISECONDS.sleep(2 * timeInterval);
        assertEquals(0, target.getStepCount());
    }

    @Test
    public void testAdvancePastStep() throws Exception {

        target.setStepTimeIntervalMillis(10);
        timeInterval = target.getStepTimeIntervalMillis();
        ++timeInterval;
        TimeUnit.MILLISECONDS.sleep(100); // allow time for threadpool to start

        timeSource.advance(timeInterval);
        TimeUnit.MILLISECONDS.sleep(2 * timeInterval);

        assertEquals(1, target.getStepCount());

    }

    @Test
    public void testAdvanceTenSteps() throws Exception {
        target.setStepTimeIntervalMillis(10);
        timeInterval = target.getStepTimeIntervalMillis();
        ++timeInterval;
        TimeUnit.MILLISECONDS.sleep(100); // allow time for threadpool to start

        for (int idx = 1; idx < 11; idx++) {
            timeSource.advance(timeInterval);
            TimeUnit.MILLISECONDS.sleep(2 * timeInterval);

            assertEquals(idx, idx, target.getStepCount());
        }
    }

    @Test
    public void testSnakeMoveOne() throws Exception {
        target.setStepTimeIntervalMillis(10);
        timeInterval = target.getStepTimeIntervalMillis();
        ++timeInterval;
        TimeUnit.MILLISECONDS.sleep(100); // allow time for threadpool to start

        TileLocation headLocPrev = target.getSnake().getHeadTileLocation();

        timeSource.advance(timeInterval);
        TimeUnit.MILLISECONDS.sleep(2 * timeInterval);

        TileLocation headLocPost = target.getSnake().getHeadTileLocation();
        Location expected = headLocPrev.getLocation().getProjectedLocation(SnakeDirection.EAST);

        assertEquals(1, target.getStepCount());
        assertTrue("adjacent", headLocPost.isAdjacentLocation(headLocPrev));
        assertTrue("projected", headLocPost.getLocation().equals(expected));
    }

    @Test
    public void testSnakeMoveFive() throws Exception {
        target.setStepTimeIntervalMillis(10);
        timeInterval = target.getStepTimeIntervalMillis();
        ++timeInterval;
        TimeUnit.MILLISECONDS.sleep(100); // allow time for threadpool to start

        TileLocation headLocPrev = target.getSnake().getHeadTileLocation();

        final int numMoves = 5;
        for (int idx = 0; idx < numMoves; idx++) {
            timeSource.advance(timeInterval);
            TimeUnit.MILLISECONDS.sleep(2 * timeInterval);
        }

        TileLocation headLocPost = target.getSnake().getHeadTileLocation();
        Location expected =
                headLocPrev.getLocation().getProjectedLocation(numMoves, SnakeDirection.EAST);

        assertEquals("numMoves", numMoves, target.getStepCount());
        assertTrue("projected", headLocPost.getLocation().equals(expected));
    }

}
