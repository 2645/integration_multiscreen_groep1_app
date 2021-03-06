package be.ehb.funinthequeue.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.ProfileTask;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class ProfielFragment extends Fragment{
    RestAPI API;
    ViewGroup c;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        User u = HelperFunctions.loadUserFromPreferences(this.getActivity());
        v = inflater.inflate(R.layout.fragment_profiel, container, false);
        ((TextView) v.findViewById(R.id.txtNaam)).setText(u.toString());
        ((TextView) v.findViewById(R.id.txtAantalCocaCoins)).setText(u.getBalance()+ " cocacoins");

        return v;

    }

    @Override
    public void onActivityCreated(Bundle created) {
        super.onActivityCreated(created);
    }

    public void setTextProfiel(){
        new ProfileTask(API, this.getActivity(), v).execute();    }
}
