package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smaboy.dragsort.EmptyActivity;
import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.SecondActivity;
import com.example.smaboy.dragsort.bean.MoreService;
import com.example.smaboy.dragsort.view.MyGridView;

import java.util.Collections;

/**
 * 类名: MoreServiceAdapter
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/30 14:43
 * <p>
 * 默认排序：recycler中有 我的服务，行前，行中，行后四个模块 ，右上角标题为智能排序，左上角不显示
 * 智能排序：recycler中有 我的服务，智能排序两个模块，右上角标题为默认排序，左上角不显示
 * <p>
 * 当处于编辑模式 ：我的服务可以拖拽排序（也可以点击删除图标），其他模块根据我的服务中的状态做相应的变化（显示已添加图标或可以添加图标）
 */
public class MoreServiceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private MoreService moreService;
    private Boolean isEdit;//编辑模式判别
    private Boolean isDefaultSort;//排序模式判别


    private static final int MY_SERVICE = 0;//我的服务
    private static final int BEFORE = 1;//行前
    private static final int MIDDLE = 2;//行中
    private static final int BEHIND = 3;//行后
    private static final int INTELLIGENT = 4;//智能排序
    private int currentType = MY_SERVICE;

    private LayoutInflater layoutInflater;

    public MoreServiceAdapter(Context context, MoreService moreService, Boolean isDefaultSort, Boolean isEdit) {

        mContext = context;
        this.moreService = moreService;
        this.isDefaultSort = isDefaultSort;
        this.isEdit = isEdit;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder;
        if (getItemViewType(i) == MY_SERVICE) {

            holder = new MyServiceViewHolder(layoutInflater.inflate(R.layout.recycler_my_service, null));

        } else if (getItemViewType(i) == BEFORE) {
            holder = new BeforeViewHolder(layoutInflater.inflate(R.layout.recycler_before, null));
        } else if (getItemViewType(i) == MIDDLE) {
            holder = new MiddleViewHolder(layoutInflater.inflate(R.layout.recycler_middle, null));
        } else if (getItemViewType(i) == BEHIND) {
            holder = new BehindViewHolder(layoutInflater.inflate(R.layout.recycler_behind, null));
        } else if (getItemViewType(i) == INTELLIGENT) {
            holder = new IntelligentViewHolder(layoutInflater.inflate(R.layout.recycler_intellgent, null));
        } else {
            holder = new MyServiceViewHolder(layoutInflater.inflate(R.layout.recycler_my_service, null));
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (getItemViewType(i) == MY_SERVICE) {

            final MyServiceViewHolder holder = (MyServiceViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getMyService().getTitle());


            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = 1;
            holder.line.setLayoutParams(layoutParams);

            holder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(mContext, moreService.getMyService(), isEdit);
            holder.recyclerView.setAdapter(myRecyclerViewAdapter);

            if(isEdit){
                holder.edit.setVisibility(View.GONE);

                holder.tv_my_service_tip.setText(moreService.getMyService().getTitle_tip());
                holder.tv_my_service_tip.setVisibility(View.VISIBLE);

                //此处还得设置recycerview的拖拽状态（开启）
                setTouchListener(myRecyclerViewAdapter,holder.recyclerView,moreService.getMyService());




            } else {

                holder.edit.setText(moreService.getEdit());
                holder.edit.setVisibility(View.VISIBLE);

                holder.tv_my_service_tip.setVisibility(View.GONE);


                //此处还得设置recycerview的拖拽状态（关闭），不设置触摸监听即可



            }

            //设置编辑按钮的监听
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //改变编辑变量的状态
                    isEdit = true;

                    //隐藏编辑按钮
                    holder.edit.setVisibility(View.GONE);

                    //根据编辑变量来设置值，并告知相应的activity
                    //修改activity的标题
                    if (mContext instanceof SecondActivity) {
                        SecondActivity activity = (SecondActivity) mContext;
                        activity.isEdit = true;

                        activity.tv_cancel.setText(moreService.getCancel());//取消
                        activity.tv_cancel.setVisibility(View.VISIBLE);//显示取消
                        activity.tv_more_service.setText(moreService.getEdit_my_service());//编辑我的服务
                        activity.tv_sort.setText(moreService.getFinish());//完成
                    }

                    //改变更多服务中所有图标的样式（右上角显示，相应状态，我的服务中显示tips，便是可以拖拽排序）



                    //刷新
                    notifyDataSetChanged();

                }
            });

        } else if (getItemViewType(i) == BEFORE) {
            BeforeViewHolder holder = (BeforeViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getBefore().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = 1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext, moreService.getDefaul_sort().getBefore().getServices(),isEdit));
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                        go2Activity(moreService.getDefaul_sort().getBefore().getServices().get(position).getTitle());
                    }
                }
            });
        } else if (getItemViewType(i) == MIDDLE) {

            MiddleViewHolder holder = (MiddleViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getMiddle().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = 1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext, moreService.getDefaul_sort().getMiddle().getServices(), isEdit));
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                        go2Activity(moreService.getDefaul_sort().getMiddle().getServices().get(position).getTitle());
                    }
                }
            });
        } else if (getItemViewType(i) == BEHIND) {

            BehindViewHolder holder = (BehindViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getDefaul_sort().getBehind().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = 1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext, moreService.getDefaul_sort().getBehind().getServices(), isEdit));
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                        go2Activity(moreService.getDefaul_sort().getBehind().getServices().get(position).getTitle());
                    }
                }
            });

        } else if (getItemViewType(i) == INTELLIGENT) {

            IntelligentViewHolder holder = (IntelligentViewHolder) viewHolder;
            //设置数据
            holder.title.setText(moreService.getIntelligent_sort().getTitle());

            //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
            layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
            layoutParams.height = 1;
            holder.line.setLayoutParams(layoutParams);

            holder.gridView.setAdapter(new GridViewAdapter(mContext, moreService.getIntelligent_sort().getServices().getServices(), isEdit));
            holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                        go2Activity(moreService.getIntelligent_sort().getServices().getServices().get(position).getTitle());
                    }
                }
            });
        }
    }

    /**
     * 这边提供点击服务跳转的方法
     *
     * @param title
     */
    private void go2Activity(String title) {
        Intent intent=new Intent();
        intent.setClass(mContext,EmptyActivity.class);
        intent.putExtra("title",title);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return isDefaultSort ? 4 : 2;
    }

    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case 0:

                currentType = MY_SERVICE;
                break;
            case 1:

                if (isDefaultSort) {//默认排序
                    currentType = BEFORE;

                } else {
                    currentType = INTELLIGENT;
                }
                break;
            case 2:

                currentType = MIDDLE;
                break;
            case 3:

                currentType = BEHIND;
                break;

            default:
                break;
        }

        return currentType;
    }

    class MyServiceViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        RecyclerView recyclerView;
        TextView line;
        TextView edit;
        TextView tv_my_service_tip;

        MyServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_my_service_title);
            recyclerView = itemView.findViewById(R.id.recycler_my_service);
            line = itemView.findViewById(R.id.tv_line);
            edit = itemView.findViewById(R.id.tv_edit);
            tv_my_service_tip = itemView.findViewById(R.id.tv_my_service_tip);
        }


    }

    class BeforeViewHolder extends RecyclerView.ViewHolder {

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

    class MiddleViewHolder extends RecyclerView.ViewHolder {

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

    class BehindViewHolder extends RecyclerView.ViewHolder {

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

    class IntelligentViewHolder extends RecyclerView.ViewHolder {

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

    private int dp2px(int dp) {

        return (int) (mContext.getResources().getDisplayMetrics().density * dp);
    }

    private void setTouchListener(final MyRecyclerViewAdapter adapter, RecyclerView recyclerView, final MoreService.MyServiceBean myService) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
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
                Collections.swap(myService.getServices(), viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());


                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //侧滑事件
                myService.getServices().remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

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

        helper.attachToRecyclerView(recyclerView);

    }

}


