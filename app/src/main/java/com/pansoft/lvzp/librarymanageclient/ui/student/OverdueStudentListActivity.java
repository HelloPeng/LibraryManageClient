package com.pansoft.lvzp.librarymanageclient.ui.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.pansoft.lvzp.librarymanageclient.BR;
import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.base.adapter.SimpleBindingAdapter;
import com.pansoft.lvzp.librarymanageclient.bean.OverdueStudentItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityOverdueStudentListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月13日
 * 时间：17:31
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class OverdueStudentListActivity
        extends BaseActivity<ActivityOverdueStudentListBinding>
        implements SwipeRefreshLayout.OnRefreshListener{

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OverdueStudentListActivity.class);
        context.startActivity(intent);
    }

    private SimpleBindingAdapter<OverdueStudentItemBean> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_overdue_student_list;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("逾期记录");
        openBackIcon();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDataBinding.swipeRefresh.setOnRefreshListener(this);
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SimpleBindingAdapter<>(R.layout.item_layout_overdue_student_list, BR.overdueStudentItemBean);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        List<OverdueStudentItemBean> list = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            OverdueStudentItemBean itemBean = new OverdueStudentItemBean();
            itemBean.setTime("1"+i+"天");
            itemBean.setBook("图书"+i);
            itemBean.setName("小张同学");
            itemBean.setActualReturnTime("2010-03-11");
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
