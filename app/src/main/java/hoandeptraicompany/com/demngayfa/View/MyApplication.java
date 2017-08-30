package com.chidm.fakecall.activity;

import android.app.Application;

import com.zer.android.ZAndroidSDK;

/**
 * Created by SONY VAIO E SERIES on 14/08/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZAndroidSDK.initApplication(this, getPackageName());
    }
}
