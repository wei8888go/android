package com.pw.apkmarket.helper;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/17 13:59
 */
public class LocationHelper {

    /**
     * 获取当前位置
     * @param context
     * @return
     */
    public static Location getLocation(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);  //高精度
        criteria.setAltitudeRequired(false);  //不求海拔
        criteria.setBearingRequired(false); //不要求方位
        criteria.setCostAllowed(true);  //允许有方位
        criteria.setPowerRequirement(Criteria.POWER_LOW); //低功耗
        String provider = lm.getBestProvider(criteria, true);
        android.location.Location location = lm.getLastKnownLocation(provider);
        return location;
    }



}
