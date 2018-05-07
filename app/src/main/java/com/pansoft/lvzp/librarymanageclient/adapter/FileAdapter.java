package com.pansoft.lvzp.librarymanageclient.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pansoft.lvzp.librarymanageclient.R;
import com.pansoft.lvzp.librarymanageclient.bean.FileItemBean;

import java.util.List;

/**
 * 作者：吕振鹏
 * E-mail:lvzhenpeng@pansoft.com
 * 创建时间：2018年04月23日
 * 时间：14:11
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private List<FileItemBean> mListData;
    private OnItemClickListener mOnItemClickListener;

    public FileAdapter(List<FileItemBean> mListData) {
        this.mListData = mListData;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(rootView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileItemBean itemBean = mListData.get(position);
        holder.ivPhoto.setImageResource(R.drawable.ic_vector_execl);
        holder.tvName.setText(itemBean.getName());
        holder.tvSize.setText(holder.context.getString(R.string.file_size) + itemBean.getSize());
        holder.tvPath.setText(holder.context.getString(R.string.file_parent_path) + itemBean.getParentPath());
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        TextView tvName, tvPath, tvSize;
        ImageView ivPhoto;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(ViewHolder.this, v, getLayoutPosition());
                    }
                }
            });
            context = itemView.getContext();
            tvName = itemView.findViewById(R.id.tv_file_name);
            tvPath = itemView.findViewById(R.id.tv_file_parent_path);
            tvSize = itemView.findViewById(R.id.tv_file_size);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
        }
    }

    public interface OnItemClickListener {
        /**
         * [RecyclerView]的条目点击事件
         */
        void onItemClick(RecyclerView.ViewHolder viewHolder, View view, int position);
    }
}
