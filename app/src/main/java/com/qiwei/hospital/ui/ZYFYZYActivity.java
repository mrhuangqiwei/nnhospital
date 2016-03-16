package com.qiwei.hospital.ui;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.NnApplication.NnApplication;

import java.util.ArrayList;

public class ZYFYZYActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTextYj;
    private TextView mTextYe;
    private  TextView mTextYy;
    private TextView mTextJz;
    private ImageButton mImgback;
    private TextView  mBrxm;
    private NnApplication app;
    private ArrayList<String> zyfyrrayList = new ArrayList<String>();
    private ArrayList<String>zyzyrrayList = new ArrayList<String>();
    private Handler myhandler=new Handler(){
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            zyzyrrayList=(ArrayList<String>)msg.obj;
            mBrxm.setText( zyzyrrayList.get(4));
            mTextYj.setText( zyzyrrayList.get(1));
            mTextYe.setText( zyzyrrayList.get(3));
            mTextJz.setText( zyzyrrayList.get(0));
            mTextYy.setText( zyzyrrayList.get(2));
            //crrayList.clear();
            //ArrayList<String> crrayList=(ArrayList<String>) msg.obj;


    }};


    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mBrxm=(TextView)findViewById(R.id.zyfy_tv_brxm);
        mTextYj=(TextView)findViewById(R.id.zyfy_tv_yjje  );
        mTextYe= (TextView) findViewById(R.id.zyfy_tv_zhye);
        mTextYy=(TextView) findViewById(R.id.zyfy_tv_yyhj);
        mTextJz=(TextView)findViewById(R.id.zyfy_tv_jzxe);
        mImgback=(ImageButton)findViewById(R.id.img_zyfy_back);
        app=(NnApplication)getApplication();
        Function();

    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return(R.layout.activity_zyfyzy);
    }




    private void Function() {
        new Thread() {
            @Override
            public void run() {
                try {

                    zyfyrrayList=app.getZyfyList();

                    Message message=new Message();
                    //message.obj=crrayList;

                    message.obj= zyfyrrayList;
                    ZYFYZYActivity.this.myhandler.sendMessage(message);


                } catch (Exception e) {
                }
            }
        }.start();
    }

    private void inintwindow() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zyfyzy);
    }

    @Override
    public void onClick(View v) {

    }
}
