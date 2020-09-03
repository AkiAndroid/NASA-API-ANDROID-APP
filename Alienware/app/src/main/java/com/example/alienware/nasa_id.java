package com.example.alienware;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface nasa_id {

    @GET("asset/{nasaid}")
    Call<CollectionNasaID> getNasaIDDetails(@Path("nasaid") String nasaid);
}

class listobject
{
    String href;
}

class items
{
    List<listobject>items;
}

class CollectionNasaID
{
    items collection;
}