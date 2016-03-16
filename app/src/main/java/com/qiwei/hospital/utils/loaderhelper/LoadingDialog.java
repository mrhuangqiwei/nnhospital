package com.qiwei.hospital.utils.loaderhelper;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.qiwei.hospital.R;


public class LoadingDialog extends AlertDialog {

    private TextView mLoadingMsg;

    private String message = null;

    public LoadingDialog(Context context) {
        super(context, R.style.commonDialog);
        setText(R.string.common_loading_msg);
    }

    public LoadingDialog(Context context, String message, boolean cancel) {
        super(context, R.style.commonDialog);
        this.message = message;
        this.setCancelable(cancel);
    }

    public LoadingDialog(Context context, int message, boolean cancel) {
        super(context, R.style.commonDialog);
        this.message = getContext().getResources().getString(message);
        this.setCancelable(cancel);
    }

    public LoadingDialog(Context context, int theme, String message, boolean cancel) {
        super(context, theme);
        this.message = message;
        this.setCancelable(cancel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.common_progress_dialog);
        mLoadingMsg = (TextView) findViewById(R.id.common_progress_dialog_msg);
        mLoadingMsg.setText(this.message);
    }

    public void setText(String message) {
        this.message = message;
        if (mLoadingMsg != null) {
            mLoadingMsg.setText(this.message);
        }
    }

    public void setText(int resId) {
        setText(getContext().getResources().getString(resId));
    }
}
