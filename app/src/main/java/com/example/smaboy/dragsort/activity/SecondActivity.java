package com.example.smaboy.dragsort.activity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;
import com.example.smaboy.dragsort.adapter.MoreServiceAdapter;
import com.example.smaboy.dragsort.bean.MoreService;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 类名: SecondActivity
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/10/29 10:51
 */
public class SecondActivity extends Activity implements View.OnClickListener {

    public TextView tv_cancel;
    public TextView tv_more_service;
    public TextView tv_sort;
    private RecyclerView recyclerview;
    private MoreService moreService;
    public Boolean isEdit;//判断当前是否为编辑模式
    public Boolean isDefaultSort;//判断当前是否为默认排序

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001://设置数据
                    setData();

                    break;
            }

            return false;
        }
    });
    private MoreServiceAdapter moreServiceAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);


        init();

        initData();

        setEvent();

    }

    private void setEvent() {
        tv_cancel.setOnClickListener(this);//取消的监听
        tv_sort.setOnClickListener(this);//默认排序，智能排序，完成等的监听

    }

    //设置数据
    private void setData() {
        if (moreService == null) {
            return;
        }

        //初始化页面状态
        isEdit = false;
        isDefaultSort = true;

        tv_cancel.setVisibility(View.GONE);
        tv_cancel.setText(TextUtils.isEmpty(moreService.getCancel()) ? "" : moreService.getCancel());
        tv_more_service.setText(TextUtils.isEmpty(moreService.getTitle()) ? "" : moreService.getTitle());
        tv_sort.setText(TextUtils.isEmpty(moreService.getIntelligent_sort().getTitle()) ? "" : moreService.getIntelligent_sort().getTitle());

        //设置recyclerview
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        moreServiceAdapter = new MoreServiceAdapter(this, moreService, isDefaultSort, isEdit);
        recyclerview.setAdapter(moreServiceAdapter);

    }

    private void initData() {
        //读取asset中指定文件的内容
        new Thread(new Runnable() {
            @Override
            public void run() {
                //子线程中进行读写操作
                String jsonFromAsset = getJsonFromAsset("MoreServiceJS.json");
                moreService = new Gson().fromJson(jsonFromAsset, MoreService.class);

                //设置数据，在UI线程操作
                handler.sendEmptyMessage(0x001);


            }
        }).start();


    }

    private void init() {
        tv_cancel = findViewById(R.id.tv_cancel);
        tv_more_service = findViewById(R.id.tv_more_service);
        tv_sort = findViewById(R.id.tv_sort);
        recyclerview = findViewById(R.id.recyclerview);
    }


    private String getJsonFromAsset(String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = getResources().getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel://取消
                //该事件的发生说明现在已处于编辑模式
                tv_cancel.setVisibility(View.GONE);
                tv_more_service.setText(moreService.getTitle());
                if(isDefaultSort){

                    tv_sort.setText(moreService.getIntelligent_sort().getTitle());
                } else {
                    tv_sort.setText(moreService.getDefaul_sort().getTitle());

                }

                //取消编辑状态,刷新适配器的数据
                isEdit=false;

                //回复编辑之前的数据
                moreServiceAdapter.setServiceData();

                //刷新recyclerview
                moreServiceAdapter.setEdit(false);
                moreServiceAdapter.notifyDataSetChanged();


                break;
            case R.id.tv_sort://默认排序、智能排序、完成
                if (isEdit) {//是否为编辑模式
                    //编辑模式(此时显示的是完成，点击之后应该回归到上次的排序，并且保存我的服务中的排序和新增及删除的服务)
                    //1.保存我的服务，更新标题栏数据

//                    saveMyService();
                    tv_cancel.setVisibility(View.GONE);
                    tv_more_service.setText(moreService.getTitle());
                    if(isDefaultSort){

                        tv_sort.setText(moreService.getIntelligent_sort().getTitle());
                    } else {
                        tv_sort.setText(moreService.getDefaul_sort().getTitle());

                    }


                    //取消编辑状态,刷新适配器的数据

                    isEdit=false;

                    //刷新recyclerview
                    moreServiceAdapter.setEdit(false);
                    moreServiceAdapter.notifyDataSetChanged();


                } else {

                    if (isDefaultSort) {//默认排序
                        //当前是默认排序，显示的文案为智能排序，点击之后为智能排序，文案为默认排序
                        isDefaultSort = false;

                        tv_sort.setText(TextUtils.isEmpty(moreService.getDefaul_sort().getTitle()) ? "" : moreService.getDefaul_sort().getTitle());

                    } else {
                        isDefaultSort = true;
                        tv_sort.setText(TextUtils.isEmpty(moreService.getIntelligent_sort().getTitle()) ? "" : moreService.getIntelligent_sort().getTitle());
                    }

                    //刷新recyclerview
                    moreServiceAdapter.setDefaultSort(isDefaultSort);
                    moreServiceAdapter.notifyDataSetChanged();
                }

                break;


        }

    }
}
