package com.qiwei.hospital.ui;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
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
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.Listener.MyOrientationListener;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.Info;
import com.qiwei.hospital.utils.Dialog.CustormDialog;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.lang.reflect.Method;
import java.util.List;

    public class HospitalLocationActivity extends Activity implements  View.OnClickListener
    {
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
        public MyLocationListener mMyLocationListener;
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
         * 地图定位的模式
         */
        private String[] mStyles = new String[] { "地图模式【正常】", "地图模式【跟随】",
                "地图模式【罗盘】" };
        private int mCurrentStyle = 0;
        // 初始化全局 bitmap 信息，不用时及时 recycle
        private BitmapDescriptor mIconMaker;
        /**
         * 详细信息的 布局
         */
        private RelativeLayout mMarkerInfoLy;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // 注意该方法要再setContentView方法之前实现
            SDKInitializer.initialize(getApplicationContext());
            setContentView(R.layout.activity_hospital_location);
            // 第一次定位
            isFristLocation = false;
            // 获取地图控件引用
            mMapView = (MapView) findViewById(R.id.id_bmapView);
            mMarkerInfoLy = (RelativeLayout) findViewById(R.id.id_marker_info);
            // 获得地图的实例
            mBaiduMap = mMapView.getMap();
            mIconMaker = BitmapDescriptorFactory.fromResource(R.mipmap.maker);
            MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
            mBaiduMap.setMapStatus(msu);
            //初始化医院位置
            initHospitalLocation();
            // 初始化定位
           initMyLocation();
            // 初始化传感器
            initOritationListener();
            initMarkerClickEvent();
            initMapClickEvent();

        }



        private void initMapClickEvent()
        {
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener()
            {

                @Override
                public boolean onMapPoiClick(MapPoi arg0)
                {
                    return false;
                }

                @Override
                public void onMapClick(LatLng arg0)
                {
                    mMarkerInfoLy.setVisibility(View.GONE);
                    mBaiduMap.hideInfoWindow();

                }
            });
        }

        private void initMarkerClickEvent()
        {
            // 对Marker的点击
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(final Marker marker)
                {
                    // 获得marker中的数据
                    Info info = (Info) marker.getExtraInfo().get("info");

                    InfoWindow mInfoWindow;
                    // 生成一个TextView用户在地图中显示InfoWindow
                    BitmapDescriptor btm;
                    TextView location = new TextView(getApplicationContext());
                    location.setBackgroundResource(R.mipmap.location_tips);
                    location.setPadding(30, 20, 30, 50);
                    location.setText(info.getName());
                    // 将marker所在的经纬度的信息转化成屏幕上的坐标
                    final LatLng ll = marker.getPosition();
                    Point p = mBaiduMap.getProjection().toScreenLocation(ll);
                    p.y -= 47;
                    LatLng llInfo = mBaiduMap.getProjection().fromScreenLocation(p);
                    // 为弹出的InfoWindow添加点击事件
                    mInfoWindow = new InfoWindow(location, llInfo,47);
                    location.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 隐藏InfoWindow
                            mBaiduMap.hideInfoWindow();
                        }
                    });


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

        /**
         * 根据info为布局上的控件设置信息
         *
         * @param
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

        @Override
        public void onClick(View view) {
            
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


        /**
         * 初始化方向传感器
         */
        private void initOritationListener()
        {
            myOrientationListener = new MyOrientationListener(
                    getApplicationContext());
            myOrientationListener
                    .setOnOrientationListener(new MyOrientationListener.OnOrientationListener()
                    {
                        @Override
                        public void onOrientationChanged(float x)
                        {
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
                                    .fromResource(R.mipmap.navi_map_gps_locked);
                            MyLocationConfiguration config = new MyLocationConfiguration(
                                    mCurrentMode, true, mCurrentMarker);
                            mBaiduMap.setMyLocationConfigeration(config);

                        }
                    });
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
         * 初始化图层
         */
        public void addInfosOverlay(List<Info> infos)
        {
            mBaiduMap.clear();
            LatLng latLng = null;
            OverlayOptions overlayOptions = null;
            Marker marker = null;
            for (Info info : infos)
            {
                // 位置
                latLng = new LatLng(info.getLatitude(), info.getLongitude());
                // 图标
                overlayOptions = new MarkerOptions().position(latLng)
                        .icon(mIconMaker).zIndex(5);
                marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                marker.setExtraInfo(bundle);
            }
            // 将地图移到到最后一个经纬度位置
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.setMapStatus(u);
        }

        /**
         * 实现实位回调监听
         */
        public class MyLocationListener implements BDLocationListener
        {
            @Override
            public void onReceiveLocation(BDLocation location)
            {

                // map view 销毁后不在处理新接收的位置
                if (location == null || mMapView == null)
                    return;
                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(location.getRadius())
                                // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(mXDirection).latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();
                mCurrentAccracy = location.getRadius();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
                mCurrentLantitude = location.getLatitude();
                mCurrentLongitude = location.getLongitude();
                // 设置自定义图标
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.mipmap.navi_map_gps_locked);
                MyLocationConfiguration config = new MyLocationConfiguration(
                        mCurrentMode, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfigeration(config);
                // 第一次定位时，将地图位置移动到当前位置
                if (isFristLocation)
                {
                    isFristLocation = false;
                    LatLng ll = new LatLng(location.getLatitude(),
                            location.getLongitude());
                    MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                    mBaiduMap.animateMapStatus(u);
                }
            }

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu)
        {
            getMenuInflater().inflate(R.menu.main, menu);
            return super.onCreateOptionsMenu(menu);
        }

        /**
         * 默认点击menu菜单，菜单项不现实图标，反射强制其显示
         */
        @Override
        public boolean onMenuOpened(int featureId, Menu menu)
        {

            if (featureId == Window.FEATURE_OPTIONS_PANEL && menu != null)
            {
                if (menu.getClass().getSimpleName().equals("MenuBuilder"))
                {
                    try
                    {
                        Method m = menu.getClass().getDeclaredMethod(
                                "setOptionalIconsVisible", Boolean.TYPE);
                        m.setAccessible(true);
                        m.invoke(menu, true);
                    } catch (Exception e)
                    {
                    }
                }

            }
            return super.onMenuOpened(featureId, menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
        {

            switch (item.getItemId())
            {
                case R.id.id_add_overlay:
                    addInfosOverlay(Info.infos);
                    break;
                case R.id.id_menu_map_common:
                    // 普通地图
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    break;
                case R.id.id_menu_map_site:// 卫星地图
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    break;
                case R.id.id_menu_map_traffic:
                    // 开启交通图

                    if (mBaiduMap.isTrafficEnabled())
                    {
                        item.setTitle("开启实时交通");
                        mBaiduMap.setTrafficEnabled(false);
                    } else
                    {
                        item.setTitle("关闭实时交通");
                        mBaiduMap.setTrafficEnabled(true);
                    }
                    break;
                case R.id.id_menu_map_myLoc:
                    center2myLoc();
                    break;

                case R.id.id_menu_map_style:
                    mCurrentStyle = (++mCurrentStyle) % mStyles.length;
                    item.setTitle(mStyles[mCurrentStyle]);
                    /*
                    // 设置自定义图标
                    switch (mCurrentStyle)
                    {
                        case 0:
                            mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                            break;
                        case 1:
                            mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                            break;
                        case 2:
                            mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
                            break;
                    }*/
                    BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                            .fromResource(R.mipmap.navi_map_gps_locked);
                    MyLocationConfiguration config = new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker);
                    mBaiduMap.setMyLocationConfigeration(config);
                    break;
            }

            return super.onOptionsItemSelected(item);
        }

        /**
         * 地图移动到我的位置,此处可以重新发定位请求，然后定位； 直接拿最近一次经纬度，如果长时间没有定位成功，可能会显示效果不好
         */
        private void center2myLoc()
        {
            LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
        }
        private void initHospitalLocation()
        {       OverlayOptions overlayOptions = null;
            Marker marker = null;
            LatLng ll = new LatLng(23.896121,106.614895); overlayOptions = new MarkerOptions().position(ll)
                .icon(mIconMaker).zIndex(5);
            marker = (Marker) (mBaiduMap.addOverlay(overlayOptions));
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
            mBaiduMap.animateMapStatus(u);
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

    }
