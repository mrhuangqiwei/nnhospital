package com.qiwei.hospital.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.MyYyAdapter;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.Service.ImageService;
import com.qiwei.hospital.utils.Bean.MyYyghBean;
import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;

import java.io.IOException;
import java.util.ArrayList;

/****
 * 开发日期： 2016-10-30
 * 开发人：黄启位
 * 功能描述：导航首页
 */

public class NavigationActivity extends BaseActivity implements View.OnClickListener {
 private ImageView mImg;
    private  String stringurl="http://192.168.101.250:8998/imgs/imc_yysy.png";
    private  MainHandler mainHandler;
    //返回键
    private ImageButton  mBackBtn;
    //标题;地图导航
    private LinearLayout mIncludeTitle,mMapnav;
    //标题名称；电话
    private TextView mTextTitle,mTextPhone;
    @Override
    protected void initEnvironment() {

    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case  R.id.img_title_back:
        finish();
        break;
    case  R.id.id_ll_navagation_map:
        Intent intent=new Intent(NavigationActivity.this,HospitalLocationActivity.class);
        startActivity(intent);
        break;

    default:
        break;
}
    }


    class MainHandler extends Handler {
        static final int MSG_GETIMG=127;

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GETIMG:
                    byte[] data= (byte[]) msg.obj;

                    try {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图
                        mImg.setImageBitmap(bitmap);   //显示图片
                    } catch (Exception e) {
                        Toast.makeText(NavigationActivity.this,"连接超时", Toast.LENGTH_LONG).show();  //通知用户连接超时信息
                        Log.i(TAG, e.toString());
                    }
                    break;
                default:
                    break;
            }
        }
    }



    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.navigation_title);
        mImg=(ImageView)findViewById(R.id.id_yy_img);
        mTextTitle=(TextView)mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.setting_main_root1);
        mBackBtn=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackBtn.setOnClickListener(this);
        mTextPhone=(TextView)findViewById(R.id.id_tv_navigation_phone);
        mTextPhone.setOnClickListener(this);
        mMapnav=(LinearLayout)findViewById(R.id.id_ll_navagation_map);
        mMapnav.setOnClickListener(this);
        initdata();
    }

    private void initdata() {
        mainHandler=new MainHandler();
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {
                    byte[] data = ImageService.getImage(stringurl);

                    Message message= mainHandler.obtainMessage(127);
                    message.obj =data;
                    mainHandler.sendMessage(message);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();


/**
      //  String urlPathContent = urlPath.getText().toString();
        try {
            byte[] data = ImageService.getImage(stringurl);
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图
            mImg.setImageBitmap(bitmap);   //显示图片
        } catch (IOException e) {
            Toast.makeText(NavigationActivity.this,"连接超时", Toast.LENGTH_LONG).show();  //通知用户连接超时信息
            Log.i(TAG, e.toString());
        }   **/
    }

    @Override
    protected int getLayoutId() {
        return(R.layout.activity_navigation);
    }

}
