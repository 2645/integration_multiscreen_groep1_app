package be.ehb.funinthequeue;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import be.ehb.funinthequeue.fragments.LoginFragmentAanmelden;
import be.ehb.funinthequeue.fragments.LoginFragmentFirst;
import be.ehb.funinthequeue.fragments.LoginFragmentRegistreren;
import be.ehb.funinthequeue.game.catch_a_cube.GameActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends FragmentActivity {

    Fragment eersteLoginScreen;
    Fragment fragment;
    Fragment aanmelden;
    Fragment registreren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/GOTHAM-BOOK.OTF")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );


        eersteLoginScreen = new LoginFragmentFirst();
        aanmelden = new LoginFragmentAanmelden();
        registreren = new LoginFragmentRegistreren();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.loginContainer, eersteLoginScreen)
                .addToBackStack(null)
                .commit();
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void changePage(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            default:
            case 0:
                fragment = aanmelden;
                break;
            case 1:
                fragment = registreren;
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.loginContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToAanmelden(View v){
        changePage(0);
    }

    public void aanmeldenClick(View v){
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
    public void goToRegistreren(View v){
        changePage(1);
    }
}
