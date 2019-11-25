package com.william.jifanghelpdesk.context;

import android.app.Application;

/**
 * 清单文件：android:name=".context.MyApplication"
 */
public class MyApplication extends Application {

    private static MyApplication app;

    public static MyApplication getInstance()
    {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

}
