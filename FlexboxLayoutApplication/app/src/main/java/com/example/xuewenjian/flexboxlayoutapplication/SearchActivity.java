package com.example.xuewenjian.flexboxlayoutapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xuewenjian.flexboxlayoutapplication.utils.Util;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Random;

/**
 * Created by xuewenjian on 2017/11/26.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnAdd;
    private FlexboxLayout mFlexboxlayout;
    private EditText mEtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBtnAdd = (Button)findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);

        mEtSearch = (EditText)findViewById(R.id.et_search);
        mFlexboxlayout = (FlexboxLayout)findViewById(R.id.flexbox);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if(id == R.id.btn_add) {
            String tx = mEtSearch.getText().toString();
            mEtSearch.setText("");
            TextView textView = createBaseFlexItemTextView(tx);
            textView.setLayoutParams(createDefaultLayoutParams());
            mFlexboxlayout.addView(textView);
        }
    }
    private int index = 0;

    private TextView createBaseFlexItemTextView(String index) {
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.test_bg);
        textView.setText(String.valueOf(index + 1));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }
    private FlexboxLayout.LayoutParams createDefaultLayoutParams() {
        View view = mFlexboxlayout.getChildAt(mFlexboxlayout.getChildCount()-1);
        FlexboxLayout.LayoutParams lp = (FlexboxLayout.LayoutParams) view.getLayoutParams();
        lp.height = Util.dpToPixel(this, 55);
        return lp;
    }
}
