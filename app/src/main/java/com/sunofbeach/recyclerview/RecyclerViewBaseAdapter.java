package com.sunofbeach.recyclerview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sunofbeach.recyclerview.bean.ItemBean;

import java.util.List;

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerViewBaseAdapter.InnerHolder> {

    private  final List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    /*
     * 用于传入数据
     */
    public RecyclerViewBaseAdapter(List<ItemBean> Data){
        this.mData = Data;
    }

    /*
     * 用于创建条目view
     */
    @NonNull
    @Override
    public RecyclerViewBaseAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new InnerHolder(view);
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    /*

     * 用于绑定数据
     * */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.InnerHolder holder, int position) {
        holder.setData(mData.get(position),position);
    }

    /*
   返回条目个数
   * */
    @Override
    public int getItemCount() {

        if(mData != null){
            return mData.size();
        }
        return 0;
    }

    /*
    * 设置监听
    * */
    public void setOnItemClickListener(OnItemClickListener Listener){

        this.mOnItemClickListener = Listener;
        Log.d("TAG","这是setOnItemClickListener");

    }

    /*
    * 创建接口
    * */
    public interface OnItemClickListener{

        void onItemClick(int position);

    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private TextView text;
        private ImageView image;
        private int mPosition;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            //找到条目的控件,通过传入的itemView

            image = itemView.findViewById(R.id.list_view_item1);
            text = itemView.findViewById(R.id.list_view_text);

            //条目被点击的时候调用
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){

                        //对接口的回调
                       mOnItemClickListener.onItemClick(mPosition);
                        Log.d("TAG","这是itemView.setOnClickListener");
                    }
                }
            });
        }
        /*
         * 这个方法用于设置数据
         * */
        public void setData(ItemBean itemBean,int position) {
            this.mPosition = position;
            text.setText(itemBean.title);
            image.setImageResource(itemBean.icon);
        }
    }
}
