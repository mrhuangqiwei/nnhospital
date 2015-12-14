package com.qiwei.hospital;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.qiwei.hospital.ActivityHelper.SubActivity;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    public final static int PAGE_1 = 0;
    public final static int PAGE_2 = 1;
    public final static int PAGE_3 = 2;
    private int mCurrentPage = -1;

    private boolean mShowAd = true;

    /******************************
     * public Members <br>
     ******************************/

    /******************************
     * private Members <br>
     ******************************/
    /** Layouts & Views */
    private ViewGroup mViewContainer;

    // Bottom bars text
    private TextView mPageText1;
    private TextView mPageText2;
    private TextView mPageText3;
    private View mBottomLayut;
    private View mRe;
    private View mAdView;
    public ArrayList<SubActivity> mSubActivityList = new ArrayList<SubActivity>();
    private SubActivity mCurrentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        this.initEnvironment();
        this.initWindow();
        this.initLayoutsAndViews();



    }
  private void initEnvironment(){
      mSubActivityList.add(new HomePageActivity(this));
      mSubActivityList.add(new t2Activity(this));
      mSubActivityList.add(new more_mainActivity(this));
    }
private  void initWindow(){
   requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.main_bottom);
}

    private void initLayoutsAndViews(){
        mBottomLayut=(View)findViewById(R.id.buttomBar);
        mPageText1=(TextView)findViewById(R.id.buttomTab1);
        mPageText2=(TextView)findViewById(R.id.buttomTab2);
        mPageText3=(TextView)findViewById(R.id.buttomTab3);
        mViewContainer = (ViewGroup) findViewById(R.id.viewContainer);
        mPageText1.setOnClickListener(this);
        mPageText2.setOnClickListener(this);
        mPageText3.setOnClickListener(this);
       updateLayoutAndView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttomTab1:
                this.startSubActivity(PAGE_1);
                //showAd(true);
                break;
            case R.id.buttomTab2:
                this.startSubActivity(PAGE_2);
                //showAd(false);

                break;
            case R.id.buttomTab3:
                this.startSubActivity(PAGE_3);
                //showAd(false);

                break;
            default:
                break;
        }
    }

    private void updateLayoutAndView(){

        for(SubActivity sa : mSubActivityList){
            sa.onCreate();
            mViewContainer.addView(sa.getMainView());
        }
        // start the first page
        mCurrentActivity = mSubActivityList.get(0);
        startSubActivity(0);

    }


    public void startSubActivity(int viewId){
        if( mCurrentPage == viewId ){
            return;
        }

        if(viewId == PAGE_1){
            mPageText1.setSelected(true);
            mPageText2.setSelected(false);
            mPageText3.setSelected(false);
        }
        else if(viewId == PAGE_2)
        {
            mPageText1.setSelected(false);
            mPageText2.setSelected(true);
            mPageText3.setSelected(false);
        }

        else{mPageText3.setSelected(true);
            mPageText1.setSelected(false);
            mPageText2.setSelected(false);


        }

		/* stop the other subActivity*/
        mSubActivityList.get(viewId^1).onPause();
//		mViewContainer.removeView(mSubActivityList.get(viewId^1).getMainView());
        mSubActivityList.get(viewId^1).getMainView().setVisibility(View.INVISIBLE);
        mSubActivityList.get(viewId^2).onPause();
//		mViewContainer.removeView(mSubActivityList.get(viewId^1).getMainView());
        mSubActivityList.get(viewId^2).getMainView().setVisibility(View.INVISIBLE);
		/* show the current subActivity*/
        mCurrentActivity = mSubActivityList.get(viewId);
        mCurrentActivity.onResume();
//		mViewContainer.addView(mCurrentActivity.getMainView());
        mCurrentActivity.getMainView().setVisibility(View.VISIBLE);
        mCurrentPage = viewId;
    }



}
