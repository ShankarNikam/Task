
package com.techbulls.assigmenttask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Movie {

    @SerializedName("Response")
    private String mResponse;
    @SerializedName("Search")
    private List<Search> mSearch;
    @SerializedName("totalResults")
    private String mTotalResults;

    public String getResponse() {
        return mResponse;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public List<Search> getSearch() {
        return mSearch;
    }

    public void setSearch(List<Search> search) {
        mSearch = search;
    }

    public String getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(String totalResults) {
        mTotalResults = totalResults;
    }

}
