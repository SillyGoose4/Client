package com.SillyGoose.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CollectTime {

    public CollectTime(){

    }

    public CollectTime(int userId){
        this.userId = userId;
    }

    public CollectTime(JSONObject msg)  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            this.devilLasttime = sdf.parse(msg.getString("devilLasttime"));
            this.rainLasttime = sdf.parse(msg.getString("rainLasttime"));
            this.cloudLasttime = sdf.parse(msg.getString("cloudLasttime"));
            this.starLasttime = sdf.parse(msg.getString("starLasttime"));
            this.windLasttime = sdf.parse(msg.getString("windLasttime"));
            this.sunLasttime = sdf.parse(msg.getString("sunLasttime"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void InitTime(Date currentDate){
        this.devilLasttime = currentDate;
        this.rainLasttime = currentDate;
        this.cloudLasttime = currentDate;
        this.starLasttime = currentDate;
        this.windLasttime = currentDate;
        this.sunLasttime = currentDate;
    }

    private Integer userId;

    private Date sunLasttime;

    private Date cloudLasttime;

    private Date rainLasttime;

    private Date devilLasttime;

    private Date windLasttime;

    private Date starLasttime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSunLasttime() {
        return sunLasttime;
    }

    public void setSunLasttime(Date sunLasttime) {
        this.sunLasttime = sunLasttime;
    }

    public Date getCloudLasttime() {
        return cloudLasttime;
    }

    public void setCloudLasttime(Date cloudLasttime) {
        this.cloudLasttime = cloudLasttime;
    }

    public Date getRainLasttime() {
        return rainLasttime;
    }

    public void setRainLasttime(Date rainLasttime) {
        this.rainLasttime = rainLasttime;
    }

    public Date getDevilLasttime() {
        return devilLasttime;
    }

    public void setDevilLasttime(Date devilLasttime) {
        this.devilLasttime = devilLasttime;
    }

    public Date getWindLasttime() {
        return windLasttime;
    }

    public void setWindLasttime(Date windLasttime) {
        this.windLasttime = windLasttime;
    }

    public Date getStarLasttime() {
        return starLasttime;
    }

    public void setStarLasttime(Date starLasttime) {
        this.starLasttime = starLasttime;
    }

    public JSONObject toJson(){
        JSONObject res = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            res.put("userId",this.getUserId().toString());
            res.put("cloudLasttime",simpleDateFormat.parse(this.getCloudLasttime().toString()));
            res.put("devilLasttime",simpleDateFormat.parse(this.getDevilLasttime().toString()));
            res.put("rainLasttime",simpleDateFormat.parse(this.getRainLasttime().toString()));
            res.put("starLasttime",simpleDateFormat.parse(this.getStarLasttime().toString()));
            res.put("sunLasttime",simpleDateFormat.parse(this.getSunLasttime().toString()));
            res.put("windLasttime",simpleDateFormat.parse(this.getWindLasttime().toString()));
            System.out.println(res.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return res;
    }
}