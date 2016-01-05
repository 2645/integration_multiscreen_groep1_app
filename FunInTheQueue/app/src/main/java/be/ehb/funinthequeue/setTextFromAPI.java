package be.ehb.funinthequeue;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class setTextFromAPI extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    TextView verwelkoming;
    TextView cocacoins;
    User user;

    public setTextFromAPI(RestAPI API, TextView verwelkoming, TextView cocacoins) {
        this.API = API;
        this.verwelkoming = verwelkoming;
        this.cocacoins = cocacoins;
    }
    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_lookup(5);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        verwelkoming.setText("Welkom, " + user.getFname() + "!");
        cocacoins.setText(user.getBalance() + " cocacoins");
    }
}