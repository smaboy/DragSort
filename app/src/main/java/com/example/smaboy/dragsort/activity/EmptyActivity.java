package com.example.smaboy.dragsort.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.smaboy.dragsort.R;

/**
 * 类名: EmptyActivity
 * 类作用描述: java类作用描述
 * 作者: Smaboy
 * 创建时间: 2018/11/1 16:17
 */
public class EmptyActivity extends Activity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_empty);

        Intent intent = getIntent();
        String sTitle=intent.getStringExtra("title");

        title = findViewById(R.id.tv_title);

        title.setText(TextUtils.isEmpty(sTitle) ? "" : sTitle);
    }
}
