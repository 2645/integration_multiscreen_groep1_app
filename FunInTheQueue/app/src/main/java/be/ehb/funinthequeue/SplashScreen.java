package be.ehb.funinthequeue;

//http://www.coderefer.com/android-splash-screen-example-tutorial/

import android.content.Intent;
import android.database.MatrixCursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import be.ehb.funinthequeue.rest.RestAPI;

public class SplashScreen extends MainActivity {
    private volatile boolean animationDone = false;
    private Object syncObject = new Object();

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
            videoHolder.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    synchronized (syncObject) {
                        animationDone = true;
                        syncObject.notify();
                    }
                }
            });

            videoHolder.start();

        } catch (Exception ex) {
            // jump();
        }
/*
        RestAPI API = new RestAPI();
        new DataLoadThread(API).execute();
    }

    class DataLoadThread extends AsyncTask<Void, Void, Void> {
        RestAPI API;

        public DataLoadThread(RestAPI API) {
            this.API = API;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.d("API", API.getUserByID(5).toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            finish();
        }
           /* try {
                synchronized (syncObject) {
                    while (!animationDone) {
                        syncObject.wait();
                        }
                    Log.d("API", "OK");
                     startActivity(new Intent(SplashScreen.this, MainActivity.class));
                     finish();
                    }

                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }*/
        }
    }
