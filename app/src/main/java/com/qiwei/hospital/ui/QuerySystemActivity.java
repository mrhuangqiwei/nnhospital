package com.qiwei.hospital.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.MainActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.UCLandingActivity;
import com.qiwei.hospital.utils.NnApplication.NnApplication;

import java.util.ArrayList;

public class QuerySystemActivity extends BaseActivity implements View.OnClickListener{
    //按钮1
    private static final int dialog1 = 1;
    private static final int dialog2 = 2;
    private View mQueryFy;
    private  View mZDCX;
    private  View mBGCX;
    private NnApplication app;
    private ArrayList<String> drrayList = new ArrayList<String>();
    //返回
private ImageButton imgback;
    @Override
    protected void initEnvironment() {
        app=(NnApplication)getApplication();
    }

    @Override
    protected void initViews() {
        mQueryFy=(View)findViewById(R.id.re_query_system_info);
        mQueryFy.setOnClickListener(this);
        mZDCX=(View)findViewById(R.id.re_query_system_zd);
        mZDCX.setOnClickListener(this);
        imgback=(ImageButton)findViewById(R.id.img_query_system_back);
        imgback.setOnClickListener(this);
        mBGCX=(View)findViewById(R.id.re_query_system_bg);
        mBGCX.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return(R.layout.activity_query_system);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.re_query_system_info:
               showDialog(dialog1);
                break;
            case R.id.re_query_system_zd:
                showDialog(dialog2);

                break;
            case R.id.img_query_system_back:
                Intent intent=new Intent(QuerySystemActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.re_query_system_bg:
                checkfrindens();

                break;

            default:
                break;
        }

    }

    private void checkfrindens() {
        drrayList=app.getFriendList();
        if(drrayList.toString().length()>80){
                Intent intent=new Intent(QuerySystemActivity.this, ReportQueActivity.class);
            startActivity(intent);
        }

        else {



            dialog();}
        //if(drrayList.size())
       // Log.e("APP",drrayList.toString().length()+"");
    }


    @Override
    protected Dialog onCreateDialog(int id){
        Dialog dialog =null;
      AlertDialog.Builder b=new AlertDialog.Builder(this);
        switch (id){
            case dialog1:
                b.setIcon(R.mipmap.img_fy);
                b.setTitle("费用查询");
                b.setItems(R.array.msa,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                           if(which==0){
                               Intent intent=new Intent(QuerySystemActivity.this, ZYFYCXActivity.class);
                               startActivity(intent);
                           }
                           else if(which==1){
                               Intent intent=new Intent(QuerySystemActivity.this, MZFYCXActivity.class);
                               startActivity(intent);
                           }
                                else {
                            return;
                           }
                            }
                        });
                dialog=b.create();
                break;

            case  dialog2:
                b.setIcon(R.mipmap.img_zl);
                b.setTitle("综合查询");
                b.setItems(R.array.dag1,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    Intent intent=new Intent(QuerySystemActivity.this, MzblActivity.class);
                                    startActivity(intent);
                                }
                                else if(which==1){
                                    Intent intent=new Intent(QuerySystemActivity.this, MzXyCfActivity.class);
                                    startActivity(intent);
                                }
                                else if (which==2){
                                    Intent intent=new Intent(QuerySystemActivity.this, MzZlczActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    return;
                                }
                            }
                        });
                dialog=b.create();
            default:
                break;
        }
        return  dialog;
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("您没添加就诊人不能查询相关信息！确认前去添加吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent2=new Intent(QuerySystemActivity.this,UccenterFriend.class);
                startActivity(intent2);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
