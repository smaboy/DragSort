package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.bean.NewsBean;

import java.util.ArrayList;

/**
 * 类名: MyAdpter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/26 11:14
 */
public class MyAdpter  extends RecyclerView.Adapter<MyAdpter.MyViewHolder> {

    private Context mContext;
    private ArrayList<NewsBean> data;

    public MyAdpter(Context context, ArrayList<NewsBean> strs) {
        mContext=context;
        data=strs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(mContext).inflate(R.layout.recycler_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.title.setText(data.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        MyViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.tv_title);
        }
    }
}
