package com.techbulls.assigmenttask.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class ApiClient {

     //Live Url
   public static final String BASE_URL = "http://www.omdbapi.com";

   public  static Retrofit retrofit = null;

//    public  static Retrofit getApiClient()
//    {
//
//        if(retrofit == null)
//        {
//            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                        .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return  retrofit;
//
//    }

    public static Retrofit getApiClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.MINUTES)
                    .build();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
