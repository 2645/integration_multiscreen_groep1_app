package be.ehb.funinthequeue.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    ImageView avatar;

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
        setTextHome();
    }

    public void setTextHome(){
        verwelkoming = (TextView) getView().findViewById(R.id.txtVerwelkoming);
        cocacoins = (TextView) getView().findViewById((R.id.txtAantalCocaCoins));
        wachttijdAttractie = (TextView) getView().findViewById(R.id.txtWachttijdTitel);
        wachttijd1 = (TextView) getView().findViewById(R.id.txtTijd1);
        afstand1 = (TextView) getView().findViewById(R.id.txtAfstand1);
        attractieimage = (ImageView) getView().findViewById(R.id.imgKortsteWachttijd);
        avatar = (ImageView) getView().findViewById(R.id.imgAvatar);

        new setTextFromAPI(API, verwelkoming, cocacoins, wachttijdAttractie, wachttijd1, afstand1, attractieimage, avatar).execute();
    }

}
