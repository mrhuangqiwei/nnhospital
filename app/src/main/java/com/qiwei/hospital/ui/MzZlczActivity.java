package com.qiwei.hospital.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.MzzlAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzlczBean;
import com.qiwei.hospital.utils.Bean.mzxybean;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;
import java.util.List;

public class MzZlczActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton mImgback;
    private ImageButton mChaxun;
    private TextView mTvjb;
    private  EditText mGhxh;
    private LinearLayout mLlmx;
    private RelativeLayout mRetitle;
    private TextView mSqys;
    private TextView mGhks;
    private TextView mBrxm;
    private TextView mSqsj;
    private ListView mZllist;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> fcrrayList = new ArrayList<String>();
    private ArrayList<String> grrayList = new ArrayList<String>();
    private List<MzzlczBean>mDatas;
    private MzzlAdapter mzzlAdapter;
 private List<MzzlczBean> mdatas;
    private HttpConnSoap Soap = new HttpConnSoap();
    Handler myhandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;
            if(crrayList.size()>5){
                mRetitle.setVisibility(View.GONE);
                mLlmx.setVisibility(View.VISIBLE);
                mGhks.setText(crrayList.get(0));
                mSqys.setText(crrayList.get(1));
                mSqsj.setText(crrayList.get(2));
                mBrxm.setText(crrayList.get(3));
                mDatas=new ArrayList<MzzlczBean>();
                for (int i=4;i<crrayList.size();i=i+4){
                   MzzlczBean mzzlczBean=new MzzlczBean(crrayList.get(i),crrayList.get(i+1),crrayList.get(i+2),crrayList.get(i+3));
                    mDatas.add(mzzlczBean);
                }
                mzzlAdapter=new MzzlAdapter(MzZlczActivity.this,mDatas);
                mZllist.setAdapter(mzzlAdapter);
            }
        }
    };

    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mImgback=(ImageButton)findViewById(R.id.img_mzfy_back);
        mTvjb=(TextView)findViewById(R.id.tv_mzbl_jbxx);
        mGhxh=(EditText)findViewById(R.id.mzbl_tv_brrygh);
        mChaxun=(ImageButton)findViewById(R.id.mzbl_btn_ghxh);
        mSqys=(TextView)findViewById(R.id.tv_mzzlcz_sqys);
        mGhks=(TextView)findViewById(R.id.tv_mzzlcz_jzks);
        mBrxm=(TextView)findViewById(R.id.tv_mzzlcz_brxm);
        mSqsj=(TextView)findViewById(R.id.tv_mzzlcz_sqsj);
        mZllist=(ListView)findViewById(R.id.list_mzzl_mx);
        mLlmx=(LinearLayout)findViewById(R.id.ll_mzcz_mx);
        mRetitle=(RelativeLayout)findViewById(R.id.re_mzbl_chx);
        mChaxun.setOnClickListener(this);
      //  mImgback.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return (R.layout.activity_mz_zlcz);
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
        InputMethodManager imm = (InputMethodManager) getSystemService(MzZlczActivity.this.INPUT_METHOD_SERVICE);
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

                    crrayList = Soap.GetWebServre("getusermzZlc", arrayList, brrayList);
                    //  Log.e("JBXX2------------>", crrayList.toString());


                    Message successMsg=new Message();


                    successMsg.obj = crrayList;

                    MzZlczActivity.this.myhandler.sendMessage(successMsg);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();
    }

    }

