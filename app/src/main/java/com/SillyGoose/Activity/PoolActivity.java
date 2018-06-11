package com.SillyGoose.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.SillyGoose.Model.Status;
import com.SillyGoose.Utils.MessageBox;
import com.SillyGoose.Utils.OkHttpUnits;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PoolActivity extends AppCompatActivity implements View.OnClickListener{
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
        btn_rain=(ImageButton) findViewById(R.id.btn_rain);
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
                finish();
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
        btn_cloud.setOnClickListener(this);
        btn_moon.setOnClickListener(this);
        btn_sun.setOnClickListener(this);
        btn_rain.setOnClickListener(this);
        btn_devil.setOnClickListener(this);
        btn_wind.setOnClickListener(this);
    }
    /* For Net thread and UI thread transinformation */
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if((boolean)message.obj){
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
            }else{

            }
            return false;
        }
    });
    /* Net Thread */
    Runnable net = new Runnable() {
        @Override
        public void run() {
            Message msg = handler.obtainMessage();
            JSONObject send = new JSONObject();
            try {
                send.putOpt("GOOSE", Status.getStatus());
                if(OkHttpUnits.post(OkHttpUnits.setAndGetUrl("/update"), send) == MessageBox.UG_SUCCESS){
                     msg.obj = true;
                }else{
                    msg.obj = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cloud:
                break;
            case R.id.btn_devil:
                break;
            case R.id.btn_moon:
                break;
            case R.id.btn_wind:
                break;
            case R.id.btn_sun:
                break;
            case R.id.btn_rain:
                break;
        }
        new Thread(net).start();
    }
}
