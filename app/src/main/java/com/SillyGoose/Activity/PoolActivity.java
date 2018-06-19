package com.SillyGoose.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.SillyGoose.Model.Goose;
import com.SillyGoose.Model.Status;
import com.SillyGoose.Utils.MessageBox;
import com.SillyGoose.Utils.OkHttpUnits;
import com.SillyGoose.Utils.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private TextView gooseENY;

    MediaPlayer mp=new MediaPlayer();

    //本类的实例
    public static PoolActivity INSTANCE;

    // 价位表
    private static Map<String,Integer> priceList = new LinkedHashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pool);
        INSTANCE = this;
        dynamicBackground();
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
        //gooseEny
        gooseENY=(TextView)findViewById(R.id.gooseEny);
        gooseENY.setText(""+Status.getGoose().getGooseEny());

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
        // set Button On Click Listener
        btn_cloud.setOnClickListener(this);
        btn_moon.setOnClickListener(this);
        btn_sun.setOnClickListener(this);
        btn_rain.setOnClickListener(this);
        btn_devil.setOnClickListener(this);
        btn_wind.setOnClickListener(this);

    }

    /**
     * 动态设置背景
     */
    private void dynamicBackground(){
        View viewanim=findViewById(R.id.pool);
        switch (Weather.WEATHER) {
            case "Cloud":
                if (Weather.ISDAY) {
                    viewanim.setBackgroundResource(R.drawable.dcloud);
                } else {
                    viewanim.setBackgroundResource(R.drawable.ncloud);
                }
                break;
            case "Rain":
                if (Weather.ISDAY) {
                    viewanim.setBackgroundResource(R.drawable.nrain);
                } else {
                    viewanim.setBackgroundResource(R.drawable.drain);
                }
                break;
            case "Sun":
                if(Weather.ISWIND) {
                    viewanim.setBackgroundResource(R.drawable.dwind);
                }else {
                    viewanim.setBackgroundResource(R.drawable.bg_day);
                    return;
                }
                break;
            case "Star":
                if(Weather.ISWIND) {
                    viewanim.setBackgroundResource(R.drawable.nwind);
                }else {
                    viewanim.setBackgroundResource(R.drawable.bg_night);
                    return;
                }
                break;
            case "Devil":
                if(Weather.ISWIND) {
                    viewanim.setBackgroundResource(R.drawable.bg_day);
                }else{
                    viewanim.setBackgroundResource(R.drawable.bg_night);
                }
                break;
            default:
                viewanim.setBackgroundResource(R.drawable.bg_day);
                break;
        }
        AnimationDrawable drawable = (AnimationDrawable) viewanim.getBackground();
        drawable.start();
    }

    /**
     * 后退键
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.moveTaskToBack(false);
        this.finish();
    }

    /**
     * ImageButton OnClickListener
     * @param view
     */
    @Override
    public void onClick(View view) {
        String clickBtn = "";
        mp.start();
        String suf="";
        Date lastTime = null;
        int res = 0;
        int gooseEny=0;
        switch (view.getId()){
            case R.id.btn_cloud:
                clickBtn = "Cloud";
                lastTime = Status.getCollectTime().getCloudLasttime();
                res = Status.getGoose().getGooseCloud();
                gooseEny=res*4;
                suf = "朵云";
                break;
            case R.id.btn_devil:
                clickBtn = "Devil";
                lastTime = Status.getCollectTime().getDevilLasttime();
                res = Status.getGoose().getGooseDevil();
                gooseEny=res*24;
                suf = "个";
                break;
            case R.id.btn_moon:
                clickBtn = "Star";
                lastTime = Status.getCollectTime().getStarLasttime();
                res = Status.getGoose().getGooseStar();
                gooseEny=res*3;
                suf = "个星星";
                break;
            case R.id.btn_wind:
                clickBtn = "Wind";
                lastTime = Status.getCollectTime().getWindLasttime();
                res = Status.getGoose().getGooseWind();
                suf = "缕风";
                break;
            case R.id.btn_sun:
                clickBtn = "Sun";
                lastTime = Status.getCollectTime().getSunLasttime();
                res = Status.getGoose().getGooseSun();
                gooseEny=res*3;
                suf = "个太阳";
                break;
            case R.id.btn_rain:
                clickBtn = "Rain";
                lastTime = Status.getCollectTime().getRainLasttime();
                res = Status.getGoose().getGooseRain();
                gooseEny=res*6;
                suf = "滴雨";
                break;
        }
        Log.d("Pool", "onClick: "+clickBtn);
        Log.d("Pool",clickBtn+" is "+res);

        setDialog(view,suf,res,clickBtn,gooseEny);
        //getDeltaTime(lastTime);
    }

    /**
     * 设置显示对话框
     * @param clickBtn
     * @param res
     * @param suf
     * @param view
     */
    public void setDialog(View view,String suf,int res, String clickBtn,int gooseEny){
        Log.d("setDialog", "setDialog: Success");
        if(res == 0){
            String msg = "你还没有收集到哦 ~ 再等等吧！";

            showButtonDialogFragment(view,msg,"0",gooseEny);
        }else{

            String msg = "你确定收集 "+res+" "+suf+"ma?将转化为鹅民币"+gooseEny+"元";

            showButtonDialogFragment(view,msg,clickBtn,gooseEny);
        }
    }



    /**
     * 显示dialog
     * @param view
     */
    public void showButtonDialogFragment(View view, String msg, final String clickBtn, final int gooseEny) {
        ButtonDialogFragment buttonDialogFragment = new ButtonDialogFragment();
        buttonDialogFragment.show("h:", msg, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PoolActivity.this, "点击了确定 " + which, Toast.LENGTH_SHORT).show();
                //new Thread(net).start();
                if(clickBtn.equals("0")){

                }else {
                    String[] params = new String[5];
                    params[0] = clickBtn;
                    params[1]= ""+gooseEny;
                    gooseENY=(TextView)findViewById(R.id.gooseEny);
                    Status.getGoose().setGooseEny(Status.getGoose().getGooseEny()+gooseEny);
                    gooseENY.setText(""+Status.getGoose().getGooseEny());
                    switch(clickBtn){
                        case "Cloud":
                            Status.getGoose().setGooseCloud(0);
                            break;
                        case "Wind":
                            Status.getGoose().setGooseWind(0);
                            break;
                        case "Devil":
                            Status.getGoose().setGooseDevil(0);
                            break;
                        case "Star":
                            Status.getGoose().setGooseStar(0);
                            break;
                        case "Sun":
                            Status.getGoose().setGooseSun(0);
                            break;
                        case "Rain":
                            Status.getGoose().setGooseRain(0);
                            break;
                    }
                    new Post().execute(params);
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PoolActivity.this, "点击了取消 " + which, Toast.LENGTH_SHORT).show();
            }
        }, getFragmentManager());
    }

    /**
     * DialogFragment class
     */
    @SuppressLint("ValidFragment")
    class ButtonDialogFragment extends DialogFragment {

        private DialogInterface.OnClickListener positiveCallback;

        private DialogInterface.OnClickListener negativeCallback;

        private String title;

        private String message;

        public void show(String title, String message, DialogInterface.OnClickListener positiveCallback,
                         DialogInterface.OnClickListener negativeCallback, FragmentManager fragmentManager) {
            this.title = title;
            this.message = message;
            this.positiveCallback = positiveCallback;
            this.negativeCallback = negativeCallback;
            show(fragmentManager, "ButtonDialogFragment");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("确定", positiveCallback);
            builder.setNegativeButton("取消", negativeCallback);
            return builder.create();
        }
    }

    /**
     *  网络类（传递消息）
     */
    class Post extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... strings) {
            System.out.println("Params------------------" + strings[0]);
            JSONObject send = new JSONObject();
            try {
                send.put("Value","COLLECTTIME");
                send.put("userId", com.SillyGoose.Model.Status.getUser().getUserId());
                send.put("One",strings[0]);
                if(OkHttpUnits.post(OkHttpUnits.setAndGetUrl("/update"), send) == MessageBox.UC_SUCCESS){
                    Log.d("doInBackGround ","UC_SUCCESS");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("Value","GOOSE");
                    jsonObject.put("gooseEny",Integer.valueOf(strings[1]));
                    jsonObject.put("gooseSun",0);
                    jsonObject.put("gooseStar",0);
                    jsonObject.put("gooseDevil",0);
                    jsonObject.put("gooseRain",0);
                    jsonObject.put("userId", com.SillyGoose.Model.Status.getUser().getUserId());
                    jsonObject.put("gooseWind",0);
                    jsonObject.put("gooseCloud",0);
                    OkHttpUnits.post(OkHttpUnits.setAndGetUrl("/update"),jsonObject);
                }else{
                    Log.d("doInBackGround ","SYS_ERROR");
                    Toast.makeText(PoolActivity.INSTANCE,"系统错误",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);
        }
    }
}



