package com.pw.apkmarket.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.pw.apkmarket.R;
import com.pw.apkmarket.app.Config;
import com.pw.apkmarket.entity.AppInfo;
import com.pw.apkmarket.utils.L;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by pengwei on 2014/9/15.
 */
public class AppContent extends Activity {

    @ViewInject(R.id.appcontent_thumb_panel)
    private LinearLayout layout;
    @ViewInject(R.id.appcontent_description)
    private TextView description;
    @ViewInject(R.id.search_lv_item_name)
    private TextView appName;
    @ViewInject(R.id.search_lv_item_rank)
    private ImageView rank;
    @ViewInject(R.id.search_lv_item_icon)
    private ImageView icon;
    @ViewInject(R.id.search_lv_item_size)
    private TextView size;

    private BitmapUtils bmUtil;
    private HttpUtils http;
    private AppInfo app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appcontent_layout);
        ViewUtils.inject(this);

        app = (AppInfo) getIntent().getSerializableExtra(getClass().getName());
        setTitle(app.name);
        http = new HttpUtils();
        bmUtil = new BitmapUtils(this);
        bmUtil.configDefaultLoadingImage(R.drawable.sw_downloading_bg);

        bmUtil.display(icon, app.icon);
        appName.setText(app.name);
        rank.setImageResource(R.drawable.rating_bg_5);
        size.setText(app.size);

        String url = Config.HOST + Config.PROJECT + "/AppInfo.php?pid=" + app.id;
        http.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>() {
            public void onSuccess(ResponseInfo<String> ResponseInfo) {
                String result = ResponseInfo.result;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray thumbArray = jsonObject.getJSONArray("thumb");
                    for (int i = 0; i < thumbArray.length(); ++i) {
                        String thumbUrl = thumbArray.getString(i);
                        ImageView imageView = new ImageView(AppContent.this);
                        bmUtil.display(imageView, thumbUrl);
                        imageView.setPadding(5, 0, 5, 0);
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        param.gravity = Gravity.CENTER_VERTICAL;
                        layout.addView(imageView, param);
                    }
                    String intro = jsonObject.getString("intro");
                    description.setText(Html.fromHtml(intro));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(HttpException e, String s) {

            }
        });

    }
}