package com.pw.apkmarket.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.pw.apkmarket.R;
import com.pw.apkmarket.adapter.SearchAdapter;
import com.pw.apkmarket.app.Config;
import com.pw.apkmarket.entity.AppInfo;
import com.pw.apkmarket.utils.IntentUtil;
import com.pw.apkmarket.utils.L;
import com.pw.apkmarket.view.AppContent;
import com.pw.apkmarket.view.Search;
import com.pw.apkmarket.widgets.MyProgress;
import com.pw.apkmarket.widgets.XListView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/16 20:29
 */
public class SearchHelper {

    private SearchAdapter adapter;
    private List<AppInfo> list;
    private XListView lv;
    private Activity activity;
    private MyProgress mpb;
    private HttpUtils http;

    private String key;
    private int page = 1;
    private int sum = 0;
    private final int pre = 15;

    private  boolean hasHeader = false;

    private static SearchHelper instance;

    public final static String API = Config.HOST + Config.PROJECT +"/SearchApi.php?key=";

    private SearchHelper() {
    }

    public static SearchHelper getInstance() {
        if (instance == null) {
            instance = new SearchHelper();
        }
        return instance;
    }

    public void Init(Activity act, String key) {
        Init(act, null, key);
    }

    public void Init(Activity act, XListView listView, String key) {
        this.activity = act;
        if (lv == null) {
            lv = (XListView) activity.findViewById(R.id.search_lv);
        } else {
            lv = listView;
        }
        lv.setPullRefreshEnable(false);
        lv.setPullLoadEnable(true);
        lv.setXListViewListener(xListViewListener);
        http = new HttpUtils();
        this.key = key;
        list = new ArrayList<AppInfo>();
        adapter = new SearchAdapter(activity.getApplicationContext(), list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(listener);
        getData(page);
    }

    public void addHeader(View header) {
        lv.addHeaderView(header);
        hasHeader = true;
    }

    public  void clear(){
        list.clear();
    }

    public void getData(int page) {
        mpb = new MyProgress(activity);
        http.send(HttpRequest.HttpMethod.GET, API + key + "&page=" + page, null, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> ResponseInfo) {
                String result = ResponseInfo.result;
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String data = jsonObject.getString("data");
                    if (sum == 0)
                        sum = Integer.parseInt(jsonObject.getString("sum"));
                    list.addAll(JsonParseHelper.parseArray(data, AppInfo.class));
                    adapter.notifyDataSetInvalidated();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mpb.dismiss();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                mpb.dismiss();
            }
        });
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            int pos = i;
            if(hasHeader) pos -= 2;
            else  pos -= 1;
            AppInfo app = list.get(pos);
            IntentUtil.start(activity, AppContent.class, app);
        }
    };

    private XListView.IXListViewListener xListViewListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
        }

        @Override
        public void onLoadMore() {
            if (pre * page < sum) {
                getData(++page);
            }
        }
    };

}
