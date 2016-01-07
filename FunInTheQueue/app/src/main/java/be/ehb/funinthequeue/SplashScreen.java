package be.ehb.funinthequeue;

    import android.content.Context;
    import android.media.MediaPlayer;
    import android.media.MediaPlayer.OnCompletionListener;
    import android.net.Uri;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.app.Activity;
    import android.content.Intent;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.VideoView;

    import com.anupcowkur.reservoir.Reservoir;

    import java.util.ArrayList;

    import be.ehb.funinthequeue.model.Attraction;
    import be.ehb.funinthequeue.model.Avatar;
    import be.ehb.funinthequeue.model.User;
    import be.ehb.funinthequeue.rest.RestAPI;
    import be.ehb.funinthequeue.tasks.DataLoadTask;

public class SplashScreen extends Activity {
    RestAPI API;
    VideoView vidHolder;
    Attraction fastest;
    Attraction closest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        API = new RestAPI();

        new SplashDataLoadTask(API, this, HelperFunctions.loadUserFromPreferences(SplashScreen.this)).execute();
        super.onCreate(savedInstanceState);

    }

    public class SplashDataLoadTask extends AsyncTask<Void, Void, Void> {

        RestAPI API;
        ArrayList<Avatar> avatars;
        ArrayList<Attraction> attractions;
        ArrayList<User> friends;
        User user;
        Context context;

        public SplashDataLoadTask(RestAPI API, Context context, User user) {
            this.API = API;
            this.user = user;
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            try {
                vidHolder = new VideoView(context);
                setContentView(vidHolder);
                Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashvideo);
                vidHolder.setVideoURI(video);
                vidHolder.setZOrderOnTop(true);
                vidHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        //jump();
                    }
                });
                vidHolder.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });
                vidHolder.start();

            } catch(Exception ex) {}
        }

        @Override
        protected Void doInBackground(Void... params) {
            synchronized (this) {
                avatars = API.avatars_list();
                attractions = API.attractions_list();
                friends = API.friendships_list(user.getId());
                fastest = API.attractions_shortest_queue_time();
                closest = API.attractions_closest();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(context, MainActivity.class));
            finish();
        }
    }
}