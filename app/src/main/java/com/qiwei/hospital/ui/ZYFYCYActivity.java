package com.qiwei.hospital.ui;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.NnApplication.NnApplication;

import java.util.ArrayList;

public class ZYFYCYActivity extends BaseActivity {
    private TextView mBrxm;
    private TextView mZfy;
    private TextView mZhyl;
    private TextView mZdl;
    private TextView mZll;
    private  TextView mKfl;
    private  TextView mZyl;
    private  TextView mXyl;
    private  TextView mZylf;
    private  TextView mXylf;
    private TextView mXyhxl;
    private TextView mHcl;
    private  TextView mQtl;
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
            mBrxm.setText( zyzyrrayList.get(11));
            mZfy.setText( zyzyrrayList.get(0));
            mZhyl.setText(zyzyrrayList.get(1));
           mZdl.setText( zyzyrrayList.get(2));
            mZll.setText( zyzyrrayList.get(3));
            mKfl.setText( zyzyrrayList.get(4));
            mZyl.setText( zyzyrrayList.get(5));
            mXyl.setText( zyzyrrayList.get(6));
           mZylf.setText( zyzyrrayList.get(7));
           mXyhxl.setText( zyzyrrayList.get(8));
           mHcl.setText( zyzyrrayList.get(9));
            mQtl.setText( zyzyrrayList.get(10));

            //crrayList.clear();
            //ArrayList<String> crrayList=(ArrayList<String>) msg.obj;


        }};


    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mBrxm=(TextView)findViewById(R.id.zyfy_tv_brxm);
        mZfy=(TextView)findViewById(R.id.zyfy_tv_zfy);
        mZhyl=(TextView)findViewById(R.id.cyzyfy_tv_zhylfw);
        mZdl=(TextView)findViewById(R.id.cyzyfy_tv_zdl);
        mZll=(TextView)findViewById(R.id.cyzyfy_tv_zll);
        mKfl=(TextView)findViewById(R.id.cyzyfy_tv_kfl);
        mZyl=(TextView)findViewById(R.id.cyzyfy_tv_zyl);
        mXyl=(TextView)findViewById(R.id.cyzyfy_tv_xyl);
        mZylf=(TextView)findViewById(R.id.cyzyfy_tv_zylf);
        mXyhxl=(TextView)findViewById(R.id.cyzyfy_tv_xyhlf);
        mHcl=(TextView)findViewById(R.id.cyzyfy_tv_hclf);
        mQtl=(TextView)findViewById(R.id.cyzyfy_tv_qtlf);
        app=(NnApplication)getApplication();
        Function();
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return(R.layout.activity_cyzyfy);
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
                    ZYFYCYActivity.this.myhandler.sendMessage(message);


                } catch (Exception e) {
                }
            }
        }.start();

    }
}
