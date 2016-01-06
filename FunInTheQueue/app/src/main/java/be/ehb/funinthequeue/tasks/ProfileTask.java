package be.ehb.funinthequeue.tasks;

/**
 * Created by janhd on 6/01/2016.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

public class ProfileTask extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    Activity a;
    View v;
    User u;

    Avatar avatar;

    public ProfileTask(RestAPI API, Activity a, View v) {
        this.API = API;
        this.a = a;
        this.v = v;
        this.u = HelperFunctions.loadUserFromPreferences(a);
    }
    @Override
    protected Void doInBackground(Void... params) {
        avatar= API.avatars_lookup(u.getAvatarId());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        byte[] decodedStringAvatar = Base64.decode(avatar.getImg(), Base64.DEFAULT);
        Bitmap decodedByteAvatar = BitmapFactory.decodeByteArray(decodedStringAvatar, 0, decodedStringAvatar.length);
        ((ImageView) v.findViewById(R.id.imgAvatar)).setImageBitmap(decodedByteAvatar);

    }
}