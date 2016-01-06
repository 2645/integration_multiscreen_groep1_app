package be.ehb.funinthequeue;

import android.os.AsyncTask;
import android.widget.EditText;

import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by ToonLeemans on 06/01/16.
 */
public class loginAPI  extends AsyncTask<Void, Void, Void> {

    RestAPI API;
    EditText email;
    EditText wachtwoord;
    User user;

    public loginAPI(RestAPI API, EditText email, EditText wachtwoord) {
        this.API = API;
        this.email = email;
        this.wachtwoord = wachtwoord;
    }
    @Override
    protected Void doInBackground(Void... params) {
        user = API.users_lookup(5);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        String stringEmail = email.getText().toString();
        String datMail = user.getMail();
        String stringWachtwoord = wachtwoord.getText().toString();
        String datWachtwoord = user.getPw();

        if(stringEmail.equals(datMail) && stringWachtwoord.equals(datWachtwoord)){
        }
        else{
        }
    }
}