package com.pansoft.lvzp.librarymanageclient.ui.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityAddStudentBinding;

public class AddStudentActivity extends BaseActivity<ActivityAddStudentBinding> {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddStudentActivity.class);
        context.startActivity(intent);
    }

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_data_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
