package com.qiwei.hospital;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ui.UCenterActivity;
import com.qiwei.hospital.utils.NnApplication.NnApplication;
import com.qiwei.hospital.utils.httplelper.MsgNetUtil;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * Layouts & Views
     */
    private ViewGroup mViewContainer;

    // Bottom bars text
    private TextView mPageText1;
    private TextView mPageText2;
    private TextView mPageText3;
    private LinearLayout mBottomLayut;
    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private NnApplication app;
    //带参数返回的MSG
    private MsgNetUtil msgNetUtil;
    //Handler
    private   MainHandler mainHandler;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();

    class MainHandler extends Handler {
        static final int MSG_GET_YYMC= 81;
        static final int MSG_GET_LISID = 177;
        static final int MSG_GET_LISMX = 178;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                /**提交注册申请**/
                case  MSG_GET_YYMC :
                    crrayList=(ArrayList<String>)msg.obj;
                  String  yymc=crrayList.get(0);
                    //;mHostname.setText(yymc +"检验报告单");
                    //  GetPacxId();;
                    //Log.e("TAG",crrayList.toString());
                    break;
                case MSG_GET_LISID:

                    break;
                case  MSG_GET_LISMX:


                    break;

                default:
                    break;
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.initWindow();
        //  this.initEnvironment();

        this.initLayoutsAndViews();


    }

    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_bottom);
    }

    private void initLayoutsAndViews() {
        mBottomLayut = (LinearLayout) findViewById(R.id.buttomBar);
        mPageText1 = (TextView) findViewById(R.id.buttomTab1);
        mPageText2 = (TextView) findViewById(R.id.buttomTab2);
        mPageText3 = (TextView) findViewById(R.id.buttomTab3);
        mViewContainer = (ViewGroup) findViewById(R.id.viewContainer);
        mPageText1.setOnClickListener(this);
        mPageText2.setOnClickListener(this);
        mPageText3.setOnClickListener(this);
        setselect(0);
        initdata();

    }

    private void initdata() {
        arrayList.clear();
        brrayList.clear();
        String name="gethosname";
        mainHandler=new MainHandler();
        msgNetUtil=new MsgNetUtil(name,  mainHandler,arrayList,brrayList,81);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttomTab1:
                mViewContainer.invalidate();
                mPageText1.setSelected(true);
                mPageText2.setSelected(false);
                mPageText3.setSelected(false);
                setselect(0);
                break;
            case R.id.buttomTab2:
                app=(NnApplication)getApplication();
                if(app.getUserid()!=null){
                    Intent intent = new Intent(MainActivity.this, UCenterActivity.class);
                    startActivity(intent);
                }
                else {
                Intent intent = new Intent(MainActivity.this, UCLandingActivity.class);
                startActivity(intent);}
                break;
            case R.id.buttomTab3:
                mViewContainer.invalidate();
                mPageText1.setSelected(false);
                mPageText2.setSelected(false);
                mPageText3.setSelected(true);
                setselect(2);
                break;
            default:
                break;
        }

    }

    private void setselect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if(mTab03!=null){
                    transaction.hide(mTab03);
                }
                if (mTab01 == null) {

                    mTab01 = new HomePageActivity();
                    transaction.add(R.id.viewContainer, mTab01);

                } else {

                    transaction.show(mTab01);
                }
                break;
          //  case 1:
             //   if (mTab02 == null) {
                   // mTab02 = new t2Activity();
                 //   transaction.add(R.id.viewContainer, mTab02);
              //  } else {
                 //   transaction.show(mTab02);
               // }
               // break;
            case 2:
                if(mTab01 !=null){
                   transaction.hide(mTab01);
                }
                if (mTab03 == null) {

                    mTab03 = new more_mainActivity();
                    transaction.add(R.id.viewContainer, mTab03);
                } else {

                    transaction.show(mTab03);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
       // if (mTab02 != null) {
            //transaction.hide(mTab02);
      //  }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
    }


}
