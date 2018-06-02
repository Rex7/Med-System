package com.example.regis.medsystem;

import android.app.Application;
import android.content.Context;

import com.cloudinary.android.MediaManager;
import com.example.regis.medsystem.cloud.CloudinaryConf;


public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

        MediaManager.init(MyApplication.getApplicationConext(),CloudinaryConf.getConfig());
    }


    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getApplicationConext() {
        return myApplication.getApplicationContext();
    }

}

