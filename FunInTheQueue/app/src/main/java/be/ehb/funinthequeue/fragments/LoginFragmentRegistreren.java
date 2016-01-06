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

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.LoginTask;
import be.ehb.funinthequeue.tasks.RegisterTask;

/**
 * Created by ToonLeemans on 06/01/16.
 */
public class LoginFragmentRegistreren extends Fragment {
    RestAPI API;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_registreren, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        API = new RestAPI();

        Button registerButton = (Button) getView().findViewById(R.id.btnRegistreren);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView registerVoornaam = (TextView) getView().findViewById(R.id.registerVoornaam);
                TextView registerAchternaam = (TextView) getView().findViewById(R.id.registerAchternaam);
                TextView registerEmail = (TextView) getView().findViewById(R.id.registerEmail);
                TextView registerWachtwoord = (TextView) getView().findViewById(R.id.registerWachtwoord);

                String vnaam = registerVoornaam.getText().toString();
                String anaam = registerAchternaam.getText().toString();
                String email = registerEmail.getText().toString();
                String pw = registerWachtwoord.getText().toString();

                if (HelperFunctions.isValidEmail(email)) {
                    if (HelperFunctions.isNotEmpty(vnaam) && HelperFunctions.isNotEmpty(anaam) && HelperFunctions.isNotEmpty(pw)) {
                        new RegisterTask(getActivity(), API, vnaam, anaam, email, pw).execute();

                    } else {
                        Toast.makeText(getActivity(), "Gelieve een wachtwoord in te voeren", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Ongeldig e-mailadres", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

