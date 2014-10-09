package com.pw.apkmarket.view;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TabHost;
import com.pw.apkmarket.R;
import com.pw.apkmarket.utils.IntentUtil;

/**
 * Created by pengwei on 2014/9/16.
 */
public class Main extends TabActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup group;
    private TabHost tabHost;
    public static final Class[] activity = {Recommend.class, Classification.class, RankingList.class, Setting.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        group = (RadioGroup) findViewById(R.id.main_radiogp);
        group.setOnCheckedChangeListener(this);
        tabHost = this.getTabHost();
        TabHost.TabSpec[] tab = new TabHost.TabSpec[activity.length];
        for (int i = 0; i < activity.length; i++) {
            tab[i] = tabHost.newTabSpec(activity[i].getName());
            tab[i].setIndicator(activity[i].getName()).setContent(new Intent(this, activity[i]));
            tabHost.addTab(tab[i]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_search){
            IntentUtil.start(this, Search.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int count = radioGroup.getChildCount();
        for (int k = 0; k < count; ++k) {
            if (radioGroup.getChildAt(k) == findViewById(i)) {
                tabHost.setCurrentTab(k);
                break;
            }
        }
    }

}