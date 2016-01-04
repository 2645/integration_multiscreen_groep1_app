package be.ehb.funinthequeue.rest;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by Dieter on 4/01/2016.
 */

public interface RestInterface {
    @GET("users/lookup/")
    Call<User> getUserByID(@QueryMap Map<String, String> options);
}
