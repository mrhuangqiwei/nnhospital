package com.qiwei.hospital;


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
import com.qiwei.hospital.ui.UCenterActivity;
import com.qiwei.hospital.ui.UserRegisterActivity;
import com.qiwei.hospital.utils.NetUtil.NetBool;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.DBUtil;
import com.qiwei.hospital.utils.comprehensive.PrefrenceUtils;
import com.qiwei.hospital.utils.comprehensive.checkutils;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UCLandingActivity extends BaseActivity implements View.OnClickListener {
//返回按钮
private ImageButton mImgBackBtn;
    private  View mReLand;
    private EditText mUserId;
    private EditText mUserpassword;
    private TextView mForgetpassword;
    private TextView mNewUser;
    private DBUtil dbUtil;
    private NetUtil netUtil;
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> frrayList = new ArrayList<String>();
    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private NnApplication app;
    private HttpConnSoap Soap = new HttpConnSoap();


    class MainHandler extends Handler {
        static final int MSG_GET_USERXX = 145;
        static final int MSG_GET_USERFRIEND = 146;
        static final int MSG_GET_USERFRIENDMX = 147;
    @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_GET_USERXX :
                    crrayList=(ArrayList<String>)msg.obj;
                   // Log.e("-----------crry","222"+crrayList.toString());
                    if(crrayList.size()>1){
                        app.setArrrList(crrayList);
                        if (!checkInputValid()) {
                            return;
                        }
                        if (checkuserinfo(mUserId.getText().toString(), mUserpassword.getText().toString())) {
                            Toast.makeText(UCLandingActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(UCLandingActivity.this, "账号或密码不对请重新检查", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    else{

                    }
                    break;
                case MSG_GET_USERFRIEND:
                    drrayList=(ArrayList<String>)msg.obj;
                    app.setfriendlist(drrayList);
                    //Log.e("MSg",drrayList.toString());
                    break;
                case MSG_GET_USERFRIENDMX:
                    drrayList=(ArrayList<String>)msg.obj;
                  //  Log.e("MSg",drrayList.toString());
                    app.setfriendxm(drrayList);

                    break;

                default:
                    break;
            }
        }}





    @Override
    protected void initEnvironment() {
        arrayList.size();
        brrayList.clear();
        crrayList.clear();


    }

    @Override
    protected void initViews() {
        mUserId=(EditText)findViewById(R.id.edt_uc_land_user);

        mUserpassword=(EditText)findViewById(R.id.edt_uc_land_pass);
        mForgetpassword=(TextView)findViewById(R.id.tv_uc_forget_pws);
        mNewUser=(TextView)findViewById(R.id.tv_uc_new_user);
        mReLand=findViewById(R.id.re_ucland_langding);
        mImgBackBtn=(ImageButton)findViewById(R.id.img_uc_land_back);
        mImgBackBtn.setOnClickListener(this);

        mReLand.setOnClickListener(this);
        mForgetpassword.setOnClickListener(this);
        mNewUser.setOnClickListener(this);
        dbUtil = new DBUtil();
        app=(NnApplication)getApplication();




    }

    private void initdata() {
        arrayList.clear();
        brrayList.clear();
        String name="getUserInfo";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,145);
       // netUtil=new NetUtil(name,myHandler,arrayList,brrayList);

    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.uc_land);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_ucland_langding:


                    mReLand.setBackgroundResource(R.mipmap.btn_press);
                Log.e("----->",""+NetBool.isNetworkAvailable(UCLandingActivity.this));
                if(!NetBool.isNetworkAvailable(UCLandingActivity.this)){
                    Toast.makeText(UCLandingActivity.this,R.string.net_contact_false,Toast.LENGTH_SHORT).show();
                }
                else {
                    initdata();


                }
                break;
            //case R.id.tv_uc_forget_pws:
               // Intent intent=new Intent(UCLandingActivity.this, UCenterActivity.class);
               // startActivity(intent);
              //  break;
            case R.id.tv_uc_new_user:
                Intent intent1=new Intent(UCLandingActivity.this, UserRegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.img_uc_land_back:
                finish();
            default:
                break;
        }
    }

    private boolean checkInputValid() {
        if (mUserId.getText().toString().isEmpty()) {
            Toast.makeText(UCLandingActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
            mUserId.requestFocus();
            return false;
        }
        if (!checkutils.checkLength(mUserpassword, "密码")) {
            return false;
        }
        return true;
    }
private boolean checkuserinfo(String user,String password){

    boolean Istrue=false;
    drrayList=app.getArrayList();
   Log.e("drr----->",drrayList.toString());
    for(int j=0;j< drrayList.size();j+=2){
        if(user.equals( drrayList.get(j))){
            if(password.equals( drrayList.get(j+1))){
                Istrue=true;
                chekfriends();
                app.setuserid(drrayList.get(j));
                chekfriends1();
                Intent intent=new Intent(UCLandingActivity.this, UCenterActivity.class);
                startActivity(intent);
                break;
            }
        }else{ Istrue=false;}



    }

return Istrue;

}

    private void chekfriends() {

        arrayList.clear();
        brrayList.clear();
        arrayList.add("phone");
        brrayList.add(mUserId.getText().toString());
        String name="getkjzr";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,146);
    }//获取病人信息于医疗卡号等信息
    private void chekfriends1() {

        arrayList.clear();
        brrayList.clear();
        arrayList.add("ph");
        brrayList.add(mUserId.getText().toString());
        String name="getbrxx1";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,147);
    }

}
