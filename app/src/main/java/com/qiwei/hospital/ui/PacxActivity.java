package com.qiwei.hospital.ui;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.Pacxadapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.PacxBean;
import com.qiwei.hospital.utils.Bean.PacxjgBean;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nnxzyy.common.Tools.StreamTool;

public class PacxActivity extends BaseActivity implements View.OnClickListener {
    private TextView mBrxm,mBrxb,mBrnl,mBrnldw,mZyh,mCwh,mKs,mMzh,mSqys,mSqsj,mJch,mJcbw,mYqmc,
    mTextBt,mYxms,mYxts,mBgr,mBgsj;
    private ImageView imgview;
    private ListView mXmList;
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    private  String yymc,zyh;
    //Handler
    private   MainHandler mainHandler;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> errayList = new ArrayList<String>();
    private Pacxadapter pacxadapter;
    private LinearLayout mLxmmc;
private Bitmap bmp;
    class MainHandler extends Handler {
        static final int MSG_GET_GHXH = 172;
        static final int MSG_GET_LISID = 173;
        static final int MSG_GET_LISMX = 174;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_GET_GHXH :
                    crrayList=(ArrayList<String>)msg.obj;
                   yymc=crrayList.get(0);
                    //;mHostname.setText(yymc +"检验报告单");
                    GetPacxId();;
                    //Log.e("TAG",crrayList.toString());
                    break;
                case MSG_GET_LISID:
                    drrayList=(ArrayList<String>)msg.obj;
                    Log.e("TAG",drrayList.toString());
                    String str=drrayList.toString();
                    String  json=str.substring(1, str.length() - 1);
                    parseJson( json);
                    break;
                case  MSG_GET_LISMX:
                    errayList=(ArrayList<String>)msg.obj;
                   // Log.e("--------->",errayList.toString());
                    String str1=errayList.toString();
                    String  json1=str1.substring(1, str1.length() - 1);
                    parseJsonmx(json1);
                    /**
                    errayList=(ArrayList<String>)msg.obj;
                    String str1=errayList.toString();
                    String  json1=str1.substring(1, str1.length() - 1);
                    Log.e("------", json1.toString());
                    parseJsonmx(json1);**/
                    break;

                default:
                    break;
            }
        }


    }

    private void parseJsonmx(String json1) {try {
        JSONObject object =new JSONObject(json1);
        final List<PacxjgBean> lisBeans =new ArrayList<PacxjgBean>();
        JSONArray lisData=object.getJSONArray("GetPacxReport");
        for(int i=0;i<lisData.length();i++){
            JSONObject lismc=lisData.getJSONObject(i);
            PacxjgBean pacxBean = JSON.parseObject(lismc.toString(), PacxjgBean.class);
            lisBeans.add(pacxBean);}
        mYxms.setText(lisBeans.get(0).getLaybe1());
        mYxts.setText(lisBeans.get(0).getLaybe2());
    }
    catch (Exception e){}
    }


    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //if(!bmp.isRecycled()){bmp.recycle();}
    }

    @Override
    protected void initViews() {
        Bundle bundle = this.getIntent().getExtras();
        zyh=bundle.getString("zyh");
        mLxmmc=(LinearLayout)findViewById(R.id.lis_ll_xmmc);
        mXmList=(ListView)findViewById(R.id.pacx_xmmc);
        mBrxm=(TextView)findViewById(R.id.lis_tv_brxm);
        mBrxb=(TextView)findViewById(R.id.lis_tv_brxb);
        mBrnl=(TextView)findViewById(R.id.lis_tv_brnl);
        mBrnldw=(TextView)findViewById(R.id.lis_tv_nldw);
        mZyh=(TextView)findViewById(R.id.lis_tv_brzyh);
        mCwh=(TextView)findViewById(R.id.lis_tv_cwh);
        mMzh=(TextView)findViewById(R.id.lis_tv_mzh);
        mKs=(TextView)findViewById(R.id.lis_tv_brks);
        mSqys=(TextView)findViewById(R.id.lis_tv_sjysxm);
        mSqsj=(TextView)findViewById(R.id.lis_tv_sqsj);
        mJch=(TextView)findViewById(R.id.lis_tv_tm);
        mJcbw=(TextView)findViewById(R.id.lis_tv_zd);
        mYqmc=(TextView)findViewById(R.id.lis_tv_yqh);
        mYxms=(TextView)findViewById(R.id.pacx_tv_yx);
        mYxts=(TextView)findViewById(R.id.pacx_tv_yxts);
        mBgr=(TextView)findViewById(R.id.id_tv_lis_zxry);
        mBgsj=(TextView)findViewById(R.id.id_tv_pacx_bgsj);
        GetYymc();
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_pacx);
    }

    @Override
    public void onClick(View view) {

    }
    private void GetYymc(){
        arrayList.clear();
        brrayList.clear();

        String name="gethosname";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,172);
    }

    private void GetPacxId(){
        arrayList.clear();
        brrayList.clear();
        arrayList.add("zyh");
        brrayList.add(zyh);
        String name="getPacx";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,173);
    }

    private void parseJson(String json) {
        try{
            JSONObject object =new JSONObject(json);
             final List<PacxBean> lisBeans =new ArrayList<PacxBean>();
            JSONArray lisData=object.getJSONArray("GetPacxId");
            for(int i=0;i<lisData.length();i++){
                JSONObject lismc=lisData.getJSONObject(i);
                PacxBean pacxBean = JSON.parseObject(lismc.toString(), PacxBean.class);
                lisBeans.add(pacxBean);}
           Pacxadapter pacxadapter=new Pacxadapter (PacxActivity.this,lisBeans);
            mXmList.setAdapter(pacxadapter);
            mXmList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mBrxm.setText(lisBeans.get(i).getNAME());
                    mBrxb.setText(lisBeans.get(i).getSEX());
                    mBrnl.setText(lisBeans.get(i).getAGE());
                    mBrnldw.setText(lisBeans.get(i).getAGEUNIT());
                    mKs.setText(lisBeans.get(i).getLODGESECTION());
                    mCwh.setText(lisBeans.get(i).getBEDNO());
                    mMzh.setText(lisBeans.get(i).getCLINICNO());
                    mZyh.setText(lisBeans.get(i).getINPATIENTNO());
                    mSqys.setText(lisBeans.get(i).getLODGEDOCTOR());
                    mSqsj.setText(lisBeans.get(i).getLODGEDATE());
                    mJch.setText(lisBeans.get(i).getSTUDYID());
                    mJcbw.setText(lisBeans.get(i).getPARTOFCHECK());
                    mYqmc.setText(lisBeans.get(i).getCLASSNAME());
                    mBgsj.setText(lisBeans.get(i).getReportDate());
                    mBgr.setText(lisBeans.get(i).getReportDoctor());
                    String studyid=lisBeans.get(i).getSTUDYID();
                    GetReport(studyid);
                }
            });
   }
        catch (Exception e){

        }

    }

    private void GetReport(String studyid) {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("studyid");
        brrayList.add(studyid);
        String name="Pacxreport";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,174);
    }
}
