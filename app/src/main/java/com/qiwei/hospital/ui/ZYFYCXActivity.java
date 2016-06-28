package com.qiwei.hospital.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendAdapter;
import com.qiwei.hospital.AdapterManger.RysjAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.RysjBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.ActivityManager;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import java.util.ArrayList;
import java.util.List;


public class ZYFYCXActivity extends BaseActivity implements View.OnClickListener{
private EditText mEditSf;
    private ImageButton mImgbtn;
    private ImageButton mImgback;
    private ListView mListFriend;
    private  ListView mListTime;
    private FriendAdapter friendAdapter;
    private  RysjAdapter rysjAdapter;
    private LinearLayout mLlfriendlist;
    private LinearLayout mLlfriendtime;
    /**
     * 包含提示布局
     */
    private  LinearLayout mIncludePoint;
    //保函两个List的布局
    private FrameLayout mFrameList;
    private LinearLayout mMzzyLin;
    private String  rysjdj;
    private  String zyh1;
    private MsgNetUtil msgNetUtil;
    private List<FriendBean> mdatas;
    private List<RysjBean> mSJdatas;
    private NnApplication app;
    private NetUtil netUtil;
    private   MainHandler mainHandler;
    private   MainHandler mainHandler1;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private HttpConnSoap Soap = new HttpConnSoap();

    class MainHandler extends Handler {
        static final int MSG_ZYFY=  112;
        static final int MSG_RYRQ= 113;
        static final int MSG_GETzyfy= 114;
        static final int MSG_ZYCF_LIST = 103;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //获取常用就诊人列表
                case  MSG_ZYFY:

                    crrayList=(ArrayList<String>)msg.obj;
                    if(crrayList==null){
                        mIncludePoint.setVisibility(View.VISIBLE);
                        mLlfriendlist.setVisibility(View.GONE);
                        mLlfriendtime.setVisibility(View.GONE);
                        mFrameList.setVisibility(View.GONE);
                    }

                  //  Log.e("crrls------", crrayList.toString());
                    if(crrayList.size()>6){
                        mdatas=new ArrayList<FriendBean>();
                        for(int k=0;k<crrayList.size();k=k+7){
                            FriendBean friendBean=new FriendBean(crrayList.get(k),
                                    crrayList.get(k+1),crrayList.get(k+2),crrayList.get(k+3),crrayList.get(k+4),crrayList.get(k+5),crrayList.get(k+6));
                            mdatas.add(friendBean);
                        }
                        friendAdapter=new FriendAdapter(ZYFYCXActivity.this,mdatas);
                        //mLlList.setVisibility(View.VISIBLE);
                        mListFriend.setAdapter(friendAdapter);
                        mListFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           rysjdj=mdatas.get(position).getSfzh();
                           inintdata2();
                       }
                   });


                    }



                    break;

                case  MSG_RYRQ:
                    drrayList=(ArrayList<String>)msg.obj;

                  //  Log.e("drry----",drrayList.toString());
                    if(drrayList==null){
                        mIncludePoint.setVisibility(View.VISIBLE);
                        mFrameList.setVisibility(View.GONE);
                        mLlfriendlist.setVisibility(View.GONE);
                        mLlfriendtime.setVisibility(View.GONE);
                    }
                  else  if(drrayList.size()>1){
                        mSJdatas=new  ArrayList<RysjBean>();
                        for(int i=0;i<drrayList.size();i=i+2){
                            RysjBean rysjBean=new RysjBean(drrayList.get(i),drrayList.get(i+1));
                            mSJdatas.add(rysjBean);
                        }
                        rysjAdapter=new RysjAdapter(ZYFYCXActivity.this,mSJdatas);
                        mListTime.setAdapter(rysjAdapter);
                        mListTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                zyh1=mSJdatas.get(position).getZyh();
                                getuserfy();
                            }
                        });
                    }

                    break;





                default:
                    break;
            }
        }
    }






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
        mLlfriendlist=(LinearLayout)findViewById(R.id.zyfy_list_friend);
        mListFriend=(ListView)findViewById(R.id.list_yygh_cyjzr);
        mLlfriendtime=(LinearLayout)findViewById(R.id.zyfy_list_yime);
        mListTime=(ListView)findViewById(R.id.list_ryrq);
        //提示无内容布局
        mIncludePoint=(LinearLayout)findViewById(R.id.zyfy_ll_point);
        //包含两个列表的布局
        mFrameList=(FrameLayout)findViewById(R.id.zyfy_Fr_LIST);
        app=(NnApplication)getApplication();
        initdata();
    }

    private void initdata() {
         mLlfriendlist.setVisibility(View.VISIBLE);
         mLlfriendtime.setVisibility(View.GONE);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("manageid");
        app=(NnApplication)getApplication();
        brrayList.add(app.getUserid());
        String name="getuserfriendinfo";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name, mainHandler,arrayList,brrayList,112);
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.activity_zyfy);
    }


    private void inintdata2() {
        mLlfriendlist.setVisibility(View.VISIBLE);
        mLlfriendtime.setVisibility(View.VISIBLE);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sfzh");
        brrayList.add(rysjdj);
        Log.e("yyyyy___",rysjdj);
        String name1="getzygbysfzg";
        mainHandler1=new MainHandler();
        msgNetUtil=new MsgNetUtil(name1,  mainHandler1,arrayList,brrayList,113);

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

        arrayList.add("zyh");
        brrayList.add(zyh1);
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
