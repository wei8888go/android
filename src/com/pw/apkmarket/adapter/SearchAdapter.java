package com.pw.apkmarket.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.pw.apkmarket.R;
import com.pw.apkmarket.entity.AppInfo;
import com.pw.apkmarket.helper.RankHelper;
import com.pw.apkmarket.utils.IntentUtil;
import com.pw.apkmarket.utils.T;
import com.pw.apkmarket.utils.ViewHolder;
import com.pw.apkmarket.view.AppContent;
import com.pw.apkmarket.view.Main;

import java.math.BigDecimal;
import java.util.List;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/15 10:57
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private List<AppInfo> list;
    private BitmapUtils bmUtil;

    public SearchAdapter(Context context, List<AppInfo> list) {
        this.context = context;
        this.list = list;
        bmUtil = new BitmapUtils(context);
        bmUtil.configDefaultLoadingImage(R.drawable.app_default_icon);
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.search_lv_item, viewGroup, false);
        }
        ImageView icon = ViewHolder.get(view, R.id.search_lv_item_icon);
        TextView name = ViewHolder.get(view, R.id.search_lv_item_name);
        ImageView rank = ViewHolder.get(view, R.id.search_lv_item_rank);
        TextView size = ViewHolder.get(view, R.id.search_lv_item_size);
        TextView btn = ViewHolder.get(view, R.id.search_lv_item_downbtn);
        TextView summary = ViewHolder.get(view, R.id.search_lv_item_summary);

        AppInfo app = list.get(i);
        bmUtil.display(icon, app.icon);
        name.setText(app.name);
        int r = new BigDecimal(Float.parseFloat(app.rank)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        rank.setImageResource(RankHelper.getRankResId(r));
        String sizeValue = String.format(context.getResources().getString(R.string.search_lv_item_size),
                app.downNum, app.size);
        size.setText(sizeValue);
        btn.setOnClickListener(new OnDownloadListener(app.downUrl));
        summary.setText(Html.fromHtml(app.summary));
        return view;
    }

    class OnDownloadListener implements View.OnClickListener {
        private String downUrl;

        public OnDownloadListener(String downUrl) {
            this.downUrl = downUrl;
        }

        public void onClick(View view) {
            T.showLong(context, "123456");
        }
    }


}

