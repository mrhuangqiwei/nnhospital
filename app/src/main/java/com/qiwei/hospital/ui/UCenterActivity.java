package com.qiwei.hospital.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class UCenterActivity extends BaseActivity implements  View.OnClickListener{
    private RelativeLayout mUcinfo;
    private  RelativeLayout mFriend;
    private  RelativeLayout mMyYy;
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mUcinfo=(RelativeLayout)findViewById(R.id.re_uc_center_info);
        mFriend=(RelativeLayout)findViewById(R.id.re_uc_center_friend);
        mMyYy=(RelativeLayout)findViewById(R.id.re_uc_center_yue);
        mMyYy.setOnClickListener(this);
        mUcinfo.setOnClickListener(this);
        mFriend.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.uc_center);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.re_uc_center_info:
               Intent intent=new Intent(UCenterActivity.this, UcInformation.class);
               startActivity(intent);
               break;
           case R.id.re_uc_center_friend:
               Intent intent1=new Intent(UCenterActivity.this, UccenterFriend.class);
               startActivity(intent1);
               break;
           case R.id.re_uc_center_yue:
               Intent intent2=new Intent(UCenterActivity.this, MyYYInfoActivity.class);
               startActivity(intent2);
           default:break;
       }
    }
}
