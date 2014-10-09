package com.pw.apkmarket.utils;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/15 13:22
 */
public class TextUtil {

    /**
     * 首字母小写
     * @param source
     * @return
     */
    public static String toLowerCaseFirstOne(String source){
        StringBuilder sb = new StringBuilder(source);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

}
