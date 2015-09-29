/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import consulting.germain.snakegame.enums.SnakeDirection;
import consulting.germain.snakegame.model.Settings;
import consulting.germain.snakegame.model.SnakeStateFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mark_local on 22/09/2015.
 * Testing Animator
 */
public class AnimatorTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private Animator              target;
    private SynchronousTimeSource timeSource;

    @Before
    public void Setup() {
        timeSource = new SynchronousTimeSource();
        target = new Animator(timeSource);
    }

    @Test
    public void testGetSnake() throws Exception {
        assertNull(target.getSnake());
    }

    @Test
    public void testGetStepCount() throws Exception {
        assertEquals(0, target.getStepCount());
    }

    @Test
    public void testGetSnakeDirection() throws Exception {
        SnakeDirection expected = SnakeStateFactory.headTileDefault.getDirectionTo();
        thrown.expect(NullPointerException.class); // snake not yet existing
        assertEquals(expected, target.getSnakeDirection());
    }

    @Test
    public void testGetSnakeDirectionControl() throws Exception {
        SnakeDirection expected = SnakeStateFactory.getDefaultSnakeDirectionControl();
        assertEquals(expected, target.getSnakeDirectionControl());
    }

    @Test
    public void testIsRunningRequested() throws Exception {
        assertTrue(target.isRunningRequested());
    }

    @Test
    public void testRequestStop() throws Exception {
        target.requestStop();
        assertFalse(target.isRunningRequested());
    }

    @Test
    public void testGetLastStepTime() throws Exception {
        long expected = timeSource.currentTimeMillis();
        assertEquals(expected, target.getLastStepTime());
    }

    @Test
    public void testGetStepTimeIntervalMillis() throws Exception {
        long expected = Settings.timebaseStartMillis;
        assertEquals(expected, target.getStepTimeIntervalMillis());
    }

    @Test
    public void testGetTimeSource() throws Exception {
        assertTrue(timeSource == target.getTimeSource());
    }

    @Test
    public void testInitialiseAnimation() throws Exception {
        long expectedTime = target.getLastStepTime() + 100;
        timeSource.set(expectedTime);
        assertNull(target.getSnake());

        long lastStepTime = target.initialiseAnimation();

        assertNotNull(target.getSnake());
        assertEquals(lastStepTime, target.getLastStepTime());
        assertEquals(expectedTime, lastStepTime);
    }


}
