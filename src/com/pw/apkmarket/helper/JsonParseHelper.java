package com.pw.apkmarket.helper;

import com.pw.apkmarket.utils.TextUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName:APKMarket
 * Description:
 * Created by:pengwei
 * date:2014/9/15 11:20
 */
public class JsonParseHelper {

    public static <T> List<T> parseArray(String json, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t = (T) cla.newInstance();
                Method[] methods = cla.getMethods();
                for (Method method : methods) {
                    String methodName = method.getName();
                    if (methodName.startsWith("set")) {
                        String columnName = TextUtil.toLowerCaseFirstOne(methodName.substring(3,
                                methodName.length()));
                        Class[] params = method.getParameterTypes();
                        if (params[0] == Integer.class) {
                            method.invoke(t, jsonObject.getInt(columnName));
                        } else {
                            method.invoke(t, jsonObject.getString(columnName));
                        }

                    }
                }
                list.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

}
