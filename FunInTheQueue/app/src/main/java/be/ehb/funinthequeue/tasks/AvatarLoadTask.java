package be.ehb.funinthequeue.tasks;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.adapters.AvatarRecyclerAdapter;
import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class AvatarLoadTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    AvatarRecyclerAdapter adapter;

    ArrayList<Avatar> avatars;

    public AvatarLoadTask(RestAPI API, AvatarRecyclerAdapter adapter) {
        this.API = API;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.e("LOG", "Loading avatars!");
        avatars = API.avatars_list();
        Log.e("LOG", "Loaded avatars!");
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e("LOG", "Swappin'!");
        adapter.swap(avatars);
    }
}