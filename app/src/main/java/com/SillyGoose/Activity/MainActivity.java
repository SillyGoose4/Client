package com.SillyGoose.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.SillyGoose.Model.OkHttpUnits;
import android.widget.Toast;
import static com.SillyGoose.Activity.R.*;

public class MainActivity extends AppCompatActivity {
    private ImageButton btn_pool;
    private ImageButton btn_trip;
    private ImageButton btn_album;
    private long mExitTime = System.currentTimeMillis();
    MediaPlayer mp=new MediaPlayer();
    //static final int COLOR1 = Color.parseColor("#FFB032");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp=MediaPlayer.create(this, raw.btn);
        setContentView(R.layout.activity_main);
        btn_pool=(ImageButton)findViewById(id.btn_pond);
        btn_pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  btn_pool.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                mp.start();
                Intent maini=new Intent(MainActivity.this,PoolActivity.class);
                startActivity(maini);
            }
        });
        btn_trip=(ImageButton)findViewById(id.btn_airplane);
        btn_trip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mp.start();
                    Intent trip=new Intent(MainActivity.this,TripActivity.class);
                    startActivity(trip);
                }
            });
        btn_album=(ImageButton)findViewById(id.btn_album);
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
    private void toast(String content){
        Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - mExitTime < 800) {
            MainActivity.this.finish();   //关闭本活动页面
        }
        else{
            toast("再按返回键退出！");
            mExitTime = System.currentTimeMillis();   //这里赋值最关键，别忘记
        }
    }
}
