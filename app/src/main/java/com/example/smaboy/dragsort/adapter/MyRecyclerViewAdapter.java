package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.bean.MoreService;

import static com.example.smaboy.dragsort.R.id.tv_grid_view_title;

/**
 * 类名: MyRecyclerViewAdapter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/11/1 11:17
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private MoreService.MyServiceBean myService;

    MyRecyclerViewAdapter(Context mContext, MoreService.MyServiceBean myService) {
        this.mContext = mContext;
        this.myService = myService;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.title.setText(myService.getServices().get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return myService.getServices() == null ? 0 : myService.getServices().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgUrl;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_grid_view_title);
            imgUrl = itemView.findViewById(R.id.image);
        }
    }

}
