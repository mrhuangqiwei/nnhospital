package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.KSjjAdapter;
import com.qiwei.hospital.AdapterManger.MzsjAdapter;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 开发人：黄启位
 * 开发时间：2016.7.1
 * 开发功能：科室简介
 */
public class KsjsActivity extends BaseActivity implements View.OnClickListener {
    /**mIncludeTitle:标题栏
     * mIncludeKSjs：科室介绍
     */
    private LinearLayout mIncludeTitle,mIncludeKSjs;
    /**
     * mTextTitle:标题栏
     * mTextKsjs：科室简介
     */
    private TextView mTextTitle,mTextKsjs;
    // 返回键
    private ImageButton mBackButton;
    /**
     * 网络操作列表
     */
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    //adapter容器
    private KSjjAdapter ksjsadapter;
    //网络请求
    private HttpConnSoap Soap = new HttpConnSoap();
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    //listVIEW
    private ListView mListKsjs;
    @Override
    protected void initEnvironment() {

    }
    class MainHandler extends Handler {
        static final int MSG_PUSH_JBXX = 141;


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_PUSH_JBXX :
                    crrayList=(ArrayList<String>)msg.obj;
                    if(crrayList==null){
                        Toast.makeText(KsjsActivity.this,"亲没有获取到网络数据，请重试",Toast.LENGTH_SHORT).show();
                    }
                   else { //Log.e("--------->", crrayList.toString());
                        String str=crrayList.toString();
                        String  json=str.substring(1, str.length() - 1);
                        Log.e("--------->1111", json);
                                 parseJson(json);
                    }
                    break;


                default:
                    break;
            }
        }}
    



    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.ksjs_title);
        mTextTitle=(TextView)mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.ks_and_ys_yyks);
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);
        mListKsjs=(ListView)findViewById(R.id.ksjj_ks_list);
        mIncludeKSjs=(LinearLayout)findViewById(R.id.ks_jj);
        mTextKsjs=(TextView)mIncludeKSjs.findViewById(R.id.include_tv_ksjj);
        mIncludeKSjs.setVisibility(View.GONE);
        initdatas();
    }

    private void initdatas() {
        arrayList.clear();
        brrayList.clear();
        String name="getksjj";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,141);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_ksjs);
    }


    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.img_title_back:
        finish();break;

    default:break;
}

    }

    /**
     * 解析json并加载数据
     * @param json
     */
    private void parseJson(String json){
        try{
            JSONObject object =new JSONObject(json);
            final List<KsandysBean> ksjjs =new ArrayList<KsandysBean>();
            JSONArray ksjjData=object.getJSONArray("GeKsjjs");
            for(int i=0;i<ksjjData.length();i++){
                KsandysBean ksandysbeanobject=new KsandysBean();
                JSONObject ksjs=ksjjData.getJSONObject(i);
                String Kabm=ksjs.getString("Kabm");
                String Ksmc=ksjs.getString("Ksmc");
                String Ksjjmx=ksjs.getString("Ksjjmx");
                ksandysbeanobject.setKabm(Kabm);
                ksandysbeanobject.setKsmc(Ksmc);
                ksandysbeanobject.setKsjjmx(Ksjjmx);
                ksjjs.add(ksandysbeanobject);
            }
            ksjsadapter=new KSjjAdapter(KsjsActivity.this,ksjjs);
            mListKsjs.setAdapter(ksjsadapter);
            mListKsjs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mListKsjs.setVisibility(View.GONE);
                    mIncludeKSjs.setVisibility(View.VISIBLE);
                    mTextKsjs.setText("   "+ksjjs.get(position).getKsjjmx());
                }
            });
        }
        catch (Exception e){

        }
    }



}
