package com.pw.apkmarket.helper;

import com.pw.apkmarket.R;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/16 9:38
 */
public class RankHelper {

    public static int getRankResId(int rank) {
        int resId = 0;
        switch (rank) {
            case 1:
                resId = R.drawable.rating_bg_1;
                break;
            case 2:
                resId = R.drawable.rating_bg_2;
                break;
            case 3:
                resId = R.drawable.rating_bg_3;
                break;
            case 4:
                resId = R.drawable.rating_bg_4;
                break;
            case 5:
                resId = R.drawable.rating_bg_5;
                break;
            case 6:
                resId = R.drawable.rating_bg_6;
                break;
            case 7:
                resId = R.drawable.rating_bg_7;
                break;
            case 8:
                resId = R.drawable.rating_bg_8;
                break;
            case 9:
                resId = R.drawable.rating_bg_9;
                break;
            case 10:
                resId = R.drawable.rating_bg_10;
                break;
            default:
                resId = R.drawable.rating_bg_1;
                break;
        }
        return resId;
    }

}
