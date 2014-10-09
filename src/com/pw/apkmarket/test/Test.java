package com.pw.apkmarket.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.adapter.SearchAdapter;
import com.pw.apkmarket.entity.AppInfo;
import com.pw.apkmarket.helper.JsonParseHelper;
import com.pw.apkmarket.utils.L;
import com.pw.apkmarket.utils.T;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by pengwei on 2014/9/15.
 */
public class Test extends Activity {

    @ViewInject(R.id.test_tv)
    private TextView test_tv;

    private final static String API = "http://pwei.sinaapp.com/APkMarket/api/SearchApi.php?key=";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        ViewUtils.inject(this);

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, API + "qq", null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> ResponseInfo) {
                String result = ResponseInfo.result;
                try {
                    String data = new JSONObject(result).getString("data");
                    List<AppInfo> list = JsonParseHelper.parseArray(data, AppInfo.class);
                    test_tv.setText(list.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                L.e(s);
            }
        });
    }
}