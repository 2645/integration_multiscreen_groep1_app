package be.ehb.funinthequeue.rest;

import android.util.Log;

import com.anupcowkur.reservoir.Reservoir;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Attr;

import java.io.IOException;
import java.lang.reflect.Type;
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
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        restAdapter = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        restService = restAdapter.create(RestInterface.class);
    }

    public User users_lookup(int userID) {
        HashMap queryMap = new HashMap();
        queryMap.put("user_id", userID);

        Call<User> call = restService.users_lookup(queryMap);
        User user = (User) executeAndTestResponse(call);
        return user;
    }

    public User users_search(String firstname, String lastname) {
        HashMap queryMap = new HashMap();
        queryMap.put("firstname", firstname);
        queryMap.put("lastname", lastname);

        Call<User> call = restService.users_search(queryMap);
        User user = (User) executeAndTestResponse(call);
        return user;
    }

    public ArrayList<User> users_list() {
        String key = "users";
        ArrayList<User> users;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<User>>() {}.getType();
            users = (ArrayList<User>) fetchListFromCache(key, resultType);
            if(users == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<User>> call = restService.users_list();
            users = (ArrayList<User>) executeAndTestResponse(call);

            putListInCache(key, users);
        }

        return users;
    }

    public int users_create(User user) {
        Call<Integer> call = restService.users_create(user);
        int userID = (int) executeAndTestResponse(call);
        return userID;
    }

    public User users_update(User user) {
        Call<User> call = restService.users_update(user);
        user = (User) executeAndTestResponse(call);
        return user;
    }

    public User users_login(String email, String pw) {
        HashMap queryMap = new HashMap();
        queryMap.put("email", email);
        queryMap.put("pw", pw);

        Call<User> call = restService.users_login(queryMap);
        return (User) executeAndTestResponse(call);
    }

    public ArrayList<Question> questions_list(int question_id) {
        HashMap queryMap = new HashMap();
        queryMap.put("question_id", question_id);

        Call<List<Question>> call = restService.questions_list(queryMap);
        ArrayList<Question> questions = (ArrayList<Question>) executeAndTestResponse(call);
        return questions;
    }

    public Game games_lookup(int gameID) {
        HashMap queryMap = new HashMap();
        queryMap.put("game_id", gameID);

        Call<User> call = restService.games_lookup(queryMap);
        Game game = (Game) executeAndTestResponse(call);
        return game;
    }

    public ArrayList<Game> games_list() {
        String key = "games";
        ArrayList<Game> games;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<Game>>() {}.getType();
            games = (ArrayList<Game>) fetchListFromCache(key, resultType);
            if(games == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<Game>> call = restService.games_list();
            games = (ArrayList<Game>) executeAndTestResponse(call);

            putListInCache(key, games);
        }

        return games;
    }

    public int games_create(Game game) {
        Call<Integer> call = restService.games_create(game);
        int gameID = (int) executeAndTestResponse(call);
        return gameID;
    }

    public int games_update(Game game) {
        Call<Integer> call = restService.games_update(game);
        int gameID = (int) executeAndTestResponse(call);
        return gameID;
    }

    public ArrayList<User> friendships_list(int userID) {
        String key = "friends_" + userID;
        ArrayList<User> friends;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<User>>() {}.getType();
            friends = (ArrayList<User>) fetchListFromCache(key, resultType);
            if(friends == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");

            HashMap queryMap = new HashMap();
            queryMap.put("user_id", userID);

            Call<ArrayList<User>> call = restService.friendships_list(queryMap);
            friends = (ArrayList<User>) executeAndTestResponse(call);

            putListInCache(key, friends);
        }

        return friends;
    }

    public int friendships_create(Friendship friendship) {
        Call<Integer> call = restService.friendships_create(friendship);
        int friendshipID = (int) executeAndTestResponse(call);
        return friendshipID;
    }

    public int friendships_destroy(Friendship friendship) {
        Call<Integer> call = restService.friendships_destroy(friendship);
        int friendshipID = (int) executeAndTestResponse(call);
        return friendshipID;
    }

    public Barcode barcodes_lookup(String id) {
        HashMap queryMap = new HashMap();
        queryMap.put("id", id);

        Call<Barcode> call = restService.barcodes_lookup(queryMap);
        Barcode barcode = (Barcode) executeAndTestResponse(call);
        return barcode;
    }

    public ArrayList<Barcode> barcodes_list() {
        String key = "barcodes";
        ArrayList<Barcode> barcodes;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<Barcode>>() {}.getType();
            barcodes = (ArrayList<Barcode>) fetchListFromCache(key, resultType);
            if(barcodes == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<Barcode>> call = restService.barcodes_list();
            barcodes = (ArrayList<Barcode>) executeAndTestResponse(call);

            putListInCache(key, barcodes);
        }

        return barcodes;
    }

    public String barcodes_create(int reward) {
        HashMap queryMap = new HashMap();
        queryMap.put("reward", reward);

        Call<Integer> call = restService.barcodes_create(queryMap);
        String barcodeID = (String) executeAndTestResponse(call);
        return barcodeID;
    }

    public Boolean barcodes_trigger(String id) {
        HashMap queryMap = new HashMap();
        queryMap.put("id", id);

        Call<Integer> call = restService.barcodes_trigger(queryMap);
        Boolean result = (Boolean) executeAndTestResponse(call);
        return result;
    }

    public void barcodes_destroy(String id) {
        HashMap queryMap = new HashMap();
        queryMap.put("id", id);

        Call<Integer> call = restService.barcodes_destroy(queryMap);
        executeAndTestResponse(call);
    }

    public int barcodes_update(Barcode barcode) {
        Call<Integer> call = restService.barcodes_update(barcode);
        int barcodeID = (int) executeAndTestResponse(call);
        return barcodeID;
    }

    public Avatar avatars_lookup(int avatarID) {
        HashMap queryMap = new HashMap();
        queryMap.put("avatar_id", avatarID);

        Call<Avatar> call = restService.avatars_lookup(queryMap);
        Avatar avatar = (Avatar) executeAndTestResponse(call);
        return avatar;
    }

    public ArrayList<Avatar> avatars_list() {
        String key = "avatars";
        ArrayList<Avatar> avatars;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<Avatar>>() {}.getType();
            avatars = (ArrayList<Avatar>) fetchListFromCache(key, resultType);
            if(avatars == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<Avatar>> call = restService.avatars_list();
            avatars = (ArrayList<Avatar>) executeAndTestResponse(call);

            putListInCache(key, avatars);
        }

        return avatars;
    }

    public int avatars_create(Avatar avatar) {
        Call<Integer> call = restService.avatars_create(avatar);
        int avatarID = (int) executeAndTestResponse(call);
        return avatarID;
    }

    public int avatars_update(Avatar avatar) {
        Call<Integer> call = restService.avatars_update(avatar);
        int avatarID = (int) executeAndTestResponse(call);
        return avatarID;
    }

    public Attraction attractions_lookup(int attractionID) {
        HashMap queryMap = new HashMap();
        queryMap.put("attraction_id", attractionID);

        Call<Attraction> call = restService.attractions_lookup(queryMap);
        Attraction attraction = (Attraction) executeAndTestResponse(call);
        return attraction;
    }

    public ArrayList<Attraction> attractions_list() {
        String key = "attractions";
        ArrayList<Attraction> attractions;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<Attraction>>() {}.getType();
            attractions = (ArrayList<Attraction>) fetchListFromCache(key, resultType);
            if(attractions == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<Attraction>> call = restService.attractions_list();
            attractions = (ArrayList<Attraction>) executeAndTestResponse(call);

            putListInCache(key, attractions);
        }

        return attractions;
    }

    public int attractions_create(Attraction attraction) {
        Call<Integer> call = restService.attractions_create(attraction);
        int attractionID = (int) executeAndTestResponse(call);
        return attractionID;
    }

    public int attractions_update(Attraction attraction) {
        Call<Integer> call = restService.attractions_update(attraction);
        int attractionID = (int) executeAndTestResponse(call);
        return attractionID;
    }

    public int attractions_update_queue_time(Attraction attraction) {
        Call<Integer> call = restService.attractions_update(attraction);
        int attractionID = (int) executeAndTestResponse(call);
        return attractionID;
    }

    public Achievement achievements_lookup(int achievementID) {
        HashMap queryMap = new HashMap();
        queryMap.put("achievement_id", achievementID);

        Call<Achievement> call = restService.achievements_lookup(queryMap);
        Achievement achievement = (Achievement) executeAndTestResponse(call);
        return achievement;
    }

    public ArrayList<Achievement> achievements_list() {
        String key = "achievements";
        ArrayList<Achievement> achievements;

        if(existsInCache(key)) {
            Log.e("LOG", "Fetching " + key + " from cache!");
            Type resultType = new TypeToken<ArrayList<Achievement>>() {}.getType();
            achievements = (ArrayList<Achievement>) fetchListFromCache(key, resultType);
            if(achievements == null) {
                try {
                    Reservoir.delete(key);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

            }

        } else {
            Log.e("LOG", "Fetching " + key + " from server!");
            Call<ArrayList<Achievement>> call = restService.achievements_list();
            achievements = (ArrayList<Achievement>) executeAndTestResponse(call);

            putListInCache(key, achievements);
        }

        return achievements;
    }

    public int achievements_create(Achievement achievement) {
        Call<Integer> call = restService.achievements_create(achievement);
        int achievementID = (int) executeAndTestResponse(call);
        return achievementID;
    }

    public boolean achievements_trigger(int userID, int achievementID) {
        HashMap queryMap = new HashMap();
        queryMap.put("user_id", userID);
        queryMap.put("achievement_id", achievementID);

        Call<Boolean> call = restService.achievements_trigger(queryMap);
        boolean result = (boolean) executeAndTestResponse(call);
        return result;
    }

    private Object executeAndTestResponse(Call call) {
        Response response = null;

        try {
            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(response == null) {
            Log.e(LOG_TAG, "Response is null");

        } else {
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
        }

        return null;
    }

    private boolean existsInCache(String key) {
        try {
            return Reservoir.contains(key);

        } catch (Exception e) {
            return false;
        }
    }

    private Object fetchObjectFromCache(String key, Class<?> objectClass) {
        try {
            return Reservoir.get(key, objectClass);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fetchListFromCache(String key, Type resultType) {
        try {
            return Reservoir.get(key, resultType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void putListInCache(String key, Object list) {
        try {
            Reservoir.put(key, list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
