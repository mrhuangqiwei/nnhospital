package com.qiwei.hospital.utils.comprehensive;

import android.app.Activity;

import java.util.Stack;


public class ActivityManager {

	private Stack<Activity> mActivities = new Stack<>();

	private static ActivityManager mInstance;

	private ActivityManager() {
	}

	public static ActivityManager getInstance() {
		if (mInstance == null) {
			synchronized (ActivityManager.class) {
				if (mInstance == null) {
					mInstance = new ActivityManager();
				}
			}
		}
		return mInstance;
	}

	public void addActivity(Activity activity) {
		if (mActivities.search(activity) == -1) {
			mActivities.add(activity);
		}
	}

	public void removeActivity(Activity activity) {
		mActivities.remove(activity);
	}

	public void setToTop(Activity activity) {
		mActivities.remove(activity);
		mActivities.push(activity);
	}

	public boolean isTopActivity(Activity activity) {
		if (mActivities.isEmpty()) {
			return false;
		}
		return activity.equals(mActivities.peek());
	}

	public Activity getTopActivity() {
		if (!mActivities.empty()) {
			return mActivities.peek();
		}
		return null;
	}

	public void finishAll() {
		for (Activity activity : mActivities) {
			activity.finish();
		}
	}
}
