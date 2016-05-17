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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendAdapter;
import com.qiwei.hospital.AdapterManger.mzblAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.TeamBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtilBool;

import java.util.ArrayList;
import java.util.List;

public class Yygh_ysxx extends BaseActivity implements View.OnClickListener{

    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> frrayList = new ArrayList<String>();
    private TextView ksmc;
    private TextView ysxm;
    private TextView kswz;
    private TextView jzrq;
    private TextView ghfy;
    private TextView sbsj;
    private TextView xbsj;
    //预约挂号
    private Button mBtnYY;
    private RelativeLayout mReList;
    private TextView mJzr;
    private ImageView mImgnext;
    private NetUtil netUtil;
    private RelativeLayout mLlList;
    private ListView mList;
   private RelativeLayout mRejzr;
    private FriendAdapter friendAdapter;
    private NnApplication app;
    private List<FriendBean> mdatas;
    private  String brxm;
    private  String brsfzh;
    private String  phdh;
    private  String brdh;
    private String brjtzz;
    private  String brxb;
    private  String brnl;
   private String ksmc1;
  private   String yszh1;
  private   String ksbm1;
   private String  ysxm1;
   private String sbs1j;
   private String xbsj1;
   private String sbrq1;
   private String sbdd;
  private  String yyzh;
    private String djrq;
    private String ghrq;
    private String yxrq;
  private   MainHandler mainHandler;
    private NetUtilBool netUtilBool;
    private MsgNetUtil msgNetUtil;
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;

            Log.e("crrls------",crrayList.toString());
            if(crrayList.size()>6){
                mdatas=new ArrayList<FriendBean>();
             for(int k=0;k<crrayList.size();k=k+7){
                 FriendBean friendBean=new FriendBean(crrayList.get(k),
                         crrayList.get(k+1),crrayList.get(k+2),crrayList.get(k+3),crrayList.get(k+4),crrayList.get(k+5),crrayList.get(k+6));
                 mdatas.add(friendBean);
             }
                friendAdapter=new FriendAdapter(Yygh_ysxx.this,mdatas);
                mLlList.setVisibility(View.VISIBLE);
                mList.setAdapter(friendAdapter);
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        mJzr.setText(mdatas.get(position).getBrxm());
                        brxm=mdatas.get(position).getBrxm();
                        brsfzh=mdatas.get(position).getSfzh();
                        phdh=mdatas.get(position).getPh();
                        brdh=mdatas.get(position).getBrdh();
                        brjtzz=mdatas.get(position).getBrjtzzl();
                        brxb=mdatas.get(position).getBrxb();
                        brnl=mdatas.get(position).getBrnl();
                        mReList.setVisibility(View.GONE);

                        mImgnext.setVisibility(View.GONE);
                    }
                });
            }

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_yygh_ysxx:
                initdata();
                mReList.setVisibility(View.VISIBLE);

            break;
            case  R.id.login_button_login:
                inintdata1();
            default:
                break;
        }
    }

    private void inintdata1() {
        arrayList.clear();
        brrayList.clear();

        arrayList.add("yyghrq");
        arrayList.add("brxm");
        arrayList.add("brxb");
        arrayList.add("brnl");
        arrayList.add("sfzh");
        arrayList.add("jtzz");
        arrayList.add("sj");
        arrayList.add("yyys");
        arrayList.add("yyks");
        arrayList.add("yydjrq");
        arrayList.add("yyyxrq");
        brrayList.add(ghrq);
        brrayList.add(brxm);
        brrayList.add(brxb);
        brrayList.add(brnl);
        brrayList.add(brsfzh);
        brrayList.add(brjtzz);
        brrayList.add(brdh);
        brrayList.add(yszh1);
        brrayList.add(ksbm1);
        brrayList.add(djrq);
        brrayList.add(yxrq);
           mainHandler=new MainHandler();

                    msgNetUtil=new MsgNetUtil("appointment ",mainHandler,arrayList,brrayList,105);








    }

    private void initdata() {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("manageid");
        app=(NnApplication)getApplication();
        brrayList.add(app.getUserid());
        String name="getuserfriendinfo";
        netUtil=new NetUtil(name,myhandler,arrayList,brrayList);
    }


    class MainHandler extends Handler {
        static final int MSG_YYGH= 105;
        static final int MSG_YYKS= 106;
        static final int Msg_ZYCFSY= 102;
        static final int MSG_ZYCF_LIST = 103;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  MSG_YYGH:

              drrayList=(ArrayList<String>)msg.obj;

               Log.e("dr----->",drrayList.toString());
                    Toast.makeText(Yygh_ysxx.this,"预约成功",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(Yygh_ysxx.this,MyYYInfoActivity.class);
                    startActivity(intent);


                    break;

                case  MSG_YYKS:
                     frrayList=(ArrayList<String>)msg.obj;
                    Log.e("frrr----->",frrayList.toString());
                    if(frrayList.size()>0){
                        ksbm1=frrayList.get(0);
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
     ksmc=(TextView)findViewById(R.id.tv_yygh_ysxx_ghks);
     ysxm=(TextView)findViewById(R.id.tv_yygh_ysxx_ghys);
        kswz=(TextView)findViewById(R.id.tv_yygh_ysxx_kswz);
        jzrq=(TextView)findViewById(R.id.tv_yygh_ysxx_jzrq);
     mImgnext=(ImageView)findViewById(R.id.img_next);
      sbsj=(TextView)findViewById(R.id.tv_yygh_ysxx_sbsj);
        xbsj=(TextView)findViewById(R.id.tv_yygh_ysxx_xbsj);
        mRejzr=(RelativeLayout)findViewById(R.id.re_yygh_ysxx);
        mRejzr.setOnClickListener(this);
        mLlList=(RelativeLayout)findViewById(R.id.ll_yygh_jzrxx);
        mList=(ListView)findViewById(R.id.list_yygh_cyjzr);
        mJzr=(TextView)findViewById(R.id.textView2);
        mReList=(RelativeLayout)findViewById(R.id.re_list_view);
        mBtnYY=(Button)findViewById(R.id.login_button_login);
        mBtnYY.setOnClickListener(this);

        Bundle bundle = this.getIntent().getExtras();

          ysxm1=bundle.getString("ysxm");
         yszh1=bundle.getString("yszh");
        sbs1j=bundle.getString("sbsj");
          xbsj1=bundle.getString("xbsj");
        sbrq1=bundle.getString("sbrq");
         ksmc1=bundle.getString("ksmc");
         sbdd=bundle.getString("dd");
        yyzh=bundle.getString("yszh");
        djrq=bundle.getString("djrq");
        ghrq=bundle.getString("ghrq");
        yxrq=bundle.getString("yxrq");
        ksmc.setText(ksmc1);
        ysxm.setText(ysxm1);
        jzrq.setText(sbrq1);
        sbsj.setText(sbs1j);
        xbsj.setText(xbsj1);
        kswz.setText(sbdd);
        inintdata2();
    }

    private void inintdata2() {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("czybm");
        brrayList.add(yszh1);
        mainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("getksbmbyyszh",mainHandler,arrayList,brrayList,106);

    }


    @Override
    protected int getLayoutId() {
        return (R.layout.activity_yygh_ysxx);
    }



}
