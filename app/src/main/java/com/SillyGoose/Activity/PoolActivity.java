package com.SillyGoose.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PoolActivity extends AppCompatActivity {
    private ImageButton btn_return;
    private ImageButton btn_trip;
    private ImageButton btn_album;
    //瓶子
    private ImageButton btn_cloud;
    private ImageButton btn_rain;
    private ImageButton btn_sun;
    private ImageButton btn_wind;
    private ImageButton btn_devil;
    private ImageButton btn_moon;
    MediaPlayer mp=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        mp=MediaPlayer.create(this,R.raw.btn);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        btn_rain=(ImageButton) findViewById(R.id. btn_rain);
        btn_sun=(ImageButton) findViewById(R.id.btn_sun);
        btn_wind=(ImageButton) findViewById(R.id.btn_wind);
        btn_devil=(ImageButton) findViewById(R.id.btn_devil);
        btn_cloud=(ImageButton) findViewById(R.id.btn_cloud);
        btn_moon=(ImageButton) findViewById(R.id.btn_moon);
        btn_trip=(ImageButton)findViewById(R.id.btn_airplane);
        btn_album=(ImageButton)findViewById(R.id.btn_album);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent it=new Intent(PoolActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
        btn_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent trip=new Intent(PoolActivity.this,TripActivity.class);
                startActivity(trip);
            }
        });
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent album=new Intent(PoolActivity.this,AlbumActivity.class);
                startActivity(album);
            }
        });
        btn_cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_moon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_devil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btn_wind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);
    }

}
