package com.pansoft.lvzp.librarymanageclient.adapter;

import com.pansoft.lvzp.librarymanageclient.BR;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingBaseRecycleAdapter;
import com.pansoft.lvzp.librarymanageclient.base.adapter.BindingViewHolder;
import com.pansoft.lvzp.librarymanageclient.bean.BookListItemBean;
import com.pansoft.lvzp.librarymanageclient.databinding.ItemSearchBooklistBinding;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年05月09日
 * 时间：17:56
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class BookListAdapter extends BindingBaseRecycleAdapter<BookListItemBean, ItemSearchBooklistBinding> {

    public BookListAdapter() {
        super(BR.bookItemBean);
    }

    @Override
    protected void bindingViews(BindingViewHolder<ItemSearchBooklistBinding> holder, int position, BookListItemBean itemBean) {
        ItemSearchBooklistBinding binding = holder.getBinding();

    }
}
