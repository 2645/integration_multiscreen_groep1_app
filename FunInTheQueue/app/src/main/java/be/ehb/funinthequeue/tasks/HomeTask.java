package be.ehb.funinthequeue.tasks;

/**
 * Created by janhd on 6/01/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Attr;
import org.w3c.dom.Text;

import java.util.ArrayList;

import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class HomeTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    Activity a;
    View v;
    User u;

    Attraction fastest;
    Attraction closest;
    Avatar avatar;

    public HomeTask(RestAPI API, Activity a, View v) {
        this.API = API;
        this.a = a;
        this.v = v;
        this.u = HelperFunctions.loadUserFromPreferences(a);
    }
    @Override
    protected Void doInBackground(Void... params) {
        ArrayList<Attraction> attractions = API.attractions_list();
        fastest = getShortestQueue(attractions);
        closest = getClosestQueue(attractions);
        avatar= API.avatars_lookup(u.getAvatarId());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ((TextView)v.findViewById(R.id.txtWachttijdTitel)).setText(fastest.getName());
        //((TextView)v.findViewById(R.id.txtTijd1)).setText(fastest.getQueuetime());
        ((TextView)v.findViewById(R.id.txtAfstand1)).setText("500");
        ((TextView)v.findViewById(R.id.txtAttractietitel)).setText(closest.getName());
       // ((TextView)v.findViewById(R.id.txtTijd2)).setText(closest.getQueuetime());
        ((TextView)v.findViewById(R.id.txtAfstand2)).setText("501");

        byte[] decodedStringAttractie = Base64.decode(fastest.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie = BitmapFactory.decodeByteArray(decodedStringAttractie, 0, decodedStringAttractie.length);
        ((ImageView) v.findViewById(R.id.imgKortsteWachttijd)).setImageBitmap(decodedByteAttractie);

        byte[] decodedStringAttractie2 = Base64.decode(closest.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie2 = BitmapFactory.decodeByteArray(decodedStringAttractie2, 0, decodedStringAttractie2.length);
        ((ImageView) v.findViewById(R.id.imgInDeBuurt)).setImageBitmap(decodedByteAttractie2);

        v.findViewById(R.id.layoutDichtste).setVisibility(View.VISIBLE);
        v.findViewById(R.id.layoutKortste).setVisibility(View.VISIBLE);

        byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
        ((ImageView) v.findViewById(R.id.imgAvatar)).setImageBitmap(decodedByteAvatar);

    }

    private Attraction getShortestQueue(ArrayList<Attraction> attractions){
        Attraction shortest = attractions.get(0);
        for(Attraction a: attractions){
            if(a.getQueuetime() < shortest.getQueuetime()){
                shortest = a;
            }
        }
        return shortest;
    }

    private Attraction getClosestQueue(ArrayList<Attraction> attractions){
        return attractions.get(0);
    }
}