package be.ehb.funinthequeue.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import be.ehb.funinthequeue.model.Achievement;
import be.ehb.funinthequeue.model.Attraction;
import be.ehb.funinthequeue.model.Avatar;
import be.ehb.funinthequeue.model.Barcode;
import be.ehb.funinthequeue.model.Friendship;
import be.ehb.funinthequeue.model.Game;
import be.ehb.funinthequeue.model.Question;
import be.ehb.funinthequeue.model.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;

/**
 * Created by Dieter on 4/01/2016.
 */

public interface RestInterface {
    // USERS
    @GET("users/lookup/")
    Call<User> users_lookup(@QueryMap Map<String, String> options);

    @GET("users/search/")
    Call<User> users_search(@QueryMap Map<String, String> options);

    @GET("users/list/")
    Call<ArrayList<User>> users_list();

    @POST("users/create/")
    Call<Integer> users_create(@Body User user);

    @POST("users/update/")
    Call<User> users_update(@Body User user);

    @GET("users/login/")
    Call<User> users_login(@QueryMap Map<String, String> options);


    // QUESTIONS
    @GET("question/list")
    Call<List<Question>> questions_list(@QueryMap Map<String, String> options);


    // GAMES
    @GET("games/lookup/")
    Call<Game> games_lookup(@QueryMap Map<String, String> options);

    @GET("games/list/")
    Call<ArrayList<Game>> games_list();

    @POST("games/create/")
    Call<Integer> games_create(@Body Game game);

    @POST("games/update/")
    Call<Integer> games_update(@Body Game game);


    // FRIENDSHIPS
    @GET("friendship/list/")
    Call<ArrayList<Friendship>> friendships_list(@QueryMap Map<String, String> options);

    @POST("friendship/create/")
    Call<Integer> friendships_create(@Body Friendship friendship);

    @POST("friendship/destroy/")
    Call<Integer> friendships_destroy(@Body Friendship friendship);


    // BARCODES
    @GET("barcode/lookup/")
    Call<Barcode> barcodes_lookup(@QueryMap Map<String, String> options);

    @GET("barcode/create/")
    Call<String> barcodes_create(@QueryMap Map<String, String> options);

    @GET("barcode/trigger/")
    Call<Boolean> barcodes_trigger(@QueryMap Map<String, String> options);

    @GET("barcode/destroy/")
    Call barcodes_destroy(@QueryMap Map<String, String> options);

    @POST("barcode/update/")
    Call<Integer> barcodes_update(@Body Barcode barcode);

    @GET("barcode/list/")
    Call<ArrayList<Barcode>> barcodes_list();


    // AVATARS
    @GET("avatars/lookup/")
    Call<Avatar> avatars_lookup(@QueryMap Map<String, String> options);

    @GET("avatars/list/")
    Call<ArrayList<Avatar>> avatars_list();

    @POST("avatars/create/")
    Call<Integer> avatars_create(@Body Avatar avatar);

    @POST("avatars/update/")
    Call<Integer> avatars_update(@Body Avatar avatar);


    // ATTRACTIONS
    @GET("attractions/lookup/")
    Call<Attraction> attractions_lookup(@QueryMap Map<String, String> options);

    @GET("attractions/list/")
    Call<ArrayList<Attraction>> attractions_list();

    @POST("attractions/create/")
    Call<Integer> attractions_create(@Body Attraction attraction);

    @POST("attractions/update/")
    Call<Integer> attractions_update(@Body Attraction attraction);

    @GET("attractions/updatequeue/")
    Call<Attraction> attractions_update_queue(@QueryMap Map<String, String> options);


    // ACHIEVEMENTS
    @GET("achievements/lookup/")
    Call<Achievement> achievements_lookup(@QueryMap Map<String, String> options);

    @GET("achievements/list/")
    Call<ArrayList<Achievement>> achievements_list();

    @POST("achievements/create/")
    Call<Integer> achievements_create(@Body Achievement Achievement);

    @GET("achievements/trigger/")
    Call<Boolean> achievements_trigger(@QueryMap Map<String, String> options);
}
