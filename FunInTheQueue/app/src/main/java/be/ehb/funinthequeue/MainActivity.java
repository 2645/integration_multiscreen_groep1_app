package be.ehb.funinthequeue;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    Fragment fragment;
    Fragment home;
    Fragment profile;
    Fragment game1;
    Fragment game2;
    Fragment wachttijden;
    Fragment detail;
    Fragment gegevens;
    Fragment highscores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //tekst();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/GOTHAM-BOOK.OTF");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        profile = new ProfielFragment();
        home = new HomeFragment();
        game1 = new GameFragment();
        game2 = new GameFragment2();
        wachttijden = new QueueFragment();
        detail = new AttractieDetailFragment();
        gegevens = new GegevensFragment();
        highscores = new HighscoresFragment();
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
        FragmentManager fragmentManager = getSupportFragmentManager(); // For AppCompat use getSupportFragmentManager
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
                fragment = game1;
                break;
            case 4:
                fragment = game2;
                break;
            case 5:
                fragment = detail;
                break;
            case 6:
                fragment = gegevens;
                break;
            case 7:
                fragment = highscores;
                break;
        }
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
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
       /* Intent myIntent = new Intent(MainActivity.this, PageViewActivity.class);
        startActivity(myIntent);*/
    }
    public void goToQueueDetail(View view) {
        changePage(5);
    }
    public void backButtonAttractie(View view) {
        changePage(2);
    }
    public void startCubeGame(View view) {
        Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void goToSettings(View view){
        changePage(6);
    }
    public void goToHighscore(View view) {
        changePage(7);
    }

}
