package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

/**
 * 开发人：黄启位
 * 开发时间：2016.7.2
 * 功能描述：可是与医生简介页面
 */
public class KsAndYsActivity extends BaseActivity implements View.OnClickListener{
    /** mYyjjBtn:医院简介
     *  mKsjjBtn:科室简介
     *  mYsjjBtn:医生简介
     * **/
private Button mYyjjBtn,mKsjjBtn,mYsjjBtn;
    //标题栏
    private LinearLayout mIncludeTitle;
    //标题
    private TextView mTextTitle;
    // 返回键
    private ImageButton mBackButton;



    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mYyjjBtn=(Button)findViewById(R.id.ks_and_ys_btn_yy);
        mKsjjBtn=(Button)findViewById(R.id.ks_and_ys_btn_ks);
        mYyjjBtn=(Button)findViewById(R.id.ks_and_ys_btn_yy);
        mIncludeTitle=(LinearLayout)findViewById(R.id.ks_and_title);
        mTextTitle=(TextView)mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.setting_main_screenshot);
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mYyjjBtn.setOnClickListener(this);
        mKsjjBtn.setOnClickListener(this);
        mYyjjBtn.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return(R.layout.activity_ks_and_ys);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back:
                finish();
                break;
            case R.id.ks_and_ys_btn_yy:
                Intent intent=new Intent(KsAndYsActivity.this,YyJs.class);
                startActivity(intent);
                break;
            case R.id.ks_and_ys_btn_ks:
                Intent intent1=new Intent(KsAndYsActivity.this,KsjsActivity.class);
                startActivity(intent1);
                break;
            case R.id.ks_and_ys_btn_ys:
                Toast.makeText(KsAndYsActivity.this,R.string.developming_wait,Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
    }
}
