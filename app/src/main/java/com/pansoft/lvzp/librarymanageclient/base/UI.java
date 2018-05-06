package com.pansoft.lvzp.librarymanageclient.base;

import android.view.View;

/**
 * Created by lv_zhp on 2018/4/30.
 */
public interface UI {

    void showToast(String msg);

    void showProgressDialog();

    void showProgressDialog(String msg);

    void dismissProgressDialog();

    void simpleError(String errorMsg);

    void setOnClickListener(View.OnClickListener listener, View... views);
}
