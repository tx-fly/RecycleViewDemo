package com.sunofbeach.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sunofbeach.recyclerview.bean.Datas;
import com.sunofbeach.recyclerview.bean.MoreItemBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoreTypeActivity extends AppCompatActivity {

    private List<MoreItemBean> mData;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView mRecyViewMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type);
        //找到控件
        mRecyViewMore = findViewById(R.id.Re_View_MoreType);
        //初始化数据
        initData();
        //创建与设置布局管理器
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyViewMore.setLayoutManager(linearLayoutManager);
        //创建与设置适配器
        MoreTypeAdapter moreTypeAdapter = new MoreTypeAdapter(mData);
        mRecyViewMore.setAdapter(moreTypeAdapter);
        Log.d("TAG","MoreTypeActivity");
    }

    private void initData() {
        Random random = new Random();
        mData = new ArrayList<>();
        for(int i=0;i< Datas.icons.length;i++){
            MoreItemBean moreItemBean = new MoreItemBean();
            moreItemBean.pic = Datas.icons[i];
            moreItemBean.type = random.nextInt(3);
            mData.add(moreItemBean);
        }
    }
}