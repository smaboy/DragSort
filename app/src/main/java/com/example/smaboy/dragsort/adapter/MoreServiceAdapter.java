package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.SecondActivity;
import com.example.smaboy.dragsort.bean.MoreService;
import com.example.smaboy.dragsort.view.MyGridView;

/**
 * 类名: MoreServiceAdapter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/30 14:43
 *
 * 默认排序：recycler中有 我的服务，行前，行中，行后四个模块 ，右上角标题为智能排序，左上角不显示
 * 智能排序：recycler中有 我的服务，智能排序两个模块，右上角标题为默认排序，左上角不显示
 *
 * 当处于编辑模式 ：我的服务可以拖拽排序（也可以点击删除图标），其他模块根据我的服务中的状态做相应的变化（显示已添加图标或可以添加图标）
 *
 *
 */
public class MoreServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private MoreService moreService;
    private Boolean isEdit;//编辑模式判别
    private Boolean isDefaultSort;//排序模式判别


    private static final int MY_SERVICE=0;//我的服务
    private static final int BEFORE=1;//行前
    private static final int MIDDLE=2;//行中
    private static final int BEHIND=3;//行后
    private static final int INTELLIGENT=4;//智能排序
    private int currentType=MY_SERVICE;

    private LayoutInflater layoutInflater;

    public MoreServiceAdapter(Context context, MoreService moreService, Boolean isDefaultSort, Boolean isEdit) {

        mContext=context;
        this.moreService=moreService;
        this.isDefaultSort=isDefaultSort;
        this.isEdit=isEdit;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder;
        if(getItemViewType(i)==MY_SERVICE){

            holder=new MyServiceViewHolder(layoutInflater.inflate(R.layout.recycler_my_service,null));

        } else if(getItemViewType(i)==BEFORE){
            holder=new BeforeViewHolder(layoutInflater.inflate(R.layout.recycler_before,null));
        }else if(getItemViewType(i)==MIDDLE){
            holder=new MiddleViewHolder(layoutInflater.inflate(R.layout.recycler_middle,null));
        } else if(getItemViewType(i)==BEHIND){
            holder=new BehindViewHolder(layoutInflater.inflate(R.layout.recycler_behind,null));
        }else if(getItemViewType(i)==INTELLIGENT) {
            holder=new IntelligentViewHolder(layoutInflater.inflate(R.layout.recycler_intellgent,null));
        }else {
            holder=new MyServiceViewHolder(layoutInflater.inflate(R.layout.recycler_my_service,null));
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if(getItemViewType(i)==MY_SERVICE){

            MyServiceViewHolder holder= (MyServiceViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getMyService().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width=mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height=1;
            holder.line.setLayoutParams(layoutParams);

            holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
            holder.recyclerView.setAdapter(new MyRecyclerViewAdapter(mContext,moreService.getMyService()));

        } else if(getItemViewType(i)==BEFORE){
            BeforeViewHolder holder= (BeforeViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getBefore().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width=mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height=1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext,moreService.getDefaul_sort().getBefore().getServices()));
        }else if(getItemViewType(i)==MIDDLE){

            MiddleViewHolder holder= (MiddleViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getMiddle().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width=mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height=1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext,moreService.getDefaul_sort().getMiddle().getServices()));
        } else if(getItemViewType(i)==BEHIND){

            BehindViewHolder holder= (BehindViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getBehind().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width=mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height=1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext,moreService.getDefaul_sort().getBehind().getServices()));
        }else if(getItemViewType(i)==INTELLIGENT) {

            IntelligentViewHolder holder= (IntelligentViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getIntelligent_sort().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width=mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height=1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext,moreService.getIntelligent_sort().getServices().getServices()));
        }
    }

    @Override
    public int getItemCount() {
        return isDefaultSort ? 4 :2;
    }

    @Override
    public int getItemViewType(int position) {
        
        switch (position) {
            case 0 :

                currentType=MY_SERVICE;
                break;
            case 1 :

                if(isDefaultSort){//默认排序
                    currentType=BEFORE;

                } else {
                    currentType=INTELLIGENT;
                }
                break;
            case 2 :

                currentType=MIDDLE;
                break;
            case 3 :

                currentType=BEHIND;
                break;

            default:
                break;
        }
        
        return currentType;
    }

    class MyServiceViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        RecyclerView recyclerView;
        TextView line;
        MyServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_my_service_title);
            recyclerView = itemView.findViewById(R.id.recycler_my_service);
            line = itemView.findViewById(R.id.tv_line);
        }
    }
    class BeforeViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        MyGridView gridView;
        TextView line;
        BeforeViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_before_title);
            gridView = itemView.findViewById(R.id.gv_before_grid_view);
            line = itemView.findViewById(R.id.tv_line);
        }
    }
    class MiddleViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView line;
        MyGridView gridView;
        MiddleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_middle_title);
            line = itemView.findViewById(R.id.tv_line);
            gridView = itemView.findViewById(R.id.gv_middle_grid_view);
        }
    }
    class BehindViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        MyGridView gridView;
        TextView line;
        BehindViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_behind_title);
            gridView = itemView.findViewById(R.id.gv_behind_grid_view);
            line = itemView.findViewById(R.id.tv_line);
        }
    }
    class IntelligentViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        MyGridView gridView;
        TextView line;
        IntelligentViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_intelligent_title);
            gridView = itemView.findViewById(R.id.gv_intelligent_grid_view);
            line = itemView.findViewById(R.id.tv_line);
        }
    }

    public Boolean getEdit() {
        return isEdit;
    }

    public void setEdit(Boolean edit) {
        isEdit = edit;
    }

    public Boolean getDefaultSort() {
        return isDefaultSort;
    }

    public void setDefaultSort(Boolean defaultSort) {
        isDefaultSort = defaultSort;
    }

    private int dp2px(int dp){

        return (int) (mContext.getResources().getDisplayMetrics().density*dp);
    }
}


