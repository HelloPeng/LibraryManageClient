package com.pansoft.lvzp.librarymanageclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityMeBinding;

public class MeActivity extends BaseActivity<ActivityMeBinding>
        implements View.OnClickListener {

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
        setOnClickListener(this,
                mDataBinding.btnChangePassword);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_password:

                break;
        }
    }
}
