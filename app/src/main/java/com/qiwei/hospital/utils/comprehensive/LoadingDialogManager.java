package com.qiwei.hospital.utils.comprehensive;
import android.app.Activity;


import com.qiwei.hospital.utils.loaderhelper.LoadingDialog;

import java.util.concurrent.atomic.AtomicInteger;


public class LoadingDialogManager {

	private static LoadingDialogManager mInstance;

	public static LoadingDialogManager getInstance() {
		if (mInstance == null) {
			synchronized (LoadingDialogManager.class) {
				if (mInstance == null) {
					mInstance = new LoadingDialogManager();
				}
			}
		}
		return mInstance;
	}

	private AtomicInteger mShowCount;
	private LoadingDialog mDialog;

	private LoadingDialogManager() {
		mShowCount = new AtomicInteger(0);
	}

	/**
	 * 显示一个加载弹窗
	 * 多次调用会增加计数，但不会弹出多个Dialog
	 */
	public void showDialog() {
		if (mShowCount.get() == 0) {
			Activity activity = ActivityManager.getInstance().getTopActivity();
			if (activity != null) {
				mDialog = new LoadingDialog(activity);
				mDialog.show();
				mShowCount.incrementAndGet();
			}
		} else if (mDialog != null && mDialog.isShowing()) {
			mShowCount.incrementAndGet();
		}
	}

	/**
	 * 取消弹窗
	 * 当计数恢复到0时才取消弹窗
	 */
	public void dismissDialog() {
		if (mDialog != null && mDialog.isShowing()) {
			if (mShowCount.decrementAndGet() == 0) {
				mDialog.dismiss();
			}
		}
	}
}
