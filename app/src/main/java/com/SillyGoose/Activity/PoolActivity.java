package com.SillyGoose.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import connect.database.test.com.clents.R;

public class PoolActivity extends AppCompatActivity {
    private ImageButton btn_return;
    private ImageButton btn_trip;
    private ImageButton btn_album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        btn_return = (ImageButton) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(PoolActivity.this,MainActivity.class);
                startActivity(it);
            }
        });
        btn_trip=(ImageButton)findViewById(R.id.btn_airplane);

        btn_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trip=new Intent(PoolActivity.this,TripActivity.class);
                startActivity(trip);

            }
        });
        btn_album=(ImageButton)findViewById(R.id.btn_album);
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent album=new Intent(PoolActivity.this,AlbumActivity.class);
                startActivity(album);

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(true);

    }

}
