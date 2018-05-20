package com.pansoft.lvzp.librarymanageclient.base;

import android.app.Application;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.http.OkHttpClientManager;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Created by lv_zhp on 2018/4/1.
 */

public class Apl extends Application {

    private static Apl sInstance;
    private String mBaseUrl;
    private String mUserOid;
    private String mUserType;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        ZXingLibrary.initDisplayOpinion(this);
        OkHttpClientManager.getInstance().init(this);
    }

    public static Apl getInstance() {
        return sInstance;
    }
    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return getString(R.string.http_format, mBaseUrl);
    }

    public void setUserOid(String userOid) {
        mUserOid = userOid;
    }

    public String getUserOid() {
        return mUserOid;
    }

    public String getUserType() {
        return mUserType;
    }

    public void setUserType(String mUserType) {
        this.mUserType = mUserType;
    }
}
