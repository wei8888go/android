package com.pw.apkmarket.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.utils.IntentUtil;
import com.pw.apkmarket.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengwei on 2014/9/17.
 */
public class Setting extends Activity {

    @ViewInject(R.id.setting_parent)
    private LinearLayout parent;
    private List<LinearLayout> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ViewUtils.inject(this);
        list = new ArrayList<LinearLayout>();
        int count = parent.getChildCount();
        for(int i = 0; i < count; ++i){
            View v = parent.getChildAt(i);
            if(v instanceof LinearLayout){
                v.setOnClickListener(listener);
                list.add((LinearLayout)v);
            }
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View view) {
            IntentUtil.start(Setting.this, AppManage.class);
        }
    };
}