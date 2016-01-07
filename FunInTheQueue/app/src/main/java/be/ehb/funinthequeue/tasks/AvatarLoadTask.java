package be.ehb.funinthequeue.tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import be.ehb.funinthequeue.adapters.AvatarRecyclerAdapter;
import be.ehb.funinthequeue.model.Avatar;
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