package com.ljb.fire.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by meloon on 2017/12/29.
 */

public class NetWorkUtil {
    /**
     * 网络是否连接有效
     *
     * @param context
     * @return
     */
    public static boolean isNetConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();

    }
}
