package com.qiwei.hospital.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 开发人：黄启位
 * 时间：2016-06-26
 * 功能描述：个人中心
 */
public class UCenterActivity extends BaseActivity implements  View.OnClickListener{
    /** mUcinfo:个人信息
     * mFriend：常用就诊人
     * mMyYy：我的预约
     */
    private RelativeLayout mUcinfo,mFriend,mMyYy;
    //返回按钮
    private ImageButton mBackBtn;


    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mUcinfo=(RelativeLayout)findViewById(R.id.re_uc_center_info);
        mFriend=(RelativeLayout)findViewById(R.id.re_uc_center_friend);
        mMyYy=(RelativeLayout)findViewById(R.id.re_uc_center_yue);
        mBackBtn=(ImageButton)findViewById(R.id.img_uc_center_back);
        mBackBtn.setOnClickListener(this);
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
               break;
           case R.id.img_uc_center_back:
               Intent intent3=new Intent(UCenterActivity.this,MainActivity.class);
               startActivity(intent3);
               break;
           default:break;
       }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent3=new Intent(UCenterActivity.this,MainActivity.class);
            startActivity(intent3);
        }
        return super.onKeyDown(keyCode, event);
    }
}
