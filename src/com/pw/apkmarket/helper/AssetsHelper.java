package com.pw.apkmarket.helper;

import android.content.Context;
import android.content.res.AssetManager;
import com.pw.apkmarket.app.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * FileName:HeartLover
 * Description:
 * Created by:pengwei
 * date:2014/9/13 23:42
 */
public class AssetsHelper {

    /**
     * 获取Assets下文件内容
     * @param filePath
     * @return
     */
    public static String getFileText(String filePath){
        AssetManager manager = MyApplication.getInstance().getAssets();
        try {
            InputStream is = manager.open(filePath);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            isr.close();
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
