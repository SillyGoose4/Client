package com.SillyGoose.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.SillyGoose.Utils.OkHttpUnits;

import connect.database.test.com.clents.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn_pool;
    private ImageButton btn_trip;
    private ImageButton btn_album;
    MediaPlayer mp=new MediaPlayer();

    //static final int COLOR1 = Color.parseColor("#FFB032");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mp=MediaPlayer.create(this,R.raw.btn);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_pool=(ImageButton)findViewById(R.id.btn_pond);
        btn_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  btn_pool.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                Intent mainit=new Intent(MainActivity.this,PoolActivity.class);
                startActivity(mainit);

            }
        }

        );

        btn_trip=(ImageButton)findViewById(R.id.btn_airplane);
        btn_trip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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


        OkHttpUnits client=OkHttpUnits.getInstance();
        startActivity(new Intent(MainActivity.this,SignInActivity.class));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);

    }

}
