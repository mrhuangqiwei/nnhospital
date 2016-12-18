package com.qiwei.hospital.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendmxAdapter;
import com.qiwei.hospital.AdapterManger.Lismcdapter;
import com.qiwei.hospital.AdapterManger.Lismxdapter;
import com.qiwei.hospital.AdapterManger.Utility;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendmxBean;
import com.qiwei.hospital.utils.Bean.LisBean;
import com.qiwei.hospital.utils.Bean.LismxBean;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LisActivity extends BaseActivity {
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> errayList = new ArrayList<String>();
   private TextView mBrxm,mBrxb,mBrnl,mYqh,mBah,mCwh,mKs,mLx,mSjys,mYblx,mTmh,mLczd,mSqsj,mCysj,mJssj,mHostname,mShsj,mJyz,mShz,mZxsb,mZylx;
   private LinearLayout mLayoutlismc;
    private String zyh;
    private Lismcdapter lismcdapter;
    private Lismxdapter lismxdapter;
   private ListView mLismc,mLisjg;
    class MainHandler extends Handler {
        static final int MSG_GET_GHXH = 165;
        static final int MSG_GET_LISID = 166;
        static final int MSG_GET_LISMX = 167;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_GET_GHXH :
                    crrayList=(ArrayList<String>)msg.obj;
                    String yymc=crrayList.get(0);
                   ;mHostname.setText(yymc +"检验报告单");
                    GetLisId();
                    //Log.e("TAG",crrayList.toString());
                    break;
                case MSG_GET_LISID:
                    drrayList=(ArrayList<String>)msg.obj;

                    String str=drrayList.toString();
                    String  json=str.substring(1, str.length() - 1);
                    parseJson( json);
                    break;
                case  MSG_GET_LISMX:
                   errayList=(ArrayList<String>)msg.obj;
                    String str1=errayList.toString();
                    String  json1=str1.substring(1, str1.length() - 1);
                    Log.e("------",json1.toString());
                    parseJsonmx(json1);
                    break;

                default:
                    break;
            }
        }


    }

    /**
     * 解析检验结果
     * @param json1
     */
    private void parseJsonmx(String json1) {
      try{
                JSONObject object =new JSONObject(json1);
                final List<LismxBean> lismxBeans =new ArrayList<LismxBean>();
                JSONArray lismxData=object.getJSONArray("GetLisId");
                for(int i=0;i<lismxData.length();i++){
                    LismxBean lismxBean=new LismxBean();
                    JSONObject friends=lismxData.getJSONObject(i);
                    String jyxh=friends.getString("jyxh");
                    String lx=friends.getString("lx");
                    String bah=friends.getString("bah");
                    String bz=friends.getString("bz");
                    String xh=friends.getString("xh");
                    String value_N=friends.getString("value_N");
                    String value_L=friends.getString("value_L");
                    String value_T=friends.getString("value_T");
                    String jg=friends.getString("jg");
                    String n_min=friends.getString("n_min");
                    String n_max=friends.getString("n_max");
                    String zt=friends.getString("zt");
                    String zwmc=friends.getString("zwmc");
                    String ywmc=friends.getString("ywmc");
                    String sjlx=friends.getString("sjlx");
                    String dw=friends.getString("dw");
                    String cklx=friends.getString("cklx");
                    String xsws=friends.getString("xsws");
                    String value_N_1=friends.getString("value_N_1");
                    String tjdw=friends.getString("tjdw");
                    String yblx=friends.getString("yblx");
                    String ckz_t=friends.getString("ckz_t");
                     lismxBean.setJyxh(jyxh);
                    lismxBean.setLx(lx);
                    lismxBean.setBah(bah);
                    lismxBean.setBz(bz);
                    lismxBean.setXh(xh);
                    lismxBean.setValue_N(value_N);
                    lismxBean.setValue_L(value_L);
                    lismxBean.setValue_T(value_T);
                    lismxBean.setJg(jg);
                    lismxBean.setN_min(n_min);
                    lismxBean.setN_max(n_max);
                    lismxBean.setZt(zt);
                    lismxBean.setZwmc(zwmc);
                    lismxBean.setYwmc(ywmc);
                    lismxBean.setSjlx(sjlx);
                    lismxBean.setDw(dw);
                    lismxBean.setCklx(cklx);
                    lismxBean.setXsws(xsws);
                    lismxBean.setValue_N_1(value_N_1);
                    lismxBean.setTjdw(tjdw);
                    lismxBean.setYblx(yblx);
                    lismxBean.setCkz_t(ckz_t);

                    lismxBeans.add(lismxBean);
                }
          lismxdapter=new Lismxdapter(LisActivity.this,lismxBeans);
                mLisjg.setAdapter(lismxdapter);
          Utility.setListViewHeightBasedOnChildren(mLisjg);
                /**
                 mListKsjs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListKsjs.setVisibility(View.GONE);
                mIncludeKSjs.setVisibility(View.VISIBLE);
                mTextKsjs.setText("   "+ksjjs.get(position).getKsjjmx());
                }
                });**/
            }
            catch (Exception e){

            }



    }

    private void parseJson(String json) {
        try{
            JSONObject object =new JSONObject(json);
            final List<LisBean> lisBeans =new ArrayList<LisBean>();
            JSONArray lisData=object.getJSONArray("GetLisId");
            for(int i=0;i<lisData.length();i++){
                LisBean lisBean=new LisBean();
                JSONObject lismc=lisData.getJSONObject(i);
                String jyxh=lismc.getString("jyxh");
                String brxm=lismc.getString("brxm");
                String brxb=lismc.getString("brxb");
                String cwh=lismc.getString("cwh");
                String lx=lismc.getString("lx");
                String bah=lismc.getString("bah");
                String brnl=lismc.getString("brnl");
                String nldw=lismc.getString("nldw");
                String ksbm=lismc.getString("ksbm");
                String ksmc=lismc.getString("ksmc");
                String sqys=lismc.getString("sqys");
                String sqysxm=lismc.getString("sqysxm");
                String ybbm=lismc.getString("ybbm");
                String ybmc=lismc.getString("ybmc");
                String lczd=lismc.getString("lczd");
                String sqrq=lismc.getString("sqrq");
                String cyrq=lismc.getString("cyrq");
                String jyxm=lismc.getString("jyxm");
                String mc=lismc.getString("mc");
                String bbbh=lismc.getString("bbbh");
                String djrq=lismc.getString("djrq");
                String zxys=lismc.getString("zxys");
                String zxysxm=lismc.getString("zxysxm");
                String zxsb=lismc.getString("zxsb");
                String sbmc=lismc.getString("sbmc");
                String shrq=lismc.getString("shrq");
                String shry=lismc.getString("shry");
                String shryxm=lismc.getString("shryxm");
                lisBean.setJyxh(jyxh);
                lisBean.setBrxm(brxm);
                lisBean.setBrxb(brxb);
                lisBean.setCwh(cwh);
                lisBean.setLx(lx);
                lisBean.setBah(bah);
                lisBean.setBrnl(brnl);
                lisBean.setNldw(nldw);
                lisBean.setKsbm(ksbm);
                lisBean.setKsmc(ksmc);
                lisBean.setSqys(sqys);
                lisBean.setSqysxm(sqysxm);
                lisBean.setYbbm(ybbm);
                lisBean.setYbmc(ybmc);
                lisBean.setLczd(lczd);
                lisBean.setSqrq(sqrq);
                lisBean.setCyrq(cyrq);
                lisBean.setJyxm(jyxm);
                lisBean.setMc(mc);
                lisBean.setBbbh(bbbh);
                lisBean.setDjrq(djrq);
                lisBean.setZxys(zxys);
                lisBean.setZxysxm(zxysxm);
                lisBean.setZxsb(zxsb);
                lisBean.setSbmc(sbmc);
                lisBean.setShrq(shrq);
                lisBean.setShry(shry);
                lisBean.setShryxm(shryxm);
                lisBeans.add(lisBean);

            }
            lismcdapter=new Lismcdapter(LisActivity.this,lisBeans);
            mLismc.setAdapter(lismcdapter);
            mLismc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   mBrxm.setText(lisBeans.get(i).getBrxm());
                    if(lisBeans.get(i).getBrxb().equals("1")){
                        mBrxb.setText("男");
                    }  else {
                        mBrxb.setText("女");}
                   mBrnl.setText(lisBeans.get(i).getBrnl());
                    mYqh.setText(lisBeans.get(i).getBbbh());
                    switch (lisBeans.get(i).getLx()){
                        case "1":
                            mZylx.setText("住院号：");
                            break;
                        case "2":
                            mZylx.setText("门诊号：");
                            break;
                        default:break;
                    }
                    mBah.setText(lisBeans.get(i).getBah());mKs.setText(lisBeans.get(i).getKsmc());
                    mSjys.setText(lisBeans.get(i).getSqysxm());mYblx.setText(lisBeans.get(i).getYbmc());
                    mTmh.setText(lisBeans.get(i).getJyxh()); mLczd.setText(lisBeans.get(i).getLczd());
                    mSqsj.setText(lisBeans.get(i).getSqrq());mCysj.setText(lisBeans.get(i).getCyrq());
                    mJssj.setText(lisBeans.get(i).getDjrq());mShsj.setText(lisBeans.get(i).getShrq());
                    mShz.setText(lisBeans.get(i).getShryxm());mZxsb.setText(lisBeans.get(i).getSbmc());
                    mJyz.setText(lisBeans.get(i).getZxysxm());mCwh.setText(lisBeans.get(i).getCwh());
                    String jyxh=lisBeans.get(i).getJyxh();
                    GetLismx(jyxh);
                }
            });

        }
        catch (Exception e){

        }

    }

    private void GetLismx(String jyxh) {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("jyxh");
        brrayList.add(jyxh);
        String name="getLisIreportmx";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,167);
    }

    @Override
    protected void initEnvironment() {

    }

    private void GetYymc(){
        arrayList.clear();
        brrayList.clear();

        String name="gethosname";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,165);
    }
    private void GetLisId(){
        arrayList.clear();
        brrayList.clear();
        arrayList.add("zyh");
        brrayList.add(zyh);
        String name="getLisId1";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,166);
    }

    @Override
    protected void initViews() {
        Bundle bundle = this.getIntent().getExtras();
        zyh=bundle.getString("zyh");
        mLayoutlismc=(LinearLayout)findViewById(R.id.lis_ll_xmmc);
       mHostname=(TextView)findViewById(R.id.lis_tv_hosname);
        mLismc=(ListView)findViewById(R.id.lis_xmmc);
        mBrxm=(TextView)findViewById(R.id.lis_tv_brxm);
        mBrxb=(TextView)findViewById(R.id.lis_tv_brxb);
        mBrnl=(TextView)findViewById(R.id.lis_tv_brnl);
        mYqh=(TextView)findViewById(R.id.lis_tv_yqh);
        mBah=(TextView)findViewById(R.id.lis_tv_brzyh);
        mCwh=(TextView)findViewById(R.id.lis_tv_cwh);
        mKs=(TextView)findViewById(R.id.lis_tv_brks);
        mSjys=(TextView)findViewById(R.id.lis_tv_sjysxm);
        mYblx=(TextView)findViewById(R.id.lis_tv_ybl);
        mTmh=(TextView)findViewById(R.id.lis_tv_tm);
        mLczd=(TextView)findViewById(R.id.lis_tv_zd);
        mSqsj=(TextView)findViewById(R.id.lis_tv_sqsj);
        mCysj=(TextView)findViewById(R.id.lis_tv_cysj);
        mJssj=(TextView)findViewById(R.id.lis_tv_tjssj);
        mShsj=(TextView)findViewById(R.id.id_tv_lis_shsj);
         mShz=(TextView)findViewById(R.id.id_tv_lis_shry);
        mZxsb=(TextView)findViewById(R.id.id_tv_lis_zxsb);
        mJyz=(TextView)findViewById(R.id.id_tv_lis_zxry);
        mZylx=(TextView)findViewById(R.id.lis_tv_zyh);
        mLisjg=(ListView)findViewById(R.id.list_ls_jyjg);
        GetYymc();
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_lis);
    }

}
