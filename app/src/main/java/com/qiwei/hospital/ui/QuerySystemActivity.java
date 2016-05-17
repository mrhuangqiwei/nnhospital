package com.qiwei.hospital.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

public class QuerySystemActivity extends BaseActivity implements View.OnClickListener{
    //按钮1
    private static final int dialog1 = 1;
    private static final int dialog2 = 2;
    private View mQueryFy;
    private  View mZDCX;

    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mQueryFy=(View)findViewById(R.id.re_query_system_info);
        mQueryFy.setOnClickListener(this);
        mZDCX=(View)findViewById(R.id.re_query_system_zd);
        mZDCX.setOnClickListener(this);
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

            default:
                break;
        }

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
}
