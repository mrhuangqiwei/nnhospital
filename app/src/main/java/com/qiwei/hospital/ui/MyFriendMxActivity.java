package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import java.util.ArrayList;

public class MyFriendMxActivity extends BaseActivity implements View.OnClickListener{
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
   private ImageButton mImgback;
    private TextView mTvbrxm;
    private Button mDeleteBtn;
    private  TextView mTvbrxb;
    private  TextView mTvbrnl;
    private TextView mTvbrdh;
    private  TextView mTvsfzh;
    private TextView mTvbrjtzz;
    private MsgNetUtil msgNetUtil;
    private MainHandler mMainHandler;
    private String sfzh;
    private String brxm;
    private String userid;
    @Override
    protected void initEnvironment() {

    }
    class MainHandler extends Handler {
        static final int MSG_GET_MY_FR_MX= 105;
        static final int MSG_DELETE_MY_FR_MX= 106;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_MY_FR_MX:
                    crrayList=(ArrayList<String>)msg.obj;
                    if(crrayList==null){
                        Toast.makeText(MyFriendMxActivity.this, "您没有预约信息", Toast.LENGTH_LONG).show();
                        return;}
                    else{
                        mTvbrxm.setText(crrayList.get(1));
                        mTvbrnl.setText(crrayList.get(2));
                        mTvsfzh.setText(crrayList.get(0));
                        mTvbrdh.setText(crrayList.get(6));
                        mTvbrjtzz.setText(crrayList.get(4));
                        userid=crrayList.get(5);
                        if(crrayList.get(3).equals("1")){
                            mTvbrxb.setText("男");
                        }
                        else {
                            mTvbrxb.setText("女");
                        }
                        }

                    break;
                case MSG_DELETE_MY_FR_MX:
                    drrayList=(ArrayList<String>)msg.obj;
                    if (crrayList.get(0).equals("true")){
                        Toast.makeText(MyFriendMxActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(MyFriendMxActivity.this, UCenterActivity.class);
                    startActivity(intent);}
                    break;






                default:
                    break;
            }
        }
    }



    @Override
    protected void initViews() {
        mImgback=(ImageButton)findViewById(R.id.img_uc_center_back);
        mImgback.setOnClickListener(this);
        mTvbrxm=(TextView)findViewById(R.id.myfriend_tv_brxm);
        mTvbrxb=(TextView)findViewById(R.id.myfriend_tv_ghks);
        mTvbrnl=(TextView)findViewById(R.id.mzbl_tv_jzys);
        mTvsfzh=(TextView)findViewById(R.id.mzbl_tv_sfzh);
        mTvbrjtzz=(TextView)findViewById(R.id.mzbl_tv_brjtzj);
        mTvbrdh=(TextView)findViewById(R.id.mzbl_tv_jzsj);

        Bundle bundle = this.getIntent().getExtras();
        brxm=bundle.getString("brxm");
        sfzh=bundle.getString("hzsfzh");
        inintdatas();
        mDeleteBtn=(Button)findViewById(R.id.login_button_cancle);
        mDeleteBtn.setOnClickListener(this);
    }

    private void inintdatas() {
        arrayList.add("sfzh");
        arrayList.add("brxm");
        brrayList.add(sfzh);
        brrayList.add(brxm);
        mMainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("getfriendmx",mMainHandler,arrayList,brrayList,105);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_my_friend_mx);
    }



    @Override
    public void onClick(View v) {
   switch (v.getId()){
       case R.id.img_uc_center_back:
           finish();break;
       case R.id.login_button_cancle:
           deletefunction();
       default:
           break;


   }
    }

    /**
     * 删除常用就诊人
     */
    private void deletefunction() {
  arrayList.clear();
        brrayList.clear();
        arrayList.add("userid");
        arrayList.add("sfzh");
        brrayList.add(userid);
        brrayList.add(sfzh);
        mMainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("deletefriend",mMainHandler,arrayList,brrayList,106);

    }
}
