package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.ehb.funinthequeue.R;

/**
 * Created by ToonLeemans on 05/01/16.
 */
public class SwipeButtonsFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    View v;
    public static final GameFragment newInstance(String message) {
        GameFragment f = new GameFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.swipebuttons, container, false);
        return v;
    }
}

