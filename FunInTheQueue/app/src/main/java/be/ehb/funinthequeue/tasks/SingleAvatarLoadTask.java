package be.ehb.funinthequeue.tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.adapters.AvatarRecyclerAdapter;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class SingleAvatarLoadTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    View rootView;

    ArrayList<Avatar> avatars;
    Avatar avatar;

    public SingleAvatarLoadTask(RestAPI API, View rootView, Avatar avatar) {
        this.API = API;
        this.avatar = avatar;
        this.rootView = rootView;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(API.existsInCache("avatars")) {
            avatars = API.avatars_list();
            avatar = avatars.get(avatars.indexOf(avatar));

        } else {
            avatar = API.avatars_lookup(avatar.getId());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e("LOG", "Saving image: " + avatar.getImg());
        ImageView img = (ImageView) rootView.findViewById(R.id.imgAvatar);
        img.setImageBitmap(avatar.getBitmap());
    }
}