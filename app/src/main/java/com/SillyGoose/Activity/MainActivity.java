package com.SillyGoose.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.SillyGoose.Model.Status;
import com.SillyGoose.Utils.LocationInfo;
import com.SillyGoose.Utils.OkHttpUnits;
import com.baidu.location.LocationClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private ImageButton btn_pond;
    private ImageButton btn_trip;
    private ImageButton btn_album;

    private long mExitTime = 0;

    public Status appStatus;
    private Thread getWeather;


    MediaPlayer mp=new MediaPlayer();
    public LocationClient mLocationClient = null;
    private LocationInfo myListener = new LocationInfo();
    //获取实例
    public static MainActivity Instance;

    //static final int COLOR1 = Color.parseColor("#FFB032");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=MediaPlayer.create(this, R.raw.btn);
        setContentView(R.layout.activity_main);
        Instance = this;
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

        // If not Sign In ~
        if(!Status.isIsSignIn()) {
            //startActivity(new Intent(MainActivity.this, SignInActivity.class));
            startActivityForResult(new Intent(MainActivity.this, SignInActivity.class),1);
        }

        /*  Maybe I should delete it because I didn't used it */
        /*  init BaiduMap SDK
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        option.setScanSpan(0);
        option.setOpenGps(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setWifiCacheTimeOut(5*60*1000);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.requestLocation();
        mLocationClient.start();
        //mLocationClient.stop(); */

    }

    private void toast(String content){
        Toast.makeText(getApplicationContext(),content, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        /* there is Something Wrong in this Method */
        long now = System.currentTimeMillis();
        if((now - mExitTime) > 2000) {
            toast("再按返回键退出！");
            mExitTime = now;   //这里赋值最关键，别忘记
        }
        else{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Value","SIGNOUT");
                        jsonObject.put("Phone",Status.getUser().getUserPhone());
                        OkHttpUnits.post(OkHttpUnits.setAndGetUrl("/post"),jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            Status.setIsSignIn(false);
            stopService(new Intent(getApplicationContext(),BgmService.class));
            stopService(new Intent(getApplicationContext(),TimerService.class));
            MainActivity.this.finish();   //关闭本活动页面
            //System.exit(0);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //有可能造成线程不同步，需解决
        //Weather.getWeather();

        Intent service = new Intent(getApplicationContext(),TimerService.class);
        startService(service);
        //Status.setGoose();
    }


}

