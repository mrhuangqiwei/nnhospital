package com.qiwei.hospital.ActivityHelper;

import android.app.Activity;
import android.os.Bundle;

import com.qiwei.hospital.utils.comprehensive.ActivityManager;


public abstract class BaseActivity extends Activity {

    protected final String TAG = this.getClass().getSimpleName();

    protected abstract void initEnvironment();

    protected abstract void initViews();

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        initEnvironment();
        setContentView(getLayoutId());
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getInstance().setToTop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().removeActivity(this);
    }
}
