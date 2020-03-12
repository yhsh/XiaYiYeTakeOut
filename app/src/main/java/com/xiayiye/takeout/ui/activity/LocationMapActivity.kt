package com.xiayiye.takeout.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.xiayiye.takeout.R
import com.xiayiye.takeout.ui.adapter.AroundRvAdapter
import com.xiayiye.takeout.utils.LogTools
import kotlinx.android.synthetic.main.activity_location_map.*
import org.jetbrains.anko.toast


/*
 * Copyright (c) 2020, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/3/12 12:46
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：
 */
class LocationMapActivity : AppCompatActivity(), PoiSearch.OnPoiSearchListener {
    override fun onPoiItemSearched(poiItem: PoiItem, code: Int) {

    }

    override fun onPoiSearched(poiResult: PoiResult, code: Int) {
        if (code == 1000) {
            val pois = poiResult.pois
            rv_around.adapter = AroundRvAdapter(this, pois)
            for (i in 0 until pois.size) {
                LogTools.showLog("打印结果", pois[i].adName)
            }
        }
    }

    private lateinit var mMapView: MapView
    private lateinit var aMap: AMap
    private val WRITE_COARSE_LOCATION_REQUEST_CODE = 888
    //声明AMapLocationClient类对象
    lateinit var mLocationClient: AMapLocationClient
    //声明AMapLocationClientOption对象
    lateinit var mLocationOption: AMapLocationClientOption

    //初始化AMapLocationClientOption对象
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_map)
        //获取地图控件引用
        mMapView = findViewById<MapView>(R.id.map)
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        rv_around.layoutManager = LinearLayoutManager(this)
        checkPermission()
    }

    /**
     * 设置定位相关参数的方法
     */
    private fun setLocation() {
        aMap = mMapView.getMap()
        mLocationClient = AMapLocationClient(applicationContext);
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        mLocationClient.startLocation();
    }

    val mLocationListener = object : AMapLocationListener {
        override fun onLocationChanged(aMapLocation: AMapLocation) {
            if (aMapLocation.errorCode == 0) {
                //设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。
                showLocationPoint()
                //可在其中解析amapLocation获取相应内容。
                val address = aMapLocation.address
                val city = aMapLocation.city
                toast(city + address)
                LogTools.showLog(city, address)
                //移动地图到当前位置
                val latLng = LatLng(aMapLocation.latitude, aMapLocation.longitude)
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng))
                aMap.moveCamera(CameraUpdateFactory.zoomTo(16f))
                doSearchQuery(aMapLocation)
                //关闭定位,省电
                mLocationClient.stopLocation();
            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                toast("定位出错")
                LogTools.showLog(
                    "地图错误", "错误码:" +
                            aMapLocation.errorCode + ", 错误信息:" + aMapLocation.errorInfo
                );
            }
        }
    }

    /**
     * 显示定位蓝点
     */
    private fun showLocationPoint() {
        val myLocationStyle = MyLocationStyle()
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //设置默认定位按钮是否显示，非必需设置。
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
        myLocationStyle.showMyLocation(true)
    }

    /**
     * 搜索周边地点
     */
    private fun doSearchQuery(aMapLocation: AMapLocation) {
        val query = PoiSearch.Query("", "", aMapLocation.city)
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(50)// 设置每页最多返回多少条poiitem
        query.setPageNum(1)//设置查询页码
        val poiSearch = PoiSearch(this, query);
        poiSearch.bound =
                //1000米范围搜索
            PoiSearch.SearchBound(LatLonPoint(aMapLocation.latitude, aMapLocation.longitude), 3000)
        poiSearch.setOnPoiSearchListener(this);
        //异步搜索
        poiSearch.searchPOIAsyn();
    }

    /**
     * 检查权限的方法
     */
    private fun checkPermission() {
        //这里以ACCESS_COARSE_LOCATION为例
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                WRITE_COARSE_LOCATION_REQUEST_CODE
            );
        } else {
            //开始定位
            setLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == WRITE_COARSE_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //点击了允许,开始定位
                setLocation()
            } else {
                toast("需要定位权限")
                finish()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy()
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState)
    }
}