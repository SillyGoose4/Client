package com.SillyGoose.Utils;

import android.app.Application;
import android.content.Intent;

import com.SillyGoose.Activity.BgmService;
import com.SillyGoose.Model.Status;
import com.mob.MobSDK;

import org.xutils.x;

/**
 *
 * Init Android Application
 * Created by wangj on 4/2/2018.
 */

public class StartUtils extends Application {



    public void onCreate(){

        super.onCreate();
        /*  init Mob SDK    */
        x.Ext.init(this);
        MobSDK.init(this,"250a858a8f300","d68d12a22c4e5d8e1f677f92bdc79062");

        Intent bgmIntent = new Intent(StartUtils.this, BgmService.class);
        startService(bgmIntent);

        Status status=Status.getStatus();
    }

}