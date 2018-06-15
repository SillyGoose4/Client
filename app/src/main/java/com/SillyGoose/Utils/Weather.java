package com.SillyGoose.Utils;

import com.show.api.ShowApiRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    // 速率表(单位/h,后面的是 min/单位)
    private static Map<String,Integer> rateList = new LinkedHashMap<String, Integer>();
    // 上限
    private static int LIMIT = 60;
    //城市代码
    public static int CITY_CODE ;
    //天气
    public static String WEATHER;
    //风单独考虑
    public static boolean ISWIND = false;
    //是否是白天
    public static boolean ISDAY = false;

    private Weather(){
        rateList.put("Sun",20);
        rateList.put("Cloud",15);
        rateList.put("Wind",15);
        rateList.put("Star",20);
        rateList.put("Rain",10);
        rateList.put("Devil",5);
        rateList.put("SunMIN",3);
        rateList.put("CloudMIN",4);
        rateList.put("WindMIN",6);
        rateList.put("StarMIN",3);
        rateList.put("RainMIN",6);
        rateList.put("DevilMIN",12);
    }


    public static Weather getCurrentWeather() {
        if(CurrentWeather == null){
            synchronized (Weather.class){
                CurrentWeather = new Weather();
            }
        }
        return CurrentWeather;
    }

    /**
     * 获取今天天气
     * 包含从凌晨到当前时间最近状态的天气（每半小时）
     * 包含未来两天天气简报
     *
     */
    public static void getWeather(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String appid="66782";//要替换成自己的
                String secret="ec285e3435a64398bf26781b0bc054d4";//要替换成自己的
                final String res=new ShowApiRequest( "http://route.showapi.com/9-4", appid, secret)
                        .addTextPara("needMoreDay", "0")
                        .addTextPara("needIndex", "0")
                        .addTextPara("needHourData", "1")
                        .addTextPara("need3HourForcast", "0")
                        .addTextPara("needAlarm", "0")
                        .post();
                try {
                    //Log.d("weather:",res);
                    //System.out.println(res);
                    //在此解析weatherData
                    JSONObject weatherData=new JSONObject(res);
                    setData(weatherData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void setData(JSONObject data) {
                Weather.data = data;
                try {
                    JSONObject city =data.getJSONObject("showapi_res_body").getJSONObject("cityInfo");
                    JSONObject now =data.getJSONObject("showapi_res_body").getJSONObject("now");
                    JSONObject f1 = data.getJSONObject("showapi_res_body").getJSONObject("f1");
                    CITY_CODE = city.getInt("c1");
                    System.out.println("CityCode :"+CITY_CODE);
                    System.out.println("当前天气 :"+now.get("weather")
                            +"\t "+now.get("wind_direction")
                            +"\t 风力 :"+now.get("wind_power"));
                    System.out.println("日出/落时间 : " + f1.getString("sun_begin_end"));
                    int hourbegin = Integer.valueOf(f1.getString("sun_begin_end").substring(0,2));
                    int hourend = Integer.valueOf(f1.getString("sun_begin_end").substring(6,8));
                    int minbegin = Integer.valueOf(f1.getString("sun_begin_end").substring(3,5));
                    int minend = Integer.valueOf(f1.getString("sun_begin_end").substring(9,11));
                    // SET Day or night
                    Calendar calendar = Calendar.getInstance();
                    System.out.println("x现在时间\t:\t"+calendar);
                    if(calendar.get(Calendar.HOUR_OF_DAY) > hourbegin && calendar.get(Calendar.HOUR_OF_DAY) < hourend){
                        ISDAY = true;
                    }else if(calendar.get(Calendar.HOUR_OF_DAY) == hourbegin){
                        if(calendar.get(Calendar.MINUTE) >= minbegin){
                            ISDAY = true;
                        }else{
                            ISDAY = false;
                        }
                    }else if(calendar.get(Calendar.HOUR_OF_DAY) == hourend) {
                        if(calendar.get(Calendar.MINUTE) <= minbegin){
                            ISDAY = true;
                        }else{
                            ISDAY = false;
                        }
                    }else{
                        ISDAY = false;
                    }
                    // Set WEATHER
                    String wind_p = now.getString("wind_power");
                    if(Integer.valueOf(wind_p.substring(0,wind_p.length()-1)) >= 4){
                        ISWIND = true;
                    }else if(now.get("weather").equals("多云")){
                        WEATHER = "Cloud";
                    }else if(now.get("weather").equals("晴")){
                        if(ISDAY) {
                            WEATHER = "Sun";
                        }else{
                            WEATHER = "Star";
                        }
                    }else if(now.getString("weather").contains("雨")){
                        WEATHER = "Rain";
                    }else{
                        WEATHER = "Devil";
                    }
                    //
                    if(ISDAY) {
                        System.out.println("白天");
                    }else{
                        System.out.println("黑夜");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    public static List<String> getHistoryWeather(final String Month, int dayS){
        String result = "";
        List<String> weatherlist = new ArrayList<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String appid="66782";//要替换成自己的
                String secret="ec285e3435a64398bf26781b0bc054d4";//要替换成自己的
                final String res=new ShowApiRequest( "http://route.showapi.com/9-7", appid, secret)
                        .addTextPara("areaid",""+CITY_CODE)
                        .addTextPara("area","")
                        .addTextPara("month",Month)
                        .post();
                try {
                    //Log.d("weather:",res);
                    //System.out.println(res);
                    //在此解析weatherData
                    JSONObject weatherData=new JSONObject(res);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return weatherlist;
    }

    public static JSONObject getData() {
        return data;
    }
}