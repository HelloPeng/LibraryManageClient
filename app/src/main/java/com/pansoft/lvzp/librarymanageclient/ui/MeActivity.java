package com.pansoft.lvzp.librarymanageclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityMeBinding;

public class MeActivity extends BaseActivity<ActivityMeBinding> {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("我的信息");
        openBackIcon();
    }
}
