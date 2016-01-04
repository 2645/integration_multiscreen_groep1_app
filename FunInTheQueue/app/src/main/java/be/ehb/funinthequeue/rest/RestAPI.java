package be.ehb.funinthequeue.rest;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public User getUserByID(int userID) {

        HashMap queryMap = new HashMap();
        queryMap.put("user_id", userID);

        Call<User> call = restService.getUserByID(queryMap);
        Response<User> response = null;
        User user = null;

        try {
            response = call.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.isSuccess()) {
            user = response.body();

            if (user == null) {
                Log.e(LOG_TAG, "Result is null");
                Log.e(LOG_TAG, response.message());

            } else {
                Log.e(LOG_TAG, "Result is not null: " + response.body().toString());
            }

        } else {
            try {
                Log.e(LOG_TAG, response.errorBody().string());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
