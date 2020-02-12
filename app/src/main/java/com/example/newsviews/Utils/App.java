package com.example.newsviews.Utils;


import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class App extends Application {

    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //Fabric.with(this, new Crashlytics());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public App() {
        super();
    }
}
