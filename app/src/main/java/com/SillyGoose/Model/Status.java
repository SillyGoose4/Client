package com.SillyGoose.Model;

import java.util.List;

/**
 * 描述当前状态类 单例模式
 * 注意线程同步
 * Created by wangj on 2018/5/29.
 */

public class Status {
    private static Status status;

    private static boolean isSignIn = false;

    private static User user;

    private static CollectTime collectTime;

    private static Goose goose ;

    private static List<Album> albumList;

    private static double latitude;//纬度

    private static double longitude;//经度

    private static boolean isTrip = false;

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

    public static CollectTime getCollectTime() {
        return collectTime;
    }

    public static Goose getGoose() {
        return goose;
    }

    public static User getUser() {
        return user;
    }

    public static List<Album> getAlbumList() {
        return albumList;
    }

    public static void setAlbumList(List<Album> albumList) {
        Status.albumList = albumList;
    }

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setCollectTime(CollectTime collectTime) {
        Status.collectTime = collectTime;
    }

    public static void setUser(User user) {
        Status.user = user;
    }

    public static void setLatitude(double latitude) {
        Status.latitude = latitude;
    }

    public static void setLongitude(double longitude) {
        Status.longitude = longitude;
    }

    public static boolean isIsTrip() {
        return isTrip;
    }

    public static void setIsTrip(boolean isTrip) {
        Status.isTrip = isTrip;
    }

    /**
     * 设置上次收集与当前之间的已收集的物品
     */
    public static void setGoose(int eny) {
        goose = new Goose();
        //goose.getΔt(Status.getUser().getLastSignIn());
        goose.initialize();
        goose.setGooseEny(eny);
        goose.setGooseCloud(100);
        goose.setGooseDevil(100);
        goose.setGooseRain(100);
        goose.setGooseStar(100);
        goose.setGooseSun(100);
    }
}
