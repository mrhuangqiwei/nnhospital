package com.qiwei.hospital.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.MzZyCfAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.Bean.TeamBean;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;
import java.util.List;

public class MzblActivity extends BaseActivity implements  View.OnClickListener{

    @Override
    public void onClick(View v) {
        switch (v.getId()){
          case  R.id.img_mzfy_back:


            break;
            case R.id.mzbl_btn_ghxh:

                function();
               // functongetinfo();
                break;
            default:
                break;
        }

    }

    class MainHandler extends Handler {
        static final int MSG_GET_MZJBXX = 100;
        static final int MSG_GET_MZZYCF= 101;
        static final int Msg_ZYCFSY= 102;
        static final int MSG_ZYCF_LIST = 103;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_MZJBXX:
                    crrayList=(ArrayList<String>) msg.obj;
                    Log.d("JBXX------------>", crrayList.toString());


                    if(crrayList.size()>2){
                    mZjbxx.setVisibility(View.GONE);
                    mTvjb.setVisibility(View.VISIBLE);
                    mBrxm.setText(crrayList.get(0));
                    mGhks.setText(crrayList.get(1));
                    mGhys.setText(crrayList.get(2));
                    mjzrq.setText(crrayList.get(3));
                    mBrnl.setText(crrayList.get(4));
                    mJtzz.setText(crrayList.get(5));
                    mSfzh.setText(crrayList.get(6));
                        mdatas = new ArrayList<MzblBean>();
                        mTeamdata=new ArrayList<TeamBean>();
                        for (int i = 0; i < crrayList.size(); i++) {
                            if (crrayList.get(i).equals("mzzycf")) {
                                listd.add(i);
                            }
                        }
                   for(int j=0;j<listd.size()-1;j++){
                       drrayList.clear();

                       for(int k=listd.get(j)+1;k<listd.get(j + 1);k++){
                       drrayList.add(crrayList.get(k));

                       }

                       //Log.e("这是drrlist", drrayList.toString());
                    ArrayList<String> frrayList = new ArrayList<String>();
                       for(int h=3;h<drrayList.size();h++){
                           frrayList.add(drrayList.get(h));

                       }
                       Log.e("这是Frrlist", frrayList.toString());
                       MzblBean mzblbean = new MzblBean(drrayList.get(0), drrayList.get(1), drrayList.get(2), frrayList);
                       mdatas.add(mzblbean);


                      // Log.e("这是Frrlist", frrayList.toString());


                   }


                        mzblAdapter1 = new mzblAdapter(MzblActivity.this, mdatas);
                        mzblList.setAdapter(mzblAdapter1);
                 }

                    break;

                case MSG_GET_MZZYCF:


                    break;





                default:
                    break;
            }
        }
    }
    private MainHandler mMainHandler;
    private ImageButton mImgback;
    private ImageButton mChaxun;
    private TextView mTvjb;
    private View mRemzcx;
    private EditText mGhxh;
    private  View mZjbxx;
    private  TextView mBrxm;
    private  TextView mGhks;
    private  TextView mGhys;
    private  TextView mJzys;
    private  TextView mjzrq;
    private  TextView mBrnl;
    private  TextView mSfzh;
    private  TextView mJtzz;
    private  TextView mLczd;
    private TextView mZyyf;
    private TextView mZyfs;
    private LinearLayout in;
    private List<MzzycfBean> mDatas;
    private List<MzblBean>mdatas;
    private  List<TeamBean> mTeamdata;
    private    LinearLayout layout;
    private ListView mListView;
    private ListView mzblList;
    private mzblAdapter mzblAdapter1;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();

    private ArrayList<String> grrayList = new ArrayList<String>();
    private ArrayList<String> hrrayList = new ArrayList<String>();
    private ArrayList<String> jrrayList = new ArrayList<String>();
    private ArrayList<String> krrayList = new ArrayList<String>();
    private ArrayList<String>[] yy;
    private ArrayList<String> mrrayList = new ArrayList<String>();
   private  ArrayList<Integer> listd=new ArrayList<Integer>();
    private ArrayList<String[]> yrrayList = new ArrayList<String[]>();
    private HttpConnSoap Soap = new HttpConnSoap();
    private   LayoutInflater inflater;
    //加载数据Adapter
    private MzZyCfAdapter mzZyCfAdapter;
    @Override
    protected void initEnvironment() {
        mMainHandler=new MainHandler();
    }
    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        return (R.layout.activity_mzbl);

    }
    @Override
    protected void initViews() {
        inflater = LayoutInflater.from(this);
        mImgback=(ImageButton)findViewById(R.id.img_mzfy_back);
        mTvjb=(TextView)findViewById(R.id.tv_mzbl_jbxx);
        mGhxh=(EditText)findViewById(R.id.mzbl_tv_brrygh);
        mChaxun=(ImageButton)findViewById(R.id.mzbl_btn_ghxh);
        mBrxm=(TextView)findViewById(R.id.mzbl_tv_brxm);
        mGhks=(TextView)findViewById(R.id.mzbl_tv_ghks);
        mGhys=(TextView)findViewById(R.id.mzbl_tv_jzys);
        mjzrq=(TextView)findViewById(R.id.mzbl_tv_jzsj);
        mBrnl=(TextView)findViewById(R.id.mzbl_tv_brnl);
        mJtzz=(TextView)findViewById(R.id.mzbl_tv_brjtzj);
        mSfzh=(TextView)findViewById(R.id.mzbl_tv_sfzh);
        mZjbxx=(View)findViewById(R.id.re_mzbl_chx);
        mzblList=(ListView)findViewById(R.id.mzbl_ll_cfd);
    //    listclear();
        crrayList.clear();
        mrrayList.clear();
        mImgback.setOnClickListener(this);
        mChaxun.setOnClickListener(this);

    }

    private void function() {
        String ghxh = mGhxh.getText().toString();


      //  listclear();
        crrayList.clear();

        arrayList.add("ghxh");
        brrayList.add(ghxh);
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getusermzjbxx", arrayList, brrayList);
                  //  Log.e("JBXX2------------>", crrayList.toString());

               Message successMsg = mMainHandler.obtainMessage(MainHandler.MSG_GET_MZJBXX);



                  successMsg.obj = crrayList;

                  MzblActivity.this.mMainHandler.sendMessage(successMsg);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();
    }


        private void listclear(){
          arrayList.clear();
          brrayList.clear();

           drrayList.clear();

            grrayList.clear();
            hrrayList.clear();
            krrayList.clear();
            jrrayList.clear();

    }

    /**
     * 通过挂号序号获取处方号
     */
    private void functongetinfo(){
        String ghxh = mGhxh.getText().toString();
        InputMethodManager imm = (InputMethodManager) getSystemService(MzblActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mGhxh.getWindowToken(), 0);
        listclear();

        arrayList.add("ghxh");
        brrayList.add(ghxh);

        new Thread() {
            @Override
            public void run() {
                try {

                    mrrayList = Soap.GetWebServre("getusermzzycfh", arrayList, brrayList);


                           Message message= mMainHandler.obtainMessage(MainHandler.MSG_GET_MZZYCF);
                    message.obj= mrrayList;
                 //   Log.e("门诊中药处方号---->",mrrayList.toString());
                    MzblActivity.this.mMainHandler.sendMessage(message);





                } catch (Exception e) {
                }
            }
        }.start();

    }

    @Override
    protected void onDestroy() {


             super.onDestroy();
    }
}
