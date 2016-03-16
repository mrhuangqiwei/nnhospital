package com.qiwei.hospital.ui;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;

public class MZFYCXActivity extends BaseActivity implements View.OnClickListener {
    private EditText mBrghxh;
    private ImageButton mChaxun;
    private TextView mBrxm;
    private  TextView mBrjzrq;
    private  TextView mJtzz;
    private  TextView mGhys;
    private TextView mZffy;
    private TextView mGhf;
    private TextView mZcf;
    private  TextView mQtf;
    private TextView mXts;
    private View mRechx;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private HttpConnSoap Soap = new HttpConnSoap();
    private Handler myhandler=new Handler(){
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            //crrayList.clear();
            //ArrayList<String> crrayList=(ArrayList<String>) msg.obj;

            crrayList=(ArrayList<String>) msg.obj;

            if(crrayList.size()>1) {
                mBrxm.setText(crrayList.get(0));
                mBrjzrq.setText(crrayList.get(1));
                mJtzz.setText(crrayList.get(2));
                mZffy.setText(crrayList.get(3));
                mGhys.setText(crrayList.get(4));
                mZcf.setText(crrayList.get(5));
                mGhf.setText(crrayList.get(6));
                mQtf.setText(crrayList.get(7));
                mXts.setVisibility(View.VISIBLE);
                mRechx.setVisibility(View.GONE);

            }
            else {
                Toast.makeText(MZFYCXActivity.this, "住院号不正确或网络连接失败，请检查！", Toast.LENGTH_SHORT).show();
            }


        }
    };
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mRechx=(View)findViewById(R.id.re_mzfy_chx);
        mChaxun=(ImageButton)findViewById(R.id.zyfy_btn_ghxh);
        mBrghxh=(EditText)findViewById(R.id.mzfy_tv_brrygh);
        mBrxm=(TextView)findViewById(R.id.zyfy_tv_brxm);
        mBrjzrq=(TextView)findViewById(R.id.mzfy_tv_brjzrq);
        mJtzz=(TextView)findViewById(R.id.mzfy_tv_brjtzj);
        mGhys=(TextView)findViewById(R.id.mzfy_tv_brghys);
        mZffy=(TextView)findViewById(R.id.mzfy_tv_brzfy);
        mGhf=(TextView)findViewById(R.id.mzfy_tv_brghf);
        mZcf=(TextView)findViewById(R.id.mzfy_tv_brzcf);
        mQtf=(TextView)findViewById(R.id.mzfy_tv_brqtf);
        mXts=(TextView)findViewById(R.id.mzfy_tv_xts);
        mChaxun.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.activity_mzfycx);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
      case R.id.zyfy_btn_ghxh:
            function();
            break;
            default:
                break;
        }
    }
    private void function(){
        InputMethodManager imm= (InputMethodManager)getSystemService(MZFYCXActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow( mBrghxh.getWindowToken(), 0);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        String zyhl= mBrghxh.getText().toString();
        arrayList.add("mzh");
        brrayList.add(zyhl);
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getbqcybz", arrayList, brrayList);

                    Message message=new Message();
                    //message.obj=crrayList;

                    message.obj=crrayList;

                    MZFYCXActivity.this.myhandler.sendMessage(message);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();



    }


}
