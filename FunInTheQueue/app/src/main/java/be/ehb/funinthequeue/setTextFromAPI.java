package be.ehb.funinthequeue;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class setTextFromAPI extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    TextView verwelkoming;
    TextView cocacoins;
    TextView wachttijdAttractie;
    TextView wachttijd1;
    TextView afstand1;
    TextView afstandAttractie;
    TextView wachttijd2;
    TextView afstand2;
    ImageView attractieimage;
    ImageView avatarimage;
    ImageView attractieimage2;

    User user;
    Attraction attractie;
    Avatar avatar;

    public setTextFromAPI(RestAPI API, TextView verwelkoming, TextView cocacoins, TextView wachttijdAttractie, TextView wachttijd1, TextView afstand1, ImageView attractieimage, ImageView avatarimage, TextView afstandAttractie, TextView wachttijd2, TextView afstand2, ImageView attractieimage2) {
        this.API = API;
        this.verwelkoming = verwelkoming;
        this.cocacoins = cocacoins;
        this.wachttijdAttractie = wachttijdAttractie;
        this.wachttijd1 = wachttijd1;
        this.afstand1 = afstand1;
        this.attractieimage = attractieimage;
        this.avatarimage = avatarimage;
        this.afstandAttractie =afstandAttractie;
        this.wachttijd2 = wachttijd2;
        this.afstand2 = afstand2;
        this.attractieimage2 = attractieimage2;
    }
    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_lookup(5);
        attractie = API.attractions_lookup(1);
        avatar = API.avatars_lookup(5);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        verwelkoming.setText("Hallo, " + user.getFname() + "!");
        cocacoins.setText(user.getBalance() + " cocacoins");
        wachttijdAttractie.setText(attractie.getName());
        wachttijd1.setText(attractie.getQueuetime() + " min");
        //afstand1.setText(attractie.getLat());
        afstandAttractie.setText(attractie.getName());
        wachttijd2.setText(attractie.getQueuetime() + " min");
        afstand2.setText("10 m");

        byte[] decodedStringAttractie = Base64.decode(attractie.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie = BitmapFactory.decodeByteArray(decodedStringAttractie, 0, decodedStringAttractie.length);
        attractieimage.setImageBitmap(decodedByteAttractie);

        byte[] decodedStringAttractie2 = Base64.decode(attractie.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie2 = BitmapFactory.decodeByteArray(decodedStringAttractie2, 0, decodedStringAttractie2.length);
        attractieimage2.setImageBitmap(decodedByteAttractie2);

        byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
        avatarimage.setImageBitmap(decodedByteAvatar);
    }
}