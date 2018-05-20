package com.pansoft.lvzp.librarymanageclient.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by lv_zhp on 2018/4/30.
 */
public abstract class BaseFragment<D extends ViewDataBinding>
        extends Fragment
        implements UI {

    protected Context mContext;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected D mDataBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mContext = getContext();
        initViews();
        return mDataBinding.getRoot();
    }


    @Override
    public void showToast(String msg) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgressDialog();
    }

    @Override
    public void showProgressDialog(String msg) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).showProgressDialog(msg);
    }

    @Override
    public void dismissProgressDialog() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).dismissProgressDialog();
    }

    @Override
    public void simpleError(String errorMsg) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).simpleError(errorMsg);
    }

    @Override
    public void setOnClickListener(View.OnClickListener listener, View... views) {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).setOnClickListener(listener, views);
    }

    protected <T> T getDataBean(Object data, Class<T> cls) {
        return JSON.parseObject(JSON.toJSONString(data), cls);
    }

    protected <T> List<T> getListDataBean(Object data, Class<T> cls) {
        return JSON.parseArray(JSON.toJSONString(data), cls);
    }
}
