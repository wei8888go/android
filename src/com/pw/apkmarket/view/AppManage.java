package com.pw.apkmarket.view;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.adapter.AppManageAdapter;
import com.pw.apkmarket.entity.AppsItemInfo;
import com.pw.apkmarket.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 2014/9/16.
 */
public class AppManage extends Activity {

    private List<AppsItemInfo> list;
    private AppManageAdapter adapter;
    @ViewInject(R.id.app_manage_lv)
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_manage_layout);
        ViewUtils.inject(this);
        list = getAllApp();
        adapter = new AppManageAdapter(this, list);
        lv.setAdapter(adapter);
    }

    public List<AppsItemInfo> getAllApp() {
        List<AppsItemInfo> apps = new ArrayList<AppsItemInfo>();
        PackageManager packageManager = getPackageManager();
        List<PackageInfo> packList = packageManager.getInstalledPackages(0);
        for (PackageInfo info : packList) {
            if ((info.applicationInfo.flags & info.applicationInfo.FLAG_SYSTEM) <= 0) {
                AppsItemInfo item = new AppsItemInfo();
                item.setIcon(packageManager.getApplicationIcon(info.applicationInfo));
                item.setName(packageManager.getApplicationLabel(info.applicationInfo).toString());
                item.setPackageName(info.packageName);
                item.setVersionCode(info.versionCode);
                item.setVersionName(info.versionName);
                item.setSize("");
                apps.add(item);
            }
        }
        return apps;
    }
}