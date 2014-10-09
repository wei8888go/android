package com.pw.apkmarket.widgets;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.app.MyApplication;
import com.pw.apkmarket.utils.L;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/16 10:12
 */
public class ImageSlide extends LinearLayout {

    private ViewPager viewPager;
    private LinearLayout layout;
    private ImageView[] imgs;

    private PagerAdapter adapter;
    private Context context;
    private int selectedItem = 0;

    public ImageSlide(Context context) {
        super(context);
        this.context = context;
    }

    public ImageSlide(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    private void init() {
        View container = LayoutInflater.from(context).inflate(R.layout.imageslide_layout, null, false);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        this.addView(container, param);
        viewPager = (ViewPager) container.findViewById(R.id.recommand_viewpage);
        layout = (LinearLayout) container.findViewById(R.id.imageslide_dot_layout);
        int count = adapter.getCount();
        imgs = new ImageView[count];
        for (int i = 0; i < count; ++i) {
            imgs[i] = new ImageView(context);
            imgs[i].setImageResource(R.drawable.dot_selector);
            imgs[i].setPadding(8, 0, 8, 10);
            imgs[i].setEnabled(false);
            layout.addView(imgs[i]);
        }
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(selectedItem);
        imgs[selectedItem].setEnabled(true);
        viewPager.setOnPageChangeListener(listener);
    }

    public void setAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
        init();
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i2) {

        }

        @Override
        public void onPageSelected(int i) {
            for(ImageView img : imgs){
                img.setEnabled(false);
            }
            imgs[i].setEnabled(true);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
