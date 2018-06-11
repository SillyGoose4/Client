package com.SillyGoose.Utils;

import android.util.Log;

import com.SillyGoose.Model.Status;
import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * get current weather class
 * Signleton pattern
 * First get GPS from baiduMapAPI
 * Then Use ShowAPI to search wearther
 * Created by wangj on 2018/6/6.
 */

public class Weather {
    private static Weather CurrentWeather = null;

    private static JSONObject data = null;

    private Weather(){
    }


    public static Weather getCurrentWeather() {
        if(CurrentWeather == null){
            synchronized (Weather.class){
                CurrentWeather = new Weather();
            }
        }
        return CurrentWeather;
    }

    public static void getWeather(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String appid="66782";//要替换成自己的
                String secret="ec285e3435a64398bf26781b0bc054d4";//要替换成自己的
                Log.d("Longitude", "run: "+String.valueOf(Status.getLongitude()));
                Log.d("Latitude","run "+String.valueOf(Status.getLatitude()));
                final String res=new ShowApiRequest( "http://route.showapi.com/9-4", appid, secret)
                        .addTextPara("needMoreDay", "0")
                        .addTextPara("needIndex", "0")
                        .addTextPara("needHourData", "1")
                        .addTextPara("need3HourForcast", "0")
                        .addTextPara("needAlarm", "0")
                        .post();
                try {
<<<<<<< HEAD
                    setData(new JSONObject(res));
                    Log.d("weather:",res);
                    System.out.println(res);
=======
                   JSONObject weatherData=new JSONObject(res);
                   //在此解析weatherData

>>>>>>> 7694ec881c0836bb3a622d532dff56e587fa7faf
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(res);
            }
        }).start();
    }

    public static JSONObject getData() {
        return data;
    }

    public static void setData(JSONObject data) {
        Weather.data = data;
    }
}