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
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.smaboy.dragsort.adapter.MyAdpter;
import com.example.smaboy.dragsort.bean.NewsBean;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

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
        final ArrayList<NewsBean> names = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            StringBuilder title= new StringBuilder();
            for(int j = 0; j <= Math.random()*100; j++) {//随机标题
              title.append("标题");
            }
            names.add(new NewsBean(title.toString(),"",""));
//            names.add(new NewsBean("标题"+i,"",""));
        }

        //设置数据

//        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        final MyAdpter adpter = new MyAdpter(this, names);
        recyclerview.setAdapter(adpter);

        //为recyvlerview绑定触摸事件
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                int dragFlags;//拖拽
                int swipFlags;//侧滑删除

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof GridLayoutManager) {//表格布局
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT;
                    swipFlags=0;

                } else if (layoutManager instanceof StaggeredGridLayoutManager) {//瀑布流布局
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT;
                    swipFlags=0;

                } else {//如果布局为线性布局

                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    swipFlags=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }
                return makeMovementFlags(dragFlags,swipFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                //滑动事件
                Collections.swap(names,viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
                adpter.notifyItemMoved(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());


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
}
