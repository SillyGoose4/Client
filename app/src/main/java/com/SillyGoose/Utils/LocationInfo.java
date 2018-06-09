package com.SillyGoose.Utils;

import android.util.Log;

import com.SillyGoose.Model.Status;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

/**
 *
 * Created by wangj on 2018/6/7.
 */

public class LocationInfo extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location){
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = location.getLatitude();    //获取纬度信息
        double longitude = location.getLongitude();    //获取经度信息
        float radius = location.getRadius();    //获取定位精度，默认值为0.0f

        String coorType = location.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        Status.setLatitude(latitude);
        Status.setLongitude(longitude);

        int errorCode = location.getLocType();
        //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        String res = "";
        res +="\n             latitude:     "+latitude;
        res +="\n             longitude:    "+longitude;
        res +="\n             coorType:     "+coorType;
        Log.d("Location :",res);
    }
}
