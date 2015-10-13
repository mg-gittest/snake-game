/*
 * Copyright (c) 2015. Germain Consulting, subject to the GPL3 licence
 *
 */

package consulting.germain.snakegame.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import consulting.germain.snakegame.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FieldActivityFragment extends Fragment {

    public FieldActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_field, container, false);
    }
}
