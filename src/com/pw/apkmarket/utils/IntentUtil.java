package com.pw.apkmarket.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;

/**
 * FileName:HeartLover
 * Description:
 * Created by:pengwei
 * date:2014/9/13 12:04
 */
public class IntentUtil {

    public static void start(Activity activity, Class<? extends Activity> cla) {
        Intent it = new Intent();
        it.setClass(activity, cla);
        activity.startActivity(it);
        //activity.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public static <T> void start(Activity activity, Class<? extends Activity> cla, T t){
        Intent it = new Intent();
        it.setClass(activity, cla);
        Bundle bundle = new Bundle();
        bundle.putSerializable(cla.getName(), (Serializable)t);
        it.putExtras(bundle);
        activity.startActivity(it);
    }

    public static void start(Activity activity, Class<? extends Activity> cla, BasicNameValuePair... name) {
        Intent it = new Intent();
        it.setClass(activity, cla);
        for (int i = 0; i < name.length; i++) {
            it.putExtra(name[i].getName(), name[i].getValue());
        }
        activity.startActivity(it);
    }


}
