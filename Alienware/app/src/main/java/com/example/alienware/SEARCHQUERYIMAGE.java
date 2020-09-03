package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SEARCHQUERYIMAGE extends AppCompatActivity {
String nasaid;
String mediatype;
ImageView queryimage;
MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_e_a_r_c_h_q_u_e_r_y_i_m_a_g_e);

        nasaid=getIntent().getStringExtra("nasaid");
        mediatype=getIntent().getStringExtra("mediatype");
        queryimage=findViewById(R.id.queryimage);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://images-api.nasa.gov/").addConverterFactory(GsonConverterFactory.create()).build();
        nasa_id nasaId=retrofit.create(nasa_id.class);
        Call<CollectionNasaID> collectionNasaIDCall=nasaId.getNasaIDDetails(nasaid);
        collectionNasaIDCall.enqueue(new Callback<CollectionNasaID>() {
            @Override
            public void onResponse(Call<CollectionNasaID> call, Response<CollectionNasaID> response) {
                if(!response.isSuccessful()){

                }
                else {
                    Log.d("Hello",mediatype);
                    if (mediatype.matches("image")){

                        Log.d("Hello",response.body().collection.items.get(0).href);
                        getImage(response.body().collection.items.get(0).href);
                    }
                    else  {
                        Toast.makeText(SEARCHQUERYIMAGE.this, "Image is not available for this section", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),SearchQuery.class);
                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionNasaID> call, Throwable t) {

                Toast.makeText(SEARCHQUERYIMAGE.this, "OOPS!! CHECK YOUR CONNECTION", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SearchQuery.class);
                startActivity(intent);

            }
        });
    }


    void getImage(String Url) {
        try {
            ImageCached imageCached=new ImageCached();
            Bitmap bitmap=imageCached.execute(Url).get();
            Log.d("Hello","Hi");
            queryimage.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}