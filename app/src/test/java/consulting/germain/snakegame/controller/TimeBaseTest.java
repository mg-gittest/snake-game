/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mark_local on 22/09/2015.
 */
public class TimeBaseTest {

    private TimeBase   target;
    private TimeSource timeSource;

    @Before
    public void setUp() throws Exception {
        timeSource = new SynchronousTimeSource();
        target = new TimeBase(timeSource);
    }

    @Test
    public void testGetAnimator() throws Exception {
        Animator animator = target.getAnimator();
        assertEquals(timeSource, animator.getTimeSource());
    }
}
