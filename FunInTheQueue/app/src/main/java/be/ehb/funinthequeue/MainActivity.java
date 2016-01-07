package be.ehb.funinthequeue;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.anupcowkur.reservoir.Reservoir;

import be.ehb.funinthequeue.fragments.AchievementsFragment;
import be.ehb.funinthequeue.fragments.AttractieDetailFragment;
import be.ehb.funinthequeue.fragments.AvatarFragment;
import be.ehb.funinthequeue.fragments.BackgroundFragment;
import be.ehb.funinthequeue.fragments.GameFragment;
import be.ehb.funinthequeue.fragments.GameFragment2;
import be.ehb.funinthequeue.fragments.HighscoresFragment;
import be.ehb.funinthequeue.fragments.HomeFragment;
import be.ehb.funinthequeue.fragments.ProfielAanpassenFragment;
import be.ehb.funinthequeue.fragments.ProfielFragment;
import be.ehb.funinthequeue.fragments.QueueFragment;
import be.ehb.funinthequeue.fragments.SwipeButtonsFragment;
import be.ehb.funinthequeue.fragments.VriendenFragment;
import be.ehb.funinthequeue.game.catch_a_cube.GameActivity;
import be.ehb.funinthequeue.game.quiz.QuizActivity;
import be.ehb.funinthequeue.model.User;
import be.ehb.funinthequeue.rest.RestAPI;
import be.ehb.funinthequeue.tasks.DataLoadTask;
import be.ehb.funinthequeue.tasks.QrTriggerTask;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

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

    private ViewPager viewPager;

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

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        try {
            Reservoir.init(this, 20000000); //in bytes
        } catch (Exception e) {
            //failure
        }
        API = new RestAPI();
        new DataLoadTask(API, HelperFunctions.loadUserFromPreferences(MainActivity.this)).execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onTrimMemory(TRIM_MEMORY_COMPLETE);
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
                String content = intent.getStringExtra("SCAN_RESULT");
                User u = HelperFunctions.loadUserFromPreferences(MainActivity.this);
                new QrTriggerTask(this, API, content, u.getId()).execute();

                Toast toast = Toast.makeText(this, "Code succesvol gescanned!", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    private void setupViewPager(ViewPager viewpager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new HomeFragment());
        adapter.addFrag(new GameFragment());
        adapter.addFrag(new ProfielFragment());
        adapter.addFrag(new QueueFragment());
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
                fragment = game2;
                break;
            case 1:
                fragment = detail;
                break;
            case 2:
                fragment = gegevens;
                break;
            case 3:
                fragment = highscores;
                break;
            case 4:
                fragment = achievements;
                break;
            case 5:
                fragment = avatars;
                break;
            case 6:
                fragment = vrienden;
                break;
            case 7:
                fragment = profile;
                break;
        }

        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToQueueDetail(View view) {
        changePage(1);
    }

    public void goToHome(View view){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(0);
    }

    public void backButtonAttractie(View view) {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(3);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }

    public void goToQueue(View view){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(3);
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
        changePage(2);
    }
    public void goToProfile(View v){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(2);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }

    public void goToQuiz(View v){
        changePage(0);
    }
    public void goToGame(View v){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setCurrentItem(1);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .remove(fragment)
                .commit();
    }


    public void goToHighscore(View view) {
        changePage(3);
    }

    public void goToAchievements(View view) {
        changePage(4);
    }

    public void goToAvatars(View view) {
        changePage(5);
    }

    public void goToFriends(View view) {
        changePage(6);
    }

    public void logOutClick(View v){
        HelperFunctions.removeUserPreferences(MainActivity.this);

        Intent newIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(newIntent);
    }
}
