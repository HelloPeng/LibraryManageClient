package com.pansoft.lvzp.librarymanageclient.ui.book;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityBookRegisterBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 借阅归还
 */
public class BookRegisterActivity
        extends BaseActivity<ActivityBookRegisterBinding>
        implements View.OnClickListener {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BookRegisterActivity.class);
        context.startActivity(intent);
    }

    private static final int REQUEST_CODE_SCAN = 0x00000013;
    private Disposable mPermissionsSubscribe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxPermissions permissions = new RxPermissions(this);
        mPermissionsSubscribe = permissions
                .request(Manifest.permission.CAMERA)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        if (!aBoolean) {
                            finish();
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPermissionsSubscribe.dispose();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_book_register;
    }

    @Override
    protected void initViews() {
        setSupportActionBar(mDataBinding.toolbar);
        setTitle("图书登记");
        openBackIcon();
        initViewListener();
    }

    private void initViewListener() {
        setOnClickListener(this,
                mDataBinding.btnConfirmBeforehandLoan,
                mDataBinding.btnConfirmReturn,
                mDataBinding.ivScanBookSn);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm_beforehand_loan:
                break;
            case R.id.btn_confirm_return:
                break;
            case R.id.iv_scan_book_sn:
                Intent intent = new Intent();
                intent.setClass(mContext, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN:
                    //处理扫描结果（在界面上显示）
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            mDataBinding.editInputBookSn.setText(result);
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
