package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.LoginActivity;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.SplashScreen;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by Dieter on 6/01/2016.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    User user;
    ArrayList<Avatar> avatars;
    Avatar avatar;

    public LoginTask(Context context, RestAPI API, String email, String pw) {
        this.context = (Activity) context;
        this.API = API;
        this.user = new User(email, pw);
    }

    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_login(user.getMail(), user.getPw());

        if(API.existsInCache("avatars")) {
            avatars = API.avatars_list();
            avatar = avatars.get(avatars.indexOf(avatar));

        } else {
            avatar = API.avatars_lookup(user.getAvatarId());
        }

        user.setImg(avatar.getImg());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(user != null) {
            if(user.getId() != 0) {
                HelperFunctions.storeUserInPreferences(context, user);
                Intent myIntent = new Intent(context, SplashScreen.class);
                context.startActivity(myIntent);
            }

        } else {
            Toast.makeText(context, "Ongeldig e-mailadres of wachtwoord.", Toast.LENGTH_LONG).show();
        }
    }
}
