package com.SillyGoose.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.SillyGoose.Utils.OkHttpUnits;
import com.SillyGoose.Utils.Status;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class MainActivity extends AppCompatActivity {
    private ImageButton btn_pond;
    private ImageButton btn_trip;
    private ImageButton btn_album;
    public Status appStatus;
    private Thread getWeather;

    MediaPlayer mp=new MediaPlayer();
    //static final int COLOR1 = Color.parseColor("#FFB032");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=MediaPlayer.create(this, R.raw.btn);
        setContentView(R.layout.activity_main);


        btn_pond=(ImageButton)findViewById(R.id.btn_pond);
        btn_pond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent in=new Intent(MainActivity.this,PoolActivity.class);
                startActivity(in);
            }
        });

        btn_trip=(ImageButton)findViewById(R.id.btn_airplane);
        btn_trip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  mp.start();
                  Intent trip=new Intent(MainActivity.this,TripActivity.class);
                  startActivity(trip);
                }
            });
        btn_album=(ImageButton)findViewById(R.id.btn_album);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent album=new Intent(MainActivity.this,AlbumActivity.class);
                startActivity(album);
            }
        });

        /*  loading current status   */
        // get OkHttp instance
        OkHttpUnits client=OkHttpUnits.getInstance();
        // If not Sign In ~
        if(!Status.isIsSignIn()) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
        }else {

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);

    }


}
