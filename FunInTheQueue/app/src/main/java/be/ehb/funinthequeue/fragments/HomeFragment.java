package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.HomeTask;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class HomeFragment extends Fragment {
    RestAPI API;
    ViewGroup c;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        c = container;
        User u = HelperFunctions.loadUserFromPreferences(this.getActivity());
        v = inflater.inflate(R.layout.fragment_home, container, false);
        ((TextView) v.findViewById(R.id.txtVerwelkoming)).setText("Hallo, " + u.getFname());
        ((TextView) v.findViewById(R.id.txtAantalCocaCoins)).setText(u.getBalance() +" cocacoins");
        v.findViewById(R.id.layoutDichtste).setVisibility(View.INVISIBLE);
        v.findViewById(R.id.layoutKortste).setVisibility(View.INVISIBLE);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setTextHome();
        super.onViewCreated(view, savedInstanceState);
    }

    public void setTextHome(){
        new HomeTask(API, this.getActivity(), v).execute();
    }
}
