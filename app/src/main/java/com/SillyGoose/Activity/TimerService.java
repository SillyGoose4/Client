package com.SillyGoose.Activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.SillyGoose.Model.Status;
import com.SillyGoose.Utils.Weather;

import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    public TimerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        //同步线程
        while(Weather.WEATHER == null){
        }
            switch (Weather.WEATHER) {
                case "Cloud":
                    Timer cloudtimer = new Timer();
                    cloudtimer.schedule(cloudAutoIncreaseTask, 240 * 1000, 240 * 1000);
                    break;
                case "Rain":
                    Timer raindtimer = new Timer();
                    raindtimer.schedule(rainAutoIncreaseTask, 360 * 1000, 360 * 1000);
                    break;
                case "Sun":
                    Timer suntimer = new Timer();
                    suntimer.schedule(sunAutoIncreaseTask, 180 * 1000, 180 * 1000);
                    break;
                case "Star":
                    Timer startimer = new Timer();
                    startimer.schedule(starAutoIncreaseTask, 180 * 1000, 180 * 1000);
                    break;
                case "Devil":
                    Timer deviltimer = new Timer();
                    deviltimer.schedule(devilAutoIncreaseTask, 720 * 1000, 720 * 1000);
                    break;
                default:
                    break;
            }
            super.onCreate();
            if (Weather.ISWIND) {
                Timer windtimer = new Timer();
                windtimer.schedule(windAutoIncreaseTask, 360 * 1000, 360 * 1000);
            }

    }

    TimerTask cloudAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseCloud(Status.getGoose().getGooseCloud() + 1);
            System.out.println("Cloud Increase! Current Cloud is"+Status.getGoose().getGooseCloud());
            if(Status.getGoose().getGooseCloud() >= 600 && Status.isIsSignIn()){
                cancel();
            }
        }
    };
    TimerTask windAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseWind(Status.getGoose().getGooseWind() + 1);
            System.out.println("Wind Increase! Current Wind is"+Status.getGoose().getGooseWind());
            if(Status.getGoose().getGooseWind() >= 600){
                cancel();
            }
        }
    };
    TimerTask rainAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseRain(Status.getGoose().getGooseRain() + 1);
            System.out.println("Rain Increase! Current Rain is"+Status.getGoose().getGooseRain());
            if(Status.getGoose().getGooseRain() >= 600){
                cancel();
            }
        }
    };
    TimerTask sunAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseSun(Status.getGoose().getGooseSun() + 1);
            System.out.println("Sun Increase! Current Sun is"+Status.getGoose().getGooseSun());
            if(Status.getGoose().getGooseSun() >= 600){
                cancel();
            }
        }
    };
    TimerTask devilAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseWind(Status.getGoose().getGooseWind() + 1);
            System.out.println("Devil Increase! Current Devil is"+Status.getGoose().getGooseDevil());
            if(Status.getGoose().getGooseDevil() >= 600){
                cancel();
            }
        }
    };
    TimerTask starAutoIncreaseTask = new TimerTask() {
        @Override
        public void run() {
            Status.getGoose().setGooseStar(Status.getGoose().getGooseStar() + 1);
            System.out.println("Star Increase! Current Star is"+Status.getGoose().getGooseStar());
            if(Status.getGoose().getGooseStar() >= 600){
                cancel();
            }
        }
    };
}
