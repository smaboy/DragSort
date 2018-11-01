package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.bean.MoreService;
import com.example.smaboy.dragsort.bean.ServiceBean;

import java.util.List;

/**
 * 类名: GridViewAdapter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/30 16:41
 */
public class GridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ServiceBean> services;

    public GridViewAdapter(Context mContext, List<ServiceBean> services) {
        this.mContext = mContext;
        this.services = services;
    }

    @Override
    public int getCount() {
        return services == null ? 0 : services.size();
    }

    @Override
    public Object getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        View view;
        if(convertView==null) {
            view=LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, null);


        }else {
            view=convertView;
        }

        holder=new MyViewHolder(view);

        //设置数据
        holder.imageView.setImageResource(R.mipmap.ic_launcher_round);
        holder.title.setText(services.get(position).getTitle());


        return holder.mView;
    }

    class MyViewHolder {

        ImageView imageView;
        TextView title;
        View mView;

         MyViewHolder(View view) {
            imageView=view.findViewById(R.id.image);
             title=view.findViewById(R.id.tv_grid_view_title);

             mView=view;

        }

        View getView(){
             return mView;
        }
    }

}
