package com.pansoft.lvzp.librarymanageclient.ui.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.pansoft.lvzp.librarymanageclient.BR;
import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.base.adapter.SimpleBindingAdapter;
import com.pansoft.lvzp.librarymanageclient.bean.BorrowBookRecordItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityBorrowBookRecordBinding;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookRecordActivity
        extends BaseActivity<ActivityBorrowBookRecordBinding>
        implements SwipeRefreshLayout.OnRefreshListener {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BorrowBookRecordActivity.class);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<BorrowBookRecordItemBean> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_borrow_book_record;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("借阅记录");
        openBackIcon();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDataBinding.swipeRefresh.setOnRefreshListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_book_borrow_record, BR.bookBorrowRecordItemBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        List<BorrowBookRecordItemBean> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            BorrowBookRecordItemBean itemBean = new BorrowBookRecordItemBean();
            itemBean.setBookName("图书" + i);
            itemBean.setPublish("长安出版社");
            itemBean.setBorrowDate("2010-03-11");
            itemBean.setReturnDate("2011-11-23");
            list.add(itemBean);
        }
        mAdapter.setupData(list);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {

    }
}
