package com.qiwei.hospital;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ui.UCenterActivity;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.DBUtil;
import com.qiwei.hospital.utils.comprehensive.PrefrenceUtils;
import com.qiwei.hospital.utils.comprehensive.checkutils;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UCLandingActivity extends Activity implements View.OnClickListener {

private  View mReLand;
    private EditText mUserId;
    private EditText mUserpassword;
    private TextView mForgetpassword;
    private TextView mNewUser;
    private DBUtil dbUtil;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private NnApplication app;
    private HttpConnSoap Soap = new HttpConnSoap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.initWindow();
        this.initEnvironment();


    }

    private void initEnvironment() {
        mUserId=(EditText)findViewById(R.id.edt_uc_land_user);
        mUserpassword=(EditText)findViewById(R.id.edt_uc_land_pass);
        mForgetpassword=(TextView)findViewById(R.id.tv_uc_forget_pws);
        mNewUser=(TextView)findViewById(R.id.tv_uc_new_user);
        mReLand=findViewById(R.id.re_ucland_langding);
        mReLand.setOnClickListener(this);
        mForgetpassword.setOnClickListener(this);
        mNewUser.setOnClickListener(this);
        String Userid = PrefrenceUtils.getString(this, PrefrenceUtils.LAST_USER_NAME, "");
        mUserId.setText(Userid);
        dbUtil = new DBUtil();
        app=(NnApplication)getApplication();

        new Thread() {
            @Override
            public void run() {
                try {
                    crrayList = Soap.GetWebServre("getUserInfo", arrayList, brrayList);

                    app.setArrrList(crrayList);
                } catch (Exception e) {
                }
            }
        }.start();
    }

    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.uc_land);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_ucland_langding:
                mReLand.setBackgroundResource(R.mipmap.btn_press);
                if (!checkInputValid()) {
                    return;
                }
                if(checkuserinfo(mUserId.getText().toString(), mUserpassword.getText().toString())){
                    Toast.makeText(UCLandingActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UCLandingActivity.this, "账号或密码不对请重新检查", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_uc_forget_pws:
                Intent intent=new Intent(UCLandingActivity.this, UCenterActivity.class);
                startActivity(intent);
                break;
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
drrayList.clear();
    boolean Istrue=false;

    drrayList=app.getArrayList();

    for(int j=0;j< drrayList.size();j+=2){
        if(user.equals( drrayList.get(j))){
            if(password.equals( drrayList.get(j+1))){
                Istrue=true;
                app.setuserid(drrayList.get(j));
                Intent intent=new Intent(UCLandingActivity.this, UCenterActivity.class);
                startActivity(intent);
                break;
            }
        }else{ Istrue=false;}



    }

return Istrue;

}


}
