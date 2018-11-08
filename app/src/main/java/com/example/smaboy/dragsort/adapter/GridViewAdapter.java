package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smaboy.dragsort.EmptyActivity;
import com.example.smaboy.dragsort.R;
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
    private final Boolean isEdit;

    /**
     * 点击添加的接口
     *
     */
    interface OnClickItemAddListener{

        void onClickItmeAdd(int position, ImageView iv_edit_tag);
    }

    private OnClickItemAddListener onClickItemAddListener;
    public void setOnClickItemAddListener(OnClickItemAddListener onClickItemAddListener){
        this.onClickItemAddListener=onClickItemAddListener;
    }


    GridViewAdapter(Context mContext, List<ServiceBean> services, Boolean isEdit) {
        this.mContext = mContext;
        this.services = services;
        this.isEdit = isEdit;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, null);


        } else {
            view = convertView;
        }

        holder = new MyViewHolder(view);

        holder.imageView.setImageResource(R.mipmap.ic_launcher_round);
        holder.title.setText(services.get(position).getTitle());

        if(isEdit){
            holder.iv_edit_tag.setVisibility(View.VISIBLE);

            //设置右上角标识图标
            if(services.get(position).getAdded()){//已经被添加
                holder.iv_edit_tag.setImageResource(R.drawable.added);
            } else {
                holder.iv_edit_tag.setImageResource(R.drawable.add);
            }



            holder.iv_edit_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(services.get(position).getAdded()){//已经被添加
                        Toast.makeText(mContext, "已经被添加", Toast.LENGTH_SHORT).show();
                    } else {
                        onClickItemAddListener.onClickItmeAdd(position, holder.iv_edit_tag);
                    }

                }
            });

        } else {
            holder.iv_edit_tag.setVisibility(View.GONE);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(mContext,EmptyActivity.class);
                    intent.putExtra("title",services.get(position).getTitle());
                    mContext.startActivity(intent);
                }
            });

        }


        return holder.mView;
    }

    class MyViewHolder {

        ImageView imageView;
        ImageView iv_edit_tag;
        TextView title;
        View mView;

        MyViewHolder(View view) {
            imageView = view.findViewById(R.id.image);
            iv_edit_tag = view.findViewById(R.id.iv_edit_tag);
            title = view.findViewById(R.id.tv_grid_view_title);


            mView = view;

        }

    }

}
