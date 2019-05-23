package com.example.dervis.autonomous.Helpers;

import android.app.Application;
import android.content.Context;

public class AppContextGetter extends Application {
    private static AppContextGetter instance;

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
