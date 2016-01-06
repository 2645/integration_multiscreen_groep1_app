package be.ehb.funinthequeue.fragments;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.GPSTracker;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.setTextFromAPI;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class HomeFragment extends Fragment {
    RestAPI API;
    TextView verwelkoming;
    TextView cocacoins;
    TextView wachttijdAttractie;
    TextView wachttijd1;
    TextView afstand1;
    ImageView attractieimage;
    ImageView attractieimage2;
    ImageView avatar;
    TextView afstandattractie;
    TextView wachttijd2;
    TextView afstand2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle created) {
        super.onActivityCreated(created);
        /*setTextHome();
        GPSTracker gps = new GPSTracker(getActivity());
        Double latitude = gps.getLatitude();
        Double longitude = gps.getLongitude();
        Log.d("Latitude", Double.toString(latitude));
        Log.d("Longitude", Double.toString(longitude));
        getLocation();*/
    }

    public void setTextHome(){
        verwelkoming = (TextView) getView().findViewById(R.id.txtVerwelkoming);
        cocacoins = (TextView) getView().findViewById((R.id.txtAantalCocaCoins));
        wachttijdAttractie = (TextView) getView().findViewById(R.id.txtWachttijdTitel);
        wachttijd1 = (TextView) getView().findViewById(R.id.txtTijd1);
        afstand1 = (TextView) getView().findViewById(R.id.txtAfstand1);
        attractieimage = (ImageView) getView().findViewById(R.id.imgKortsteWachttijd);
        avatar = (ImageView) getView().findViewById(R.id.imgAvatar);
        afstandattractie = (TextView) getView().findViewById(R.id.txtAttractietitel);
        wachttijd2 = (TextView) getView().findViewById(R.id.txtTijd2);
        afstand2 = (TextView) getView().findViewById(R.id.txtAfstand2);
        attractieimage2 = (ImageView) getView().findViewById(R.id.imgInDeBuurt);

        new setTextFromAPI(API, verwelkoming, cocacoins, wachttijdAttractie, wachttijd1, afstand1, attractieimage, avatar, afstandattractie, wachttijd2, afstand2, attractieimage2).execute();
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
