package com.qiwei.hospital.utils.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qiwei.hospital.R;


/**
 * Created by Administrator on 2016/8/17.
 */
public class CustormDialog extends Dialog implements DialogInterface {

    private String title;
    private String content;
    private DialogCallBack callback;
    private int index;
    /***
     * @param context
     * @param title 对话框标题
     * @param content 对话框内容
     * @param theme 对应的style 这里为R.style.CustomDialog_1 可自定义style
     * @param dialogcallback 确定取消按钮的回调 分别是 onCancle onOk
     * @param index 显示几个button 1 为只有一个确定键，其他为有确定取消两个按钮
     *
     * 调用实例
     *      dialog = new CustormDialog(SettingsActivity.this,"缓存清理",
     *      "点击确定为您清理以下历史信息:\n系统通知，提箱小票，行业资讯，装箱单录入", R.style.CustomDialog_1,
     *      new DialogCallBack(){
     *          @Override
     *          public void OkDown() {
     *              dialog.dismiss();
     *           //这里放 确定按钮响应
     *          }
     *          @Override
     *          public void CancleDown() {
     *              dialog.dismiss();
     *            //这里放取消按钮响应
     *          } },2);
     */
    public CustormDialog(Context context,String title,String content,int theme,DialogCallBack dialogcallback,int index) {
        super(context, theme);
        this.title = title;
        this.content = content;
        this.callback = dialogcallback;
        this.index = index;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dg_custormdialog);
        TextView titl = (TextView) findViewById(R.id.title);
        TextView cont = (TextView) findViewById(R.id.tv_content);

        titl.setText(title);
        cont.setText(content);

        Button cancel = (Button) findViewById(R.id.cancel);
        Button ok = (Button) findViewById(R.id.sure);
        if(index == 1){
            cancel.setVisibility(View.GONE);
        }else{
            cancel.setOnClickListener(new android.view.View.OnClickListener() {
                public void onClick(View v) {
                    CustormDialog.this.dismiss();
                    callback.CancleDown();
                }
            });
        }
        ok.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustormDialog.this.dismiss();
                callback.OkDown();
            }
        });
    }

    public interface DialogCallBack {
        abstract void OkDown();
        abstract void CancleDown();
    }


}