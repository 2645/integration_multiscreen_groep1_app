package be.ehb.funinthequeue;

import android.app.FragmentManager;
import android.content.Intent;
import android.app.Fragment;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    Fragment fragment = new HomeFragment();
    Fragment home;
    Fragment profile;
    Fragment game;
    Fragment wachttijden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = new ProfielFragment();
        home = new HomeFragment();
        game = new GameFragment();
        wachttijden = new QueueFragment();

        TextView verwelkoming = (TextView) findViewById(R.id.txtVerwelkoming);
        TextView wachttijd = (TextView) findViewById(R.id.txtWachttijd);
        TextView indebuurt = (TextView) findViewById(R.id.txtInDeBuurt);
        TextView wachttijdattractie = (TextView) findViewById(R.id.txtWachttijdTitel);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/GOTHAM-BOOK.OTF");
        verwelkoming.setTypeface(face);
        wachttijd.setTypeface(face);
        indebuurt.setTypeface(face);
        wachttijdattractie.setTypeface(face);
    }

    public void changePage(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager(); // For AppCompat use getSupportFragmentManager
        switch(position) {
            default:
            case 0:
                fragment = home;
                break;
            case 1:
                fragment = profile;
                break;
            case 2:
                fragment = wachttijden;
                break;
            case 3:
                fragment = game;
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void goToHome(View view) {
        changePage(0);
    }
    public void goToProfile(View view) {
        changePage(1);
    }
    public void goToQueue(View view) {
        changePage(2);
    }
    public void goToGame(View view) {
        changePage(3);
    }

}
