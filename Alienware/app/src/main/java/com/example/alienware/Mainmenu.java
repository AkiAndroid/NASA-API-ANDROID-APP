package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

public class Mainmenu extends AppCompatActivity {

    ImageView apodbutton,searchnasabutton;
    VideoView videoview2;
    int currentvideoposition;
    MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        apodbutton=findViewById(R.id.apodbutton);
        searchnasabutton=findViewById(R.id.searchnasabutton);


        videoview2=(VideoView) findViewById(R.id.mainmenubackg);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.backgvideo);
        videoview2.setVideoURI(uri);
        videoview2.start();

        videoview2.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

            @Override
            public void onPrepared(MediaPlayer mp) {
                mplayer=mp;

                mplayer.setLooping(true);
                if (currentvideoposition!=0){
                    mplayer.seekTo(currentvideoposition);
                    mplayer.start();
                }
            }
        });

        apodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DatePicker.class);
                startActivity(intent);
            }
        });

        searchnasabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SearchQuery.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentvideoposition= mplayer.getCurrentPosition();
        videoview2.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        videoview2.start();
    }

}