package com.ljb.fire.utils;

import android.content.Context;

/**
 * 资源id转化工具类
 */

public class ResourceUtil {

    public static String resToString(Context context, int ids) {
        return context.getResources().getString(ids);
    }

    public static String[] resToStringArr(Context context, int ids) {
        return context.getResources().getStringArray(ids);
    }

    public static int resToColor(Context context, int ids) {
        return context.getResources().getColor(ids);
    }
}
