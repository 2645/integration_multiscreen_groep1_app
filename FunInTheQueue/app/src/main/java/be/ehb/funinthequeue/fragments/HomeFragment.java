package be.ehb.funinthequeue.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.GPSTracker;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.setTextFromAPI;
import be.ehb.funinthequeue.tasks.HomeTask;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class HomeFragment extends Fragment {
    RestAPI API;
    ViewGroup c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        c = container;
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle created) {
        super.onActivityCreated(created);
        setTextHome();
    }

    public void setTextHome(){
        SharedPreferences sharedPref = c.getContext().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        int uId = sharedPref.getInt("userID", 0);
        new HomeTask(API,(Fragment) this, uId).execute();
    }


    public Location getLocation() {
        GPSTracker tracker = new GPSTracker(getActivity());
        if (tracker.canGetLocation()) {
            Location location = tracker.getLocation();
            if (location != null) {
                return location;
            }
        }
        throw new RuntimeException("Could not retrieve location.");
    }
}
