package com.example.regis.medsystem;

import android.app.Application;
import android.content.Context;

/**
 * Created by Regis on 19-02-2018.
 */

public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }


    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getApplicationConext() {
        return myApplication.getApplicationContext();
    }

}

