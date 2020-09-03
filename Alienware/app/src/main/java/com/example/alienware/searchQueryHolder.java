package com.example.alienware;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface searchQueryHolder {

    @GET("search")
    Call<NasaIDObject> getOutput(@Query("q")String SearchString);
}

class nasaid
{
    String nasa_id;
    String title;
    String media_type;
}

class data
{
    List<nasaid> data;
}

class Collection
{
    List<data> items;
}

class NasaIDObject
{
    Collection collection;
}

