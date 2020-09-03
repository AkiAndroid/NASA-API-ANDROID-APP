package com.example.alienware;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONHolder
{
     @GET("planetary/apod?api_key=kGtU6DlObe1fqiejathzlZt5bmiqg0lU2YNgGJ7F")
     Call<Date> getAPOD(@Query("date") String Date);
}
