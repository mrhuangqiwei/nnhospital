package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendAdapter;
import com.qiwei.hospital.AdapterManger.MzsjAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.MzsjBean;
import com.qiwei.hospital.utils.Bean.TeamBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import java.util.ArrayList;

/**
 * 开发人：黄启位
 * 时间：2016-06-27
 * 功能描述：用户注册
 */
public class UserRegisterActivity extends BaseActivity implements View.OnClickListener{
    //返回安妮
private ImageButton mImageBack;
    private LinearLayout IncludeTitle;
    //标题
    private TextView mTextTitle;
    /** mEditBrxm:姓名
     * mEditPhone：电话号码
     * mEditSfzh：身份证号
     * mEditJtzz：家庭住址
     */
    private EditText mEditBrxm,mEditPhone,mEditSfzh,mEditJtzz,mEditpass;
    //提交
    private Button mBtntj;
    /**
     * 网络操作列表
     */
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    //注册电话号码
  private   String dhhm;
    //网络请求
    private HttpConnSoap Soap = new HttpConnSoap();
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    //application
    private NnApplication app;
    @Override
    protected void initEnvironment() {

    }


    class MainHandler extends Handler {
        static final int MSG_PUSH_JBXX = 121;


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_PUSH_JBXX :
                    crrayList=(ArrayList<String>)msg.obj;
                    Log.e("Tag cccc",crrayList.toString());
                    if (crrayList.get(0).equals("true")){
                        app=(NnApplication)getApplication();
                        app.setuserid(dhhm);
                        Toast.makeText(UserRegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserRegisterActivity.this, UCenterActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(UserRegisterActivity.this,"失败",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserRegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    break;


                default:
                    break;
            }
        }
    }

    @Override
    protected void initViews() {
        IncludeTitle=(LinearLayout)findViewById(R.id.register_title);
        mImageBack=(ImageButton)IncludeTitle.findViewById(R.id.img_title_back);
        mTextTitle=(TextView)IncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.register_uc_myfriend_title);
        mImageBack.setOnClickListener(this);
        mEditBrxm=(EditText)findViewById(R.id.register_edittext_name);
        mEditJtzz=(EditText)findViewById(R.id.uc_info_hint_jtzz);
        mEditPhone=(EditText)findViewById(R.id.register_edittext_phone);
        mEditSfzh=(EditText)findViewById(R.id.register_edittext_sfzh);
        mBtntj=(Button)findViewById(R.id.register_button_login);
        mEditpass=(EditText)findViewById(R.id.uc_info_hint_mm);
        mBtntj.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_user_register);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_title_back:
                finish();break;
            case R.id.register_button_login:
                initdata();
                break;
            default:break;
        }

    }

    /**
     * 提交数据
     */
    private void initdata() {
        arrayList.clear();
        brrayList.clear();
        String brxm=mEditBrxm.getText().toString();
        String sfzh=mEditSfzh.getText().toString();
        String jtzz=mEditJtzz.getText().toString();
         dhhm=mEditPhone.getText().toString();
        String pass=mEditpass.getText().toString();
        String name="insertuserInfo";
        arrayList.add("userid");
        arrayList.add("idcard");
        arrayList.add("username");
        arrayList.add("password");
        arrayList.add("jtzz");
        brrayList.add(dhhm);
        brrayList.add(sfzh);
        brrayList.add(brxm);
        brrayList.add(pass);
        brrayList.add(jtzz);
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,121);
    }
}
