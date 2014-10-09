package com.pw.apkmarket.view;

import android.app.Activity;
import android.os.Bundle;
import com.lidroid.xutils.ViewUtils;
import com.pw.apkmarket.R;

/**
 * Created by pengwei on 2014/9/17.
 */
public class Classification extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classification_layout);
        ViewUtils.inject(this);
    }
}