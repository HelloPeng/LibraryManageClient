package com.pansoft.lvzp.librarymanageclient.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by lv_zhp on 2018/4/1.
 * 所有Activity的父类
 * <p>
 * 主要目的：
 * 1.初始化DataBinding对象，子类只需要传递进来对应的范型即可
 * 2.通用的加载框
 * 3.通用Toast
 * </p>
 */

public abstract class BaseActivity<D extends ViewDataBinding>
        extends AppCompatActivity
        implements UI {

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected D mDataBinding;

    private ProgressDialog mProgressDialog;

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initViews();
    }

    /**
     * 开启返回键
     */
    protected void openBackIcon() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(null);
    }

    @Override
    public void showProgressDialog(String message) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);
        }
        if (TextUtils.isEmpty(message)) {
            message = "数据加载中...";
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    @Override
    public void simpleError(String errorMsg) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    protected <T> T getDataBean(Object data, Class<T> cls) {
        return JSON.parseObject(JSON.toJSONString(data), cls);
    }

    protected <T> List<T> getListDataBean(Object data, Class<T> cls) {
        return JSON.parseArray(JSON.toJSONString(data), cls);
    }
}
