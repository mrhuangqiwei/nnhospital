package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.KSjjAdapter;

import com.qiwei.hospital.AdapterManger.YSPBAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.Bean.YSBCBean;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**开发时间：2016.9.20
 * 开发人： 黄启位
 * 功能描述：显示预约挂号科室上班医生
 * **/
public class Yyys_Activity extends BaseActivity implements  View.OnClickListener {
 /** 星期**/
    private TextView mTvxq1,mTvxq2,mTvxq3,mTvxq4,mTvxq5,mTvxq6,mTvxq7;
/**日期**/
    private TextView rq1,rq2,rq3,rq4,rq5,rq6,rq7;
    /**grideview **/
    private GridView mGride,mGride1;
    //回传消息
    private MsgNetUtil msgNetUtil;
    /**arraylist 数组**/
    private ArrayList<String> arrayList = new ArrayList<String>(), brrayList = new ArrayList<String>(),crrayList = new ArrayList<String>(),drrayList = new ArrayList<String>(),errayList = new ArrayList<String>();
    //handler
    private MainHandler mainHandler;
    private MainHandler mainHandler1;
    private MainHandler mainHandler2;
    private YSPBAdapter yspbAdapter;
/**返回键**/
    private ImageButton mImgback;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_mzfy_back:
                finish();
                break;
            default:
                break;
        }
    }


    private class MainHandler extends Handler {
        static final int MSG_YYSWPB=124;
        static final int MSG_YYXWPB=125;
        static final int MSG_XQARQ=126;
       @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_YYSWPB:
                  drrayList=(ArrayList<String>)msg.obj;
                    if(drrayList==null){
                        Toast.makeText(Yyys_Activity.this,"亲没有获取到网络数据，请重试",Toast.LENGTH_SHORT).show();
                    }
                    else { Log.e("--------->", drrayList.toString());
                        String str=drrayList.toString();
                        String  json=str.substring(1, str.length() - 1);
                     //   Log.e("--------->1111", json);
                        parseJson(json);

                        inintdata2();
                    }

                    break;
/**
                case  MSG_YYXXMX:
                    drrayList=(ArrayList<String>)msg.obj;
                    if(drrayList.get(0).equals("true")){
                        Toast.makeText(MyyyghMxActivity.this, "取消预约成功", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(MyyyghMxActivity.this, UCenterActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MyyyghMxActivity.this,"取消预约失败!",Toast.LENGTH_LONG).show();

                    }

                    break;
**/

                case MSG_YYXWPB:
                    errayList=(ArrayList<String>)msg.obj;
                    if(errayList==null){
                        Toast.makeText(Yyys_Activity.this,"亲没有获取到网络数据，请重试",Toast.LENGTH_SHORT).show();
                    }
                    else { Log.e("--------->",errayList.toString());
                        String str=errayList.toString();
                        String  json=str.substring(1, str.length() - 1);
                        //   Log.e("--------->1111", json);
                        parseJson1(json);
                    }

                    break;
                case MSG_XQARQ:
                    crrayList=(ArrayList<String>)msg.obj;
                    if(crrayList==null){
                        Toast.makeText(Yyys_Activity.this,"亲没有获取到网络数据，请重试",Toast.LENGTH_SHORT).show();
                    }
                    else {
                      //  Log.e( "handleMessage: ",crrayList.toString() );
                        rq1.setText(crrayList.get(0)); mTvxq1.setText(crrayList.get(1));
                        rq2.setText(crrayList.get(2)); mTvxq2.setText(crrayList.get(3));
                        rq3.setText(crrayList.get(4)); mTvxq3.setText(crrayList.get(5));
                        rq4.setText(crrayList.get(6)); mTvxq4.setText(crrayList.get(7));
                        rq5.setText(crrayList.get(8)); mTvxq5.setText(crrayList.get(9));
                        rq6.setText(crrayList.get(10)); mTvxq6.setText(crrayList.get(11));
                        rq7.setText(crrayList.get(12)); mTvxq7.setText(crrayList.get(13));
                        inintdata();
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
        mGride=(GridView)findViewById(R.id.yspb_gride);
        mTvxq1=(TextView)findViewById(R.id.tv_ysgh_ys_layout1);
        mTvxq2=(TextView)findViewById(R.id.tv_ysgh_ys_layout2);
        mTvxq3=(TextView)findViewById(R.id.tv_ysgh_ys_layout3);
        mTvxq4=(TextView)findViewById(R.id.tv_ysgh_ys_layout4);
        mTvxq5=(TextView)findViewById(R.id.tv_ysgh_ys_layout5);
        mTvxq6=(TextView)findViewById(R.id.tv_ysgh_ys_layout6);
        mTvxq7=(TextView)findViewById(R.id.tv_ysgh_ys_layout7);
        rq1=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq1);
        rq2=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq2);
        rq3=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq3);
        rq4=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq4);
        rq5=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq5);
        rq6=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq6);
        rq7=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq7);
        mGride1=(GridView)findViewById(R.id.yspb_gride1);
        mImgback=(ImageButton)findViewById(R.id.img_mzfy_back);
        mImgback.setOnClickListener(this);
        initdata1();
       // inintdata();
       // inintdata2();
    }

    /**获取下午医生排班信息**/
    private void inintdata2() {
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("ksbm");
        arrayList.clear();
        brrayList.clear();
        arrayList.add("ksbm");
        brrayList.add(name);
        String name1="yspbxwjson";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name1,mainHandler,arrayList,brrayList,125);
    }
    /**获取日期与星期**/
    private  void initdata1(){
        arrayList.clear();
        brrayList.clear();
        mainHandler2=new MainHandler();
        msgNetUtil=new MsgNetUtil("getyyrqandxq",mainHandler2,arrayList,brrayList,126);

    }
    /**获取早上医生排班信息**/
    private void inintdata() {
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("ksbm");
        arrayList.clear();
        brrayList.clear();
        arrayList.add("ksbm");
        brrayList.add(name);
        String name1="yspbswjson";
        mainHandler1=new MainHandler();
        msgNetUtil=new MsgNetUtil(name1,mainHandler1,arrayList,brrayList,124);
    }
    @Override
    protected int getLayoutId() {
        return (R.layout.yygh_ys_layout);
    }
    /**
     * 解析json并加载数据上午
     * @param json
     */
    private void parseJson(String json){
        try{
            JSONObject object =new JSONObject(json);
            final List<YSBCBean> ysbcBeans =new ArrayList<YSBCBean>();
            JSONArray ysbcData=object.getJSONArray("GetYspb");
            for(int i=0;i<ysbcData.length();i++){
                YSBCBean ysbccbeanobject=new YSBCBean();
                JSONObject ysbc=ysbcData.getJSONObject(i);
                String Yzrq=ysbc.getString("Yzrq");
                String sbsj=ysbc.getString("sbsj");
                String xbsj=ysbc.getString("xbsj");
                String xhzs=ysbc.getString("xhzs");
                String xyzs=ysbc.getString("xyzs");
                String czybm=ysbc.getString("czybm");
                String czyxm=ysbc.getString("czyxm");
                String zcmc=ysbc.getString("zcmc");
                String xq=ysbc.getString("xq");
                String mzsbdd=ysbc.getString("mzsbdd");
                String ksmc=ysbc.getString("ksmc");
                String ksbm=ysbc.getString("ksbm");
                String yydjsj=ysbc.getString("yydjsj");
                String yyghsj=ysbc.getString("yyghsj");
                String yyyxsj=ysbc.getString("yyyxsj");
               // String Kabm=ksjs.getString("Kabm");
                ysbccbeanobject.setYzrq(Yzrq);
                ysbccbeanobject.setSbsj(sbsj);
                ysbccbeanobject.setXbsj(xbsj);
                ysbccbeanobject.setXhzs(xhzs);
                ysbccbeanobject.setXyzs(xyzs);
                ysbccbeanobject.setCzybm(czybm);
                ysbccbeanobject.setCzyxm(czyxm);
                ysbccbeanobject.setZcmc(zcmc);
                ysbccbeanobject.setXq(xq);
                ysbccbeanobject.setMzsbdd(mzsbdd);
                ysbccbeanobject.setKsmc(ksmc);
                ysbccbeanobject.setKsbm(ksbm);
                ysbccbeanobject.setYydjsj(yydjsj);
                ysbccbeanobject.setYyghsj(yyghsj);
                ysbccbeanobject.setYyyxsj(yyyxsj);
                ysbcBeans.add(ysbccbeanobject);
            }
            yspbAdapter=new YSPBAdapter(Yyys_Activity.this,ysbcBeans);
            mGride.setAdapter(yspbAdapter);
            mGride.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent();
                    intent.setClass(Yyys_Activity.this, Yygh_ysxx.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("sj","shangwu");
                    bundle.putString("yszh",ysbcBeans.get(position).getCzybm());
                    bundle.putString("djrq",ysbcBeans.get(position).getYydjsj());
                    bundle.putString("ghrq",ysbcBeans.get(position).getYyghsj());
                    bundle.putString("yxrq",ysbcBeans.get(position).getYyyxsj());
                    bundle.putString("ysxm",ysbcBeans.get(position).getCzyxm());
                    //  Log.e("--->",mdatas.get(position).getYsxm());
                  //  bundle.putString("yszh", mdatas.get(position).getYszh());
                    bundle.putString("sbsj", ysbcBeans.get(position).getSbsj());
                    bundle.putString("xbsj",ysbcBeans.get(position).getXbsj());
                    bundle.putString("sbrq", ysbcBeans.get(position).getYzrq());
                    bundle.putString("xq",ysbcBeans.get(position).getXq());
                    bundle.putString("dd",ysbcBeans.get(position).getMzsbdd());
                    bundle.putString("ksmc",ysbcBeans.get(position).getKsmc());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }
        catch (Exception e){

        }
    }

    /**
     * 解析json并加载数据下午
     * @param json
     */
    private void parseJson1(String json){
        try{
            JSONObject object =new JSONObject(json);
            final List<YSBCBean> ysbcBeans =new ArrayList<YSBCBean>();
            JSONArray ysbcData=object.getJSONArray("GetYspb");
            for(int i=0;i<ysbcData.length();i++){
                YSBCBean ysbccbeanobject=new YSBCBean();
                JSONObject ysbc=ysbcData.getJSONObject(i);
                String Yzrq=ysbc.getString("Yzrq");
                String sbsj=ysbc.getString("sbsj");
                String xbsj=ysbc.getString("xbsj");
                String xhzs=ysbc.getString("xhzs");
                String xyzs=ysbc.getString("xyzs");
                String czybm=ysbc.getString("czybm");
                String czyxm=ysbc.getString("czyxm");
                String zcmc=ysbc.getString("zcmc");
                String xq=ysbc.getString("xq");
                String mzsbdd=ysbc.getString("mzsbdd");
                String ksmc=ysbc.getString("ksmc");
                String ksbm=ysbc.getString("ksbm");
                String yydjsj=ysbc.getString("yydjsj");
                String yyghsj=ysbc.getString("yyghsj");
                String yyyxsj=ysbc.getString("yyyxsj");
                // String Kabm=ksjs.getString("Kabm");
                ysbccbeanobject.setYzrq(Yzrq);
                ysbccbeanobject.setSbsj(sbsj);
                ysbccbeanobject.setXbsj(xbsj);
                ysbccbeanobject.setXhzs(xhzs);
                ysbccbeanobject.setXyzs(xyzs);
                ysbccbeanobject.setCzybm(czybm);
                ysbccbeanobject.setCzyxm(czyxm);
                ysbccbeanobject.setZcmc(zcmc);
                ysbccbeanobject.setXq(xq);
                ysbccbeanobject.setMzsbdd(mzsbdd);
                ysbccbeanobject.setKsmc(ksmc);
                ysbccbeanobject.setKsbm(ksbm);
                ysbccbeanobject.setYydjsj(yydjsj);
                ysbccbeanobject.setYyghsj(yyghsj);
                ysbccbeanobject.setYyyxsj(yyyxsj);
                ysbcBeans.add(ysbccbeanobject);
            }
            yspbAdapter=new YSPBAdapter(Yyys_Activity.this,ysbcBeans);
            mGride1.setAdapter(yspbAdapter);
            mGride1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent();
                    intent.setClass(Yyys_Activity.this, Yygh_ysxx.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("sj","xiawu");
                    bundle.putString("yszh",ysbcBeans.get(position).getCzybm());
                    bundle.putString("djrq",ysbcBeans.get(position).getYydjsj());
                    bundle.putString("ghrq",ysbcBeans.get(position).getYyghsj());
                    bundle.putString("yxrq",ysbcBeans.get(position).getYyyxsj());
                    bundle.putString("ysxm",ysbcBeans.get(position).getCzyxm());
                    //  Log.e("--->",mdatas.get(position).getYsxm());
                    //  bundle.putString("yszh", mdatas.get(position).getYszh());
                    bundle.putString("sbsj", ysbcBeans.get(position).getSbsj());
                    bundle.putString("xbsj",ysbcBeans.get(position).getXbsj());
                    bundle.putString("sbrq", ysbcBeans.get(position).getYzrq());
                    bundle.putString("xq",ysbcBeans.get(position).getXq());
                    bundle.putString("dd",ysbcBeans.get(position).getMzsbdd());
                    bundle.putString("ksmc",ysbcBeans.get(position).getKsmc());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }
        catch (Exception e){

        }
    }
}
