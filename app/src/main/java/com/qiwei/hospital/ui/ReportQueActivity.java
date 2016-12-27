package com.qiwei.hospital.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendmxAdapter;
import com.qiwei.hospital.AdapterManger.TimeAdapter;
import com.qiwei.hospital.AdapterManger.TjsjAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendmxBean;
import com.qiwei.hospital.utils.Bean.TimeBean;
import com.qiwei.hospital.utils.Bean.TjglbhBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.MsgNetUtilwitharg1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportQueActivity extends BaseActivity implements View.OnClickListener {
    //标题，常用就诊人列表
    private View mReportTitle, mReportList;
    //朋友列表，时间列表
    private LinearLayout mFriendView, mTimeView;
    //返回按钮
    private ImageButton imgback;
    //标题表
    private TextView mTextTitle;
    //联系人列表；时间列表，
    private ListView mFriendList, mTimeList;
    //报告查询，检验查询，体检查询
    private RelativeLayout mBgcx, mJycx, mTjcx;
    private ArrayList<String> crrayList = new ArrayList<String>();
    private NnApplication app;
    private FriendmxAdapter friendAdapter;
    //带参数返回的MSG
    private MsgNetUtilwitharg1 msgNetUtil;
    private  MsgNetUtil msgNetUtil1;
    private BaseActivity activity;
    //Handler
    private MainHandler mainHandler;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> drrayList = new ArrayList<String>();
    private ArrayList<String> errayList = new ArrayList<String>();

    class MainHandler extends Handler {
        static final int MSG_GET_GHXH = 160;
        static final int MSG_GET_TJBH = 161;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case MSG_GET_GHXH:
                    crrayList = (ArrayList<String>) msg.obj;
                    String str = crrayList.toString();
                    String json = str.substring(1, str.length() - 1);
                    int q=msg.arg1;
                    parseJson1(json,q);
                    //Log.e("TAG",crrayList.toString());
                    break;
                case MSG_GET_TJBH:
                    errayList=(ArrayList<String>) msg.obj;
                  //  Log.e("----->",errayList.toString());
                    String str1 =errayList.toString();
                    String json1 = str1.substring(1, str1.length() - 1);
                    parseJson2( json1);
                    break;
                default:
                    break;
            }
        }
    }

    private void parseJson2(String json1) {
        mTimeList.setVisibility(View.VISIBLE);
        try {
        JSONObject object = new JSONObject(json1);
        final List<TjglbhBean> tjglbhBeans = new ArrayList<TjglbhBean>();
        JSONArray friendData = object.getJSONArray("GetTjjbxx");
        for (int i = 0; i < friendData.length(); i++) {
            //   TimeBean friendBean=new TimeBean();
            JSONObject friends = friendData.getJSONObject(i);
            TjglbhBean tjglbhBean = JSON.parseObject(friends.toString(), TjglbhBean.class);
           tjglbhBeans.add(tjglbhBean);
        }
           // Log.e("-------->",tjglbhBeans.get(0).getTjrq());
 TjsjAdapter timeAdapter = new TjsjAdapter(ReportQueActivity.this, tjglbhBeans);
        mTimeList.setAdapter(timeAdapter);
       mTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String tjbh=tjglbhBeans.get(i).getTjbh();
               Intent intent = new Intent();
               intent.setClass(ReportQueActivity.this,ExamnitionActivity.class);
               Bundle bundle = new Bundle();
               bundle.putString("tjbh", tjbh);

               intent.putExtras(bundle);
               startActivity(intent);
           }
       });
    }

    catch (Exception e) {

    }
    }

    @Override
    protected void initEnvironment() {
        app = (NnApplication) getApplication();
    }

    @Override
    protected void initViews() {
        mReportTitle = (View) findViewById(R.id.report_title);
        mReportList = (View) findViewById(R.id.report_list_title);
        mTextTitle = (TextView) mReportTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.tv_query_system_bg);
        imgback = (ImageButton) mReportTitle.findViewById(R.id.img_title_back);
        imgback.setOnClickListener(this);
        mFriendView = (LinearLayout) mReportList.findViewById(R.id.zyfy_list_friend);
        mFriendList = (ListView) mReportList.findViewById(R.id.list_yygh_cyjzr);
        mTimeList = (ListView) mReportList.findViewById(R.id.list_ryrq);
        mTimeView = (LinearLayout) findViewById(R.id.zyfy_list_yime);
        mBgcx = (RelativeLayout) findViewById(R.id.report_query_inspect);
        mJycx = (RelativeLayout) findViewById(R.id.report_query_check);
        mTjcx = (RelativeLayout) findViewById(R.id.report_query_test);
        mBgcx.setOnClickListener(this);
        mJycx.setOnClickListener(this);
        mTjcx.setOnClickListener(this);
        initdata();
    }

    private void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_report_que);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_title_back:
                finish();
                break;
            case R.id.report_query_inspect:
                drrayList = app.getFriendList();
                String str1 = drrayList.toString();
                String json1 = str1.substring(1, str1.length() - 1);
                // activity=PacxActivity;
                // Log.e("--------->1111", json);
                parseJson(json1, 1);
                break;
            case R.id.report_query_check:
                crrayList = app.getFriendList();
                String str = crrayList.toString();
                Log.e("str---",str);
                String json = str.substring(1, str.length() - 1);
                // Log.e("--------->1111", json);
                //activity=LisActivity;
                parseJson(json, 2);
                break;
            case R.id.report_query_test:
                String str2=app.getFriendList().toString();
                parseJson(str2.substring(1, str2.length() - 1),3);
                break;
            default:
                break;
        }
    }


    /**
     * 解析json并加载数据
     *
     * @param json
     */
    private void parseJson(String json, final int k) {
        mFriendView.setVisibility(View.VISIBLE);
        mReportList.setVisibility(View.VISIBLE);
        try {
            JSONObject object = new JSONObject(json);
            final List<FriendmxBean> friendBeans = new ArrayList<FriendmxBean>();
            JSONArray friendData = object.getJSONArray("GetBrxx");
            for (int i = 0; i < friendData.length(); i++) {
                FriendmxBean friendBean = new FriendmxBean();
                JSONObject friends = friendData.getJSONObject(i);
                String sfzh = friends.getString("sfzh");
                String brxm = friends.getString("brxm");
                String brnl = friends.getString("brnl");
                String brxb = friends.getString("brxb");
                String brjtzz = friends.getString("brjtzz");
                String ph = friends.getString("ph");
                String brdh = friends.getString("brdh");
                String ylkh = friends.getString("ylkh");
                String JDSJ = friends.getString("JDSJ");
                String brid = friends.getString("brid");
                String brnldw = friends.getString("brnldw");
                String nldwmc = friends.getString("nldwmc");
                friendBean.setSfzh(sfzh);
                friendBean.setBrxm(brxm);
                friendBean.setBrnl(brnl);
                friendBean.setBrxb(brxb);
                friendBean.setBrjtzzl(brjtzz);
                friendBean.setPh(ph);
                friendBean.setBrdh(brdh);
                ;
                friendBean.setBrdh(ylkh);
                friendBean.setBrdh(JDSJ);
                friendBean.setBrdh(brid);
                friendBean.setBrdh(brnldw);
                friendBean.setBrdh(nldwmc);
                friendBeans.add(friendBean);
            }
            friendAdapter = new FriendmxAdapter(ReportQueActivity.this, friendBeans);
            mFriendList.setAdapter(friendAdapter);
            mFriendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String sfzh = friendBeans.get(i).getSfzh();
                    int j = k;
                    if(k==3){
                     Getxh1(sfzh);
                    }
                    else {
                    Getxh(sfzh, k);}
                }
            });
            /**
             mListKsjs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListKsjs.setVisibility(View.GONE);
            mIncludeKSjs.setVisibility(View.VISIBLE);
            mTextKsjs.setText("   "+ksjjs.get(position).getKsjjmx());
            }
            });**/
        } catch (Exception e) {

        }
    }

    private void Getxh1(String sfzh) {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("sfzh");
        brrayList.add(sfzh);
        String name="getTjjbxx";
        mainHandler=new MainHandler();
        msgNetUtil1=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,161);
    }

    private void Getxh(String sfzh, int i) {
        arrayList.clear();
        brrayList.clear();
        arrayList.add("sfzh");
        brrayList.add(sfzh);
        String name = "getbrxx";
        int m=i;
        mainHandler = new MainHandler();
        msgNetUtil = new MsgNetUtilwitharg1(name, mainHandler, arrayList, brrayList, 160,m);
    }

    /**
     * 解析json并加载数据
     *
     * @param json
     */
    private void parseJson1(String json,final int t) {
        //mFriendView.setVisibility(View.VISIBLE);
        // mReportList.setVisibility(View.VISIBLE);
        mTimeList.setVisibility(View.VISIBLE);
        try {
            JSONObject object = new JSONObject(json);
            final List<TimeBean> friendBeans = new ArrayList<TimeBean>();
            JSONArray friendData = object.getJSONArray("GetBrxx");
            for (int i = 0; i < friendData.length(); i++) {
                //   TimeBean friendBean=new TimeBean();
                JSONObject friends = friendData.getJSONObject(i);
                TimeBean friendBean = JSON.parseObject(friends.toString(), TimeBean.class);
                /**
                 String brid=friends.getString("brid");
                 String ghxh=friends.getString("ghxh");
                 String ylkh=friends.getString("ylkh");
                 String ghrq=friends.getString("ghrq");
                 String sfzh=friends.getString("sfzh");
                 String zylx=friends.getString("zylx");
                 friendBean.setBrid(brid);
                 friendBean.setGhxh(ghxh);
                 friendBean.setYlkh(ylkh);
                 friendBean.setGhrq(ghrq);
                 friendBean.setSfzh(sfzh);
                 friendBean.setZylx(zylx);**/
                friendBeans.add(friendBean);
            }
            TimeAdapter timeAdapter = new TimeAdapter(ReportQueActivity.this, friendBeans);
            mTimeList.setAdapter(timeAdapter);
            mTimeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String ghxh = friendBeans.get(i).getGhxh();
                    if (t == 1) {

                        Intent intent = new Intent();
                        intent.setClass(ReportQueActivity.this, PacxActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("zyh", ghxh);

                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else if (t == 2) {
                        Intent intent = new Intent();
                        intent.setClass(ReportQueActivity.this, LisActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("zyh", ghxh);

                        intent.putExtras(bundle);
                        startActivity(intent);

                    }
                    // Getxh(sfzh);
                }
            });
            /**
             mListKsjs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mListKsjs.setVisibility(View.GONE);
            mIncludeKSjs.setVisibility(View.VISIBLE);
            mTextKsjs.setText("   "+ksjjs.get(position).getKsjjmx());
            }
            });**/
        } catch (Exception e) {

        }
    }


}
