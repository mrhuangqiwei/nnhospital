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
import android.widget.ListView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.YyksAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.item_yy_ksBean;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class YyKsActivity extends BaseActivity implements View.OnClickListener {
private ListView mListview;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private NetUtil netUtil;
    private List<item_yy_ksBean> mdatas;
    private YyksAdapter yyksAdapter;
    /**
     * 返回按钮
     */
    private ImageButton mImgBack;
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;
            if(crrayList.size()>0){
                mdatas=new ArrayList<item_yy_ksBean>();
                for(int i=0;i<crrayList.size();i=i+2){
                    item_yy_ksBean item_yy_ksbean=new item_yy_ksBean(crrayList.get(i),crrayList.get(i+1));
                    mdatas.add(item_yy_ksbean);
                }
               yyksAdapter = new YyksAdapter(YyKsActivity.this, mdatas);
                mListview.setAdapter( yyksAdapter);
                mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       // Toast.makeText(YyKsActivity.this,mdatas.get(position).getKsmc(),Toast.LENGTH_LONG).show();
                        Intent intent= new Intent();
                        intent.setClass(YyKsActivity.this, YyysActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putString("ksbm", mdatas.get(position).getKsbm());

                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    };
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mListview=(ListView)findViewById(R.id.list_yy_ks);
        inintdatas();
        mImgBack=(ImageButton)findViewById(R.id.img_mzfy_back);
        mImgBack.setOnClickListener(this);
    }

    private void inintdatas() {
        arrayList.clear();
        brrayList.clear();

        String name="getKsxx";
      netUtil=new NetUtil(name,myhandler,arrayList,brrayList);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_yy_ks);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_mzfy_back:
                finish();
                break;
            default:break;
        }
    }
}
