package com.qiwei.hospital.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.R;

/**开发时间 2016-11-14
 * 功能：通过就诊卡添加常用联系人
 * 开发人：黄启位**/
public class AddFriendByYlkhActivity extends BaseActivity implements View.OnClickListener {
   private LinearLayout mTitle;
    private TextView mTextTitle;
    private ImageButton mImgBack,mImgPost;
    private EditText mEditylkh,mEditSfzh;
    @Override
    protected void initEnvironment() {

    }

    @Override
    protected void initViews() {
        mTitle=(LinearLayout)findViewById(R.id.friend_title);
        mTextTitle=(TextView)mTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText(R.string.uc_insert_jiuzhenren);
        mImgBack=(ImageButton)mTitle.findViewById(R.id.img_title_back);
        mImgBack.setOnClickListener(this);
        mImgPost=(ImageButton)findViewById(R.id.login_button_login);
        mImgPost.setOnClickListener(this);
        mEditylkh=(EditText)findViewById(R.id.edit_friend_ylkh);
        mEditSfzh=(EditText)findViewById(R.id.edit_friend_sfzh);
    }

    @Override
    protected int getLayoutId() {
        return(R.layout.activity_add_friend_by_ylkh);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_title_back:
                finish();
                break;
            default:break;
        }
    }
}
