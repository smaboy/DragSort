package com.example.smaboy.dragsort;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.smaboy.dragsort.adapter.MyAdpter;
import com.example.smaboy.dragsort.bean.NewsBean;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private ArrayList<NewsBean> names;
    private ArrayList<NewsBean> names2;
    private MyAdpter adpter;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init
        init();
    }

    private void init() {
        recyclerview = findViewById(R.id.recyclerview);

        //模拟数据
        names = new ArrayList<>();
        names2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            StringBuilder title = new StringBuilder();
            for (int j = 0; j <= Math.random() * 100; j++) {//随机标题
                title.append("标题");
            }
            names2.add(new NewsBean(title.toString(), "", ""));
            names.add(new NewsBean("标题"+i, "", ""));
        }
        setRecyclerViewData(names,1);


    }

    private void setRecyclerViewData(final ArrayList<NewsBean> data, int flag) {
        //设置布局
        setLayout(flag);

        //设置数据
        setAdapterData(data);

        //为recyvlerview绑定触摸事件,设置监听
        setTouchListener(data);
    }

    private void setTouchListener(final ArrayList<NewsBean> data) {
         helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                int dragFlags;//拖拽
                int swipFlags;//侧滑删除

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {//表格布局
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                    swipFlags = 0;

                } else if (layoutManager instanceof StaggeredGridLayoutManager) {//瀑布流布局
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                    swipFlags = 0;

                } else {//如果布局为线性布局

                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags, swipFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                //滑动事件
                Collections.swap(data, viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
                adpter.notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());


                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //侧滑事件
                names.remove(viewHolder.getAdapterPosition());
                adpter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }

            @Override
            public boolean isLongPressDragEnabled() {//默认支持长按拖拽
                return super.isLongPressDragEnabled();
            }

            @Override
            public boolean isItemViewSwipeEnabled() {//默认支持侧滑
                return super.isItemViewSwipeEnabled();
            }
        });

        helper.attachToRecyclerView(recyclerview);
    }

    private void setAdapterData(ArrayList<NewsBean> data) {
        adpter = new MyAdpter(this, data);
        recyclerview.setAdapter(adpter);
    }

    private void setLayout(int flag) {
        if (flag == 1) {
            recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        } else if (flag == 2) {
            recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        } else if (flag == 3) {
            recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        } else {
            recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
    }

    public void test01(View view) {


        setLayout(1);
        setAdapterData(names);
    }

    public void test02(View view) {

        setLayout(2);
        setAdapterData(names);
    }

    public void test03(View view) {
        setLayout(3);
        setAdapterData(names2);
    }

    public void test04(View view) {


    }
}
