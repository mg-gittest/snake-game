/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.view;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mark_local on 13/10/2015.
 * to test TileView
 */
public class TileViewTest {

    Context context;
    TileView target;

    @Before
    public void setup() {


        target = new TileView(context);
    }

    @Test
    public void testGetXCount() throws Exception {
        target.getXCount();
    }

    @Test
    public void testGetYCount() throws Exception {
        target.getYCount();
    }

    @Test
    public void testGetTileSize() throws Exception {
        target.getTileSize();
    }

    @Test
    public void testGetAnimator() throws Exception {
        target.getAnimator();
    }

    @Test
    public void testGetBitMaps() throws Exception {
        target.getBitMaps();
    }
}
