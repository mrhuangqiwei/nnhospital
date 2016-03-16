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
                functongetinfo();
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
                    Log.d("JBXX------------>", "  " + crrayList.size());

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
                 }
                    else {
                        return;
                    }

                    break;

                case MSG_GET_MZZYCF:
                drrayList=(ArrayList<String>)msg.obj;

                Log.e("!!!!!!!!!!", "0000" + drrayList.size());
                    if(drrayList.size()!=0) {
                        arrayList.clear();
                        brrayList.clear();
                        frrayList.clear();
                        grrayList.clear();
                        hrrayList.clear();
                        jrrayList.clear();
                        krrayList.clear();
                        for (int i = 0; i < drrayList.size(); i++) {
                            if (drrayList.get(i).equals("mzzycf")) {
                                listd.add(i);
                            }
                        }
                        Log.e("位置-----》", listd.toString());
                        mdatas = new ArrayList<MzblBean>();
                        if (listd.size() == 0) {
                            grrayList = drrayList;

                            Log.e("2222222222222", grrayList.toString());

                            for (int j = 3; j < grrayList.size(); j++) {
                                hrrayList.add(grrayList.get(j));
                            }
                            MzblBean mzblbean = new MzblBean(grrayList.get(0), grrayList.get(1), grrayList.get(2), hrrayList);
                            mdatas.add(mzblbean);
                        }


                        if (listd.size() == 1) {
                            grrayList.clear();
                            hrrayList.clear();
                            for (int j = 0; j < listd.get(0); j++) {
                                grrayList.add(drrayList.get(j));

                            }
                            Log.e("测试中断位置", grrayList.toString());
                            for (int k = 3; k < grrayList.size(); k++) {
                                hrrayList.add(grrayList.get(k));
                            }
                            MzblBean mzblbean = new MzblBean(grrayList.get(0), grrayList.get(1), grrayList.get(2), hrrayList);
                            mdatas.add(mzblbean);


                            for (int k = listd.get(0) + 1; k < drrayList.size(); k++) {
                                frrayList.add(drrayList.get(k));
                            }

                            for (int j = 3; j < frrayList.size(); j++) {
                                jrrayList.add(frrayList.get(j));
                            }
                            Log.e("这里是jrraylist",jrrayList.toString());
                            mzblbean = new MzblBean(frrayList.get(0), frrayList.get(1), frrayList.get(2), jrrayList);
                            mdatas.add(mzblbean);


                        }
                        mzblAdapter1 = new mzblAdapter(MzblActivity.this, mdatas);

                        mzblList.setAdapter(mzblAdapter1);
                    }
/**
                        int i;
                        for ( i=0;i<drrayList.size();i++){
                         in=(LinearLayout)findViewById(R.id.mzbl_ll_cfd);
                         layout = (LinearLayout) inflater.inflate(R.layout.activity_mzzycf, null).findViewById(R.id.mzzycf_ll_title);
                            layout.setTag(i);
                            in.addView(layout);
                            mLczd=(TextView)findViewById(R.id.zycf_tv_lczd);
                            mLczd.setTag(i);
                            mZyyf=(TextView)findViewById(R.id.mzzycf_tv_zyyf);
                            mZyyf.setTag(i);
                            mZyfs=(TextView)findViewById(R.id.mzzycf_tv_zyfs);
                            mZyfs.setTag(i);
                            mListView=(ListView)findViewById(R.id.mzzycf_list_cf);
**/
/**                         arrayList.clear();
                            brrayList.clear();
                            grrayList.clear();
                            arrayList.add("cfh");
                           brrayList.add(drrayList.get(i));
                            Log.e("第几次运行",drrayList.get(i));
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        try {

                                            frrayList= Soap.GetWebServre("getusermzzyyfyzdxx", arrayList, brrayList);



                                            Log.e("000000000000000--->", frrayList.toString());
                                            if (frrayList.size() == 0) {

                                                return;

                                            }

                                            Message textmessage = mMainHandler.obtainMessage(MainHandler.Msg_ZYCFSY);

                                            textmessage.obj=frrayList;


                                            MzblActivity.this.mMainHandler.sendMessage(textmessage);



                                        }
                                        catch (Exception e){

                                        }

                                    } catch (Exception e) {
                                    }
                                }
                            }.start();


                        }}
**/


/**

                            hrrayList.clear();
                            jrrayList.clear();
                            hrrayList.add("cfh");
                            jrrayList.add(drrayList.get(i));
                            Log.e("drraylis---------------", drrayList.get(i));
                            new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        try {

                                            grrayList= Soap.GetWebServre("getusermzzycfxx", hrrayList, jrrayList);
                                            Log.e("List2222---------------",""+grrayList.size());
                                            Log.e("List2222---------------",grrayList.toString());
                                            if(grrayList.size()==0){
                                                return;
                                            }


                                            Message itemmessage = mMainHandler.obtainMessage(MainHandler.MSG_ZYCF_LIST);

                                              itemmessage.obj=mDatas;
                                            MzblActivity.this.mMainHandler.sendMessage(itemmessage);

                                        }
                                        catch (Exception e){

                                        }

                                    } catch (Exception e) {
                                    }
                                }
                            }.start();

                        }**/








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
    private    LinearLayout layout;
    private ListView mListView;
    private ListView mzblList;
    private mzblAdapter mzblAdapter1;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> frrayList = new ArrayList<String>();
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


        listclear();
        crrayList.clear();

        arrayList.add("ghxh");
        brrayList.add(ghxh);
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getusermzjbxx", arrayList, brrayList);
                    Log.e("JBXX2------------>", crrayList.toString());

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
            frrayList.clear();
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
                    Log.e("门诊中药处方号---->",mrrayList.toString());
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
