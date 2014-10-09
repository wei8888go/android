package com.pw.apkmarket.widgets;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/16 21:21
 */
public class MyProgress {
    private Context context;
    private Dialog dialog;

    public MyProgress(Context context) {
        this.context = context;
        dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        ProgressBar pb = new ProgressBar(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(pb);
    }

    public void dismiss(){
        dialog.dismiss();
    }



}
