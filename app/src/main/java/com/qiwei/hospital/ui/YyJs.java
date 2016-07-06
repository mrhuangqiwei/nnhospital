package com.qiwei.hospital.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

public class YyJs extends BaseActivity  implements View.OnClickListener{
    private Button mYyjjBtn,mKsjjBtn,mYsjjBtn;
    //标题栏
    private LinearLayout mIncludeTitle;
    //标题
    private TextView mTextTitle;
    private  ImageButton mBackButton;
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.yy_js_title);
        mTextTitle=(TextView)mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.ks_and_ys_yyjs);
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_yy_js);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back:
                finish();
                break;
            default:break;
        }
    }
}
