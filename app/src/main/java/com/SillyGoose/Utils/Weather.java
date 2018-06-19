package com.SillyGoose.Utils;

import com.SillyGoose.Model.Status;
import com.show.api.ShowApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.LinkedHashMap;
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
     * 获取天气
     * 包含从凌晨到当前时间最近状态的天气（每半小时）
     * 包含未来两天天气简报
     * 包含历史天气
     * @param dayS 相隔时间 天
     * @param dh 相隔时间 小时
     * @param Month 当前月
     */
    public static JSONObject getHistoryWeather(final String Month, final int dayS,final int dh){
        final String result = "";
        JSONObject res = null;
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
                    setData(weatherData,dh);
                    if(dayS >= 1) {
                        getHist(Month, dayS);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            public void setData(JSONObject data,int dh) {
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
                    setDayOrNight(f1);
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
                    }else if(now.getString("weather").contains("阴")){
                        WEATHER = "Cloud";
                    }else{
                        WEATHER = "Devil";
                    }
                    //

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void setDayOrNight(JSONObject f1) throws JSONException {
                int hourbegin = Integer.valueOf(f1.getString("sun_begin_end").substring(0,2));
                int hourend = Integer.valueOf(f1.getString("sun_begin_end").substring(6,8));
                int minbegin = Integer.valueOf(f1.getString("sun_begin_end").substring(3,5));
                int minend = Integer.valueOf(f1.getString("sun_begin_end").substring(9,11));
                // SET Day or night
                Calendar calendar = Calendar.getInstance();
                // 设置白天黑夜
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
                if(ISDAY) {
                    System.out.println("白天");
                }else{
                    System.out.println("黑夜");
                }
            }
            public void getHist(String Month, int dayS){
                String appid="66782";//要替换成自己的
                String secret="ec285e3435a64398bf26781b0bc054d4";//要替换成自己的
                System.out.println("获取 " + CITY_CODE + " 的 "+Month+" 月天气");
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
                    setList(weatherData.getJSONObject("showapi_res_body"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void setList(JSONObject data){
                try {
                    JSONArray jsonArray = data.getJSONArray("list");
                    JSONObject temp;
                    int sun   = 0;
                    int star  = 0;
                    int wind  = 0;
                    int cloud = 0;
                    int devil = 0;
                    int rain  = 0;
                    for(int i =0 ;i<dayS;i++){
                        temp = jsonArray.getJSONObject(i);
                        System.out.println(temp.getString("weather"));
                        if(temp.getString("weather").contains("晴")){
                            sun  += 600;
                            star += 600;
                        }
                        if(temp.getString("weather").contains("雨") && (temp.getString("weather").contains("转") || temp.getString("weather").contains("阵雨"))){
                            rain += 150;
                        }
                        if(temp.getString("weather").contains("中雨")||temp.getString("weather").contains("大雨")){
                            rain += 600;
                        }
                        if(temp.getString("weather").contains("多云")){
                            cloud +=600;
                        }
                        if(temp.getString("wind_power").contains("4") || temp.getString("wind_power").contains("大风")){
                            wind +=600;
                        }
                        else devil += 100;
                    }
                    if(sun >= 600) sun =600;
                    if(wind >= 600) wind =600;
                    if(cloud >= 600) cloud =600;
                    if(star >= 600) star =600;
                    if(devil >= 600) devil =600;
                    if(rain >= 600) rain =600;
                    Status.getGoose().setGooseRain(rain);
                    Status.getGoose().setGooseStar(star);
                    Status.getGoose().setGooseCloud(cloud);
                    Status.getGoose().setGooseDevil(devil);
                    Status.getGoose().setGooseWind(wind);
                    Status.getGoose().setGooseSun(sun);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return res;
    }

    public static JSONObject getData() {
        return data;
    }
}