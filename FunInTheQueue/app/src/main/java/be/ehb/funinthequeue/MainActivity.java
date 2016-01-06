package be.ehb.funinthequeue;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import be.ehb.funinthequeue.fragments.AchievementsFragment;
import be.ehb.funinthequeue.fragments.AttractieDetailFragment;
import be.ehb.funinthequeue.fragments.AvatarFragment;
import be.ehb.funinthequeue.fragments.BackgroundFragment;
import be.ehb.funinthequeue.fragments.GameFragment;
import be.ehb.funinthequeue.fragments.GameFragment2;
import be.ehb.funinthequeue.fragments.ProfielAanpassenFragment;
import be.ehb.funinthequeue.fragments.HighscoresFragment;
import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.fragments.ProfielFragment;
import be.ehb.funinthequeue.fragments.QueueFragment;
import be.ehb.funinthequeue.fragments.SwipeButtonsFragment;
import be.ehb.funinthequeue.fragments.VriendenFragment;
import be.ehb.funinthequeue.game.catch_a_cube.GameActivity;
import be.ehb.funinthequeue.game.quiz.QuizActivity;
import be.ehb.funinthequeue.rest.RestAPI;
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
    Fragment achievements;
    Fragment avatars;
    Fragment vrienden;
    Fragment swipe;
    Fragment backgroundGames;

    ViewPager viewpager;

    RestAPI API;
    TextView verwelkoming;
    TextView cocacoins;

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

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

        backgroundGames = new BackgroundFragment();
        fragment = new HomeFragment();
        swipe = new SwipeButtonsFragment();
        profile = new ProfielFragment();
        home = new HomeFragment();
        game1 = new GameFragment();
        game2 = new GameFragment2();
        wachttijden = new QueueFragment();
        detail = new AttractieDetailFragment();
        gegevens = new ProfielAanpassenFragment();
        highscores = new HighscoresFragment();
        achievements = new AchievementsFragment();
        avatars = new AvatarFragment();
        vrienden = new VriendenFragment();


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.container, home)
                .addToBackStack(null)
                .commit();

        API = new RestAPI();
    }

    //product qr code mode
    public void goToQR(View v) {
        try {

            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(MainActivity.this, "Geen correcte scanner gevonden", "Wilt u een scanner downloaden", "Ja", "Nee").show();
        }
    }

    //download de scanner
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

    // scandata ontvangen
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //get the extras that are returned from the intent
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Toast toast = Toast.makeText(this, "U hebt " + contents + " coins ontvangen!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
    private void setupViewPagerGames(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new GameFragment());
        adapter.addFrag(new GameFragment2());
        viewpager.setAdapter(adapter);
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void changePage(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
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
            case 8:
                fragment = achievements;
                break;
            case 9:
                fragment = avatars;
                break;
            case 10:
                fragment = vrienden;
                break;
            case 11:
                fragment = swipe;
                break;
            case 12:
                fragment = backgroundGames;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }
    public void changeBackground(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {
            default:
            case 0:
                fragment = backgroundGames;
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.backgroundFragment, fragment)
                .addToBackStack(null)
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();

        changeBackground(0);
        changePage(11);

        viewpager = (ViewPager) findViewById(R.id.main_viewpager);
        setupViewPagerGames(viewpager);
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

    public void startQuiz(View view) {
        Intent newIntent = new Intent(MainActivity.this, QuizActivity.class);
        MainActivity.this.startActivity(newIntent);
    }

    public void goToSettings(View view) {
        changePage(6);
    }

    public void goToHighscore(View view) {
        changePage(7);
    }

    public void goToAchievements(View view) {
        changePage(8);
    }

    public void goToAvatars(View view) {
        changePage(9);
    }

    public void goToFriends(View view) {
        changePage(10);
    }

}
