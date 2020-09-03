package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    String Date;
    TextView heading,explanation;
    WebView videoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        heading=findViewById(R.id.heading);
        explanation=findViewById(R.id.explanation);
        videoView=findViewById(R.id.videoplayer);

        Intent intent=getIntent();
        Date=getIntent().getExtras().getString("date");


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.nasa.gov/").addConverterFactory(GsonConverterFactory.create()).build();

        JSONHolder DateHolder = retrofit.create(JSONHolder.class);


        Call<Date> Reciever = DateHolder.getAPOD(Date);

        Reciever.enqueue(new Callback<Date>() {
            @Override
            public void onResponse(Call<Date> call, Response<Date> response) {
                if (!response.isSuccessful()) {
                    Log.d("Hello", "404");
                } else {
                    Date Output = response.body();

                    if (Output.media_type.matches("image")) {
                        videoView.setVisibility(View.GONE);
                        imageView.setVisibility(View.VISIBLE);
                        getImage(Output.url);
                    }
                    else if (Output.media_type.matches("video")){
                        videoView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.INVISIBLE);

                        videoView.setWebViewClient(new WebViewClient());
                        videoView.getSettings().setJavaScriptEnabled(true);
                        videoView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                        videoView.getSettings().setPluginState(WebSettings.PluginState.ON);
                        videoView.getSettings().setMediaPlaybackRequiresUserGesture(false);
                        videoView.setWebChromeClient(new WebChromeClient());
                        videoView.loadUrl(Output.url);

                    }

                    heading.setText(Output.title);
                    explanation.setText(Output.explanation);

                    Log.d("Hello", Output.media_type + "");
                }

            }

            @Override
            public void onFailure(Call<Date> call, Throwable t) {
                Log.d("Hello", "OOPS");
                Toast.makeText(MainActivity.this, "OOPS!! CHECK YOUR CONNECTION", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DatePicker.class);
                startActivity(intent);

            }
        });


    }

    void getImage(String Url) {
      try {
          ImageCached imageCached=new ImageCached();
          Bitmap bitmap=imageCached.execute(Url).get();
          Log.d("Hello","Hi");
          imageView.setImageBitmap(bitmap);
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }

    }

}
