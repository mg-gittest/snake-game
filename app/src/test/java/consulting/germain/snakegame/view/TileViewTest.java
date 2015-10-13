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
        assertEquals(-1, target.getXCount());
    }

    @Test
    public void testGetYCount() throws Exception {
        assertEquals(-1, target.getYCount());
    }

    @Test
    public void testGetTileSize() throws Exception {
        assertEquals(TileView.DEFAULT_TILE_SIZE, target.getTileSize());
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

        int defSize = TileView.DEFAULT_TILE_SIZE;
        int expectXcount = 30;
        int expectYcount = 31;
        int expectCount = Math.min(expectXcount, expectYcount);

        int expectXoffset = 3 + ((expectXcount - expectCount) * (defSize / 2));
        int expectYoffset = 7 + ((expectYcount - expectCount) * (defSize / 2));

        int w = (expectCount * defSize) + (2 * expectXoffset);
        int h = (expectCount * defSize) + (2 * expectYoffset);

        int oldh = -1;
        int oldw = -1;

        target.onSizeChanged(w, h, oldw, oldh);

        collector.checkThat(target.getXCount(), is(expectCount));
        collector.checkThat(target.getYCount(), is(expectCount));
        collector.checkThat(target.getXOffset(), is(expectXoffset));
        collector.checkThat(target.getYOffset(), is(expectYoffset));
    }
}
