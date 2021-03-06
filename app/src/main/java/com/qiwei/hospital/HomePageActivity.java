package com.qiwei.hospital;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qiwei.hospital.ui.HospitalLocationActivity;
import com.qiwei.hospital.ui.KsAndYsActivity;
import com.qiwei.hospital.ui.MingYiActivity;
import com.qiwei.hospital.ui.MyddcxmActivity;
import com.qiwei.hospital.ui.NavigationActivity;
import com.qiwei.hospital.ui.QuerySystemActivity;
import com.qiwei.hospital.ui.UCenterActivity;
import com.qiwei.hospital.ui.YyKsActivity;
import com.qiwei.hospital.utils.NnApplication.NnApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomePageActivity extends Fragment implements View.OnClickListener {
    private ViewPager viewPager; // android-support-v4中的滑动组件
    private List<ImageView> imageViews; // 滑动的图片集合
    private NnApplication app;
    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private List<View> dots; // 图片标题正文的那些点
    /**
     *
     */
    private static final int dialog1 = 1;
    private TextView tv_title;
    /**  查询**/
    private LinearLayout mTvquery;
    /**预约挂号**/
    private LinearLayout mYygh;
    //科室医生
    private LinearLayout mKsandYs;
    /**满意度调查**/
    private LinearLayout mMyddc;
    /**名医馆**/
    private LinearLayout mMyg;
    /**医院导航**/
    private LinearLayout mYydh;
    private int currentItem = 0; // 当前图片的索引号

    private View contextView;
    // An ExecutorService that can schedule commands to run after a given delay,
    // or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService;
    private Context contex;
    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }

        ;
    };


    static HomePageActivity newInstance(CharSequence lable1) {
        HomePageActivity f = new HomePageActivity();
        Bundle b = new Bundle();
        b.putCharSequence("lable", lable1);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        contextView = inflater.inflate(R.layout.homepage, container, false);
        contex = container.getContext();
        this.initWindow();
         this.initevenment();

        return contextView;


    }

    private void initevenment() {
        app=NnApplication.getInstance();
        mTvquery= (LinearLayout) contextView.findViewById(R.id.setting_main_capture1);
        mTvquery.setOnClickListener(this);
        mYygh=(LinearLayout)contextView.findViewById(R.id.setting_main_capture);
        mYygh.setOnClickListener(this);
        mKsandYs=(LinearLayout)contextView.findViewById(R.id.setting_main_screenshot);
        mKsandYs.setOnClickListener(this);
        mMyddc=(LinearLayout)contextView.findViewById(R.id.setting_main_file);
        mMyddc.setOnClickListener(this);
        mMyg=(LinearLayout)contextView.findViewById(R.id.setting_main_root);
        mMyg.setOnClickListener(this);
        mYydh=(LinearLayout)contextView.findViewById(R.id.setting_main_root1);
        mYydh.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), (long) 3, 3, TimeUnit.SECONDS);
        super.onStart();
    }

    @Override
    public void onStop() {
        scheduledExecutorService.shutdown();
        super.onStop();
    }

    private void initWindow() {

        imageResId = new int[]{R.mipmap.p1, R.mipmap.p2, R.mipmap.p3};
        titles = new String[imageResId.length];
        titles[0] = "宁南县中医医院院训";
        titles[1] = "医院宗旨";
        titles[2] = "医院精神";


        imageViews = new ArrayList<ImageView>();

        // 初始化图片资源
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(contex);
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        dots = new ArrayList<View>();
        dots.add(contextView.findViewById(R.id.v_dot0));
        dots.add(contextView.findViewById(R.id.v_dot1));
        dots.add(contextView.findViewById(R.id.v_dot2));


        tv_title = (TextView) contextView.findViewById(R.id.tv_title1);
        tv_title.setText(titles[0]);//

        viewPager = (ViewPager) contextView.findViewById(R.id.mainViewpager);
        viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_main_capture1:
             if(app.getUserid()!=null){
                    Intent intent=new Intent(contex, QuerySystemActivity.class);
                    startActivity(intent);}
                else{
                 dialog();

             }
                break;
            case  R.id.setting_main_capture:

                if(app.getUserid()!=null){
                    Intent intent1=new Intent(contex,YyKsActivity.class);
                    startActivity(intent1);}
                else{
                    dialog();

                }
              //  }

            /**
                else {
                    Intent intent=new Intent(contex, UCLandingActivity.class);
                    startActivity(intent);
                }**/
                break;
            case R.id.setting_main_screenshot:
                Intent intent2=new Intent(contex,KsAndYsActivity.class);
                startActivity(intent2);
                break;
            case R.id.setting_main_file:
                    Intent intent3=new Intent(contex,MyddcxmActivity.class);
                startActivity(intent3);
                break;
            case  R.id.setting_main_root:
                Intent intent4 =new Intent(contex, MingYiActivity.class);
                startActivity(intent4);
                break;
            case R.id.setting_main_root1:
                Intent intent5=new Intent(contex,NavigationActivity.class);
                startActivity(intent5);
                break;
            default:
                break;

        }
    }


    /**
     * 换行切换任务
     *
     * @author Administrator
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author Administrator
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }


    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }
    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(contex);
        builder.setMessage("您没有登录！确认前去登录吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent2=new Intent(contex,UCLandingActivity.class);
                startActivity(intent2);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
