package be.ehb.funinthequeue;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import be.ehb.funinthequeue.fragments.AttractieDetailFragment;
import be.ehb.funinthequeue.fragments.GameFragment;
import be.ehb.funinthequeue.fragments.GameFragment2;
import be.ehb.funinthequeue.fragments.GegevensFragment;
import be.ehb.funinthequeue.fragments.HighscoresFragment;
import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.fragments.ProfielFragment;
import be.ehb.funinthequeue.fragments.QueueFragment;
import be.ehb.funinthequeue.game.quiz.QuizActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/GOTHAM-BOOK.OTF")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );

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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void changePage(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
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
        changePage(4);
    }
    public void goToQueueDetail(View view) {
        changePage(5);
    }
    public void backButtonAttractie(View view) {
        changePage(2);
    }
   /*public void startCubeGame(View view) {
        Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
        MainActivity.this.startActivity(myIntent);
    }*/
    public void startQuiz(View view) {
        Intent newIntent = new Intent(MainActivity.this, QuizActivity.class);
        MainActivity.this.startActivity(newIntent);
    }
    public void goToSettings(View view){
        changePage(6);
    }
    public void goToHighscore(View view) {
        changePage(7);
    }

}
