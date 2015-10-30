/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import consulting.germain.snakegame.R;
import consulting.germain.snakegame.controller.Animator;
import consulting.germain.snakegame.controller.TimeBaseAnimator;
import consulting.germain.snakegame.enums.GameState;
import consulting.germain.snakegame.model.Limits;
import consulting.germain.snakegame.model.Snake;
import consulting.germain.snakegame.model.Tile;
import consulting.germain.snakegame.model.TileLocation;
import consulting.germain.snakegame.model.TileLocationList;

/**
 * Created by mark_local on 13/10/2015.
 * A view to display a 2D array of tiles, with each tile having a location within the grid
 * Each tile has a bitmap
 * The view manages drawing tiles that are created or changed, and deleting tiles that are removed
 */
public class TileView extends View {

    /**
     * A Paint for painting
     */
    private final Paint paint      = new Paint();
    /**
     * view width in tiles, assume square tiles and square field
     */
    private final int   xTileCount = Limits.tileCountX;
    /**
     * view height in tiles, assume square tiles and square field
     */
    private final int   yTileCount = Limits.tileCountY;
    /**
     * dip offset to allow side border when size does not fit perfectly in width
     */
    private       int   xOffset    = -1;
    /**
     * dip offset to allow top border when size does not fit perfectly in height
     */
    private       int   yOffset    = -1;
    /**
     * tile size in dip (tile assumed square)
     */
    private       int   tileSize   = -1;
    /**
     * Animation being drawn
     */
    private Animator             animator;
    /**
     * bitmaps for tiles, resource id to bitmap
     */
    private Map<Integer, Bitmap> bitMaps;

    /**
     * statusText: text shows to the user in some run states
     */
    private TextView statusText;

    private GameState gameState;

    /**
     * constructor
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public TileView(Context context) {
        this(context, new TimeBaseAnimator().getAnimator());
    }

    /**
     * constructor
     *
     * @param context  The Context the view is running in, through which it can
     *                 access the current theme, resources, etc.
     * @param animator animator to use
     */
    @SuppressLint("UseSparseArrays")
    public TileView(Context context, Animator animator) {
        this(context, animator, new HashMap<Integer, Bitmap>());
    }

    /**
     * constructor
     *
     * @param context  The Context the view is running in, through which it can
     *                 access the current theme, resources, etc.
     * @param animator animator to use
     * @param bitmaps  preloaded map of bitmaps
     */
    public TileView(Context context, Animator animator, Map<Integer, Bitmap> bitmaps) {
        super(context);
        this.animator = animator;
        this.bitMaps = bitmaps;
        this.gameState = GameState.LOADING;
    }

    /**
     * @return number of Tiles in X direction
     */
    public int getXCount() {
        return xTileCount;
    }

    /**
     * @return number of Tiles in Y direction
     */
    public int getYCount() {
        return yTileCount;
    }

    /**
     * @return tile size in dip, assumed square
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * test access
     */
    int getXOffset() {
        return xOffset;
    }

    /**
     * test access
     */
    int getYOffset() {
        return yOffset;
    }

    /**
     * test access
     */
    Animator getAnimator() {
        return animator;
    }

    /**
     * test access
     */
    Map<Integer, Bitmap> getBitMaps() {
        return bitMaps;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // calculate the pixels per tile to get the required number of square tile on screen.
        final int minDim = Math.min(w, h);
        tileSize = minDim / xTileCount;

        xOffset = ((w - (tileSize * xTileCount)) / 2);
        yOffset = ((h - (tileSize * yTileCount)) / 2);
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Snake snake = animator.getSnake();
        TileLocationList deleted;
        TileLocationList updated;
        synchronized (snake) {
            // snych so snake cannot move while we work out what to draw
            deleted = snake.getAndClearDeletedTileLocations();
            updated = snake.getAndClearUpdatedTileLocations();
        }
        Context context = getContext();
        deleteTiles(context, canvas, deleted);
        drawTiles(context, canvas, updated);
    }

    /**
     * given a list of tile locations, delete them by drawing a blank tile.
     *
     * @param context       where to draw
     * @param canvas        how to draw
     * @param tileLocations tile locations to delete
     */
    private void deleteTiles(Context context, Canvas canvas, TileLocationList tileLocations) {

        Bitmap blankBitmap = getBitmap(context, R.drawable.blank_tile);

        for (TileLocation tl : tileLocations) {
            int x = xOffset + tl.getX() * tileSize;
            int y = yOffset + tl.getY() * tileSize;
            canvas.drawBitmap(blankBitmap, x, y, paint);
        }
    }

    /**
     * given a list of tile locations, draw the appropirate bitmap in each location
     *
     * @param context       where to draw
     * @param canvas        how to draw
     * @param tileLocations tiles with the locations to draw
     */
    private void drawTiles(Context context, Canvas canvas, TileLocationList tileLocations) {

        for (TileLocation tl : tileLocations) {
            Tile tile = tl.getTile();
            Bitmap bitmap = getBitmap(context, tile.getDrawableId());

            int x = xOffset + tl.getX() * tileSize;
            int y = yOffset + tl.getY() * tileSize;
            canvas.drawBitmap(bitmap, x, y, paint);
        }
    }

    /**
     * factory method pulling from local map after first construction
     *
     * @param context    where to build
     * @param drawableId key for what to find/build
     * @return bitmap required
     */
    private Bitmap getBitmap(Context context, int drawableId) {
        Bitmap bitmap = bitMaps.get(drawableId);
        if (bitmap == null) {
            bitmap = loadBitMap(context, drawableId);
            bitMaps.put(drawableId, bitmap);
        }
        return bitmap;
    }

    /**
     * load a bitmap for drawable id using the context
     *
     * @param context    where to build
     * @param drawableId key for what to find/build
     * @return bitmap required
     */
    private Bitmap loadBitMap(Context context, int drawableId) {

        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        drawable.setBounds(0, 0, tileSize, tileSize);

        Bitmap bitmap = Bitmap.createBitmap(tileSize, tileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.draw(canvas);

        return bitmap;
    }

    /**
     * attach status text view
     *
     * @param statusText view to attach
     */
    public void setStatusText(TextView statusText) {
        this.statusText = statusText;
    }

    @Override
    public String toString() {
        return "TileView{" +
                "tileSize=" + tileSize +
                ", xTileCount=" + xTileCount +
                ", yTileCount=" + yTileCount +
                "} " + super.toString();
    }

    /**
     * @return current game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param newGameState state to set
     */
    public void setGameState(GameState newGameState) {
        if (this.gameState != newGameState) {
            enterState(newGameState);
        }
    }

    /**
     * @return the current status message
     */
    public String getStatusMessage() {
        return (String) statusText.getText();
    }

    private void enterState(GameState newGameState) {
        if (statusText != null) {
            switch (newGameState) {
                case LOADING:
                    statusText.setVisibility(View.VISIBLE);
                    statusText.setText(R.string.game_loading);
                    break;

                case READY:
                    statusText.setVisibility(View.VISIBLE);
                    statusText.setText(R.string.game_ready);
                    break;

                case RUNNING:
                    statusText.setVisibility(View.INVISIBLE);
                    statusText.setText(R.string.game_running);
                    break;

                case PAUSE:
                    statusText.setVisibility(View.VISIBLE);
                    statusText.setText(R.string.game_paused);
                    break;

                case LOST:
                    statusText.setVisibility(View.INVISIBLE);
                    statusText.setText(R.string.game_paused);
                    break;
            }
        }

        this.gameState = newGameState;
    }

    public void restoreState(Bundle map) {
        this.animator.restoreState(map);
    }

    public Bundle saveState(Bundle map) {

        this.animator.saveState(map);

        return map;
    }
}
