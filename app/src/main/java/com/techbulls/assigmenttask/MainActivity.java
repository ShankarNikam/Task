package com.techbulls.assigmenttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techbulls.assigmenttask.Adapter.MovieAdapter;
import com.techbulls.assigmenttask.Api.ApiClient;
import com.techbulls.assigmenttask.Api.ApiInterface;
import com.techbulls.assigmenttask.model.Movie;
import com.techbulls.assigmenttask.model.Search;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private  EditText movie_serch;
    private  ApiInterface apiInterface;
    private  RecyclerView search_list;
    private  String apikey = "89c88a2c";
    private  LinearLayoutManager linearLayoutManager;
    private  MovieAdapter movieAdapter;
    private  TextView submit;
    private  ArrayList<Movie> searchArrayList = new ArrayList<>();
    private  List<Search> searchList;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        movie_serch = (EditText) findViewById(R.id.movie_search);
        search_list = (RecyclerView) findViewById(R.id.search_list);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        search_list.setLayoutManager(linearLayoutManager);


        movie_serch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>3)
                    progressBar.setVisibility(View.VISIBLE);
                    searchMovie(charSequence.toString().trim(), apikey);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void searchMovie(String movieName, String apikey) {
        apiInterface.getSearchMovie(movieName,apikey).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    searchList =response.body().getSearch();
                    if (searchList!=null) {
                        addlist(searchList);
                    }else {
                        if (movieAdapter!=null)
                        movieAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
               t.getMessage();
            }
        });


    }

    private void addlist(List<Search> searchList) {
         movieAdapter = new MovieAdapter(MainActivity.this, searchList);
        search_list.setAdapter(movieAdapter);
    }



}