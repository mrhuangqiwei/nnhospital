

package com.qiwei.hospital.utils.NnApplication;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.graphics.Path;


public class NnApplication extends Application {
	private static final String TAG = "EasyScreenShotApplication";

	/******************************
	 * private Members <br>
	 ******************************/
	
	
	private List<Activity> mActivityList = new LinkedList<Activity>();
	
	/** Default WorkSpace's path */
	private String mDefaultWorkeSpacePath = null;

	
	/** User clip path */
	private Path mUserClipPath = null;
	
/** ע���û��Ƚϵ�ArrayList**/
	private ArrayList<String> kcrrayList=null;
	private  ArrayList<String> crrayList;
	
	/******************************
	 * InnerClass <br>
	 ******************************/

	/** Singleton Methods */
	private static NnApplication mInstance;

	public static synchronized NnApplication getInstance() {
		if(mInstance == null){
			mInstance = new NnApplication();
		}
		return mInstance;
	}

	/******************************
	 * Construct <br>
	 ******************************/

	/******************************
	 * Implement Methods <br>
	 ******************************/

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		setArrrList(kcrrayList);
		
	}

	public void setArrrList(ArrayList<String> crrayList2) {
		// TODO Auto-generated method stub
		this.crrayList=crrayList2;
	}
public ArrayList<String> getArrayList(){
	return crrayList;
}
	
	
	
	@Override
	public void onTerminate() {
	
		super.onTerminate();
	}
	
	/******************************
	 * Public Methods <br>
	 ******************************/
	
	/** Add activity in the RunTime-Manage-activity-list */
	public void addActivity(Activity activity){
		if(activity != null && !mActivityList.contains(activity)){
			mActivityList.add(activity);
		}
	}
	
	/** Remove activity from the RunTime-Manage-activity-list */
	public void removeActivity(Activity activity){
		if(activity != null && mActivityList.contains(activity)){
			mActivityList.remove(activity);
		}
	}
	
	/**  traves the RunTime-Manage-activity-list and finish all of them */
	public void exitApp(){
		for(Activity act : mActivityList){
			if( act != null ) act.finish();
		}
		System.exit(0);
	}
	
	
}
