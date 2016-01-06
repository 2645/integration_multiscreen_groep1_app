package be.ehb.funinthequeue.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import be.ehb.funinthequeue.MainActivity;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;

/**
 * Created by janhd on 6/01/2016.
 */
public class QrTriggerTask extends AsyncTask<Void, Void, Void> {

    Activity context;
    RestAPI API;
    String qrId;
    int reward, uId;
    boolean canAdd;

    public QrTriggerTask(Context context, RestAPI API, String qrId, int uId) {
        this.context = (Activity) context;
        this.API = API;
        this.qrId = qrId;
        this.uId = uId;
    }

    @Override
    protected Void doInBackground(Void... params) {
        this.canAdd = API.barcodes_trigger(qrId);
        if(this.canAdd) {
            this.reward = API.barcodes_lookup(qrId).getReward();
            User u = API.users_lookup(uId);
            u.setBalance(u.getBalance() + this.reward);
            API.users_update(u);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(this.canAdd) {
            Toast.makeText(context, "Je hebt "+this.reward+" cocacoins verdient!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Deze qrcode is al gescanned!", Toast.LENGTH_LONG).show();
        }
    }
}