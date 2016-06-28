package com.qiwei.hospital.ui;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendAdapter;
import com.qiwei.hospital.AdapterManger.MzsjAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.MzsjBean;
import com.qiwei.hospital.utils.Bean.TeamBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 开发人：黄启位
 * 时间：2016-06-06
 * 功能描述：门诊费用查询
 */
public class MZFYCXActivity extends BaseActivity implements View.OnClickListener {
    private EditText mBrghxh;
    private ImageButton mChaxun;
    /**
     * mBrxm: 病人姓名
     * mBrjzrq：病人就诊日期
     * mJtzz： 家庭住址
     * mGhys： 挂号医生
     * mZffy：
     * mGhf： 挂号费
     * mZcf ：诊查费
     * mQtf： 其它费用
     * mXts：提示
     */
    private TextView mBrxm, mBrjzrq, mJtzz, mGhys, mZffy, mGhf, mZcf, mQtf, mXts;
    private View mRechx;
    private FriendAdapter friendAdapter;
    /**
     * mIncude: 保函include的父容器
     * mLmzfyListt: 两个list的父容器
     */
    private LinearLayout mIncude,mLmzfyListt;
    /** mIncludeList:列表
     * mIncludeFriend：提示
     */
    private LinearLayout mIncludeList,mIncludeFriend;
    // 基本信息父容器
    private LinearLayout mLzfyjbxx;
    // 当获取到的数据为空时显示的提示
    private View mLmzfyNull;
    /**
     * mReMzFriend: 常用就诊人
     * mRemzTime: 选择就诊时间
     * mReEditGhxh:输入挂号序号窗口
     */
    private RelativeLayout mReMzFriend, mRemzTime,mReEditGhxh;
    /**
     * mListFriend: 常用就诊人列 表
     * mListTime: 选择就诊时间LIST
     */
    private ListView mListFriend, mListTime;
    // handler
    private List<FriendBean> mfrienddatas;
    //

    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String>brrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();

    /**
    //
    private ArrayList<String> brrayList = null;
    //
    private ArrayList<String> crrayList = null;
    //
    private ArrayList<String> drrayList = null;
    //
    **/
    private HttpConnSoap Soap = new HttpConnSoap();
    private List<MzsjBean> mSJdatas = null;

   //  private HttpConnSoap Soap = null;
    private NnApplication app;
    //回调MSG
    private MsgNetUtil msgNetUtil;
    //回调handler
    //回调handler.

    private  MainHandler mainHandler;

    private MzsjAdapter mzsjAdapter;
    /**
    public void  MZFYCXActivity(){
        // arrayList = new ArrayList<String>();
        brrayList = new ArrayList<String>();
        crrayList = new ArrayList<String>();
        drrayList = new ArrayList<String>();
        Soap =  new HttpConnSoap();
    }**/
    //身份证号
      private String  rysjdj;
    //挂号序号
    private  String zyh1;
    private Handler myhandler = new Handler(){
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //crrayList.clear();
            //ArrayList<String> crrayList=(ArrayList<String>) msg.obj;
            crrayList = (ArrayList<String>) msg.obj;
            Log.e("--------crrr",crrayList.toString());
            if (crrayList==null){
                mIncludeList.setVisibility(View.GONE);
                mLzfyjbxx.setVisibility(View.GONE);
                mIncludeFriend.setVisibility(View.VISIBLE);
            }
            if(crrayList.size() > 1) {
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

    /**
     * 功能描述：
     */
    class MainHandler extends Handler {
        //
        static final int MSG_GET_MZFRIEND = 90;
        //
        static final int MSG_GET_MZTIME= 91;
        static final int Msg_GETFRIENDLIST= 92;
        /**
         * 功能：更新网络数据
         * @param msg：
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //  获取门诊基本信息
                case MSG_GET_MZFRIEND:
                    break;
                // 获取就诊时间
                case MSG_GET_MZTIME:
                    drrayList = (ArrayList<String>)msg.obj;
                  //  Log.e("222222222222222","yyyy"+drrayList.toString());
                    if (drrayList==null){
                        mIncludeList.setVisibility(View.GONE);
                        mLzfyjbxx.setVisibility(View.GONE);
                        mIncludeFriend.setVisibility(View.VISIBLE);
                    }
                    else if( drrayList.size() > 1) {


                        mSJdatas = new  ArrayList<MzsjBean>();
                        for(int i = 0;i < drrayList.size();i = i+3){
                            MzsjBean mzsjBean = new MzsjBean(drrayList.get(i), drrayList.get(i+1), drrayList.get(i+2));
                            mSJdatas.add(mzsjBean);
                        }
                        mzsjAdapter = new MzsjAdapter(MZFYCXActivity.this,mSJdatas);
                        mListTime.setAdapter(mzsjAdapter);
                        mListTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                zyh1 = mSJdatas.get(position).getGhxh();
                                function();
                            }
                        });
                    }

                    break;
                //
                case Msg_GETFRIENDLIST:
                    crrayList = (ArrayList<String>)msg.obj;
                   if (crrayList==null){
                       mIncludeList.setVisibility(View.GONE);
                       mLzfyjbxx.setVisibility(View.GONE);
                       mIncludeFriend.setVisibility(View.VISIBLE);
                   }
                    if( crrayList.size() > 6 ) {
                         mfrienddatas = new ArrayList<FriendBean>();
                        for( int k = 0;k < crrayList.size();k = k+7){
                            FriendBean friendBean = new FriendBean(crrayList.get(k),
                                    crrayList.get(k+1), crrayList.get(k+2), crrayList.get(k+3), crrayList.get(k+4), crrayList.get(k+5), crrayList.get(k+6));
                            mfrienddatas.add(friendBean);
                        }
                        friendAdapter = new FriendAdapter(MZFYCXActivity.this, mfrienddatas);
                        //mLlList.setVisibility(View.VISIBLE);
                        mListFriend.setAdapter(friendAdapter);
                        mListFriend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                rysjdj = mfrienddatas.get(position).getSfzh();
                                inintdata2();
                            }
                        });
                    }
                    break;

                default:
                    break;
            }
        }
    }
//获取用户就诊时间
    private void inintdata2() {
        mReMzFriend.setVisibility(View.VISIBLE);

        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("sfzh");
        brrayList.add(rysjdj);
        Log.e("yyyyy___", rysjdj);
        String name1="getghxhbysfzg";
        // mainHandler1=new MainHandler();
        msgNetUtil=new MsgNetUtil(name1,  mainHandler,arrayList,brrayList,91);

    }

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
        //包含列表布局
        mIncludeList=(LinearLayout)findViewById(R.id.mzfycx_ll_list);
        //包含提示布局
        mIncludeFriend=(LinearLayout)findViewById(R.id.mzfycx_ll_point);
        //最外层基本信息
        mLzfyjbxx=(LinearLayout)findViewById(R.id.mzfy_ll_jbxx);
        //输入挂号序号窗口
        mReEditGhxh=(RelativeLayout)findViewById(R.id.re_mzfy_chx);
        mIncude=(LinearLayout)findViewById(R.id.mzfy_ll_list);
        mChaxun.setOnClickListener(this);
        //常用就诊人布局
        mReMzFriend=(RelativeLayout)mIncludeList.findViewById(R.id.ll_yygh_jzrxx);
        //就诊时间布局
        mRemzTime=(RelativeLayout)mIncludeList.findViewById(R.id.ll_yygh_jzrq);
        //常用就诊人列表
        mListFriend=(ListView)findViewById(R.id.list_yygh_cyjzr);
        //就诊时间
        mListTime=(ListView)findViewById(R.id.list_ryrq);
        initdata();
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

    /**
     * 获取门诊病人基本信息
     */
    private void function(){
        mReMzFriend.setVisibility(View.GONE);
        mRemzTime.setVisibility(View.GONE);
        mLzfyjbxx.setVisibility(View.VISIBLE);

        InputMethodManager imm= (InputMethodManager)getSystemService(MZFYCXActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mBrghxh.getWindowToken(), 0);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
       // String zyhl= mBrghxh.getText().toString();
        arrayList.add("mzh");
        brrayList.add(zyh1);
       // LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getbqcybz", arrayList, brrayList);

                    Message message=new Message();
                    //message.obj=crrayList;

                    message.obj=crrayList;

                    MZFYCXActivity.this.myhandler.sendMessage(message);
                    //LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();



    }

    /**获取常用就诊人列表**/
    private void initdata() {
        mReMzFriend.setVisibility(View.VISIBLE);
        mRemzTime.setVisibility(View.VISIBLE);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("manageid");
        app=(NnApplication)getApplication();
        brrayList.add(app.getUserid());
        String name="getuserfriendinfo";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name, mainHandler,arrayList,brrayList,92);
    }


}
