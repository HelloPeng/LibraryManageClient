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
import com.pansoft.lvzp.librarymanageclient.bean.BlackItemBaen;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityBlackListBinding;

import java.util.ArrayList;
import java.util.List;

public class BlackListActivity
        extends BaseActivity<ActivityBlackListBinding>
        implements SwipeRefreshLayout.OnRefreshListener {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BlackListActivity.class);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<BlackItemBaen> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_black_list;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("黑名单");
        openBackIcon();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDataBinding.swipeRefresh.setOnRefreshListener(this);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_black_list, BR.blackItemBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        List<BlackItemBaen> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            BlackItemBaen itemBean = new BlackItemBaen();
            itemBean.setName("小张同学");
            itemBean.setReason("借书逾期" + i + "天");
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
