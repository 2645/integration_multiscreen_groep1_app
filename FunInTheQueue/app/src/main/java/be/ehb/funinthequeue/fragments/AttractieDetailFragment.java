package be.ehb.funinthequeue.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.funinthequeue.NameViewAdapter;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.AttractionCache;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 16/12/15.
 */
public class AttractieDetailFragment extends Fragment {
    Attraction attractie = new Attraction();
    RestAPI api = new RestAPI();
    int id = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attractiedetail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(new Runnable() {
            public void run() {
                //attractie = api.attractions_lookup(1);
                attractie = AttractionCache.getSelectedAttraction();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("attractie", attractie+"");
                        byte[] decodedStringAttractie = Base64.decode(attractie.getImg(), Base64.DEFAULT);
                        Bitmap decodedByteAttractie = BitmapFactory.decodeByteArray(decodedStringAttractie, 0, decodedStringAttractie.length);
                        TextView tvNaam = (TextView) getActivity().findViewById(R.id.txtAttractieNaam);
                        tvNaam.setText(attractie.getName());
                        TextView tvTijd = (TextView) getActivity().findViewById(R.id.textView);
                        tvTijd.setText(attractie.getQueuetime() + " min");
                        TextView tvDescription = (TextView) getActivity().findViewById(R.id.textView8);
                        tvDescription.setText(attractie.getDescription());

                        ImageView iv = (ImageView) getActivity().findViewById(R.id.imageView16);
                        iv.setImageBitmap(decodedByteAttractie);

                    }

                });
            }
        }).start();
    }

}
