package com.pw.apkmarket.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pw.apkmarket.R;
import com.pw.apkmarket.entity.AppsItemInfo;
import com.pw.apkmarket.utils.ViewHolder;

import java.util.List;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/17 10:48
 */
public class AppManageAdapter extends BaseAdapter {

    private Context context;
    private List<AppsItemInfo> list;

    public AppManageAdapter(Context context, List<AppsItemInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.app_manage_item, viewGroup, false);
        }
        ImageView icon = ViewHolder.get(view, R.id.app_manage_item_icon);
        TextView name = ViewHolder.get(view, R.id.app_manage_item_name);
        TextView version = ViewHolder.get(view, R.id.app_manage_item_version);
        TextView open = ViewHolder.get(view, R.id.app_manage_item_open);
        TextView del = ViewHolder.get(view, R.id.app_manage_item_del);

       final  AppsItemInfo itemInfo = list.get(i);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = context.getPackageManager();
                Intent i = pm.getLaunchIntentForPackage(itemInfo.packageName);//获取启动的包名
                context.startActivity(i);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("package:"+itemInfo.packageName);//获取删除包名的URI
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setAction(Intent.ACTION_DELETE);
                intent.setData(uri);
                context.startActivity(intent);
            }
        });

        icon.setImageDrawable(itemInfo.icon);
        name.setText(itemInfo.name);
        version.setText(itemInfo.versionName);

        return view;
    }
}
