package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.VideoView;

public class welcomeActivity extends AppCompatActivity {


    ImageView alienware,forspider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(getApplicationContext(), Mainmenu.class);
                startActivity(homeintent);
                finish();
            }
        }, 5000);

        alienware=findViewById(R.id.alienware);
        forspider=findViewById(R.id.forspider);

        alienware.animate().alpha(1).setDuration(4000);
        forspider.animate().alpha(1).setDuration(4000);

    }


}