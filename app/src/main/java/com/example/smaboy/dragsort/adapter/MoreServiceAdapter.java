package com.example.smaboy.dragsort.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smaboy.dragsort.activity.EmptyActivity;
import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.activity.SecondActivity;
import com.example.smaboy.dragsort.bean.MoreService;
import com.example.smaboy.dragsort.bean.ServiceBean;
import com.example.smaboy.dragsort.utils.SPUtils;
import com.example.smaboy.dragsort.view.MyGridView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    private List<ServiceBean> mySercices=new ArrayList<>();
    private List<ServiceBean> oldMySercices;
    private List<ServiceBean> beforeSercices;
    private List<ServiceBean> middleSercices;
    private List<ServiceBean> behindSercices;
    private List<ServiceBean> intelligentSercices;
    private ItemTouchHelper helper;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private MyServiceViewHolder myServiceViewholder;

    private Gson gson;

    public MoreServiceAdapter(Context context, MoreService moreService, Boolean isDefaultSort, Boolean isEdit) {

        mContext = context;
        this.moreService = moreService;
        this.isDefaultSort = isDefaultSort;
        this.isEdit = isEdit;
        layoutInflater = LayoutInflater.from(context);

        //初始化各服务组
        if (moreService != null) {
            if (moreService.getMyService().getServices() != null) {
                mySercices = moreService.getMyService().getServices();
            }
            if (moreService.getDefaul_sort() != null && moreService.getDefaul_sort().getBefore() != null && moreService.getDefaul_sort().getBefore().getServices() != null) {
                beforeSercices = moreService.getDefaul_sort().getBefore().getServices();
            }
            if (moreService.getDefaul_sort() != null && moreService.getDefaul_sort().getBefore() != null && moreService.getDefaul_sort().getMiddle().getServices() != null) {
                middleSercices = moreService.getDefaul_sort().getMiddle().getServices();
            }
            if (moreService.getDefaul_sort() != null && moreService.getDefaul_sort().getBefore() != null && moreService.getDefaul_sort().getBehind().getServices() != null) {
                behindSercices = moreService.getDefaul_sort().getBehind().getServices();
            }
            if (moreService.getIntelligent_sort() != null && moreService.getIntelligent_sort().getServices() != null && moreService.getIntelligent_sort().getServices().getServices() != null) {
                intelligentSercices = moreService.getIntelligent_sort().getServices().getServices();
            }
        }

        //初始化gson
        gson=new Gson();
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

            bindMyService((MyServiceViewHolder) viewHolder);

        } else if (getItemViewType(i) == BEFORE) {

            bindBeforeService((BeforeViewHolder) viewHolder);

        } else if (getItemViewType(i) == MIDDLE) {

            bindMiddleService((MiddleViewHolder) viewHolder);
        } else if (getItemViewType(i) == BEHIND) {

            bindBehindService((BehindViewHolder) viewHolder);

        } else if (getItemViewType(i) == INTELLIGENT) {

            bindIntelligentService((IntelligentViewHolder) viewHolder);
        }
    }

    private void bindIntelligentService(@NonNull IntelligentViewHolder viewHolder) {
        IntelligentViewHolder holder = viewHolder;
        //设置数据
        holder.title.setText(moreService.getIntelligent_sort().getTitle());

        //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = 1;
        holder.line.setLayoutParams(layoutParams);

        GridViewAdapter intelligentGridViewAdapter = new GridViewAdapter(mContext, intelligentSercices, isEdit);
        holder.gridView.setAdapter(intelligentGridViewAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                    go2Activity(moreService.getIntelligent_sort().getServices().getServices().get(position).getTitle());
                }
            }
        });

        intelligentGridViewAdapter.setOnClickItemAddListener(new GridViewAdapter.OnClickItemAddListener() {
            @Override
            public void onClickItmeAdd(int position, ImageView iv_edit_tag) {
                //点击添加按钮后，将服务添加到我的服务列表中，并将当前点击的服务标记为已添加状态(设置背景图片和设置添加字段的标识)
                mySercices.add(intelligentSercices.get(position));
                myRecyclerViewAdapter.notifyDataSetChanged();
                iv_edit_tag.setImageResource(R.drawable.added);
                intelligentSercices.get(position).setAdded(true);

                //设置默认组的添加标识
                setServiceFromDefultOtherService(intelligentSercices.get(position).getId(), true);

                //判别我的服务组的UI是否为空来控制显示
                setMyServiceIsEmptyUI(myServiceViewholder);
            }
        });
    }

    private void bindBehindService(@NonNull BehindViewHolder viewHolder) {
        BehindViewHolder holder = viewHolder;
        //设置数据
        holder.title.setText(moreService.getDefaul_sort().getBehind().getTitle());

        //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = 1;
        holder.line.setLayoutParams(layoutParams);

        GridViewAdapter behindGridViewAdapter = new GridViewAdapter(mContext, behindSercices, isEdit);
        holder.gridView.setAdapter(behindGridViewAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                    go2Activity(moreService.getDefaul_sort().getBehind().getServices().get(position).getTitle());
                }
            }
        });

        behindGridViewAdapter.setOnClickItemAddListener(new GridViewAdapter.OnClickItemAddListener() {
            @Override
            public void onClickItmeAdd(int position, ImageView iv_edit_tag) {
                //点击添加按钮后，将服务添加到我的服务列表中，并将当前点击的服务标记为已添加状态(设置背景图片和设置添加字段的标识)
                mySercices.add(behindSercices.get(position));
                myRecyclerViewAdapter.notifyDataSetChanged();
                iv_edit_tag.setImageResource(R.drawable.added);
                behindSercices.get(position).setAdded(true);

                //设置智能排序组的添加标识
                setServiceFromInteligentService(behindSercices.get(position).getId(), true);


                //判别我的服务组的UI是否为空来控制显示
                setMyServiceIsEmptyUI(myServiceViewholder);
            }
        });
    }

    private void bindMiddleService(@NonNull MiddleViewHolder viewHolder) {
        MiddleViewHolder holder = viewHolder;
        //设置数据
        holder.title.setText(moreService.getDefaul_sort().getMiddle().getTitle());

        //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = 1;
        holder.line.setLayoutParams(layoutParams);

        GridViewAdapter middleGridViewAdapter = new GridViewAdapter(mContext, middleSercices, isEdit);
        holder.gridView.setAdapter(middleGridViewAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                    go2Activity(moreService.getDefaul_sort().getMiddle().getServices().get(position).getTitle());
                }
            }
        });

        middleGridViewAdapter.setOnClickItemAddListener(new GridViewAdapter.OnClickItemAddListener() {
            @Override
            public void onClickItmeAdd(int position, ImageView iv_edit_tag) {
                //点击添加按钮后，将服务添加到我的服务列表中，并将当前点击的服务标记为已添加状态(设置背景图片和设置添加字段的标识)
                mySercices.add(middleSercices.get(position));
                myRecyclerViewAdapter.notifyDataSetChanged();
                iv_edit_tag.setImageResource(R.drawable.added);
                middleSercices.get(position).setAdded(true);
                //设置智能排序组的添加标识
                setServiceFromInteligentService(middleSercices.get(position).getId(), true);

                //判别我的服务组的UI是否为空来控制显示
                setMyServiceIsEmptyUI(myServiceViewholder);
            }
        });
    }

    private void bindBeforeService(@NonNull BeforeViewHolder viewHolder) {
        BeforeViewHolder holder = viewHolder;
        //设置数据
        holder.title.setText(moreService.getDefaul_sort().getBefore().getTitle());

        //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.line.getLayoutParams();
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = 1;
        holder.line.setLayoutParams(layoutParams);

        GridViewAdapter beforeGridViewAdapter = new GridViewAdapter(mContext, beforeSercices, isEdit);
        holder.gridView.setAdapter(beforeGridViewAdapter);

        //设置监听
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isEdit) {//编辑状态不响应点击监听,非编辑状态，响应
                    go2Activity(moreService.getDefaul_sort().getBefore().getServices().get(position).getTitle());
                }
            }
        });
        beforeGridViewAdapter.setOnClickItemAddListener(new GridViewAdapter.OnClickItemAddListener() {
            @Override
            public void onClickItmeAdd(int position, ImageView iv_edit_tag) {
                //点击添加按钮后，将服务添加到我的服务列表中，并将当前点击的服务标记为已添加状态(设置背景图片和设置添加字段的标识)

                mySercices.add(beforeSercices.get(position));
                myRecyclerViewAdapter.notifyDataSetChanged();

                iv_edit_tag.setImageResource(R.drawable.added);
                beforeSercices.get(position).setAdded(true);

                //设置智能排序组的添加标识
                setServiceFromInteligentService(beforeSercices.get(position).getId(), true);

                //判别我的服务组的UI是否为空来控制显示
                setMyServiceIsEmptyUI(myServiceViewholder);

            }
        });

    }

    private void bindMyService(@NonNull MyServiceViewHolder viewHolder) {
        myServiceViewholder = viewHolder;
        //设置数据
        myServiceViewholder.title.setText(moreService.getMyService().getTitle());
        myServiceViewholder.tv_no_add_my_service.setText(moreService.getMyService().getNo_add_my_service());
        myServiceViewholder.tv_click_right_up_button.setText(moreService.getMyService().getClick_right_up_button());

        setMyServiceIsEmptyUI(myServiceViewholder);

        //该步骤设置，可以使嵌套在recyclerview中的gridview正常显示
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) myServiceViewholder.line.getLayoutParams();
        layoutParams.width = mContext.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = 1;
        myServiceViewholder.line.setLayoutParams(layoutParams);

        myServiceViewholder.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(mContext, mySercices, isEdit);
        myServiceViewholder.recyclerView.setAdapter(myRecyclerViewAdapter);

        //此处还得设置recycerview的拖拽状态（开启和关闭通过isEdit判别）
        setTouchListener(myServiceViewholder.recyclerView);

        //设置UI
        myServiceViewholder.edit.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        myServiceViewholder.tv_my_service_tip.setVisibility(isEdit ? View.VISIBLE : View.GONE);
        myServiceViewholder.tv_my_service_tip.setText(moreService.getMyService().getTitle_tip());
        myServiceViewholder.edit.setText(moreService.getEdit());

        //设置删除监听
        myRecyclerViewAdapter.setOnClickItemDeleteListener(new MyRecyclerViewAdapter.OnClickItemDeleteListener() {
            @Override
            public void onClickItemDelete(int position, int itemCount) {


                //通知其他组，清空添加标识
                setServiceFromDefultOtherService(mySercices.get(position).getId(), false);
                setServiceFromInteligentService(mySercices.get(position).getId(), false);

                //移除我的服务中选定服务
                mySercices.remove(position);
                myRecyclerViewAdapter.notifyItemRemoved(position);

                //当删除的时候是最后一个item时，显示空的UI
                if(position==itemCount-1) {
                    setMyServiceIsEmptyUI(myServiceViewholder);
                }

                //刷新UI,刷新除了我的服务组之外其他所有服务组
                notifyItemRangeChanged(1,4);





            }
        });

        //设置编辑按钮的监听
        myServiceViewholder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //记录此时各服务组的数据
                saveServiceData();

                //改变编辑变量的状态
                isEdit = true;

                //隐藏编辑按钮
                myServiceViewholder.edit.setVisibility(View.GONE);

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
    }

    /**
     *
     * 以json数据串的形式，将数据保存到本地
     * 保存各服务组数据
     *
     */
    private void saveServiceData() {

        SPUtils.getInstance(mContext).put("my_service", gson.toJson(mySercices));
        SPUtils.getInstance(mContext).put("before", gson.toJson(beforeSercices));
        SPUtils.getInstance(mContext).put("middle", gson.toJson(middleSercices));
        SPUtils.getInstance(mContext).put("behind", gson.toJson(behindSercices));
        SPUtils.getInstance(mContext).put("intelligent", gson.toJson(intelligentSercices));


    }


    public void setServiceData(){

        //取出数据
        String my_service=SPUtils.getInstance(mContext).getString("my_service");
        String before=SPUtils.getInstance(mContext).getString("before");
        String middle=SPUtils.getInstance(mContext).getString("middle");
        String behind=SPUtils.getInstance(mContext).getString("behind");
        String intelligent=SPUtils.getInstance(mContext).getString("intelligent");


        //重新设置数据
        mySercices=getServiceBeanByJson(my_service);
        beforeSercices=getServiceBeanByJson(before);
        middleSercices=getServiceBeanByJson(middle);
        behindSercices=getServiceBeanByJson(behind);
        intelligentSercices=getServiceBeanByJson(intelligent);



    }

    private List<ServiceBean> getServiceBeanByJson(String me) {

        List<ServiceBean> sbs=new ArrayList<>();
        JsonArray jsonArray = gson.fromJson(me, JsonArray.class);
        for(int i = 0; i < jsonArray.size(); i++) {
            ServiceBean serviceBean=gson.fromJson(jsonArray.get(i),ServiceBean.class);
            sbs.add(serviceBean);

        }

        return sbs;
    }


    private void setMyServiceIsEmptyUI(MyServiceViewHolder holder) {
        if(mySercices==null||mySercices.size()==0) {
            holder.tv_no_add_my_service.setVisibility(View.VISIBLE);
            holder.tv_click_right_up_button.setVisibility(isEdit ? View.GONE : View.VISIBLE);
        }else {
            holder.tv_no_add_my_service.setVisibility(View.GONE);
            holder.tv_click_right_up_button.setVisibility(View.GONE);
        }
    }

    /**
     * 这边提供点击服务跳转的方法
     *
     * @param title
     */
    private void go2Activity(String title) {
        Intent intent = new Intent();
        intent.setClass(mContext, EmptyActivity.class);
        intent.putExtra("title", title);
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
        TextView tv_no_add_my_service;
        TextView tv_click_right_up_button;
        RecyclerView recyclerView;
        TextView line;
        TextView edit;
        TextView tv_my_service_tip;

        MyServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_my_service_title);
            tv_no_add_my_service = itemView.findViewById(R.id.tv_no_add_my_service);
            tv_click_right_up_button = itemView.findViewById(R.id.tv_click_right_up_button);
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

    private void setTouchListener(RecyclerView recyclerView) {
        if (helper != null) {
            return;
        }
        helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
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
                Collections.swap(mySercices, viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
                myRecyclerViewAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());


                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //侧滑事件
                mySercices.remove(viewHolder.getAdapterPosition());
                myRecyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());


            }

            @Override
            public boolean isLongPressDragEnabled() {//默认支持长按拖拽
                return isEdit;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {//默认支持侧滑
                return isEdit;
            }
        });

        helper.attachToRecyclerView(recyclerView);

    }


    /**
     * 通过提供的id，查找在智能排序组中的对应的服务
     *
     * @param id    服务id
     * @param added 添加状态标识字段
     * @return 成功返回标识
     */
    private Boolean setServiceFromInteligentService(String id, Boolean added) {

        for (int i = 0; i < intelligentSercices.size(); i++) {

            if (id.equals(intelligentSercices.get(i).getId())) {
                intelligentSercices.get(i).setAdded(added);
                return true;
            }
        }

        return false;
    }

    /**
     * 通过提供的id和添加标识符，查找在默认排序其他组中的服务，并设值添加标识
     *
     * @param id    服务id
     * @param added 添加状态标识字段
     * @return 返回标识
     */
    private Boolean setServiceFromDefultOtherService(String id, Boolean added) {

        for (int i = 0; i < beforeSercices.size(); i++) {

            if (id.equals(beforeSercices.get(i).getId())) {
                beforeSercices.get(i).setAdded(added);
                return true;
            }
        }
        for (int i = 0; i < middleSercices.size(); i++) {

            if (id.equals(middleSercices.get(i).getId())) {
                middleSercices.get(i).setAdded(added);
                return true;
            }
        }
        for (int i = 0; i < behindSercices.size(); i++) {

            if (id.equals(behindSercices.get(i).getId())) {
                behindSercices.get(i).setAdded(added);
                return true;
            }
        }

        return false;
    }


}


