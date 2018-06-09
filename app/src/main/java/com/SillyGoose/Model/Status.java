package com.SillyGoose.Model;

import com.SillyGoose.Utils.Weather;

/**
 * 描述当前状态类 单例模式
 * Created by wangj on 2018/5/29.
 */

public class Status {
    private static Status status;

    private static boolean isSignIn = false;

    private static User user;

    private static CollectTime collectTime;

    private static Goose goose;

    private static Album album;

    private static Weather weather;

    private static double latitude;//纬度

    private static double longitude;//经度

    public static Status getStatus() {
        if(status == null){
            synchronized (Status.class){
                status = new Status();
            }
        }
        return status;
    }

    /* init */
    private Status(){

    }

    public static boolean isIsSignIn() {
        return isSignIn;
    }

    public static void setIsSignIn(boolean isSignIn) {
        Status.isSignIn = isSignIn;
    }

    public static Album getAlbum() {
        return album;
    }

    public static CollectTime getCollectTime() {
        return collectTime;
    }

    public static Goose getGoose() {
        return goose;
    }

    public static User getUser() {
        return user;
    }

    public static Weather getWeather() {
        return weather;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setAlbum(Album album) {
        Status.album = album;
    }

    public static void setCollectTime(CollectTime collectTime) {
        Status.collectTime = collectTime;
    }

    public static void setGoose(Goose goose) {
        Status.goose = goose;
    }

    public static void setStatus(Status status) {
        Status.status = status;
    }

    public static void setUser(User user) {
        Status.user = user;
    }

    public static void setWeather(Weather weather) {
        Status.weather = weather;
    }

    public static void setLatitude(double latitude) {
        Status.latitude = latitude;
    }

    public static void setLongitude(double longitude) {
        Status.longitude = longitude;
    }
}
