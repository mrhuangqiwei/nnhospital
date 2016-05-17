package com.qiwei.hospital.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.MzxyAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.mzxybean;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;
import java.util.List;

public class MzXyCfActivity extends BaseActivity implements View.OnClickListener{

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
    private MzxyAdapter mzblAdapter1;
    private ListView mzblList;
    private   LayoutInflater inflater;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> fcrrayList = new ArrayList<String>();
    private ArrayList<String> grrayList = new ArrayList<String>();
    private List<mzxybean> mdatas;
    private  ArrayList<Integer> listd=new ArrayList<Integer>();
    private HttpConnSoap Soap = new HttpConnSoap();
    @Override
    protected void initEnvironment() {

    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                  crrayList=(ArrayList<String>)msg.obj;
            Log.e("11111-->",crrayList.toString());
            for (int i = 0; i < crrayList.size(); i++) {
                if (crrayList.get(i).equals("mzxycf")) {
                    listd.add(i);
                }
            }
            if(listd.size()==0){
                if(crrayList.size()==0){

                }

            }
            else {
                mZjbxx.setVisibility(View.GONE);
                mTvjb.setVisibility(View.VISIBLE);
                mBrxm.setText(crrayList.get(0));
                mGhks.setText(crrayList.get(1));
                mGhys.setText(crrayList.get(2));
                mjzrq.setText(crrayList.get(3));
                mBrnl.setText(crrayList.get(4));
                mJtzz.setText(crrayList.get(5));
                mSfzh.setText(crrayList.get(6));
                mdatas = new ArrayList<mzxybean>();
                for(int j=0;j<listd.size()-1;j++){
                    ArrayList<String> drrayList = new ArrayList<String>();

                    for(int k=listd.get(j)+1;k<listd.get(j + 1);k++){
                        drrayList.add(crrayList.get(k));

                    }

                    //Log.e("这是drrlist", drrayList.toString());
                    ArrayList<String> frrayList = new ArrayList<String>();
                    for(int h=2;h<drrayList.size();h++){
                        frrayList.add(drrayList.get(h));

                    }
                    Log.e("这是Frrlist", frrayList.toString());
                   mzxybean mzblbean = new mzxybean(drrayList.get(0), drrayList.get(1),frrayList);
                    mdatas.add(mzblbean);


                    // Log.e("这是Frrlist", frrayList.toString());


                }


                mzblAdapter1 = new MzxyAdapter(MzXyCfActivity.this, mdatas);
                mzblList.setAdapter(mzblAdapter1);
            }


        }
    };
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
        mImgback.setOnClickListener(this);
        mChaxun.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.activity_mzbl);
    }


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


    private void function() {
        String ghxh = mGhxh.getText().toString();
        InputMethodManager imm = (InputMethodManager) getSystemService(MzXyCfActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mGhxh.getWindowToken(), 0);

        //  listclear();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();

        arrayList.add("ghxh");
        brrayList.add(ghxh);
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre("getusermzqtcfh", arrayList, brrayList);
                    //  Log.e("JBXX2------------>", crrayList.toString());


                 Message successMsg=new Message();


                    successMsg.obj = crrayList;

                    MzXyCfActivity.this.handler.sendMessage(successMsg);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();
    }
}
