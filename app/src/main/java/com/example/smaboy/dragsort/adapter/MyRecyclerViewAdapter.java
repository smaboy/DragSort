package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smaboy.dragsort.EmptyActivity;
import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.bean.ServiceBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名: MyRecyclerViewAdapter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/11/1 11:17
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private List<ServiceBean> mySercices;
    private Context mContext;
    private Boolean isEdit;

    private OnClickItemDeleteListener onClickItemDeleteListener;
    public interface OnClickItemDeleteListener{//设置点击删除的接口
        void onClickItemDelete(int position, int itemCount);
    }

    void setOnClickItemDeleteListener(OnClickItemDeleteListener onClickItemDeleteListener) {
        this.onClickItemDeleteListener = onClickItemDeleteListener;
    }

    MyRecyclerViewAdapter(Context mContext, List<ServiceBean> mySercices, Boolean isEdit) {
        this.mContext = mContext;
        this.mySercices = mySercices;
        this.isEdit=isEdit;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.title.setText(mySercices.get(i).getTitle());

        if(isEdit){//编辑状态，item不可点，右上角tag表示显示
            myViewHolder.iv_edit_tag.setVisibility(View.VISIBLE);
            myViewHolder.iv_edit_tag.setImageResource(R.drawable.delete);
            myViewHolder.iv_edit_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //注意，这里不要直接使用pisition，不然会造成排序错乱，应该使用myViewHolder.getAdapterPosition()
                    onClickItemDeleteListener.onClickItemDelete(myViewHolder.getAdapterPosition(),getItemCount());
                }
            });

        } else {//非编辑状态，item可点，右上角tag不显示
            myViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent();
                    intent.putExtra("title",mySercices.get(i).getTitle());
                    intent.setClass(mContext,EmptyActivity.class);
                    mContext.startActivity(intent);
                }
            });

            myViewHolder.iv_edit_tag.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return mySercices==null ? 0 : mySercices.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView imgUrl;
        ImageView iv_edit_tag;
        View rootView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_grid_view_title);
            imgUrl = itemView.findViewById(R.id.image);
            iv_edit_tag = itemView.findViewById(R.id.iv_edit_tag);
            this.rootView=itemView;
        }
    }


}
