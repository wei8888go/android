package com.pw.apkmarket.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import com.pw.apkmarket.R;
import com.pw.apkmarket.test.Test;
import com.pw.apkmarket.utils.IntentUtil;

public class Splash extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtil.start(Splash.this, Main.class);
                finish();
            }
        }, 1000 * 2);
    }
}
