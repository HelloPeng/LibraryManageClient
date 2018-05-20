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
import com.pansoft.lvzp.librarymanageclient.adapter.StudentListAdapter;
import com.pansoft.lvzp.librarymanageclient.base.BaseFragment;
import com.pansoft.lvzp.librarymanageclient.bean.BookDao;
import com.pansoft.lvzp.librarymanageclient.bean.BookListItemBean;
import com.pansoft.lvzp.librarymanageclient.bean.SearchStudentItemBean;
import com.pansoft.lvzp.librarymanageclient.bean.StudentDao;
import com.pansoft.lvzp.librarymanageclient.databinding.FragmentSearchListBinding;
import com.pansoft.lvzp.librarymanageclient.http.ApiUrl;
import com.pansoft.lvzp.librarymanageclient.http.HttpResultCallback;
import com.pansoft.lvzp.librarymanageclient.http.OkHttpClientManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索列表的Fragment
 */
public class SearchListFragment
        extends BaseFragment<FragmentSearchListBinding>
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final int SEARCH_TYPE_BOOK = 100;
    private static final int SEARCH_TYPE_STUDENT = 110;
    private static final String SEARCH_TYPE = "search_type";
    private static final String ARG_SEARCH_KEY = "search_key";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param searchKey 搜索关键字
     * @return A new instance of fragment SearchListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchListFragment newInstance(String searchType, String searchKey) {
        SearchListFragment fragment = new SearchListFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_TYPE, searchType);
        args.putString(ARG_SEARCH_KEY, searchKey);
        fragment.setArguments(args);
        return fragment;
    }

    private BookListAdapter mBookAdapter;
    private StudentListAdapter mStudentAdapter;
    private String mSearchKey;
    private int mSearchType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mSearchKey = arguments.getString(ARG_SEARCH_KEY);
            String searchType = arguments.getString(SEARCH_TYPE);
            mSearchType = Integer.parseInt(searchType);
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
        loadData();
    }

    private void loadData() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", mSearchKey);
        params.put("type", mSearchType);
        OkHttpClientManager.getInstance().asyncGetParams(ApiUrl.SEARCH_DATA, params, new HttpResultCallback() {
            @Override
            public void onSuccess(Object data) {
                if (mSearchType == SEARCH_TYPE_BOOK) {
                    if (mBookAdapter == null) {
                        mBookAdapter = new BookListAdapter();
                        mDataBinding.recyclerView.setAdapter(mBookAdapter);
                    }
                    List<BookDao> listDataBean = getListDataBean(data, BookDao.class);
                    List<BookListItemBean> list = new ArrayList<>();
                    if (listDataBean != null && !listDataBean.isEmpty())
                        for (BookDao bookDao : listDataBean) {
                            BookListItemBean itemBean = new BookListItemBean();
                            itemBean.setName(bookDao.getName());
                            itemBean.setPublishHouse(bookDao.getPublish());
                            itemBean.setPublishTime(bookDao.getPublishDate());
                            list.add(itemBean);
                        }
                    mBookAdapter.setupData(list);
                    mBookAdapter.notifyDataSetChanged();
                } else if (mSearchType == SEARCH_TYPE_STUDENT) {
                    if (mStudentAdapter == null) {
                        mStudentAdapter = new StudentListAdapter();
                        mDataBinding.recyclerView.setAdapter(mStudentAdapter);
                    }
                    List<StudentDao> listDataBean = getListDataBean(data, StudentDao.class);
                    List<SearchStudentItemBean> list = new ArrayList<>();
                    if (listDataBean != null && !listDataBean.isEmpty()) {
                        for (StudentDao studentDao : listDataBean) {
                            SearchStudentItemBean itemBean = new SearchStudentItemBean();
                            itemBean.setName(studentDao.getName());
                            itemBean.setCollege(studentDao.getCollege());
                            list.add(itemBean);
                        }
                    }
                    mStudentAdapter.setupData(list);
                    mStudentAdapter.notifyDataSetChanged();
                }
                mDataBinding.swipeRefresh.setRefreshing(false);
            }

            @Override
            public void onError(String msg) {
                simpleError(msg);
            }
        });

    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        loadData();
    }
}
