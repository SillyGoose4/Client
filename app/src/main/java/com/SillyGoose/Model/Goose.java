package com.SillyGoose.Model;

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
}