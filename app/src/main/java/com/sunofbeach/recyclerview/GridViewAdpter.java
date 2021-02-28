package com.sunofbeach.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import com.sunofbeach.recyclerview.bean.ItemBean;
import java.util.List;

public class GridViewAdpter extends RecyclerViewBaseAdapter {

    GridViewAdpter(List<ItemBean> Data) {
       super(Data);
    }


    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_grid_view, null);
        return view;
    }
}
