package be.ehb.funinthequeue.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.funinthequeue.HelperFunctions;
import be.ehb.funinthequeue.R;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.ProfileEditTask;
import be.ehb.funinthequeue.tasks.RegisterTask;

/**
 * Created by ToonLeemans on 15/12/15.
 */
public class ProfielAanpassenFragment extends Fragment {
    RestAPI API;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profiel_aanpassen, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        User u = HelperFunctions.loadUserFromPreferences(getActivity());
        API = new RestAPI();

        TextView editProfileVoornaam = (TextView) getView().findViewById(R.id.editProfileVoornaam);
        TextView editProfileAchternaam = (TextView) getView().findViewById(R.id.editProfileAchternaam);
        TextView editProfileEmail = (TextView) getView().findViewById(R.id.editProfileEmail);
        TextView editProfileWachtwoord = (TextView) getView().findViewById(R.id.editProfileWachtwoord);

        editProfileVoornaam.setText(u.getFname());
        editProfileAchternaam.setText(u.getLname());
        editProfileEmail.setText(u.getMail());

        Button editProfileSubmit = (Button) getView().findViewById(R.id.editProfileSubmit);
        editProfileSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView editProfileVoornaam = (TextView) getView().findViewById(R.id.editProfileVoornaam);
                TextView editProfileAchternaam = (TextView) getView().findViewById(R.id.editProfileAchternaam);
                TextView editProfileEmail = (TextView) getView().findViewById(R.id.editProfileEmail);
                TextView editProfileWachtwoord = (TextView) getView().findViewById(R.id.editProfileWachtwoord);

                User user = new User(
                        editProfileVoornaam.getText().toString(),
                        editProfileAchternaam.getText().toString(),
                        editProfileEmail.getText().toString(),
                        editProfileWachtwoord.getText().toString()
                );

                if (HelperFunctions.isValidEmail(user.getMail())) {
                    if (HelperFunctions.isNotEmpty(user.getFname()) && HelperFunctions.isNotEmpty(user.getLname())) {
                        if (HelperFunctions.isNotEmpty(user.getPw())) {
                            new ProfileEditTask(getActivity(), API, user).execute();

                        } else {
                            Toast.makeText(getActivity(), "Gelieve een wachtwoord in te voeren", Toast.LENGTH_LONG).show();
                        }

                    }

                } else {
                    Toast.makeText(getActivity(), "Ongeldig e-mailadres", Toast.LENGTH_LONG).show();
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    public void btnProfielAanpassenOnClick() {

    }
}
