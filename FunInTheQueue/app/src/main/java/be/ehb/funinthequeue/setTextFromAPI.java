package be.ehb.funinthequeue;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    ImageView attractieimage;
    ImageView avatarimage;

    User user;
    Attraction attractie;
    Avatar avatar;

    public setTextFromAPI(RestAPI API, TextView verwelkoming, TextView cocacoins, TextView wachttijdAttractie, TextView wachttijd1, TextView afstand1, ImageView attractieimage, ImageView avatarimage) {
        this.API = API;
        this.verwelkoming = verwelkoming;
        this.cocacoins = cocacoins;
        this.wachttijdAttractie = wachttijdAttractie;
        this.wachttijd1 = wachttijd1;
        this.afstand1 = afstand1;
        this.attractieimage = attractieimage;
        this.avatarimage = avatarimage;
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
        afstand1.setText("100 m");

        byte[] decodedStringAttractie = Base64.decode(attractie.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAttractie = BitmapFactory.decodeByteArray(decodedStringAttractie, 0, decodedStringAttractie.length);
        attractieimage.setImageBitmap(ImageClip.getCroppedBitmap(decodedByteAttractie, 30));

        byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
        avatarimage.setImageBitmap(decodedByteAvatar);
    }
}