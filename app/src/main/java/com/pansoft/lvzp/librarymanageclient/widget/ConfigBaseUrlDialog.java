package com.pansoft.lvzp.librarymanageclient.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.pansoft.lvzp.librarymanageclient.Constant;
import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.TitleBaseDialog;
import com.pansoft.lvzp.librarymanageclient.databinding.LayoutDialogConfigBaseUrlBinding;
import com.pansoft.lvzp.librarymanageclient.utils.SharedPreferencesUtils;
/**
 * Created by lv_zhp on 2018/4/1.
 */

public class ConfigBaseUrlDialog extends TitleBaseDialog<LayoutDialogConfigBaseUrlBinding> {


    public ConfigBaseUrlDialog(@NonNull Context context) {
        super(context);
        setTitleText(context.getString(R.string.dialog_title_config));
        String hostIp = (String) SharedPreferencesUtils.getParam(context, Constant.SERVICE_HOST_KEY, "192.168.2.149");
        if (!TextUtils.isEmpty(hostIp)) {
            mDataBinding.editInputIp.setText(hostIp);
            mDataBinding.editInputIp.setSelection(hostIp.length());
        }
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.layout_dialog_config_base_url;
    }


    public String getInputIp() {
        return mDataBinding.editInputIp.getText().toString();
    }

}
