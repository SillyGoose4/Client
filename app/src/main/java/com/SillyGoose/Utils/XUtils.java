package com.SillyGoose.Utils;

import android.app.Application;
import android.media.MediaPlayer;

import com.SillyGoose.Activity.R;
import com.mob.MobSDK;

import org.xutils.x;


/**
 *
 * Init Android Application
 * Created by wangj on 4/2/2018.
 */

public class XUtils extends Application {
    private MediaPlayer mp=new MediaPlayer();
    public void onCreate(){

        mp=MediaPlayer.create(this, R.raw.goose);
        mp.start();
        mp.setLooping(true);
        super.onCreate();
        x.Ext.init(this);
        MobSDK.init(this,"250a858a8f300","d68d12a22c4e5d8e1f677f92bdc79062");
    }

}
