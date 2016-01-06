package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.avatarAPI;
import be.ehb.funinthequeue.profielAPI;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class AvatarFragment extends Fragment {
    RestAPI API;
    TextView avatarnaam;
    ImageView avatar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        API = new RestAPI();
        return inflater.inflate(R.layout.fragment_avatars, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTextAvatars();

    }

    public void setTextAvatars(){
        avatarnaam = (TextView) getView().findViewById(R.id.txtAvatar2);
        avatar = (ImageView) getView().findViewById(R.id.imgAvatar2);

        new avatarAPI(API, avatarnaam, avatar).execute();
    }
}
