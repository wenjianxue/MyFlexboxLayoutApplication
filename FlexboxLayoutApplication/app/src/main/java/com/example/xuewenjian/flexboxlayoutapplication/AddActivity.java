package com.example.xuewenjian.flexboxlayoutapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuewenjian.flexboxlayoutapplication.utils.Util;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Random;

/**
 * Created by xuewenjian on 2017/11/26.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnAdd;
    private FlexboxLayout mFlexboxlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);

        mFlexboxlayout = (FlexboxLayout)findViewById(R.id.flexbox);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if(id == R.id.btn_add) {
            TextView textView = createBaseFlexItemTextView(index++);
            textView.setLayoutParams(createDefaultLayoutParams());
            mFlexboxlayout.addView(textView);
        }
    }
    private int index = 0;

    private TextView createBaseFlexItemTextView(int index) {
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.test_bg);
        textView.setText(String.valueOf(index + 1));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
    private FlexboxLayout.LayoutParams createDefaultLayoutParams() {

        int max=60;
        int min=10;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;

        int ran = 50 + Math.round(s);
        Log.d("ran",""+ran);
        FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(Util.dpToPixel(this, ran), Util.dpToPixel(this, 30));
        return lp;
    }
}
