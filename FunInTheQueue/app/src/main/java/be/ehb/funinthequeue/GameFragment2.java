package be.ehb.funinthequeue;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ToonLeemans on 16/12/15.
 */
public class GameFragment2 extends Fragment {
    /*View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.layout_game2, container, false);

        return rootview;
    }*/
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    public static final GameFragment2 newInstance(String message) {
        GameFragment2 f = new GameFragment2();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_game2, container, false);
        return v;
    }
}
