package com.qiwei.hospital.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import java.util.ArrayList;

public class UcInformation extends BaseActivity implements View.OnClickListener{
    private TextView mUsername;
    private TextView mUserPhone;
    private  TextView mUserJtzhuzhi;
    private  TextView mUserSf;
    private EditText mEditUsname;
    private  EditText mEditUsJtzz;
    private  EditText mEditUsSf;
    private Button mTijiao;
    private  TextView mChange;
    private LinearLayout mLUserNew;
    private   LinearLayout mLUserOld;
    private NnApplication app;
    private ImageButton mImgback;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> fcrrayList = new ArrayList<String>();
    private NetUtil netUtil;
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;
           // Log.e("crr---->",crrayList.toString());
            if (crrayList==null){
                Toast.makeText(UcInformation.this,"亲，没有查找到数据！请与医院信息科联系。",Toast.LENGTH_SHORT).show();
            }

        else{
                mUsername.setText(crrayList.get(0));
                mUserSf.setText(crrayList.get(1));
                mUserJtzhuzhi.setText(crrayList.get(2));
                mUserPhone.setText(crrayList.get(3));

            }

        }
    };
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mLUserNew=(LinearLayout)findViewById(R.id.Ll_ucinfo_new);
        mLUserOld=(LinearLayout)findViewById(R.id.Ll_ucinfo_old);
        mUsername=(TextView)findViewById(R.id.tv_ucinfo_name);
        mUserJtzhuzhi=(TextView)findViewById(R.id.uc_info_tv_jtzz);
        mUserSf=(TextView)findViewById(R.id.tv_ucinfo_sfzh);
        mEditUsJtzz=(EditText)findViewById(R.id.uc_info_edit_jtzz);
        mEditUsname=(EditText)findViewById(R.id.login_edittext_account);
        mUserPhone=(TextView)findViewById(R.id.uc_info_edit_dh);
        mEditUsSf=(EditText)findViewById(R.id.login_edittext_password);
        mImgback=(ImageButton)findViewById(R.id.img_uc_center_back);
        mImgback.setOnClickListener(this);
        mChange=(TextView)findViewById(R.id.uc_inseret_friend);
        mTijiao=(Button)findViewById(R.id.login_button_login);
        mChange.setOnClickListener(this);
        mTijiao.setOnClickListener(this);
        CheckuserInfo();
    }

    private void CheckuserInfo() {
        app=(NnApplication)getApplication();
        Log.e("------app","++++"+app.getUserid());
        if(app.getUserid()!=null){
       mLUserOld.setVisibility(View.VISIBLE);
         setvisibleview(

         );
        }


    }

    private void setvisibleview() {
        arrayList.add("userid");
        brrayList.add(app.getUserid());
        String name="getUserxx";
        netUtil=new NetUtil(name,myhandler,arrayList,brrayList);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.uc_infomation);
    }


    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.img_uc_center_back:
                 finish();
                 break;
             default:break;
         }
    }
}
