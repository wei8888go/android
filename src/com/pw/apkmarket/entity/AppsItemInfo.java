package com.pw.apkmarket.entity;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/16 23:24
 */
public class AppsItemInfo implements Serializable {
    public Drawable icon;
    public String name;
    public String packageName;
    public int versionCode;
    public String versionName;
    public String size;

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
