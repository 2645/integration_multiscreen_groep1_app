package be.ehb.funinthequeue;

//http://www.coderefer.com/android-splash-screen-example-tutorial/

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.koushikdutta.ion.Ion;

public class SplashScreen extends MainActivity {

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        try {
            VideoView videoHolder = new VideoView(this);
            setContentView(videoHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashvideo);
            videoHolder.setVideoURI(video);
            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }
}
/*
        ImageView splashView = (ImageView) findViewById(R.id.splash_screen);
        splashView.setBackgroundResource(R.drawable.splash_animation);
        AnimationDrawable splashAnimation = (AnimationDrawable) splashView.getBackground();


        ImageView splashImageView = (ImageView) findViewById(R.id.splash_screen);
        Ion.with(splashImageView).load("android.resource://be.ehb.funinthequeue" + R.drawable.splashscreen);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}*/