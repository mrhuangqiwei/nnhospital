package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.MyYyAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MyYyghBean;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import java.util.ArrayList;

public class MyyyghMxActivity extends BaseActivity implements View.OnClickListener{
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private   String yyghid;
    private  String brid;
    private String brxm;
    private  String yyys;
    private  String yyysxm;
    private String yyghrq;
    private  String yyrq;
    private String brdh;
    private  String ksmc;
    private  String kswz;
    private TextView mBrxm;
    private TextView mYsxm;
    private TextView mYyjzrq; 
    private TextView mYydd;
    private TextView mBrdh;
    private TextView mKsmc;
    private TextView mSbsj;
    private TextView mXbsj;
    private MsgNetUtil msgNetUtil;
   private MainHandler mainHandler;
     private ImageButton imgback;
    private Button mQxyy;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_uc_center_back:
                finish();
                break;
            case R.id.login_button_cancle:
                quxiao();
                break;
            default:
                break;
        }

    }

    private void quxiao() {
        arrayList.clear();
        brrayList.clear();

        arrayList.add("brid");
        arrayList.add("yyghid");
        brrayList.add(brid);
        brrayList.add(yyghid);

        mainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("deleteyyxx",mainHandler,arrayList,brrayList,108);
    }

    private class MainHandler extends Handler {
        static final int MSG_YYSXBSJ= 109;
        static final int MSG_YYXXMX= 108;


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_YYSXBSJ:

                    crrayList=(ArrayList<String>)msg.obj;


                    if(crrayList.size()>1){
                        mSbsj.setText(crrayList.get(0));
                        mXbsj.setText(crrayList.get(1));
                    }


                    break;

                case  MSG_YYXXMX:
                   drrayList=(ArrayList<String>)msg.obj;
                    if(drrayList.get(0).equals("true")){
                        Toast.makeText(MyyyghMxActivity.this,"取消预约成功",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MyyyghMxActivity.this, UCenterActivity.class);
                        startActivity(intent);
                    }
                   else {
                        Toast.makeText(MyyyghMxActivity.this,"取消预约失败!",Toast.LENGTH_LONG).show();

                    }

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
        imgback=(ImageButton)findViewById(R.id.img_uc_center_back);
        mBrxm=(TextView)findViewById(R.id.tv_item_myyyinfo_brxm);
        mYsxm=(TextView)findViewById(R.id.item_tv_myyinfo_ys);
        mYyjzrq=(TextView)findViewById(R.id.id_tv_yyjzsj);
        mYydd=(TextView)findViewById(R.id.id_tv_yykswz);
        mBrdh=(TextView)findViewById(R.id.id_tv_hzdh);
        mKsmc=(TextView)findViewById(R.id.id_tv_ghks);
        mSbsj=(TextView)findViewById(R.id.id_tv_yssbsj);
        mXbsj=(TextView)findViewById(R.id.id_tv_ysxbsj);
        mQxyy=(Button)findViewById(R.id.login_button_cancle);

        Bundle bundle = this.getIntent().getExtras();
        brxm=bundle.getString("brxm");
        yyys=bundle.getString("yyyszh");
        Log.e("yyyszh---",yyys);

        yyysxm=bundle.getString("yyysxm");
        yyrq=bundle.getString("yyrq");
        kswz=bundle.getString("kswz");
        brdh=bundle.getString("brdh");
        ksmc=bundle.getString("ksmc");
        yyghrq=bundle.getString("yyghrqmx");
        Log.e("yyghrqmx--",yyghrq+"");
        brid=bundle.getString("brid");
        yyghid=bundle.getString("yyghid");
        mBrxm.setText(brxm);
        mYsxm.setText(yyysxm);
        mYyjzrq.setText(yyrq);
        mYydd.setText(kswz);
        mBrdh.setText(brdh);
        mKsmc.setText(ksmc);
        imgback.setOnClickListener(this);
        mQxyy.setOnClickListener(this);
        initdata();

    }

    private void initdata() {
        arrayList.clear();
        brrayList.clear();

        arrayList.add("ysbm");
        arrayList.add("yyghrq");
        brrayList.add(yyys);
        brrayList.add(yyghrq);

        mainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("getyssbbyyymx",mainHandler,arrayList,brrayList,109);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_myyygh_mx);
    }

 
}
