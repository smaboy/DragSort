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
import android.widget.Toast;

import com.example.smaboy.dragsort.EmptyActivity;
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
    private Boolean isEdit;

    MyRecyclerViewAdapter(Context mContext, MoreService.MyServiceBean myService, Boolean isEdit) {
        this.mContext = mContext;
        this.myService = myService;
        this.isEdit=isEdit;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.title.setText(myService.getServices().get(i).getTitle());

        if(isEdit){//编辑状态，item不可点，右上角tag表示显示
            myViewHolder.iv_edit_tag.setVisibility(View.VISIBLE);
            myViewHolder.iv_edit_tag.setImageResource(R.drawable.delete);
            myViewHolder.iv_edit_tag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,myService.getServices().get(i).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {//非编辑状态，item可点，右上角tag不显示
            myViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent();
                    intent.putExtra("title",myService.getServices().get(i).getTitle());
                    intent.setClass(mContext,EmptyActivity.class);
                    mContext.startActivity(intent);
                }
            });

            myViewHolder.iv_edit_tag.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return myService.getServices() == null ? 0 : myService.getServices().size();
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
