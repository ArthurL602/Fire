package com.ljb.fire.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by meloon on 2017/12/29.
 */

public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static Context getAppContext() {
        return sApp;
    }
}
