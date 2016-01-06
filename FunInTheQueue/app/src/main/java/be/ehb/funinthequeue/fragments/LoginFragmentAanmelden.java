package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.LoginTask;

/**
 * Created by ToonLeemans on 05/01/16.
 */
public class LoginFragmentAanmelden extends Fragment {
    RestAPI API;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_aanmelden, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        API = new RestAPI();

        Button loginButton = (Button) getView().findViewById(R.id.btnAanmelden);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView editEmail = (TextView) getView().findViewById(R.id.editEmail);
                TextView editWachtwoord = (TextView) getView().findViewById(R.id.editWachtwoord);

                String email = editEmail.getText().toString();
                String pw = editWachtwoord.getText().toString();

                if (isValidEmail(email)) {
                    Log.e("LOGIN", email);
                    Log.e("LOGIN", isValidEmail(email) ? "valid" : "not valid");

                    if (isValidPassword(pw)) {
                        Log.e("LOGIN", "Starting AsyncTask...");
                        new LoginTask(getActivity(), API, email, pw).execute();

                    } else {
                        Toast.makeText(getActivity(), "Gelieve een wachtwoord in te voeren", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Ongeldig e-mailadres", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // Source: http://stackoverflow.com/a/7882950/2637528
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final static boolean isValidPassword(CharSequence target) {
        return !TextUtils.isEmpty(target);
    }
}

