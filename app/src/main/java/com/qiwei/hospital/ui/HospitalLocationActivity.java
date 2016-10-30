package com.qiwei.hospital.ui;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.Listener.MyOrientationListener;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.Info;
import com.qiwei.hospital.utils.Dialog.CustormDialog;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

public class HospitalLocationActivity extends BaseActivity implements View.OnClickListener {
    /**mIncludeTitle:标题栏
     */
    private LinearLayout mIncludeTitle;
    /**
     * mTextTitle:标题栏

     */
    private TextView mTextTitle;
    // 返回键
    private ImageButton mBackButton;

    private CustormDialog dialog;

    /**
     * 地图控件
     */
    private MapView mMapView = null;
    /**
     * 地图实例
     */
    private BaiduMap mBaiduMap;
    /**
     * 定位的客户端
     */
    private LocationClient mLocationClient;
    /**
     * 定位的监听器
     */
    public MyOrientationListener mMyLocationListener;
    /**
     * 当前定位的模式
     *
     */
    private MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
    /***
     * 是否是第一次定位
     */
    private volatile boolean isFristLocation = true;

    /**
     * 最新一次的经纬度
     */
    private double mCurrentLantitude;
    private double mCurrentLongitude;
    /**
     * 当前的精度
     */
    private float mCurrentAccracy;
    /**
     * 方向传感器的监听器
     */
    private MyOrientationListener myOrientationListener;
    /**
     * 方向传感器X方向的值
     */
    private int mXDirection;
    /**
     * 详细信息的 布局
     */
    private RelativeLayout mMarkerInfoLy;

    private int mCurrentStyle = 0;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    private BitmapDescriptor mIconMaker;

    @Override
    protected void initEnvironment() {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

    @Override
    protected void initViews() {
        mIncludeTitle=(LinearLayout)findViewById(R.id.hslocation_title);
        mTextTitle=(TextView) mIncludeTitle.findViewById(R.id.tv_include_title);
        mTextTitle.setText("正在开发中");
        mBackButton=(ImageButton)mIncludeTitle.findViewById(R.id.img_title_back);
        mBackButton.setOnClickListener(this);	// 第一次定位
        isFristLocation = true;
        // 获取地图控件引用
        mMapView = (MapView) findViewById(R.id.id_bmapView);
        mMarkerInfoLy = (RelativeLayout) findViewById(R.id.id_marker_info);
        // 获得地图的实例
        mBaiduMap = mMapView.getMap();
        mIconMaker = BitmapDescriptorFactory.fromResource(R.drawable.maker);
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
        // 初始化定位
        initMyLocation();
        initOritationListener();
        initMarkerClickEvent();
        initMapClickEvent();

    }

    @Override
    protected int getLayoutId() {
        return (R.layout.activity_hospital_location);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.img_title_back:
           //    finish();

                   dialog = new CustormDialog(HospitalLocationActivity.this,"缓存清理",
                   "点击确定为您清理以下历史信息:\n系统通知，提箱小票，行业资讯，装箱单录入", R.style.CustomDialog_1,
                 new CustormDialog.DialogCallBack(){
                       @Override
                       public void OkDown() {
                           dialog.dismiss();
                        //这里放 确定按钮响应
                           Log.e("----------------->","111111111111111111111");
                       }
             @Override
                       public void CancleDown() {
                           dialog.dismiss();
                 Log.e("----------------->", "2222222222222222222222222");
                         //这里放取消按钮响应
             } },2);
dialog.show();

             break;
         default:break;
     }
    }


    /**
     * 初始化定位相关代码
     */
    private void initMyLocation()
    {
        // 定位初始化
        mLocationClient = new LocationClient(this);
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        // 设置定位的相关配置
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);
    }
    /**
     * 初始化方向传感器
     */
    private void initOritationListener()
    {
        myOrientationListener = new MyOrientationListener(
                getApplicationContext());
        myOrientationListener
                .setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
                    @Override
                    public void onOrientationChanged(float x) {
                        mXDirection = (int) x;
                        // 构造定位数据
                        MyLocationData locData = new MyLocationData.Builder()
                                .accuracy(mCurrentAccracy)
                                        // 此处设置开发者获取到的方向信息，顺时针0-360
                                .direction(mXDirection)
                                .latitude(mCurrentLantitude)
                                .longitude(mCurrentLongitude).build();
                        // 设置定位数据
                        mBaiduMap.setMyLocationData(locData);
                        // 设置自定义图标
                        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                                .fromResource(R.drawable.navi_map_gps_locked);
                        MyLocationConfiguration config = new MyLocationConfiguration(
                                mCurrentMode, true, mCurrentMarker);
                        mBaiduMap.setMyLocationConfigeration(config);

                    }
                });
    }

    private void initMarkerClickEvent()
    {
        // 对Marker的点击
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                // 获得marker中的数据
                Info info = (Info) marker.getExtraInfo().get("info");

                InfoWindow mInfoWindow;
                // 生成一个TextView用户在地图中显示InfoWindow
                BitmapDescriptor btm;
                TextView location = new TextView(getApplicationContext());
                location.setBackgroundResource(R.drawable.location_tips);
                location.setPadding(30, 20, 30, 50);
                location.setText(info.getName());
                // 将marker所在的经纬度的信息转化成屏幕上的坐标
                final LatLng ll = marker.getPosition();
                Point p = mBaiduMap.getProjection().toScreenLocation(ll);
                p.y -= 47;
                LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
                // 为弹出的InfoWindow添加点击事件
                mInfoWindow = new InfoWindow(location, llInfo, 47);
                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 隐藏InfoWindow
                        mBaiduMap.hideInfoWindow();
                    }
                });

                /**
                 mInfoWindow = new InfoWindow(location, llInfo,
                 new InfoWindow.OnInfoWindowClickListener()
                 {

                 @Override public void onInfoWindowClick()
                 {
                 // 隐藏InfoWindow
                 mBaiduMap.hideInfoWindow();
                 }
                 });
                 **/
                // 显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
                // 设置详细信息布局为可见
                mMarkerInfoLy.setVisibility(View.VISIBLE);
                // 根据商家信息为详细信息布局设置信息
                popupInfo(mMarkerInfoLy, info);
                return true;
            }
        });
    }

    private void initMapClickEvent()
    {
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                mMarkerInfoLy.setVisibility(View.GONE);
                mBaiduMap.hideInfoWindow();

            }
        });
    }

    @Override
    protected void onStart()
    {
        // 开启图层定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted())
        {
            mLocationClient.start();
        }
        // 开启方向传感器
        myOrientationListener.start();
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();

        // 关闭方向传感器
        myOrientationListener.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mIconMaker.recycle();
        mMapView = null;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        // 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    /**
     * 根据info为布局上的控件设置信息
     *
     * @param mMarkerInfo2
     * @param info
     */
    protected void popupInfo(RelativeLayout mMarkerLy, Info info)
    {
        ViewHolder viewHolder = null;
        if (mMarkerLy.getTag() == null)
        {
            viewHolder = new ViewHolder();
            viewHolder.infoImg = (ImageView) mMarkerLy
                    .findViewById(R.id.info_img);
            viewHolder.infoName = (TextView) mMarkerLy
                    .findViewById(R.id.info_name);
            viewHolder.infoDistance = (TextView) mMarkerLy
                    .findViewById(R.id.info_distance);
            viewHolder.infoZan = (TextView) mMarkerLy
                    .findViewById(R.id.info_zan);

            mMarkerLy.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) mMarkerLy.getTag();
        viewHolder.infoImg.setImageResource(info.getImgId());
        viewHolder.infoDistance.setText(info.getDistance());
        viewHolder.infoName.setText(info.getName());
        viewHolder.infoZan.setText(info.getZan() + "");
    }

    /**
     * 复用弹出面板mMarkerLy的控件
     *
     * @author zhy
     *
     */
    private class ViewHolder
    {
        ImageView infoImg;
        TextView infoName;
        TextView infoDistance;
        TextView infoZan;
    }
}
