package com.pansoft.lvzp.librarymanageclient.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseFragment;
import com.pansoft.lvzp.librarymanageclient.databinding.FragmentSearchListBinding;

/**
 * 搜索列表的Fragment
 */
public class SearchListFragment extends BaseFragment<FragmentSearchListBinding> {

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

    }
}
