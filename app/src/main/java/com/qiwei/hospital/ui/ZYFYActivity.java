package com.qiwei.hospital.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

import java.util.ArrayList;


public class ZYFYActivity extends Activity implements View.OnClickListener{
private EditText mEditSf;
    private ImageButton mImgbtn;
    private ImageButton mImgback;
    private TextView  mBrxm;
    private TextView mTextYj;
    private TextView mTextYe;
    private  TextView mTextYy;
    private TextView mTextJz;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    private HttpConnSoap Soap = new HttpConnSoap();
    public  Handler myhandler=new Handler(){
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            //crrayList.clear();
            //ArrayList<String> crrayList=(ArrayList<String>) msg.obj;

            crrayList=(ArrayList<String>) msg.obj;
            Log.e("66666", "------------>" + crrayList.size());
           if(!crrayList.isEmpty()){
            mBrxm.setText(crrayList.get(4));
            mTextYj.setText(crrayList.get(1));
            mTextYe.setText(crrayList.get(3));
               mTextJz.setText(crrayList.get(0));
            mTextYy.setText(crrayList.get(2));

            }


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           inintWindow();
           inintViews();
           Function();

    }

    private void Function() {
    }

    private void inintViews() {
        mEditSf=(EditText)findViewById(R.id.zyfy_tv_sfzh);
        mImgbtn=(ImageButton)findViewById(R.id.zyfy_btn_sfzh);
        mImgback=(ImageButton)findViewById(R.id.img_zyfy_back);
        mBrxm=(TextView)findViewById(R.id.zyfy_tv_brxm);
        mTextYj=(TextView)findViewById(R.id.zyfy_tv_yjje  );
        mTextYe= (TextView) findViewById(R.id.zyfy_tv_zhye);
        mTextYy=(TextView) findViewById(R.id.zyfy_tv_yyhj);
        mTextJz=(TextView)findViewById(R.id.zyfy_tv_jzxe);
        mImgbtn.setOnClickListener(this);
        mImgback.setOnClickListener(this);


    }

    private void inintWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_zyfy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zyfy_btn_sfzh:
                getuserfy();
                break;
            default:
                break;
        }

    }

    private void getuserfy() {
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();
        String zyhl=mEditSf.getText().toString();
        arrayList.add("zyh");
        brrayList.add(zyhl);

        new Thread() {
            @Override
            public void run() {
                try {
                    crrayList = Soap.GetWebServre("getUserFF", arrayList, brrayList);
                    Log.e("00000000000000","------------>" +crrayList.size());

                    Message message=new Message();
                    //message.obj=crrayList;

                    message.obj=crrayList;
                    ZYFYActivity .this.myhandler.sendMessage(message);
                   // message.sendToTarget();
                    Log.e("2222222222", "------------>" + message.obj);
                  //  myhandler.sendMessage(message);

                } catch (Exception e) {
                }
            }
        }.start();





    }
}
