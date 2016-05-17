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
import com.qiwei.hospital.AdapterManger.MyYyAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MyYyghBean;
import com.qiwei.hospital.utils.Bean.item_yy_ksBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class MyYYInfoActivity extends BaseActivity implements View.OnClickListener{
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> frrayList = new ArrayList<String>();
    private ImageButton mImgback;
    private List<MyYyghBean> mdatas;
    private MsgNetUtil msgNetUtil;
    private ListView mListView;
    private  MainHandler mainHandler;
    private NnApplication app;
    private  String userid;
    private  String kswz;
    private NetUtil netUtil;
    private MyYyAdapter myYyAdapter;
private MyYyghBean myYyghBean;
    private   String yyghid;
    private  String brid;
    private String brxm;
    private  String yyys;
    private  String yyysxm;
    private String yyghrq;
    private  String yyrq;
    private String brdh;
    private  String ksmc;

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.img_uc_center_back:
        finish();
        break;
    default:
        break;
}
    }

    class MainHandler extends Handler {
        static final int MSG_YYXX= 107;
        static final int MSG_YYXXMX= 108;
                @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  MSG_YYXX:
             crrayList=(ArrayList<String>)msg.obj;
                  if(crrayList==null){
               Toast.makeText(MyYYInfoActivity.this,"您没有预约信息",Toast.LENGTH_LONG).show();
                          return;
}
                     if(crrayList.size()>5){

                             mdatas=new ArrayList<MyYyghBean>();
                             for(int i=0;i<crrayList.size();i=i+11){
                              myYyghBean=new MyYyghBean(crrayList.get(i+0),crrayList.get(i+1),crrayList.get(i+2),crrayList.get(i+3),crrayList.get(i+4),crrayList.get(i+5),
                                    crrayList.get(i+6),crrayList.get(i+7),crrayList.get(i+8),crrayList.get(i+9),crrayList.get(i+10));
                                 mdatas.add(myYyghBean);
                        }


                        myYyAdapter=new MyYyAdapter(MyYYInfoActivity.this,mdatas);
                        mListView.setAdapter(myYyAdapter);
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                brxm=mdatas.get(position).getBrxm();yyys=mdatas.get(position).getYyyszh();
                                yyysxm=mdatas.get(position).getYyysxm();yyghrq=mdatas.get(position).getYyghrq();
                                Log.e("yyghrq------->",yyghrq);
                                Log.e("ys------>",yyys);
                                yyghid=mdatas.get(position).getYyghid();brid=mdatas.get(position).getBrid();
                                kswz=mdatas.get(position).getKswz();brdh=mdatas.get(position).getBrdh();
                                ksmc=mdatas.get(position).getKsmc();yyrq=mdatas.get(position).getYyrq();
                                Intent intent= new Intent();
                                intent.setClass(MyYYInfoActivity.this, MyyyghMxActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("brxm",brxm);
                                bundle.putString("yyyszh",yyys);
                                bundle.putString("yyysxm",yyysxm);
                                bundle.putString("yyghrqmx",yyghrq);
                                bundle.putString("brid",brid);
                                bundle.putString("yyghid",yyghid);
                                bundle.putString("kswz", kswz);
                                bundle.putString("ksmc", ksmc);
                                bundle.putString("brdh", brdh);
                                bundle.putString("yyrq",yyrq);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });

                    }


                    break;

                case  MSG_YYXXMX:
                

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
mListView=(ListView)findViewById(R.id.list_myyyinfo);
        mImgback=(ImageButton)findViewById(R.id.img_uc_center_back);
        mImgback.setOnClickListener(this);
   initdata();     
    }

    private void initdata() {
        app=(NnApplication)getApplication();
        userid=app.getUserid();
        arrayList.add("manageid");
        brrayList.add(userid);
        mainHandler=new MainHandler();

        msgNetUtil=new MsgNetUtil("getsamplefriendinfo",mainHandler,arrayList,brrayList,107);

    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_my_yyinfo);
    }


}
