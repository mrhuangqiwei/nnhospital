package com.qiwei.hospital.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.Pacxadapter;
import com.qiwei.hospital.AdapterManger.Tjgl_jbxxAdapter;
import com.qiwei.hospital.AdapterManger.TjzbjgAdapter;
import com.qiwei.hospital.AdapterManger.Utility;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.PacxBean;
import com.qiwei.hospital.utils.Bean.TjglbhBean;
import com.qiwei.hospital.utils.Bean.TjjgBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**功能描述:体检模块
 * 开发人：黄启位
 * 开发时间：2016，12.25
 * **/
public class ExamnitionActivity extends BaseActivity implements View.OnClickListener {
private TextView mBrxm,mBrnl,mBrxb,mTjbh,mDah,mSfzh,mDwmc,mLxdh,mJtzz,mBgrq,mTjTitle;
   private ListView mXmListView,mZbListview;
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    private String tjbh,yymc;
    private NnApplication app;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> errayList = new ArrayList<String>();

    class MainHandler extends Handler {
        static final int MSG_GET_TJJBXX= 176;
        static final int MSG_GET_TJZBJG = 301;
        static final int MSG_GET_LISMX = 179;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_GET_TJJBXX :
                    crrayList=(ArrayList<String>)msg.obj;
                    Log.e("---------",crrayList.toString());
                    mTjTitle.setText(yymc + "体检报告");
                    String str1=crrayList.toString();
                    String  json1=str1.substring(1, str1.length() - 1);
                    parseJson1(json1);
                    break;
                case MSG_GET_TJZBJG:
                    drrayList.clear();
                    drrayList=(ArrayList<String>)msg.obj;
                Log.e("TAG", "111ss"+drrayList.toString());
                   // Log.e("=====","uuu"+drrayList.size());
                    String str=drrayList.toString();
                    String  json=str.substring(1, str.length() - 1);
                    parseJson( json);
                    break;
                case  MSG_GET_LISMX:
                    errayList=(ArrayList<String>)msg.obj;
                    // Log.e("--------->",errayList.toString());
                   // String str1=errayList.toString();
                  //  String  json1=str1.substring(1, str1.length() - 1);
                  //  parseJsonmx(json1);

                    break;

                default:
                    break;
            }
        }


    }

    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
     mDah=(TextView)findViewById(R.id.tv_tjgl_dah);
        mTjbh=(TextView)findViewById(R.id.tv_tjgl_tjbh);
        mSfzh=(TextView)findViewById(R.id.tv_tjgl_sfzh);
        mBrxm=(TextView)findViewById(R.id.tv_tjgl_brxm);
        mBrxb=(TextView)findViewById(R.id.tv_tjgl_brxb);
        mBrnl=(TextView)findViewById(R.id.tv_tjgl_brnl);
        mLxdh=(TextView)findViewById(R.id.tv_tjgl_lxdh);
        mBgrq=(TextView)findViewById(R.id.tv_tjgl_bgrq);
        mDwmc=(TextView)findViewById(R.id.tv_tjgl_ssdw);
        mJtzz=(TextView)findViewById(R.id.tv_tjgl_jtzz);
        mXmListView=(ListView)findViewById(R.id.lis_xmmc);
        mZbListview=(ListView)findViewById(R.id.list_ls_jyjg);
        mTjTitle=(TextView)findViewById(R.id.tv_examinition_title);
        app=(NnApplication)getApplication();
        yymc=app.getYymc();
        Bundle bundle = this.getIntent().getExtras();
        tjbh=bundle.getString("tjbh");
        inintdata();
    }

    private void inintdata() {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("sfzh");
        brrayList.add(tjbh);
        String name="getTjjbxx";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,176);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_examnition);
    }
    private void parseJson(String json) {
        try{
            JSONObject object =new JSONObject(json);
            final List<TjjgBean> tjjgBeanss =new ArrayList<TjjgBean>();
            JSONArray lisData=object.getJSONArray("GetTjjg");
            for(int i=0;i<lisData.length();i++){
                JSONObject lismc=lisData.getJSONObject(i);
            TjjgBean tjjgBean = JSON.parseObject(lismc.toString(), TjjgBean.class);
               tjjgBeanss.add(tjjgBean);}
          //  Log.e("----------->",tjjgBeanss.get(0).getZbjg());
          TjzbjgAdapter tjzbjgAdapter=new TjzbjgAdapter(ExamnitionActivity.this,tjjgBeanss);
            mZbListview.setAdapter(tjzbjgAdapter);
            Utility.setListViewHeightBasedOnChildren( mZbListview);
            //mXmList.setAdapter(tjzbjgAdapter);
        }
        catch (Exception e){
        }
    }


    private void parseJson1(String json1) {
        try{
            JSONObject object =new JSONObject(json1);
            final List<TjglbhBean> tjglbhBeans =new ArrayList<TjglbhBean>();
            JSONArray lisData=object.getJSONArray("GetTjjbxx");
            for(int i=0;i<lisData.length();i++){
                JSONObject lismc=lisData.getJSONObject(i);
                TjglbhBean tjglbhBean = JSON.parseObject(lismc.toString(), TjglbhBean.class);
               tjglbhBeans.add(tjglbhBean);}
            Tjgl_jbxxAdapter tjgl_jbxxAdapter=new Tjgl_jbxxAdapter(ExamnitionActivity.this,tjglbhBeans);
            mXmListView.setAdapter(tjgl_jbxxAdapter);
            mXmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mBrxm.setText(tjglbhBeans.get(i).getXm());
                    mDah.setText(tjglbhBeans.get(i).getGrbh());
                    mTjbh.setText(tjglbhBeans.get(i).getTjbh());
                    mSfzh.setText(tjglbhBeans.get(i).getSfzh());
                    mBrxb.setText(tjglbhBeans.get(i).getXb());
                    mBrnl.setText(tjglbhBeans.get(i).getNl());
                    mLxdh.setText(tjglbhBeans.get(i).getSj());
                    mBgrq.setText(tjglbhBeans.get(i).getBgdyrq());
                    mDwmc.setText(tjglbhBeans.get(i).getDwmc());
                    mJtzz.setText(tjglbhBeans.get(i).getJtdz());
                    GetTjjg();
                }
            });
            //mXmList.setAdapter(tjzbjgAdapter);
        }
        catch (Exception e){
        }
    }

    private void GetTjjg() {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("tjbh");
        brrayList.add(tjbh);
        String name="getTjjbg";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,301);

    }


    @Override
    public void onClick(View view) {

    }
}
