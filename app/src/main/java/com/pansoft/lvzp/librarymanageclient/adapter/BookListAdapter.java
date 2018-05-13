package com.pansoft.lvzp.librarymanageclient.adapter;

import android.view.View;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingBaseRecycleAdapter;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingViewHolder;
import com.pansoft.lvzp.librarymanageclient.bean.BookListItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ItemLayoutSearchBooklistBinding;
import com.pansoft.lvzp.librarymanageclient.ui.book.BookInfoActivity;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月09日
 * 时间：17:56
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class BookListAdapter extends BindingBaseRecycleAdapter<BookListItemBean, ItemLayoutSearchBooklistBinding> {

    public BookListAdapter() {
        super(R.layout.item_layout_search_booklist);
    }

    @Override
    protected void bindingViews(final BindingViewHolder<ItemLayoutSearchBooklistBinding> holder, final int position, BookListItemBean itemBean) {
        final ItemLayoutSearchBooklistBinding binding = holder.getBinding();
        binding.setBookItemBean(itemBean);
        binding.llBookInfoParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookInfoActivity.actionStart(binding.getRoot().getContext());
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
