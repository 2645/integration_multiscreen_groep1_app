package be.ehb.funinthequeue;

    import android.media.MediaPlayer;
    import android.media.MediaPlayer.OnCompletionListener;
    import android.net.Uri;
    import android.os.Bundle;
    import android.app.Activity;
    import android.content.Intent;
    import android.widget.VideoView;

    public class SplashScreen extends LoginActivity
    {
        VideoView vidHolder;
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            try
            {
                vidHolder = new VideoView(this);
                setContentView(vidHolder);
                Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashvideo);
                vidHolder.setVideoURI(video);
                vidHolder.setZOrderOnTop(true);
                vidHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
                {
                    public void onCompletion(MediaPlayer mp) {
                        jump();
                    }});
                vidHolder.start();

            } catch(Exception ex) {
                jump();
            }
        }

        private void jump()
        {
            if(isFinishing())
                return;
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
            try {
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
            }
        }
    }
*/