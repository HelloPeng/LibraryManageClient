package com.pansoft.lvzp.librarymanageclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.adapter.BookListAdapter;
import com.pansoft.lvzp.librarymanageclient.base.BaseFragment;
import com.pansoft.lvzp.librarymanageclient.bean.BookListItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.FragmentSearchListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索列表的Fragment
 */
public class SearchListFragment
        extends BaseFragment<FragmentSearchListBinding>
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_SEARCH_KEY = "search_key";

    private String mSearchKey;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param searchKey 搜索关键字
     * @return A new instance of fragment SearchListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchListFragment newInstance(String searchKey) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SEARCH_KEY, searchKey);
        fragment.setArguments(args);
        return fragment;
    }

    private BookListAdapter mBookAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSearchKey = getArguments().getString(ARG_SEARCH_KEY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search_list;
    }

    @Override
    protected void initViews() {
        mDataBinding.swipeRefresh.setOnRefreshListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mBookAdapter = new BookListAdapter();
        mDataBinding.recyclerView.setAdapter(mBookAdapter);
        List<BookListItemBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookListItemBean itemBean = new BookListItemBean();
            itemBean.setName("图书第" + i + "本");
            itemBean.setPublishHouse("辉煌出版社");
            itemBean.setPublishTime("公元前：221-01-31");
            list.add(itemBean);
        }
        mBookAdapter.setupData(list);
        mBookAdapter.notifyDataSetChanged();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        mDataBinding.swipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataBinding.swipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }
}
