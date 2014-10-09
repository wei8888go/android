package com.pw.apkmarket.app;

import android.app.Application;

/**
 * FileName:HeartLover
 * Description:
 * Created by:pengwei
 * date:2014/9/13 0:00
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    public synchronized static MyApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }
}
