package com.SillyGoose.Model;

import com.SillyGoose.Utils.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

public class Goose {

    public Goose(){

    }
    public Goose(int userId,
                 int gooseDevil,
                 int gooseCloud,
                 int gooseEny,
                 int gooseMap,
                 int gooseRain,
                 int gooseSun,
                 int gooseWind,
                 int gooseStar){
        this.gooseDevil = gooseDevil;
        this.gooseEny = gooseEny;
        this.gooseMap = gooseMap;
        this.gooseRain = gooseRain;
        this.gooseCloud = gooseCloud;
        this.gooseStar = gooseStar;
        this.gooseWind = gooseWind;
        this.userId = userId;
        this.gooseSun = gooseSun;
    }

    public Goose(int userId){
        this.userId = userId;
    }

    public Goose(JSONObject msg) throws JSONException {
        this.gooseCloud=msg.getInt("gooseCloud");
        this.userId=msg.getInt("userId");
        this.gooseRain=msg.getInt("gooseRain");
        this.gooseSun=msg.getInt("gooseSun");
        this.gooseWind=msg.getInt("gooseWind");
        this.gooseDevil=msg.getInt("gooseDevil");
        this.gooseEny=msg.getInt("gooseEny");
        this.gooseStar=msg.getInt("gooseStar");
    }

    public void InitGoose(){
        this.gooseDevil = 0;
        this.gooseEny = 0;
        this.gooseMap = 0;
        this.gooseRain = 0;
        this.gooseCloud = 0;
        this.gooseStar = 0;
        this.gooseWind = 0;
        this.gooseSun = 0;
    }
    private int userId;

    private int gooseEny;

    private int gooseMap;

    private int gooseSun;

    private int gooseCloud;

    private int gooseStar;

    private int gooseWind;

    private int gooseRain;

    private int gooseDevil;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGooseEny() {
        return gooseEny;
    }

    public void setGooseEny(int gooseEny) {
        this.gooseEny = gooseEny;
    }

    public int getGooseMap() {
        return gooseMap;
    }

    public void setGooseMap(int gooseMap) {
        this.gooseMap = gooseMap;
    }

    public int getGooseSun() {
        return gooseSun;
    }

    public void setGooseSun(int gooseSun) {
        this.gooseSun = gooseSun;
    }

    public int getGooseCloud() {
        return gooseCloud;
    }

    public void setGooseCloud(int gooseCloud) {
        this.gooseCloud = gooseCloud;
    }

    public int getGooseStar() {
        return gooseStar;
    }

    public void setGooseStar(int gooseStar) {
        this.gooseStar = gooseStar;
    }

    public int getGooseWind() {
        return gooseWind;
    }

    public void setGooseWind(int gooseWind) {
        this.gooseWind = gooseWind;
    }

    public int getGooseRain() {
        return gooseRain;
    }

    public void setGooseRain(int gooseRain) {
        this.gooseRain = gooseRain;
    }

    public int getGooseDevil() {
        return gooseDevil;
    }

    public void setGooseDevil(int gooseDevil) {
        this.gooseDevil = gooseDevil;
    }

    /**
     * 计算时间差
     * @param fisttime 第一次时间
     * @return 两次是时间差值（分钟）
     */
    private long getΔt(Date fisttime,Date lasttime){
        // 智商压制
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // int deltaMin = 0;
        // String current = simpleDateFormat.format(lasttime);
        // String fistTime = simpleDateFormat.format(fisttime);
        // int Δyear = int.valueOf(current.substring(0,3)) - int.valueOf(fistTime.substring(0,3));
        // int Δmouth = int.valueOf(current.substring(5,6)) - int.valueOf(fistTime.substring(5,6));
        // int Δday = int.valueOf(current.substring(8,9)) - int.valueOf(fistTime.substring(8,9));
        // int Δhour = int.valueOf(current.substring(11,12)) - int.valueOf(fistTime.substring(11,12));
        // int Δmin = int.valueOf(current.substring(13,14)) - int.valueOf(fistTime.substring(13,14));
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

        long diff = lasttime.getTime() - fisttime.getTime();
        long day = diff / nd;
        long hour = diff / nh;
        long min = diff / nm;

        return min;
    }


    /**
     * 初始化
     * 获取上次收集和这次收集的时间间隔，再通过获取间隔时间的天气获取收集数
     */
    public void initialize(){
        //this.initialize();
        Date current = new Date(System.currentTimeMillis());
        Calendar month = Calendar.getInstance();
        int MONTH = month.get(Calendar.MONTH) + 1;
        int YEAR = month.get(Calendar.YEAR);
        int DAY = month.get(Calendar.DAY_OF_MONTH);
        int HOUR = month.get(Calendar.HOUR_OF_DAY);
        long rain = getΔt(Status.getCollectTime().getRainLasttime(), current);
        long star = getΔt(Status.getCollectTime().getStarLasttime(), current);
        long sun  = getΔt(Status.getCollectTime().getSunLasttime(),  current);
        long wind = getΔt(Status.getCollectTime().getWindLasttime(), current);
        long devil= getΔt(Status.getCollectTime().getDevilLasttime(),current);
        long cloud= getΔt(Status.getCollectTime().getCloudLasttime(),current);
        long max = rain;
        if(star > max) max = star;
        if(sun  > max) max = sun;
        if(wind > max) max = wind;
        if(devil> max) max = devil;
        if(cloud> max) max = cloud;
        System.out.println("Δt:\tStar\t: " + star+"\n" +
                              "\tSun\t: " + sun +"\n" +
                              "\tWind\t: " + wind + "\n" +
                              "\tDevil\t: "+ devil +"\n" +
                              "\tCloud\t: "+cloud+"\n" +
                              "\tRain\t: "+rain);
        int dd = (int) (max / 1440);
        int dh = (int) ((max % 1440) / 60);
        int dm = (int) ((max % 1440) % 60);
        String m = "" + YEAR + (MONTH > 10 ? MONTH : "0"+MONTH);
        Weather.getHistoryWeather(m,dd,dh);
        if((max / 60) >= HOUR) {
            System.out.println("Month is " + m);
            System.out.println("相隔 " + dd + " 天 " + dh + " 小时 "+ dm + " 分钟");
        }
    }
}