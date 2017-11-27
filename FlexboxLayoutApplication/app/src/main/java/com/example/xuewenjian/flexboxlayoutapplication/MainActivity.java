package com.example.xuewenjian.flexboxlayoutapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuewenjian.flexboxlayoutapplication.utils.Util;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAdd;
    private Button mBtnSearch;
    private FlexboxLayout mFlexboxlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnSearch = (Button)findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);

        mFlexboxlayout = (FlexboxLayout)findViewById(R.id.flexbox);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if(id == R.id.btn_add) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        } else if(id == R.id.btn_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }

}
