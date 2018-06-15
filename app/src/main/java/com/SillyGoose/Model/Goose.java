package com.SillyGoose.Model;

import com.SillyGoose.Utils.Weather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private Integer userId;

    private Integer gooseEny;

    private Integer gooseMap;

    private Integer gooseSun;

    private Integer gooseCloud;

    private Integer gooseStar;

    private Integer gooseWind;

    private Integer gooseRain;

    private Integer gooseDevil;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGooseEny() {
        return gooseEny;
    }

    public void setGooseEny(Integer gooseEny) {
        this.gooseEny = gooseEny;
    }

    public Integer getGooseMap() {
        return gooseMap;
    }

    public void setGooseMap(Integer gooseMap) {
        this.gooseMap = gooseMap;
    }

    public Integer getGooseSun() {
        return gooseSun;
    }

    public void setGooseSun(Integer gooseSun) {
        this.gooseSun = gooseSun;
    }

    public Integer getGooseCloud() {
        return gooseCloud;
    }

    public void setGooseCloud(Integer gooseCloud) {
        this.gooseCloud = gooseCloud;
    }

    public Integer getGooseStar() {
        return gooseStar;
    }

    public void setGooseStar(Integer gooseStar) {
        this.gooseStar = gooseStar;
    }

    public Integer getGooseWind() {
        return gooseWind;
    }

    public void setGooseWind(Integer gooseWind) {
        this.gooseWind = gooseWind;
    }

    public Integer getGooseRain() {
        return gooseRain;
    }

    public void setGooseRain(Integer gooseRain) {
        this.gooseRain = gooseRain;
    }

    public Integer getGooseDevil() {
        return gooseDevil;
    }

    public void setGooseDevil(Integer gooseDevil) {
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
        // int Δyear = Integer.valueOf(current.substring(0,3)) - Integer.valueOf(fistTime.substring(0,3));
        // int Δmouth = Integer.valueOf(current.substring(5,6)) - Integer.valueOf(fistTime.substring(5,6));
        // int Δday = Integer.valueOf(current.substring(8,9)) - Integer.valueOf(fistTime.substring(8,9));
        // int Δhour = Integer.valueOf(current.substring(11,12)) - Integer.valueOf(fistTime.substring(11,12));
        // int Δmin = Integer.valueOf(current.substring(13,14)) - Integer.valueOf(fistTime.substring(13,14));
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
        if((max/1440) >= 1) {
            String m = "" + YEAR + MONTH;
            System.out.println("Month is " + m);
            List<String> ls= Weather.getHistoryWeather(m,DAY);
            for (String s : ls){
                switch (s) {
                    case "Cloud":
                        break;
                    case "Wind":
                        break;
                    case "Sun":
                        break;
                    default:
                        break;
                }
            }
        }
    }
}