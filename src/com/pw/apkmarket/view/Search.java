package com.pw.apkmarket.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import android.widget.SearchView;
import com.pw.apkmarket.helper.SearchHelper;
import com.pw.apkmarket.widgets.KeywordsFlow;

import java.util.Random;

/**
 * Created by pengwei on 2014/9/16.
 */
public class Search extends Activity {

    public String[] keywords = {
            "QQ", "小黄人", "记事本", "360", "闹钟",
            "短信", "桌面精灵", "去吧皮卡去", "美食", "育儿",
            "笑话", "笔记本", "驾照考试", "少女时代", "捕鱼达人",
            "内存清理", "地图", "导航", "闹钟", "主题",
            "通讯录", "播放器", "爱情公寓", "安全", "3D",
            "美女", "天气", "亲子", "工作", "学习",
            "欧朋", "浏览器", "愤怒的小鸟", "奥特曼", "网易公开课",
            "iciba", "刀塔传奇", "网游App", "互联网", "365日历",
            "脸部识别", "Chrome", "Safari", "中国版Siri", "A5处理器",
            "iPhone4S", "摩托 ME525", "魅族MX4", "尼康 S2500"
    };

    @ViewInject(R.id.search_cloud)
    private KeywordsFlow keywordsFlow;
    private EditText searchBox;
    @ViewInject(R.id.search_data_panel)
    private LinearLayout dataPanel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        ViewUtils.inject(this);
        setActionBarLayout(R.layout.search_searchbox);

        keywordsFlow.setDuration(800l);
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
        keywordsFlow.setOnTouchListener(touchListener);
        keywordsFlow.setOnItemClickListener(clickListener);

        if(searchBox != null)
            searchBox.addTextChangedListener(textWatcher);
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_UP) {
                keywordsFlow.rubKeywords();
                // keywordsFlow.rubAllViews();
                feedKeywordsFlow(keywordsFlow, keywords);
                keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
            } else {
                keywordsFlow.rubKeywords();
                feedKeywordsFlow(keywordsFlow, keywords);
                keywordsFlow.go2Show(KeywordsFlow.ANIMATION_IN);
            }
            return false;
        }
    };

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            String key = charSequence.toString();
            if (key == null || key.length() == 0) {
                dataPanel.setVisibility(View.GONE);
            } else {
                dataPanel.setVisibility(View.VISIBLE);
                SearchHelper.getInstance().Init(Search.this, key);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < KeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view instanceof TextView) {
                String keyword = ((TextView) view).getText().toString();
                searchBox.setText(keyword);
            }
        }
    };

    private void setActionBarLayout(int layoutId) {
        ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(layoutId, null);
            searchBox = (EditText) v.findViewById(R.id.search_searchBox_edit);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(v, layout);
        }
    }
}