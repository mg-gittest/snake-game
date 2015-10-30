/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.util.Map;

import consulting.germain.snakegame.controller.Animator;
import consulting.germain.snakegame.enums.TileSnakeHead;

/**
 * Created by mark_local on 13/10/2015.
 * A view to display a 2D array of tiles, with each tile having a location within the grid
 * Each tile has a bitmap
 * The view manages drawing tiles that are created or changed, and deleting tiles that are removed
 */
public class ControlView extends View {

    /**
     * A Paint for painting
     */
    private final Paint   paint          = new Paint();
    /**
     * size for the drawable
     */
    private final int     size           = 200;
    /**
     * is the area we have to point in vertical or horizontal
     */
    private       boolean isVerticalArea = true;
    /**
     * the drawble we want to put in the view onDraw
     */
    private int drawableid;
    /**
     * offset of the bitmap we draw
     */
    private int xOffset;
    /**
     * offset of the bitmap we draw
     */
    private int yOffset;

    /**
     * constructor
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public ControlView(Context context, Animator animator, Map<Integer, Bitmap> bitmaps) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        isVerticalArea = w < h;
        drawableid = TileSnakeHead.WEST.ordinal();
        if (isVerticalArea) {
            drawableid = TileSnakeHead.NORTH.ordinal();
        }

        xOffset = ((w - size) / 2);
        yOffset = ((h - size) / 2);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Context context = getContext();
        Drawable drawable = ContextCompat.getDrawable(context, drawableid);
        drawable.setBounds(0, 0, size, size);

        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

        canvas.drawBitmap(bitmap, xOffset, yOffset, paint);
    }

}
