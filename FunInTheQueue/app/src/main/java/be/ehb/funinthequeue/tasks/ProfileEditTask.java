package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by Dieter on 6/01/2016.
 */
public class ProfileEditTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    Boolean successful;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    User user;

    public ProfileEditTask(Context context, RestAPI API, User user) {
        this.context = (Activity) context;
        this.API = API;
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_update(user);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(user != null) {
            if(user.getId() != 0) {
                HelperFunctions.storeUserInPreferences(context, user);
                Intent myIntent = new Intent(context, MainActivity.class);
                context.startActivity(myIntent);
                Toast.makeText(context, "Data succesvol bijgewerkt.", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(context, "Profiel bij het bijwerken van uw gegevens.", Toast.LENGTH_LONG).show();
        }
    }
}
