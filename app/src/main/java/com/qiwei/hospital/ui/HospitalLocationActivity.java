package com.qiwei.hospital.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Dialog.CustormDialog;

public class HospitalLocationActivity extends BaseActivity implements View.OnClickListener {
    /**mIncludeTitle:标题栏
     */
    private LinearLayout mIncludeTitle;
    /**
     * mTextTitle:标题栏

     */
    private TextView mTextTitle;
    // 返回键
    private ImageButton mBackButton;

    private CustormDialog dialog;
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.hslocation_title);
        mTextTitle=(TextView) mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText("正在开发中");
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_hospital_location);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.img_title_back:
           //    finish();

                   dialog = new CustormDialog(HospitalLocationActivity.this,"缓存清理",
                   "点击确定为您清理以下历史信息:\n系统通知，提箱小票，行业资讯，装箱单录入", R.style.CustomDialog_1,
                 new CustormDialog.DialogCallBack(){
                       @Override
                       public void OkDown() {
                           dialog.dismiss();
                        //这里放 确定按钮响应
                           Log.e("----------------->","111111111111111111111");
                       }
             @Override
                       public void CancleDown() {
                           dialog.dismiss();
                 Log.e("----------------->", "2222222222222222222222222");
                         //这里放取消按钮响应
             } },2);
dialog.show();

             break;
         default:break;
     }
    }
}
