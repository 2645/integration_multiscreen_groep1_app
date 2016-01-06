package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import be.ehb.funinthequeue.LoginActivity;
import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by Dieter on 6/01/2016.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    Boolean succesfulLogin;
    String email;
    String pw;

    public LoginTask(Context context, RestAPI API, String email, String pw) {
        this.context = (Activity) context;
        this.API = API;
        this.email = email;
        this.pw = pw;
    }

    @Override
    protected Void doInBackground(Void... params) {
        succesfulLogin = API.users_login(email, pw);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(succesfulLogin) {
            Intent myIntent = new Intent(context, MainActivity.class);
            context.startActivity(myIntent);
        } else {
            Toast.makeText(context, "Ongeldig e-mailadres of wachtwoord.", Toast.LENGTH_LONG).show();
        }
    }
}
