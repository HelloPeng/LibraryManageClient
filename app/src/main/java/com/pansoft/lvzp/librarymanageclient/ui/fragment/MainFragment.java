package com.pansoft.lvzp.librarymanageclient.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseFragment;
import com.pansoft.lvzp.librarymanageclient.databinding.FragmentMainBinding;
import com.pansoft.lvzp.librarymanageclient.databinding.IncludeLayoutManagerBinding;
import com.pansoft.lvzp.librarymanageclient.databinding.IncludeLayoutStudentBinding;
import com.pansoft.lvzp.librarymanageclient.ui.FileScanActivity;
import com.pansoft.lvzp.librarymanageclient.ui.book.BookInfoActivity;
import com.pansoft.lvzp.librarymanageclient.ui.student.AddStudentActivity;
import com.pansoft.lvzp.librarymanageclient.ui.student.StudentInfoActivity;

/**
 * 通用首页Fragment
 */
public class MainFragment
        extends BaseFragment<FragmentMainBinding>
        implements View.OnClickListener {

    public static final String USER_TYPE_MANAGER = "manager";
    public static final String USER_TYPE_STUDENT = "student";

    private static final String ARG_USER_TYPE = "user_type";
    private static final String TAG = MainFragment.class.getSimpleName();

    private String mUserType;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userType 用户类型
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String userType) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    private IncludeLayoutManagerBinding mManagerBinding;
    private IncludeLayoutStudentBinding mStudentBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserType = getArguments().getString(ARG_USER_TYPE);
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
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews() {
        if (USER_TYPE_MANAGER.equals(mUserType)) {
            ViewStub viewStub = mDataBinding.viewStudManager.getViewStub();
            if (viewStub != null)
                viewStub.inflate();
            mManagerBinding = (IncludeLayoutManagerBinding) mDataBinding.viewStudManager.getBinding();
            initManagerViews();
        } else if (USER_TYPE_STUDENT.equals(mUserType)) {
            ViewStub viewStub = mDataBinding.viewStudStudent.getViewStub();
            if (viewStub != null)
                viewStub.inflate();
            mStudentBinding = (IncludeLayoutStudentBinding) mDataBinding.viewStudStudent.getBinding();
            initStudentViews();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_book_single_into:
                BookInfoActivity.actionStart(mContext);
                break;
            case R.id.ll_book_batch_into:
                FileScanActivity.actionStart(mContext, FileScanActivity.SCAN_TYPE_BOOK);
                break;
            case R.id.ll_book_register_manage:
                StudentInfoActivity.actionStart(mContext);
                break;
            case R.id.ll_student_single_into:
                AddStudentActivity.actionStart(mContext);
                break;
            case R.id.ll_student_batch_into:
                FileScanActivity.actionStart(mContext, FileScanActivity.SCAN_TYPE_STUDENT);
                break;
        }
    }

    private void initManagerViews() {
        setOnClickListener(this,
                mManagerBinding.llBookSingleInto,
                mManagerBinding.llBookBatchInto,
                mManagerBinding.llBookRegisterManage,
                mManagerBinding.llStudentBatchInto,
                mManagerBinding.llStudentSingleInto);
    }

    private void initStudentViews() {

    }
}
