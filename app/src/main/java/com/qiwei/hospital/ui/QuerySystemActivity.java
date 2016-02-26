package com.qiwei.hospital.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.qiwei.hospital.R;

public class QuerySystemActivity extends Activity implements View.OnClickListener{
    //按钮1
    private static final int dialog1 = 1;
    private View mQueryFy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_system);
        mQueryFy=(View)findViewById(R.id.re_query_system_info);
        mQueryFy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.re_query_system_info:
               showDialog(dialog1);
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
                               Intent intent=new Intent(QuerySystemActivity.this, ZYFYActivity.class);
                               startActivity(intent);
                           }
                                else {
                               return;
                           }
                            }
                        });
                dialog=b.create();
                break;
            default:
                break;
        }
        return  dialog;
    }
}
