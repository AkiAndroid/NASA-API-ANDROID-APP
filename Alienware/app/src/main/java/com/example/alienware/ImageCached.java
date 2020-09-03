package com.example.alienware;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageCached extends AsyncTask<String, Void, Bitmap> {


    @Override
    protected Bitmap doInBackground(String... imageurl) {

        try {
            String URLs = imageurl[0];

            if(!URLs.contains("https:"))
            {
                URLs = URLs.replace("http:","https:");
            }

            URL url = new URL(URLs);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return null;
    }
}