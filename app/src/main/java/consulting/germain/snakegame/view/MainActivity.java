/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 */

package consulting.germain.snakegame.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import consulting.germain.snakegame.R;
import consulting.germain.snakegame.controller.Animator;
import consulting.germain.snakegame.enums.GameState;

public class MainActivity extends AppCompatActivity {

    /**
     * key for the resource bundle to which we save state
     */
    private static final String STATE_KEY = Animator.class.getName();

    /**
     * displays the tiles
     */
    private TileView tileView;

    /**
     * displays the Controls
     */
    private ControlView controlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        controlView = (ControlView) findViewById(R.id.controlview);
        tileView = (TileView) findViewById(R.id.tileview);

        tileView.setStatusText((TextView) findViewById(R.id.text));

        if (savedInstanceState == null) {
            // just launched so enter first load state
            tileView.setGameState(GameState.LOADING);
            return;
        }

        // we are being restored
        Bundle map = savedInstanceState.getBundle(STATE_KEY);
        if (map != null) {
            tileView.restoreState(map);
        } else {
            tileView.setGameState(GameState.PAUSE);
        }
    }
}
