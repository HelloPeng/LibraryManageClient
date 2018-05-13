package com.pansoft.lvzp.librarymanageclient.ui.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityAddStudentBinding;

public class AddStudentActivity
        extends BaseActivity<ActivityAddStudentBinding>
        implements CompoundButton.OnCheckedChangeListener {



    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddStudentActivity.class);
        context.startActivity(intent);
    }

    private boolean isNeedDefaultPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_student;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("");
        openBackIcon();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_data_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initListener() {
        mDataBinding.cbxDefaultPassword.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isNeedDefaultPsw = isChecked;
        if (isChecked){
            mDataBinding.editInputPassword.setVisibility(View.INVISIBLE);
        }else{
            mDataBinding.editInputPassword.setVisibility(View.VISIBLE);
        }
    }
}
