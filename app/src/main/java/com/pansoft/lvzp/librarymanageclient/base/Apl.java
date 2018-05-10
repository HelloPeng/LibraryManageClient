package com.pansoft.lvzp.librarymanageclient.base;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Created by lv_zhp on 2018/4/1.
 */

public class Apl extends Application {

    private static Apl sInstance;
    private String mBaseUrl;
    private String mUserOid;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static Apl getInstance() {
        return sInstance;
    }

}
