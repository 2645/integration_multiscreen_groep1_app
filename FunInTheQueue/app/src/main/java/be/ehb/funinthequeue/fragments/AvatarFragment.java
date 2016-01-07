package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.adapters.AvatarRecyclerAdapter;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.AvatarLoadTask;

/**
 * Created by ToonLeemans on 04/01/16.
 */

public class AvatarFragment extends Fragment {

    RestAPI API;
    RecyclerView recycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_avatars, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        API = new RestAPI();
        recycler = (RecyclerView) getView().findViewById(R.id.recycler_avatars);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        AvatarRecyclerAdapter adapter = new AvatarRecyclerAdapter(new ArrayList<Avatar>());
        recycler.setAdapter(adapter);
        Log.e("LOG", "Calling avatar task!");
        new AvatarLoadTask(API, adapter).execute();
        super.onViewCreated(view, savedInstanceState);
    }
}