package com.pansoft.lvzp.librarymanageclient.ui.book;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityBookInfoBinding;
import com.pansoft.lvzp.librarymanageclient.utils.RenderScriptBlur;

public class BookInfoActivity extends BaseActivity<ActivityBookInfoBinding> {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BookInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_info;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("图书详情");
        openBackIcon();
        mDataBinding.ivBookBlur.setImageBitmap(
                RenderScriptBlur.blur(
                        mContext, BitmapFactory.decodeResource(
                                getResources(), R.drawable.bg_book), 0.8F));
    }
}
