package com.pw.apkmarket.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.adapter.SearchAdapter;
import com.pw.apkmarket.adapter.ViewPagerAdapter;
import com.pw.apkmarket.app.Config;
import com.pw.apkmarket.entity.AppInfo;
import com.pw.apkmarket.helper.SearchHelper;
import com.pw.apkmarket.widgets.ImageSlide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 2014/9/15.
 */
public class Recommend extends Activity {

    private final static String API = Config.HOST + Config.PROJECT + "/SearchApi.php?key=";
    private List<AppInfo> list;
    private SearchAdapter adapter;

    @ViewInject(R.id.search_nav_lauout)
    private LinearLayout navLayout;
    private TextView[] nav;

    private ImageSlide imageSlide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_layout);
        ViewUtils.inject(this);

        int count = navLayout.getChildCount();
        nav = new TextView[count];
        for(int i = 0; i < count; ++i){
            nav[i] = (TextView)navLayout.getChildAt(i);
            nav[i].setTag(i);
            nav[i].setOnClickListener(nevListener);
        }
        nav[0].setEnabled(false);

        SearchHelper sh = SearchHelper.getInstance();
        sh.Init(this, "软件");

        List<View> list = new ArrayList<View>();
        for (int i = 0; i < 5; ++i) {
            View v = new ImageView(this);
            int res = 0;
            switch (i){
                case 0:
                    res = R.drawable.index_slide_1;
                    break;
                case 1:
                    res = R.drawable.index_slide_2;
                    break;
                case 2:
                    res = R.drawable.index_slide_3;
                    break;
                case 3:
                    res = R.drawable.index_slide_4;
                    break;
                case 4:
                    res = R.drawable.index_slide_5;
                    break;
                default:
                    res = R.drawable.index_slide_1;
            }
            v.setBackgroundResource(res);
            list.add(v);
        }
        View recommend_slide = LayoutInflater.from(this).inflate(R.layout.recomend_slide_lay, null, false);
        imageSlide = (ImageSlide) recommend_slide.findViewById(R.id.search_slide);
        imageSlide.setAdapter(new ViewPagerAdapter(list));
        sh.addHeader(recommend_slide);
    }


    private View.OnClickListener nevListener = new View.OnClickListener() {
        public void onClick(View view) {
           for(TextView tv : nav){
               tv.setEnabled(true);
           }
           nav[Integer.parseInt(view.getTag()+"")].setEnabled(false);
            SearchHelper sh = SearchHelper.getInstance();
            String tag = "";
            switch ((Integer)view.getTag()){
                case 0:
                    tag = "软件";
                    break;
                case 1:
                    tag = "游戏";
                    break;
                case 2:
                    tag = "360";
                    break;
                case 3:
                    tag = "QQ";
                    break;
                default:
                    tag = "软件";
            }
            sh.Init(Recommend.this, tag);
        }
    };
}