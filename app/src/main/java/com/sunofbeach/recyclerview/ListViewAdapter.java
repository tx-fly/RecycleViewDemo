package com.sunofbeach.recyclerview;

import android.view.View;
import android.view.ViewGroup;

import com.sunofbeach.recyclerview.bean.ItemBean;

import java.util.List;

public class ListViewAdapter extends RecyclerViewBaseAdapter {

    public ListViewAdapter(List<ItemBean> Data) {
        super(Data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        //第一：拿到View
        //第二：创建InnerHolder
        View view = View.inflate(parent.getContext(), R.layout.item_list_view, null);
        return view;
    }
}
