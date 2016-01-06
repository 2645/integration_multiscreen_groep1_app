package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by Dieter on 6/01/2016.
 */

public class RegisterTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    User user;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public RegisterTask(Context context, RestAPI API, String vnaam, String anaam, String email, String pw) {
        this.context = (Activity) context;
        this.API = API;
        this.user = new User(vnaam, anaam, email, pw);
        this.sharedPref = this.context.getPreferences(Context.MODE_PRIVATE);
        this.editor = this.sharedPref.edit();
    }

    @Override
    protected Void doInBackground(Void... params) {
        user.setId(API.users_create(user));
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(user.getId() != 0) {
            editor.putInt("userID", this.user.getId());
            editor.commit();

            Intent myIntent = new Intent(context, MainActivity.class);
            context.startActivity(myIntent);

        } else {
            Toast.makeText(context, "Kan niet registreren.", Toast.LENGTH_LONG).show();
        }
    }
}
