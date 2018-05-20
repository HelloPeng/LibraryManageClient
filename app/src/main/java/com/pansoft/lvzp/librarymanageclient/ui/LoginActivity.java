package com.pansoft.lvzp.librarymanageclient.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;

import com.pansoft.lvzp.librarymanageclient.Constant;
import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.Apl;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.bean.LoginUserBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityLoginBinding;
import com.pansoft.lvzp.librarymanageclient.http.ApiUrl;
import com.pansoft.lvzp.librarymanageclient.http.HttpResultCallback;
import com.pansoft.lvzp.librarymanageclient.http.OkHttpClientManager;
import com.pansoft.lvzp.librarymanageclient.utils.SharedPreferencesUtils;
import com.pansoft.lvzp.librarymanageclient.widget.ConfigBaseUrlDialog;

import java.util.Map;

/**
 * Created by lv_zhp on 2018/4/29.
 */
public class LoginActivity
        extends BaseActivity<ActivityLoginBinding>
        implements View.OnClickListener {


    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private ConfigBaseUrlDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        setOnClickListener(this, mDataBinding.btnLogin);
        String username = (String) SharedPreferencesUtils.getParam(mContext, Constant.USER_LOGIN_USERNAME, "");
        mDataBinding.editUsername.setText(username);
        String hostIp = Apl.getInstance().getBaseUrl();
        if (hostIp.contains("null")) {
            buildConfigDialog();
        } else {
            String userOid = Apl.getInstance().getUserOid();
            if (!TextUtils.isEmpty(userOid)) {
                MainActivity.actionStart(mContext, Apl.getInstance().getUserType());
                finish();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                final String username = mDataBinding.editUsername.getText().toString();
                String password = mDataBinding.editPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    showToast(getString(R.string.input_username_hint));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast(getString(R.string.input_password_hint));
                    return;
                }
                Map<String, Object> params = new ArrayMap<>();
                params.put("username", username);
                params.put("password", password);
                showProgressDialog();
                OkHttpClientManager.getInstance().asyncPost(ApiUrl.LOGIN, params, new HttpResultCallback() {
                    @Override
                    public void onSuccess(Object data) {
                        LoginUserBean dataBean = getDataBean(data, LoginUserBean.class);
                        dismissProgressDialog();
                        showToast("登录成功");
                        String userType;
                        if ("1".equals(dataBean.getUserType())) {
                            userType = MainActivity.USER_TYPE_MANAGER;
                        } else {
                            userType = MainActivity.USER_TYPE_STUDENT;
                        }
                        SharedPreferencesUtils.setParam(mContext, Constant.USER_LOGIN_USERNAME, username);
                        SharedPreferencesUtils.setParam(mContext, Constant.USER_LOGIN_OID, dataBean.getUserOid());
                        SharedPreferencesUtils.setParam(mContext, Constant.USER_LOGIN_TYPE, userType);
                        Apl.getInstance().setUserOid(dataBean.getUserOid());
                        Apl.getInstance().setUserType(userType);
                        MainActivity.actionStart(mContext, userType);
                        finish();
                    }

                    @Override
                    public void onError(String msg) {
                        simpleError(msg);
                    }
                });


               /* LoginUser user = new LoginUser();
                user.setUsername(username);
                user.setPassword(password);
                SqlDatabase.getInstance(this).getLoginDao().saveLoginUsers(user);*/
                break;
            default:
                break;
        }
    }

    private void buildConfigDialog() {
        if (mDialog == null) {
            mDialog = new ConfigBaseUrlDialog(this);
            mDialog.setOnConfirmClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String inputIp = mDialog.getInputIp();
                    if (TextUtils.isEmpty(inputIp)) {
                        showToast("请输入ip地址后确认");
                        return;
                    }
                    Apl.getInstance().setBaseUrl(inputIp);
                    showProgressDialog();
                    OkHttpClientManager.getInstance().asyncGet(ApiUrl.BASE_CHECK, new HttpResultCallback() {
                        @Override
                        public void onSuccess(Object data) {
                            dismissProgressDialog();
                            if ((Boolean) data) {
                                mDialog.dismiss();
                                showToast(getString(R.string.connect_sul));
                                SharedPreferencesUtils.setParam(mContext, Constant.SERVICE_HOST_KEY, inputIp);
                                String userOid = (String) SharedPreferencesUtils.getParam(mContext, Constant.USER_LOGIN_OID, "");
                                String userType = (String) SharedPreferencesUtils.getParam(mContext, Constant.USER_LOGIN_TYPE, "");
                                if (!TextUtils.isEmpty(userOid)) {
                                    Apl.getInstance().setUserOid(userOid);
                                    MainActivity.actionStart(mContext, userType);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onError(String msg) {
                            simpleError(getString(R.string.not_found_ip_service) + "；错误信息：" + msg);
                            Apl.getInstance().setBaseUrl(null);
                        }
                    });
                }
            });
        }
        mDialog.show();
    }


}
