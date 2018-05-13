package com.pansoft.lvzp.librarymanageclient.ui.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityAddBookBinding;

public class AddBookActivity extends BaseActivity<ActivityAddBookBinding> {


    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddBookActivity.class);
        context.startActivity(intent);
    }

    private MenuItem mSaveMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_book;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("添加图书");
        openBackIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mSaveMenu = menu.add("保存");
        mSaveMenu.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == mSaveMenu){
            showToast("点击了保存");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
