package com.example.ccei.recyclerviewtotal;

import android.app.Application;
import android.content.Context;

/**
 * Created by ccei on 2016-07-11.
 */
public class MyApplication extends Application {
    private static Context myContext;
    @Override
    public void onCreate() {
        super.onCreate();
        myContext = this;
    }

    public static Context getMyContext() {
        return myContext;
    }
}
