package be.ehb.funinthequeue.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.profielAPI;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.setTextFromAPI;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class ProfielFragment extends Fragment{
    RestAPI API;
    TextView naam;
    ImageView avatar;
    TextView cocacoins;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        v = inflater.inflate(R.layout.fragment_profiel, container, false);
        setTextProfiel();
        return v;

    }

    @Override
    public void onActivityCreated(Bundle created) {
        super.onActivityCreated(created);
    }

    public void setTextProfiel(){
        naam = (TextView) v.findViewById(R.id.txtNaam);
        avatar = (ImageView) v.findViewById(R.id.imgAvatar);
        cocacoins = (TextView) v.findViewById(R.id.txtAantalCocaCoins);

        new profielAPI(API, naam, avatar, cocacoins).execute();
    }
}
