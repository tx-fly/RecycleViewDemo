package com.sunofbeach.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunofbeach.recyclerview.bean.MoreItemBean;

import java.util.List;

class MoreTypeAdapter extends RecyclerView.Adapter {

    private List<MoreItemBean> mData;

    //设置常量标识
    private static final int TYPE_FULL_IMAGE = 0;
    private static final int TYPE_RIGHT_IMAGE = 1;
    private static final int TYPE_THREE_IMAGE = 2;

    //获取数据
    public MoreTypeAdapter(List<MoreItemBean> Data) {
        mData = Data;
    }

    //得到View，返回ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image, null);
            return new FullHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_right_image, null);
            return new RightHolder(view);
        } else if (viewType == TYPE_THREE_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image, null);
            return new ThreeHolder(view);
        }
        return null;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    //获取长度
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //重写条目类型
    @Override
    public int getItemViewType(int position) {
        MoreItemBean moreItemBean = mData.get(position);
        if (moreItemBean.type == TYPE_FULL_IMAGE) {
            return TYPE_FULL_IMAGE;

        } else if (moreItemBean.type == TYPE_RIGHT_IMAGE) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGE;
        }
    }

    private class FullHolder extends RecyclerView.ViewHolder {

        public FullHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class RightHolder extends RecyclerView.ViewHolder {

        public RightHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ThreeHolder extends RecyclerView.ViewHolder {

        public ThreeHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
