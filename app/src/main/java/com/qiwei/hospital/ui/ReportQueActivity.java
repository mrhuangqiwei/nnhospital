package com.qiwei.hospital.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.FriendAdapter;
import com.qiwei.hospital.AdapterManger.KSjjAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.NnApplication.NnApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportQueActivity extends BaseActivity implements View.OnClickListener {
    //标题，常用就诊人列表
private View mReportTitle,mReportList;
    //朋友列表，时间列表
    private LinearLayout mFriendView,mTimeView;
    //返回按钮
    private ImageButton imgback;
    //标题表
    private TextView mTextTitle;
    //联系人列表；时间列表，
     private ListView mFriendList,mTimeList;
    //报告查询，检验查询，体检查询
private RelativeLayout mBgcx,mJycx,mTjcx;
    private ArrayList<String> crrayList = new ArrayList<String>();
    private NnApplication app;
    private FriendAdapter friendAdapter;
    @Override
    protected void initEnvironment() {
        app=(NnApplication)getApplication();
    }

    @Override
    protected void initViews() {
        mReportTitle=(View)findViewById(R.id.report_title);
        mReportList=(View)findViewById(R.id.report_list_title);
        mTextTitle=(TextView)mReportTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.tv_query_system_bg);
        imgback=(ImageButton)mReportTitle.findViewById(R.id.img_title_back);
        imgback.setOnClickListener(this);
        mFriendView=(LinearLayout)mReportList.findViewById(R.id.zyfy_list_friend);
        mFriendList=(ListView)mReportList.findViewById(R.id.list_yygh_cyjzr);
        mTimeList=(ListView)mReportList.findViewById(R.id.list_ryrq);
        mTimeView=(LinearLayout)findViewById(R.id.zyfy_list_yime);
        mBgcx=(RelativeLayout)findViewById(R.id.report_query_inspect);
        mJycx=(RelativeLayout)findViewById(R.id.report_query_check);
        mTjcx=(RelativeLayout)findViewById(R.id.report_query_test);
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
        switch (view.getId()){
            case R.id.img_title_back:
                finish();
                break;
            case R.id.report_query_inspect:
                break;
            case R.id.report_query_check:
                crrayList=app.getHrrayList();
                String str=crrayList.toString();
                String  json=str.substring(1, str.length() - 1);
                Log.e("--------->1111", json);
                parseJson(json);
                break;
            case R.id.report_query_test:
                break;
            default:break;
        }
    }


    /**
     * 解析json并加载数据
     * @param json
     */
    private void parseJson(String json){
        mFriendView.setVisibility(View.VISIBLE);
        mReportList.setVisibility(View.VISIBLE);
        try{
            JSONObject object =new JSONObject(json);
            final List<FriendBean> friendBeans =new ArrayList<FriendBean>();
            JSONArray friendData=object.getJSONArray("GetCylxr");
            for(int i=0;i<friendData.length();i++){
               FriendBean friendBean=new FriendBean();
                JSONObject friends=friendData.getJSONObject(i);
                String sfzh=friends.getString("sfzh");
                String brxm=friends.getString("brxm");
                String brnl=friends.getString("brnl");
                String brxb=friends.getString("brxb");
                String brjtzz=friends.getString("brjtzz");
                String ph=friends.getString("ph");
                String brdh=friends.getString("brdh");
                friendBean.setSfzh(sfzh);
                friendBean.setBrxm(brxm);
                friendBean.setBrnl(brnl);
                friendBean.setBrxb(brxb);
                friendBean.setBrjtzzl(brjtzz);
                friendBean.setPh(ph);
                friendBean.setBrdh(brdh);
              ;
                friendBeans.add(friendBean);
            }
            friendAdapter=new FriendAdapter(ReportQueActivity.this,friendBeans);
            mFriendList.setAdapter( friendAdapter);
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


}
