package com.pansoft.lvzp.librarymanageclient.ui.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityStudentInfoBinding;

public class StudentInfoActivity extends BaseActivity<ActivityStudentInfoBinding> {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, StudentInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_student_info;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("用户信息");
        openBackIcon();
    }
}
