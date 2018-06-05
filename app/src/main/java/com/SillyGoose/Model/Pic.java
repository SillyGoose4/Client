package com.SillyGoose.Model;

public class Pic {
    private Integer picId;

    private String picUrl;

    private Integer picBelong;

    private Integer picLevel;

    private String picDescribe;

    public String getPicDescribe() {
        return picDescribe;
    }

    public void setPicDescribe(String picDescribe) {
        this.picDescribe = picDescribe;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public Integer getPicBelong() {
        return picBelong;
    }

    public void setPicBelong(Integer picBelong) {
        this.picBelong = picBelong;
    }

    public Integer getPicLevel() {
        return picLevel;
    }

    public void setPicLevel(Integer picLevel) {
        this.picLevel = picLevel;
    }
}