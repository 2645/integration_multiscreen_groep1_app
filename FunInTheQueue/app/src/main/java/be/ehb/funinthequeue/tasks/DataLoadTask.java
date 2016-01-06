package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.adapters.AvatarRecyclerAdapter;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class DataLoadTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    ArrayList<Avatar> avatars;
    ArrayList<Attraction> attractions;
    ArrayList<User> friends;
    User user;

    public DataLoadTask(RestAPI API, User user) {
        this.API = API;
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... params) {
        avatars = API.avatars_list();
        attractions = API.attractions_list();
        friends = API.friendships_list(user.getId());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
    }
}