package be.ehb.funinthequeue.rest;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ehb.funinthequeue.model.Achievement;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.Barcode;
import be.ehb.funinthequeue.model.Friendship;
import be.ehb.funinthequeue.model.Game;
import be.ehb.funinthequeue.model.Question;
import be.ehb.funinthequeue.model.User;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Dieter on 4/01/2016.
 */

public class RestAPI {
    private Retrofit restAdapter;
    private RestInterface restService;

    private final String BASE_URL = "http://10.3.50.220:8080";
    private final String LOG_TAG = "API";

    public RestAPI() {
        restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restService = restAdapter.create(RestInterface.class);
    }

    public class users {
        public User lookup(int userID) {
            HashMap queryMap = new HashMap();
            queryMap.put("user_id", userID);

            Call<User> call = restService.users_lookup(queryMap);
            User user = (User) executeAndTestResponse(call);
            return user;
        }

        public User search(String firstname, String lastname) {
            HashMap queryMap = new HashMap();
            queryMap.put("firstname", firstname);
            queryMap.put("lastname", lastname);

            Call<User> call = restService.users_search(queryMap);
            User user = (User) executeAndTestResponse(call);
            return user;
        }

        public ArrayList<User> list() {
            Call<ArrayList<User>> call = restService.users_list();
            ArrayList<User> users = (ArrayList<User>) executeAndTestResponse(call);
            return users;
        }

        public int create(User user) {
            Call<Integer> call = restService.users_create(user);
            int userID = (int) executeAndTestResponse(call);
            return userID;
        }

        public User update(User user) {
            Call<User> call = restService.users_update(user);
            user = (User) executeAndTestResponse(call);
            return user;
        }
    }

    public class questions {
        public ArrayList<Question> list(int question_id) {
            HashMap queryMap = new HashMap();
            queryMap.put("question_id", question_id);

            Call<List<Question>> call = restService.questions_list(queryMap);
            ArrayList<Question> questions = (ArrayList<Question>) executeAndTestResponse(call);
            return questions;
        }
    }

    public class games {
        public Game lookup(int gameID) {
            HashMap queryMap = new HashMap();
            queryMap.put("game_id", gameID);

            Call<User> call = restService.games_lookup(queryMap);
            Game game = (Game) executeAndTestResponse(call);
            return game;
        }

        public ArrayList<Game> list() {
            Call<ArrayList<Game>> call = restService.games_list();
            ArrayList<Game> games = (ArrayList<Game>) executeAndTestResponse(call);
            return games;
        }

        public int create(Game game) {
            Call<Integer> call = restService.games_create(game);
            int gameID = (int) executeAndTestResponse(call);
            return gameID;
        }

        public int update(Game game) {
            Call<Integer> call = restService.games_update(game);
            int gameID = (int) executeAndTestResponse(call);
            return gameID;
        }
    }

    public class friendships {
        public ArrayList<User> list(int userID) {
            HashMap queryMap = new HashMap();
            queryMap.put("user_id", userID);

            Call<ArrayList<User>> call = restService.friendships_list(queryMap);
            ArrayList<User> friends = (ArrayList<User>) executeAndTestResponse(call);
            return friends;
        }

        public int create(Friendship friendship) {
            Call<Integer> call = restService.friendships_create(friendship);
            int friendshipID = (int) executeAndTestResponse(call);
            return friendshipID;
        }

        public int destroy(Friendship friendship) {
            Call<Integer> call = restService.friendships_destroy(friendship);
            int friendshipID = (int) executeAndTestResponse(call);
            return friendshipID;
        }
    }

    public class barcodes {
        public Barcode lookup(int barcodeID) {
            HashMap queryMap = new HashMap();
            queryMap.put("barcode_id", barcodeID);

            Call<Barcode> call = restService.barcodes_lookup(queryMap);
            Barcode barcode = (Barcode) executeAndTestResponse(call);
            return barcode;
        }

        public int create(Barcode barcode) {
            Call<Integer> call = restService.barcodes_create(barcode);
            int barcodeID = (int) executeAndTestResponse(call);
            return barcodeID;
        }

        public int update(Barcode barcode) {
            Call<Integer> call = restService.barcodes_update(barcode);
            int barcodeID = (int) executeAndTestResponse(call);
            return barcodeID;
        }
    }

    public class avatars {
        public Avatar lookup(int avatarID) {
            HashMap queryMap = new HashMap();
            queryMap.put("avatar_id", avatarID);

            Call<Avatar> call = restService.avatars_lookup(queryMap);
            Avatar avatar = (Avatar) executeAndTestResponse(call);
            return avatar;
        }

        public ArrayList<Avatar> list() {
            Call<ArrayList<Avatar>> call = restService.avatars_list();
            ArrayList<Avatar> avatars = (ArrayList<Avatar>) executeAndTestResponse(call);
            return avatars;
        }

        public int create(Avatar avatar) {
            Call<Integer> call = restService.avatars_create(avatar);
            int avatarID = (int) executeAndTestResponse(call);
            return avatarID;
        }

        public int update(Avatar avatar) {
            Call<Integer> call = restService.avatars_update(avatar);
            int avatarID = (int) executeAndTestResponse(call);
            return avatarID;
        }
    }

    public class attractions {
        public Attraction lookup(int attractionID) {
            HashMap queryMap = new HashMap();
            queryMap.put("attraction_id", attractionID);

            Call<Attraction> call = restService.attractions_lookup(queryMap);
            Attraction attraction = (Attraction) executeAndTestResponse(call);
            return attraction;
        }

        public ArrayList<Attraction> list() {
            Call<ArrayList<Attraction>> call = restService.attractions_list();
            ArrayList<Attraction> attractions = (ArrayList<Attraction>) executeAndTestResponse(call);
            return attractions;
        }

        public int create(Attraction attraction) {
            Call<Integer> call = restService.attractions_create(attraction);
            int attractionID = (int) executeAndTestResponse(call);
            return attractionID;
        }

        public int update(Attraction attraction) {
            Call<Integer> call = restService.attractions_update(attraction);
            int attractionID = (int) executeAndTestResponse(call);
            return attractionID;
        }

        public int update_queue_time(Attraction attraction) {
            Call<Integer> call = restService.attractions_update(attraction);
            int attractionID = (int) executeAndTestResponse(call);
            return attractionID;
        }
    }

    public class achievements {
        public Achievement lookup(int achievementID) {
            HashMap queryMap = new HashMap();
            queryMap.put("achievement_id", achievementID);

            Call<Achievement> call = restService.achievements_lookup(queryMap);
            Achievement achievement = (Achievement) executeAndTestResponse(call);
            return achievement;
        }

        public ArrayList<Achievement> list() {
            Call<ArrayList<Achievement>> call = restService.achievements_list();
            ArrayList<Achievement> achievements = (ArrayList<Achievement>) executeAndTestResponse(call);
            return achievements;
        }

        public int create(Achievement achievement) {
            Call<Integer> call = restService.achievements_create(achievement);
            int achievementID = (int) executeAndTestResponse(call);
            return achievementID;
        }

        public boolean trigger(int userID, int achievementID) {
            HashMap queryMap = new HashMap();
            queryMap.put("user_id", userID);
            queryMap.put("achievement_id", achievementID);

            Call<Boolean> call = restService.achievements_trigger(queryMap);
            boolean result = (boolean) executeAndTestResponse(call);
            return result;
        }
    }

    private Object executeAndTestResponse(Call call) {
        Response response = null;

        try {
            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            if (response.body() == null) {
                Log.e(LOG_TAG, "Result is null");
                Log.e(LOG_TAG, response.message());

            } else {
                return response.body();
            }

        } else {
            try {
                Log.e(LOG_TAG, response.errorBody().string());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
