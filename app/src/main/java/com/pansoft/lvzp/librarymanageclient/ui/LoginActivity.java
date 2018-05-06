package com.pansoft.lvzp.librarymanageclient.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityLoginBinding;
import com.pansoft.lvzp.librarymanageclient.db.SqlDatabase;
import com.pansoft.lvzp.librarymanageclient.db.bean.LoginUser;

/**
 * Created by lv_zhp on 2018/4/29.
 */
public class LoginActivity
        extends BaseActivity<ActivityLoginBinding>
        implements View.OnClickListener {

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String username = mDataBinding.editUsername.getText().toString();
                String password = mDataBinding.editPassword.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    showToast(getString(R.string.input_username_hint));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast(getString(R.string.input_password_hint));
                    return;
                }
                MainActivity.actionStart(mContext);
               /* LoginUser user = new LoginUser();
                user.setUsername(username);
                user.setPassword(password);
                SqlDatabase.getInstance(this).getLoginDao().saveLoginUsers(user);*/
                break;
            default:
                break;
        }
    }
}
