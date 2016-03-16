package com.qiwei.hospital.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.ActivityManager;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;


public class ZYFYCXActivity extends BaseActivity implements View.OnClickListener{
private EditText mEditSf;
    private ImageButton mImgbtn;
    private ImageButton mImgback;
    private NnApplication app;
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
            if(crrayList.size()>5){
               Intent intent=new Intent(ZYFYCXActivity.this, ZYFYCYActivity.class);
                startActivity(intent);
            }
         else   if(crrayList.size()>0&&crrayList.size()<=5){
                Intent intent=new Intent(ZYFYCXActivity.this, ZYFYZYActivity.class);
                startActivity(intent);
            }
          else {
                Toast.makeText(ZYFYCXActivity.this, "住院号不正确或网络连接失败，请检查！", Toast.LENGTH_SHORT).show();
            }


        }
    };

    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mEditSf=(EditText)findViewById(R.id.zyfy_tv_sfzh);
        mImgbtn=(ImageButton)findViewById(R.id.zyfy_btn_sfzh);
        mImgback=(ImageButton)findViewById(R.id.img_zyfy_back);
        mImgbtn.setOnClickListener(this);
        mImgback.setOnClickListener(this);
        app=(NnApplication)getApplication();
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.activity_zyfy);
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zyfy_btn_sfzh:
                getuserfy();
                break;
            default:
                break;
        }

    }

    private void getuserfy() {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        String zyhl=mEditSf.getText().toString();
        arrayList.add("zyh");
        brrayList.add(zyhl);
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getUserFF", arrayList, brrayList);
                     app.setZyfyArrrList(crrayList);

                    Message message=new Message();
                    //message.obj=crrayList;

                    message.obj=crrayList;
                    LoadingDialogManager.getInstance().dismissDialog();
                    ZYFYCXActivity.this.myhandler.sendMessage(message);


                } catch (Exception e) {
                }
            }
        }.start();

    }
}
