package com.qiwei.hospital.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

/**
 * 开发人：黄启位
 * 时间：2016-7-12
 * 功能：名医馆
 */
public class MingYiActivity extends BaseActivity implements View.OnClickListener{
    /**mIncludeTitle:标题栏
     */
    private LinearLayout mIncludeTitle;
    /**
     * mTextTitle:标题栏

     */
    private TextView mTextTitle;
    // 返回键
    private ImageButton mBackButton;
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.mingyi_title);
        mTextTitle=(TextView) mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText("正在开发中");
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_ming_yi);
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
