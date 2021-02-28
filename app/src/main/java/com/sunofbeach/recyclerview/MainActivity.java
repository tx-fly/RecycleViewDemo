package com.sunofbeach.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sunofbeach.recyclerview.bean.Datas;
import com.sunofbeach.recyclerview.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
*@copyright : 1000
*
*createTime : 2021/1/13
*
*author : g
 * **/
public class MainActivity extends AppCompatActivity {


    private RecyclerView mList;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mDapter;
    private SwipeRefreshLayout mrvRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        mList = findViewById(R.id.recycle_view);
        mrvRefresh = findViewById(R.id.swiperefresh);
        //模拟数据
        initData();
        //初始化
        showList(true, false);
        //下拉刷新数据
        handlerDownPullUpdata();

        //上拉刷新数据
        handlerUpPullUpdata();

    }

    /*
     * 上拉刷新数据函数
     * */
    private void handlerUpPullUpdata() {

    }

    /*
    * 下拉刷新数据函数
    * */
    private void handlerDownPullUpdata() {

        //设置颜色
        mrvRefresh.setColorSchemeResources(R.color.design_default_color_on_secondary,
                R.color.design_default_color_primary,R.color.teal_200);
        //设为可用
        mrvRefresh.setEnabled(true);
        //当触发上拉时调用
        mrvRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //添加数据
                ItemBean itemBean = new ItemBean();
                itemBean.title="我是新添加的数据";
                itemBean.icon=R.mipmap.pic_02;
                mData.add(0,itemBean);
                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //更新数据
                        mDapter.notifyDataSetChanged();
                        //让刷新停止
                        mrvRefresh.setRefreshing(false);
                    }
                },3000);
            }
        });

    }


    /*
    * 实现GridView
    * */
    private void showGrid(boolean isVertical, boolean isReverse) {

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        //设置垂直或水平
        gridLayoutManager.setOrientation(isVertical ? gridLayoutManager.VERTICAL : gridLayoutManager.HORIZONTAL);
        //设置正反向
        gridLayoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(gridLayoutManager);

        //创建与设置适配器
        mDapter = new GridViewAdpter(mData);
        mList.setAdapter(mDapter);

        //初始化监听
        initListener();
    }

    /*
    * 实现ListView
    * */
    private void showList(boolean isVertical, boolean isReverse) {

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        //设置垂直或水平
        linearLayoutManager.setOrientation(isVertical ? linearLayoutManager.VERTICAL : linearLayoutManager.HORIZONTAL);
        //设置正反向
        linearLayoutManager.setReverseLayout(isReverse);

        mList.setLayoutManager(linearLayoutManager);

//        //设置分割线，系统默认
//        mList.addItemDecoration(new DividerItemDecoration(
//                this, DividerItemDecoration.VERTICAL));

        //创建适配器
        mDapter= new ListViewAdapter(mData);

        //设置适配器到RecyclerView里面
        mList.setAdapter(mDapter);

        //初始化监听
        initListener();
    }

    /*
    * 实现瀑布流
    * */
    private void showStagger(boolean isVertical, boolean isReverse) {

        //创建布局管理器
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);

        //是否正反向
        staggeredGridLayoutManager.setReverseLayout(isReverse);

        //添加布局管理器
        mList.setLayoutManager(staggeredGridLayoutManager);

        //创建适配器
        mDapter= new StaggerViewAdapter(mData);
        //设置适配器
        mList.setAdapter(mDapter);

        //初始化监听
        initListener();
    }


    /*
    这个方法用于模拟数据
    * */
    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < Datas.icons.length; i++) {
            ItemBean itemBean = new ItemBean();
            itemBean.icon = Datas.icons[i];
            itemBean.title = "图片" + (i + 1);
            mData.add(itemBean);
        }
    }

    /*
    * 用于初始化监听
    * */
    private void initListener() {

        //实现接口OnItemClickListener
        mDapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            //对父类的重写
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this,"点击了第"+(position+1)+"个条目",Toast.LENGTH_SHORT).show();
                Log.d("TAG","这是nItemClick");
            }
        });
    }
    /*
     * onCreateOptionsMenu用于创建菜单
     *
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * onOptionsItemSelected用于菜单的点击事件
     *
     * */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            //ListVew点击
            case R.id.ListView_vertical_stander:
                showList(true, false);
                Log.d("TAG", "点击了ListView_vertical_stander");
                break;
            case R.id.ListView_vertical_reverse:
                showList(true, true);
                Log.d("TAG", "点击了ListView_vertical_reverse");
                break;
            case R.id.ListView_horizontal_stander:
                showList(false, false);
                Log.d("TAG", "点击了ListView_horizontal_stander");
                break;
            case R.id.ListView_horizontal_reverse:
                showList(false, true);
                Log.d("TAG", "点击了ListView_horizontal_reverse");
                break;

            //GridVew点击
            case R.id.GridView_vertical_stander:
                showGrid(true, false);
                Log.d("TAG", "点击了GridView_vertical_stander");
                break;
            case R.id.GridView_vertical_reverse:
                showGrid(true, true);
                break;
            case R.id.GridView_horizontal_stander:
                showGrid(false, false);
                break;
            case R.id.GridView_horizontal_reverse:
                showGrid(false, true);
                break;

            //StaggerVew点击
            case R.id.stagger_vertical_stander:
                showStagger(true, false);
                Log.d("TAG", "点击了stagger_vertical_stander");
                break;
            case R.id.stagger_vertical_reverse:
                showStagger(true, true);
                break;
            case R.id.stagger_horizontal_stander:
                showStagger(false, false);
                break;
            case R.id.stagger_horizontal_reverse:
                showStagger(false, true);
                break;

            //MoreType点击
            case R.id.more_type:
                Intent intent = new Intent(this,MoreTypeActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}