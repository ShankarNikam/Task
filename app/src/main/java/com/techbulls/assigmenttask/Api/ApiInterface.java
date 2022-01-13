package com.techbulls.assigmenttask.Api;





import com.techbulls.assigmenttask.model.Movie;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("/")
    Call<Movie> getSearchMovie(
            @Query("s") String device_id,
            @Query("apikey") String fb_verify);

}
