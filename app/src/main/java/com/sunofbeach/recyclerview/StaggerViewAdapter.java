package com.sunofbeach.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import com.sunofbeach.recyclerview.bean.ItemBean;

import java.util.List;

class StaggerViewAdapter extends RecyclerViewBaseAdapter{
    public StaggerViewAdapter(List<ItemBean> Data) {
        super(Data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.item_stagger_view,null);
        return view;
    }
}
