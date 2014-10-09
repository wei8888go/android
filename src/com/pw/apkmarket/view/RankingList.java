package com.pw.apkmarket.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.adapter.ViewPagerAdapter;
import com.pw.apkmarket.helper.SearchHelper;
import com.pw.apkmarket.widgets.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 2014/9/17.
 */
public class RankingList extends Activity {

    @ViewInject(R.id.ranking_list_vp)
    private ViewPager vp;
    private List<View> list;
    private XListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_list_layout);
        ViewUtils.inject(this);

        list = new ArrayList<View>();
        lv = new XListView(this);
        SearchHelper.getInstance().Init(this, lv, "热门软件");
        for(int i = 0; i < 3; ++i){
            list.add(lv);
        }
        vp.setAdapter(new ViewPagerAdapter(list));
    }
}