package com.qiwei.hospital.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.KSjjAdapter;
import com.qiwei.hospital.AdapterManger.MyddcxmAdapter;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.Bean.MyddcBean;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtilBool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nnxzyy.common.Tools.TimeTools;

/**
 * 开发人：黄启位
 * 开发时间：2016-7-9
 * 功能描述：满意度调查
 */
public class MyddcxmActivity extends BaseActivity implements View.OnClickListener{
    /**mIncludeTitle:标题栏
     * mInclude：建议
     */
    private LinearLayout mIncludeTitle,mInclude;
    /**
     * mTextTitle:标题栏
     * mTextsuggest：建议
     */
    private TextView mTextTitle,mTextsuggest;
    // 返回键
    private ImageButton mBackButton;
    /**
     * 网络操作列表
     */
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
   //满意度调查LIST
    private ListView mListmyd;
    //adapter容器
    private MyddcxmAdapter  myddcxmAdapter;
    //网络请求
    private HttpConnSoap Soap = new HttpConnSoap();
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //布尔型网络操作
   private NetUtilBool netUtilBool;
    //Handler
    private   MainHandler mainHandler;
    //提交btn
    private Button mBtntj;
    //用户建议
    private EditText mEditSuggest;
    /**
     * 满意度调查数据集
     */
    private  final List<MyddcBean> myddcs =new ArrayList<MyddcBean>();
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.myddc_title);
        mTextTitle=(TextView)mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.myd_dc);
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);
        mListmyd=(ListView)findViewById(R.id.list_myddcxm);
     //   mTextsuggest=(TextView)findViewById(R.id.myddc_tv_suggest);
        mInclude=(LinearLayout)View.inflate(this, R.layout.item_layout_myddc, null);
        mBtntj=(Button)mInclude.findViewById(R.id.login_button_cancle);
        mBtntj.setOnClickListener(this);
        mEditSuggest=(EditText)mInclude.findViewById(R.id.myddc_tv_suggest);
        initdatas();
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_myddcxm);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.img_title_back:
        finish();
        break;
    case R.id.login_button_cancle:
        updatedata();

        break;
    default:break;
}
    }

    private void updatedata() {
        String yhid= TimeTools.callTime();
        String data1=TimeTools.getStringDate();
        String dcxmid;
        String name="insertUserMyddc";
        myddcxmAdapter.notifyDataSetChanged();
        Log.e("----------3333", " " + myddcxmAdapter.getCount());
        Log.e("----------3333", " " + myddcs.get(0).getFenshu());
        Log.e("----------3333", " " + myddcs.get(0).getMyddcid());

        for(int i=0;i<myddcxmAdapter.getCount();i++){
            arrayList.clear();
            brrayList.clear();
            dcxmid= myddcs.get(i).getMyddcid();
            String k=String.valueOf(myddcs.get(i).getFenshu());
            arrayList.add("yhid");
            arrayList.add("myddcid");
            arrayList.add("dcfs");
            arrayList.add("data1");
            brrayList.add(yhid);
            brrayList.add(dcxmid);
            brrayList.add(k);
            brrayList.add(data1);
            Log.e("------------bbb",brrayList.toString());
           // mainHandler=new MainHandler();
            netUtilBool=new NetUtilBool(name,arrayList,brrayList);
        };
        Toast.makeText(MyddcxmActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MyddcxmActivity.this, MainActivity.class);
        startActivity(intent);
    }

    class MainHandler extends Handler {
        static final int MSG_GET_MYDDCXM = 151;
      @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case  MSG_GET_MYDDCXM :
                    crrayList=(ArrayList<String>)msg.obj;
                    if(crrayList==null){
                        Toast.makeText(MyddcxmActivity.this, "亲没有获取到网络数据，请重试", Toast.LENGTH_SHORT).show();
                    }
                    else { //Log.e("--------->", crrayList.toString());
                        String str=crrayList.toString();
                        String  json=str.substring(1, str.length() - 1);
                      //  Log.e("--------->1111", json);
                        parseJson(json);
                    }
                    break;
              default:
                    break;
            }
        }}
    /**
     * 解析json并加载数据
     * @param json
     */
    private void parseJson(String json){
        try{
            JSONObject object =new JSONObject(json);

            JSONArray mYddcData=object.getJSONArray("Gemyddcxms");
            for(int i=0;i<mYddcData.length();i++){
                MyddcBean myddcBeanobject=new MyddcBean();
                JSONObject ksjs=mYddcData.getJSONObject(i);
                String Kabm=ksjs.getString("myddcid");
                String Ksmc=ksjs.getString("myddcmc");
                String Ksjjmx=ksjs.getString("myddcqybz");
                myddcBeanobject.setMyddcid(Kabm);
                myddcBeanobject.setMyddcmc(Ksmc);
                myddcBeanobject.setMyddcqybz(Ksjjmx);
                myddcs.add( myddcBeanobject);
            }
            myddcxmAdapter=new MyddcxmAdapter(MyddcxmActivity.this,myddcs);
          //  Log.e("-----111111111",""+myddcxmAdapter.getCount());
           // Log.e("-----222",""+myddcs.get(1).getFenshu() );
            mListmyd.addFooterView(mInclude);
            mListmyd.setAdapter(myddcxmAdapter);
            /**
            mListmyd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                }
            });**/
        }
        catch (Exception e){

        }
    }
    private void initdatas() {
        arrayList.clear();
        brrayList.clear();
        String name="getmyddcxm";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,151);
    }

}
