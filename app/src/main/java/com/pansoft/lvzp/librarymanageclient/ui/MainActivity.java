package com.pansoft.lvzp.librarymanageclient.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.lapism.searchview.Search;
import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.BaseActivity;
import com.pansoft.lvzp.librarymanageclient.databinding.ActivityMainBinding;
import com.pansoft.lvzp.librarymanageclient.ui.fragment.MainFragment;
import com.pansoft.lvzp.librarymanageclient.ui.fragment.SearchListFragment;

public class MainActivity
        extends BaseActivity<ActivityMainBinding>
        implements Search.OnOpenCloseListener
        , Search.OnMenuClickListener
        , Search.OnLogoClickListener
        , Search.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    private int mSelectSearchWhich;
    private String[] mSearchKeyTypes;
    private String[] mSearchValueTypes;
    private String[] mSearchTypeCodes;
    private boolean isOpenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mDataBinding.toolbar);
        buildSearchType();
        mDataBinding.searchView.setHint(getSearchTypeHintData());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initSearchListener();
        replaceFragment(MainFragment.newInstance(MainFragment.USER_TYPE_MANAGER));
    }

    @Override
    public void onBackPressed() {
        if (isOpenList) {
            isOpenList = false;
            mDataBinding.searchView.setLogoHamburgerToLogoArrowWithAnimation(false);
            replaceFragment(MainFragment.newInstance(MainFragment.USER_TYPE_MANAGER));
        } else {
            super.onBackPressed();
        }
    }

    private void initSearchListener() {
        //设置右侧菜单的点击事件
        mDataBinding.searchView.setOnMenuClickListener(this);
        //设置左侧logo的点击事件
        mDataBinding.searchView.setOnLogoClickListener(this);
        //设置开启和关闭的监听
        mDataBinding.searchView.setOnOpenCloseListener(this);
        //设置输入框的监听事件
        mDataBinding.searchView.setOnQueryTextListener(this);
    }

    @Override
    public void onMenuClick() {
        openOptionsMenu();
    }

    @Override
    public void onLogoClick() {
        if (isOpenList) {
            onBackPressed();
            return;
        }
        new MaterialDialog
                .Builder(mContext)
                .title(R.string.search_type_dialog_title)
                .items(mSearchKeyTypes)
                .theme(Theme.LIGHT)
                .itemsCallbackSingleChoice(mSelectSearchWhich,
                        new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog,
                                                       View itemView,
                                                       int which,
                                                       CharSequence text) {
                                mSelectSearchWhich = which;
                                return true;
                            }
                        })
                .positiveText(R.string.confirm_choice_dialog_positive)
                .alwaysCallSingleChoiceCallback()
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog,
                                        @NonNull DialogAction which) {
                        Toast.makeText(mContext, "Code = " + mSearchTypeCodes[mSelectSearchWhich], Toast.LENGTH_SHORT).show();
                        mDataBinding.searchView.setHint(getSearchTypeHintData());
                    }
                })
                .show();
    }

    @Override
    public void onOpen() {
        mDataBinding.searchView.setOnMenuClickListener(null);
    }

    @Override
    public void onClose() {
        mDataBinding.searchView.setOnMenuClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(CharSequence query) {
        mDataBinding.searchView.close();
        mDataBinding.searchView.setLogoHamburgerToLogoArrowWithAnimation(true);
        isOpenList = true;
        replaceFragment(SearchListFragment.newInstance(query.toString()));
        return true;
    }

    @Override
    public void onQueryTextChange(CharSequence newText) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sys_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_about:
                Toast.makeText(mContext, "点击了:" + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
            /* setIconEnable(menu,true);*/
        }
        return super.onMenuOpened(featureId, menu);
    }

    @NonNull
    private String getSearchTypeHintData() {
        return "请输入" + mSearchValueTypes[mSelectSearchWhich];
    }

    public void buildSearchType() {
        String[] searchTypes = getResources().getStringArray(R.array.search_type);
        mSearchKeyTypes = new String[searchTypes.length];
        mSearchValueTypes = new String[searchTypes.length];
        mSearchTypeCodes = new String[searchTypes.length];
        for (int i = 0; i < searchTypes.length; i++) {
            String searchTypeStr = searchTypes[i];
            String[] searchType = searchTypeStr.split(",");
            mSearchKeyTypes[i] = searchType[0];
            mSearchValueTypes[i] = searchType[1];
            mSearchTypeCodes[i] = searchType[2];
        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_replace, fragment);
        transaction.commit();
    }
}
