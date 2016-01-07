package be.ehb.funinthequeue.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.ehb.funinthequeue.R;

/**
 * Created by ToonLeemans on 16/12/15.
 */
public class GameFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game1, container, false);
        return v;
    }
}

