/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.view;

import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import consulting.germain.snakegame.controller.SynchronousTimeSource;
import consulting.germain.snakegame.controller.TimeBaseAnimator;
import consulting.germain.snakegame.controller.TimeSource;
import consulting.germain.snakegame.model.Limits;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mark_local on 13/10/2015.
 * Testing TileView
 */
@RunWith(MockitoJUnitRunner.class)
public class TileViewTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();
    TileView target;
    @Mock
    Context context;
    private TimeSource       timeSource;
    private TimeBaseAnimator timeBaseAnimator;

    @Before
    public void Setup() {
        timeSource = new SynchronousTimeSource();
        timeBaseAnimator = new TimeBaseAnimator(timeSource);
        target = new TileView(context, timeBaseAnimator.getAnimator());
    }

    @Test
    public void testGetXCount() throws Exception {
        assertEquals(Limits.tileCountX, target.getXCount());
    }

    @Test
    public void testGetYCount() throws Exception {
        assertEquals(Limits.tileCountY, target.getYCount());
    }

    @Test
    public void testGetTileSize() throws Exception {
        // true on construction until onSizechanged is called
        assertEquals(-1, target.getTileSize());
    }

    @Test
    public void testGetxOffset() throws Exception {
        assertEquals(-1, target.getXOffset());
    }

    @Test
    public void testGetyOffset() throws Exception {
        assertEquals(-1, target.getYOffset());
    }

    @Test
    public void testGetAnimator() throws Exception {
        assertEquals(timeBaseAnimator.getAnimator(), target.getAnimator());
    }

    @Test
    public void testGetBitMaps() throws Exception {
        assertNotNull(target.getBitMaps());
        assertEquals(0, target.getBitMaps().size());
    }

    @Test
    public void testOnSizeChange() throws Exception {

        int expectXcount = Limits.tileCountX;
        int expectYcount = Limits.tileCountY;
        int expectCount = Math.min(expectXcount, expectYcount);

        int expectXoffset = 3;
        int expectYoffset = 7;
        int expectedTileSize = 30;

        int w = (expectCount * expectedTileSize) + (2 * expectXoffset);
        int h = (expectCount * expectedTileSize) + (2 * expectYoffset);

        int oldh = -1;
        int oldw = -1;

        target.onSizeChanged(w, h, oldw, oldh);

        collector.checkThat(target.getXCount(), is(expectCount));
        collector.checkThat(target.getYCount(), is(expectCount));
        collector.checkThat(target.getXOffset(), is(expectXoffset));
        collector.checkThat(target.getYOffset(), is(expectYoffset));
    }
}
