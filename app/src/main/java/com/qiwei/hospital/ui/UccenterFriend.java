package com.qiwei.hospital.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.qiwei.hospital.utils.NetUtil.NetBool;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;
import com.qiwei.hospital.utils.httplelper.NetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtilBool;

import java.util.ArrayList;
import java.util.List;

public class UccenterFriend extends BaseActivity implements View.OnClickListener{
    final int MENU_GENDER_MALE=0;
    final int MENU_GENDER_FEMALE=1;
    final int LIST_DIALOG_SINGLE=3;
    private NnApplication app;
    private ImageButton imgBack;
    private ListView mListView;
    private EditText mBrxm;
    private EditText mJtzz;
    private  EditText mSfzh;
    private EditText mdhhm;
    private  EditText mbrnl;
    private LinearLayout mLinInsert;
    private TextView mInsertLxr;
    private RelativeLayout mRelay;
    private  EditText mDhhm;
    private TextView brxb;
    private NetUtil netUtil;
    private Button mYygh;
    private ImageButton mBack;
    private List<FriendBean> mdatas;
    private String    sex;
    private TextView mBrxb;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private MainHandler mMainHandler;
    private NetUtilBool netUtilBool;
    MenuItem male=null;
    private FriendAdapter friendAdapter;
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;
            if(crrayList==null){
                mInsertLxr.setVisibility(View.VISIBLE);
                Toast.makeText(UccenterFriend.this,"亲您当前没有就诊人请添加！",Toast.LENGTH_LONG).show();

            }

          //  Log.e("crrls------",crrayList.toString());
           else if(crrayList.size()>6){
                if (crrayList.size()<15){
                    mInsertLxr.setVisibility(View.VISIBLE);
                }

                mdatas=new ArrayList<FriendBean>();
                for(int k=0;k<crrayList.size();k=k+7){
                    FriendBean friendBean=new FriendBean(crrayList.get(k),
                            crrayList.get(k+1),crrayList.get(k+2),crrayList.get(k+3),crrayList.get(k+4),crrayList.get(k+5),crrayList.get(k+6));
                    mdatas.add(friendBean);
                }
                friendAdapter=new FriendAdapter(UccenterFriend.this,mdatas);
               mListView.setVisibility(View.VISIBLE);
                mListView.setAdapter(friendAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String sfzhm=mdatas.get(position).getSfzh();
                        String hzxm=mdatas.get(position).getBrxm();
                        Intent intent= new Intent();
                        intent.setClass(UccenterFriend.this, MyFriendMxActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("hzsfzh",sfzhm);
                        bundle.putString("brxm",hzxm);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });

            }
            else {
                Toast.makeText(UccenterFriend.this,"还没有就诊人请添加",Toast.LENGTH_LONG).show();}

        }
    };

    class MainHandler extends Handler {
        static final int MSG_GET_XB = 100;
        static final int MSG_GET_fREIND= 101;
        static final int Msg_ZYCFSY= 102;
        static final int MSG_ZYCF_LIST = 103;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_XB:

                    mBrxb.setText((String)msg.obj);
                    break;

                case  MSG_GET_fREIND:


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
        mInsertLxr=(TextView)findViewById(R.id.uc_inseret_friend);
        mRelay=(RelativeLayout)findViewById(R.id.re_list_view);
        mLinInsert=(LinearLayout)findViewById(R.id.ll_ucenter_friend);
        mListView=(ListView)findViewById(R.id.uc_friend_list);
        mBrxm=(EditText)findViewById(R.id.login_edittext_account);
        mSfzh=(EditText)findViewById(R.id.login_edittext_password);
        mJtzz=(EditText)findViewById(R.id.uc_info_edit_jtzz);
        mBrxb=(TextView)findViewById(R.id.tv_user_friend_brxm);
        mbrnl=(EditText)findViewById(R.id.edt_ucenter_friend_nl);
        mdhhm=(EditText)findViewById(R.id.login_edittext_dhhm);
        mYygh=(Button)findViewById(R.id.login_button_login);
        mBack=(ImageButton)findViewById(R.id.img_uc_center_back);

        initdata2();
        mYygh.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mBrxb.setOnClickListener(this);
        mInsertLxr.setOnClickListener(this);
    }

    private void initdata2() {
        if(!NetBool.isNetworkAvailable(this)){
            Toast.makeText(UccenterFriend.this,R.string.net_contact_false,Toast.LENGTH_SHORT).show();
        }
        else{
        mLinInsert.setVisibility(View.GONE);
        mRelay.setVisibility(View.VISIBLE);
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        arrayList.add("manageid");
        app=(NnApplication)getApplication();
        brrayList.add(app.getUserid());
        String name="getuserfriendinfo";
        netUtil=new NetUtil(name,myhandler,arrayList,brrayList);
    }}

    @Override
    protected int getLayoutId() {
        return(R.layout.activity_uccenter_friend);
    }

@Override
    protected   Dialog onCreateDialog(int id){
    Dialog dialog=null;
AlertDialog.Builder b =new AlertDialog.Builder(this);
    switch (id){
        case LIST_DIALOG_SINGLE:
            b.setTitle(R.string.tv_yygh_yy_brxb);
            b.setSingleChoiceItems(R.array.sex, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, int which) {

                    sex = getResources().getStringArray(R.array.sex)[which];
                  mBrxb.setText(sex);

                }
            });
            b.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog=b.create();
            break;
        default:
            break;
    }
   return dialog;
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button_login:
                initdata();
            break;
            case R.id.tv_user_friend_brxm:
                showDialog(LIST_DIALOG_SINGLE);
                break;
            case R.id.img_uc_center_back:
                finish();
                break;
            case R.id.uc_inseret_friend:
                mRelay.setVisibility(View.GONE);
                mLinInsert.setVisibility(View.VISIBLE);
                break;
            default:break;
        }
    }

    private void initdata() {
        String brxm=mBrxm.getText().toString();
        String sfzh=mSfzh.getText().toString();
        String jtzz=mJtzz.getText().toString();
        String dhhm=mdhhm.getText().toString();
        String brnl=mbrnl.getText().toString();

        String xb="1";
        if(sex.equals("男")){
            xb="1";
        }
        else {
            xb="2";
        }
        app=(NnApplication)getApplication();
        String userid=app.getUserid();


        arrayList.add("sfzh");
        arrayList.add("brxm");
        arrayList.add("brnl");
        arrayList.add("brxb");
        arrayList.add("brjtzz");
        arrayList.add("ph");
        arrayList.add("brdh");

        brrayList.add(sfzh);
        brrayList.add(brxm);
        brrayList.add(brnl);
        brrayList.add( xb);
        brrayList.add(jtzz);
        brrayList.add(userid);
        brrayList.add(dhhm);
        netUtilBool=new NetUtilBool("insertuserFriend",arrayList,brrayList);
        Toast.makeText(UccenterFriend.this, "提交成功", Toast.LENGTH_SHORT).show();
    }
}
