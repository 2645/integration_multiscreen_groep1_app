package be.ehb.funinthequeue.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.EditText;

/**
 * Created by ToonLeemans on 05/01/16.
 */
public class LoginFragmentAanmelden extends Fragment {
    RestAPI API;

    private EditText email;
    private EditText wachtwoord;

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

                if (HelperFunctions.isValidEmail(email)) {
                    if (HelperFunctions.isNotEmpty(pw)){
                        new LoginTask(getActivity(), API, email, pw).execute();

                    }else{
                        Toast.makeText(getActivity(), "Gelieve een wachtwoord in te voeren", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Ongeldig e-mailadres", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

