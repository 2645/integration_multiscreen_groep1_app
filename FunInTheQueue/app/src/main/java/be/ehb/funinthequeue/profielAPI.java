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

import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 04/01/16.
 */
public class profielAPI extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    TextView naam;
    ImageView avatarimage;
    TextView cocacoins;

    User user;
    Avatar avatar;

    public profielAPI(RestAPI API, TextView naam, ImageView avatarimage, TextView cocacoins) {
        this.API = API;
        this.naam = naam;
        this.avatarimage = avatarimage;
        this.cocacoins = cocacoins;
    }

    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_lookup(5);
        avatar = API.avatars_lookup(5);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        naam.setText(user.getFname() + " " + user.getLname());
        cocacoins.setText(user.getBalance() + " cocacoins");

        byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
        avatarimage.setImageBitmap(decodedByteAvatar);
    }
}