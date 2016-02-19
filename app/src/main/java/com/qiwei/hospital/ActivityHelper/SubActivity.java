package com.qiwei.hospital.ActivityHelper;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public abstract class SubActivity {
    protected Activity mParent;
    protected View mMainView;

    public SubActivity(Activity activity) {
        mParent = activity;
    }

    public abstract void onCreate();

    public abstract View onCreateView();

    public abstract void onResume();

    public abstract void onPause();

    public abstract void onDestory();

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        return;
    }

    /**
     * do after window show ,if do not Override, do nothing
     */
    public void onWindowFocusChanged(boolean hasFocus) {
        // if do not Override, do nothing
    }

    /**
     * Set Content View
     */
    public void setContentView(int res) {
        mMainView = mParent.getLayoutInflater().inflate(res, null);
    }

    /**
     * Get MainView
     */
    public View getMainView() {
        return mMainView;
    }
}
