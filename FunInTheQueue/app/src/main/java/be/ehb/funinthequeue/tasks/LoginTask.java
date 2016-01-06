package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import be.ehb.funinthequeue.LoginActivity;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by Dieter on 6/01/2016.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    Boolean successful;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    User user;

    public LoginTask(Context context, RestAPI API, String email, String pw) {
        this.context = (Activity) context;
        this.API = API;
        this.user = new User(email, pw);
        this.sharedPref = this.context.getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        this.editor = this.sharedPref.edit();
    }

    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_login(user.getMail(), user.getPw());
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(user != null) {
            if(user.getId() != 0) {
                editor.putInt("userID", this.user.getId());
                editor.commit();

                Intent myIntent = new Intent(context, MainActivity.class);
                context.startActivity(myIntent);
            }

        } else {
            Toast.makeText(context, "Ongeldig e-mailadres of wachtwoord.", Toast.LENGTH_LONG).show();
        }
    }
}
