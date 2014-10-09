package com.pw.apkmarket.entity;

import java.io.Serializable;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/15 10:49
 */
public class AppInfo implements Serializable {

    public String id;  //appId
    public String name;  //名字
    public String icon;  //图标
    public String rank;  //评分
    public String size;  //安装包大小
    public String summary; //描述
    public String downNum;  //下载量
    public String downUrl; //下载地址

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDownNum(String downNum) {
        this.downNum = downNum;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
}
