package be.ehb.funinthequeue.tasks;

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
public class AvatarLoadTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    ImageView avatarimage;
    TextView avatarnaam;

    User user;
    Avatar avatar;

    public AvatarLoadTask(RestAPI API, TextView avatarnaam, ImageView avatarimage) {
        this.API = API;
        this.avatarimage = avatarimage;
        this.avatarnaam = avatarnaam;
    }

    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_lookup(5);
        avatar = API.avatars_lookup(5);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
            avatarnaam.setText(avatar.getName());

            byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
            Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
            avatarimage.setImageBitmap(decodedByteAvatar);
    }
}