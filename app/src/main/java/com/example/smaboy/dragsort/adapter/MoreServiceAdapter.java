package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.SecondActivity;
import com.example.smaboy.dragsort.bean.MoreService;

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
        RecyclerView.ViewHolder holder=null;
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

        } else if(getItemViewType(i)==BEFORE){
            BeforeViewHolder holder= (BeforeViewHolder) viewHolder;

        }else if(getItemViewType(i)==MIDDLE){

            MiddleViewHolder holder= (MiddleViewHolder) viewHolder;
        } else if(getItemViewType(i)==BEHIND){

            BehindViewHolder holder= (BehindViewHolder) viewHolder;
        }else if(getItemViewType(i)==INTELLIGENT) {

            IntelligentViewHolder holder= (IntelligentViewHolder) viewHolder;
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

        public MyServiceViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class BeforeViewHolder extends RecyclerView.ViewHolder{

        public BeforeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class MiddleViewHolder extends RecyclerView.ViewHolder{

        public MiddleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class BehindViewHolder extends RecyclerView.ViewHolder{

        public BehindViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    class IntelligentViewHolder extends RecyclerView.ViewHolder{

        public IntelligentViewHolder(@NonNull View itemView) {
            super(itemView);
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
}
