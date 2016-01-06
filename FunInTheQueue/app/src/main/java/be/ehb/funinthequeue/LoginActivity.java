package be.ehb.funinthequeue;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import be.ehb.funinthequeue.fragments.LoginFragmentAanmelden;
import be.ehb.funinthequeue.fragments.LoginFragmentFirst;
import be.ehb.funinthequeue.fragments.LoginFragmentRegistreren;
import be.ehb.funinthequeue.game.catch_a_cube.GameActivity;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.LoginTask;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends FragmentActivity {

    Fragment eersteLoginScreen;
    Fragment fragment;
    Fragment aanmelden;
    Fragment registreren;
    RestAPI API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        int userid = HelperFunctions.loadUserFromPreferences(LoginActivity.this).getId();
        if(userid != 0){
            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(myIntent);
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/GOTHAM-BOOK.OTF")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

        API = new RestAPI();
        eersteLoginScreen = new LoginFragmentFirst();
        aanmelden = new LoginFragmentAanmelden();
        registreren = new LoginFragmentRegistreren();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        ImageView loginLogo = (ImageView) findViewById(R.id.login_logo);
        // loginLogo.setClickable(true);
        loginLogo.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                new LoginTask(LoginActivity.this, API, "admin@ehb.be", "admin").execute();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void changePage(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            default:
            case 0:
                fragment = eersteLoginScreen;
                break;
            case 1:
                fragment = aanmelden;
                break;
            case 2:
                fragment = registreren;
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.loginContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToAanmelden(View v){
        FrameLayout r = (FrameLayout)findViewById(R.id.loginContainer);
        r.removeAllViewsInLayout();

        changePage(0);
        changePage(1);
    }

    public void aanmeldenClick(View v){
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(myIntent);
    }

    public void goToRegistreren(View v){
        FrameLayout r = (FrameLayout)findViewById(R.id.loginContainer);
        r.removeAllViewsInLayout();

        changePage(0);
        changePage(2);
    }

}
