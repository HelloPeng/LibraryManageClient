package com.pansoft.lvzp.librarymanageclient.adapter;

import android.view.View;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingBaseRecycleAdapter;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingViewHolder;
import com.pansoft.lvzp.librarymanageclient.bean.SearchStudentItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ItemLayoutSearchStudentInfoBinding;
import com.pansoft.lvzp.librarymanageclient.ui.student.StudentInfoActivity;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月09日
 * 时间：17:56
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class StudentListAdapter extends BindingBaseRecycleAdapter<SearchStudentItemBean, ItemLayoutSearchStudentInfoBinding> {

    public StudentListAdapter() {
        super(R.layout.item_layout_search_student_info);
    }

    @Override
    protected void bindingViews(final BindingViewHolder<ItemLayoutSearchStudentInfoBinding> holder, final int position, SearchStudentItemBean itemBean) {
        final ItemLayoutSearchStudentInfoBinding binding = holder.getBinding();
        binding.setSearchStudentItemBean(itemBean);
        binding.llStudentInfoParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfoActivity.actionStart(binding.getRoot().getContext());
            }
        });
        binding.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(holder.getLayoutPosition());
            }
        });
    }
}
