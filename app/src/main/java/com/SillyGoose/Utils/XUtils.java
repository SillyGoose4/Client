package com.SillyGoose.Utils;

import android.app.Application;

import com.mob.MobSDK;

import org.xutils.x;

/**
 *
 * Init Android Application
 * Created by wangj on 4/2/2018.
 */

public class XUtils extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        x.Ext.init(this);
        MobSDK.init(this,"250a858a8f300","d68d12a22c4e5d8e1f677f92bdc79062");
    }

}
