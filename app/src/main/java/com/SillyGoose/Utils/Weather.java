package com.SillyGoose.Utils;

import com.SillyGoose.Model.Status;
import com.show.api.ShowApiRequest;

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


    public static Weather getCurrWeather() {
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
                final String res=new ShowApiRequest( "http://route.showapi.com/9-5", appid, secret)
                        .addTextPara("from", "5")
                        .addTextPara("lng", String.valueOf(Status.getLongitude()))
                        .addTextPara("lat", String.valueOf(Status.getLatitude()))
                        .addTextPara("needMoreDay", "0")
                        .addTextPara("needIndex", "0")
                        .addTextPara("needHourData", "0")
                        .addTextPara("need3HourForcast", "0")
                        .addTextPara("needAlarm", "0")
                        .post();

                System.out.println(res);
            }
        });
    }

    public static JSONObject getData() {
        return data;
    }

    public static void setData(JSONObject data) {
        Weather.data = data;
    }
}