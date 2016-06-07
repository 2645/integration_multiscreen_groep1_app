package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.NameViewAdapter;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.AttractionCache;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 16/12/15.
 */
public class QueueFragment extends Fragment {
    RestAPI api = new RestAPI();
    ArrayList<Attraction> attracties = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_queue, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getActivity(), "Attracties laden...", Toast.LENGTH_LONG).show();

        new Thread(new Runnable() {
            public void run() {

                if (!AttractionCache.getAttractions().isEmpty()) {
                    attracties = AttractionCache.getAttractions();
                } else {
                    attracties = api.attractions_list();
                    AttractionCache.setAttractions(attracties);
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!attracties.isEmpty()) {
                            ListView lv = (ListView) getActivity().findViewById(R.id.listView);
                            lv.setAdapter(new NameViewAdapter(getActivity(), attracties));
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    AttractionCache.setSelectedId(position);
                                    if (getActivity() instanceof MainActivity) {
                                        ((MainActivity) getActivity()).changePage(1);
                                    }
                                }
                            });
                        }

                    }

                });
            }
        }).start();
    }
}


