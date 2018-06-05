package com.SillyGoose.Utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wangj on 2018/5/29.
 */

public class ActivityUnits {

    private static ActivityUnits activityUnits;

    private static Stack<Activity> activityStack;

    private ActivityUnits(){
    }

    public static ActivityUnits getInstance(){
        if(activityUnits == null){
            synchronized (ActivityUnits.class){
                activityUnits = new ActivityUnits();
                activityStack = new Stack<Activity>();
            }
        }
        return  activityUnits;
    }



}
