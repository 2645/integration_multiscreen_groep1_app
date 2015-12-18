package be.ehb.funinthequeue;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    Fragment fragment = new HomeFragment();
    Fragment home;
    Fragment profile;
    Fragment game;
    Fragment wachttijden;
    Fragment detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //tekst();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/GOTHAM-BOOK.OTF");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        profile = new ProfielFragment();
        home = new HomeFragment();
        game = new GameFragment();
        wachttijden = new QueueFragment();
        detail = new AttractieDetailFragment();
    }

    public void tekst() {
        TextView wachttijdattractie = (TextView) findViewById(R.id.txtWachttijdTitel);
        TextView attractie = (TextView) findViewById(R.id.txtAttractietitel);
        TextView catchacube = (TextView) findViewById(R.id.txtCatchACube);
        Typeface medium = Typeface.createFromAsset(getAssets(),
                "fonts/GOTHAM-MEDIUM.OTF");
        wachttijdattractie.setTypeface(medium);
        attractie.setTypeface(medium);
        catchacube.setTypeface(medium);
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
            case 4:
                fragment = detail;
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
    public void goToHome(View view) {
        changePage(0);
    }
    public void goToQR(View view) {
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
    public void goToQueueDetail(View view) {
        changePage(4);
    }
    public void startCubeGame(View view) {
        Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

}
