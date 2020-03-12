package com.xiayiye.takeout.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapUtils
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.model.*
import com.xiayiye.takeout.R
import com.xiayiye.takeout.ui.adapter.OrderRvAdapter
import com.xiayiye.takeout.utils.OrderChangeFunction
import kotlinx.android.synthetic.main.activity_order_detail.*
import org.json.JSONObject
import java.util.*

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
 * 创建时间：2020/3/12 17:29
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：订单详情页面
 */
class OrderDetailActivity : AppCompatActivity(), Observer {
    private lateinit var orderId: String
    private lateinit var aMap: AMap
    private lateinit var riderMarket: Marker
    private lateinit var lat: String
    private lateinit var lng: String
    override fun update(p0: Observable?, pushKeyAndValue: Any?) {
        //当收到推送消息，从这里刷新订单状态
        val jsonObject = JSONObject(pushKeyAndValue as String)
        val pushOrderId = jsonObject.optString("id")
        val pushOrderType = jsonObject.optString("type")
        if (jsonObject.has("location")) {
            //更新骑手位置
            lat = jsonObject.optString("lat")
            lng = jsonObject.optString("lng")
            //type等于60标识移动骑手位置
            updateRiderLocation(lat, lng)
        }
        setMapData(pushOrderType)
        if (pushOrderId == orderId) {
            //自动更改订单状态
            for (i in 0 until ll_order_detail_type_container.childCount) {
                val orderDetailType = OrderRvAdapter.getType(pushOrderType)
                if (orderDetailType == (ll_order_detail_type_container.getChildAt(i) as TextView).text.toString()) {
                    (ll_order_detail_type_point_container.getChildAt(i) as ImageView).setImageResource(
                        R.mipmap.order_time_node_disabled
                    )
                } else {
                    //其它设置为灰色
                    (ll_order_detail_type_point_container.getChildAt(i) as ImageView).setImageResource(
                        R.mipmap.order_time_node_normal
                    )
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        //添加绑定关系
        OrderChangeFunction.instance.addObserver(this)
        //获取地图控件引用
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        aMap = mMapView.map
        processIntent()
    }

    private fun processIntent() {
        orderId = intent.getStringExtra("orderId")!!
        val orderType = intent.getStringExtra("orderType")
        val orderDetailType = OrderRvAdapter.getType(orderType!!)
        setMapData(orderType)
        for (i in 0 until ll_order_detail_type_container.childCount) {
            if (orderDetailType == (ll_order_detail_type_container.getChildAt(i) as TextView).text.toString()) {
                (ll_order_detail_type_point_container.getChildAt(i) as ImageView).setImageResource(R.mipmap.order_time_node_disabled)
            }
        }
    }

    /**
     * 设置地图相关的数据
     */
    private fun setMapData(pushOrderType: String) {
        if (pushOrderType == "30") {
            //显示地图
            mMapView.visibility = View.VISIBLE
            //标注卖家位置
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(31.001205, 114.018779), 16f))
            aMap.addMarker(
                MarkerOptions().position(LatLng(31.001205, 114.018779)).icon(
                    BitmapDescriptorFactory.fromResource(R.mipmap.order_seller_icon)
                ).title("孝感米酒管").snippet("卖家店铺")
            )
            val buyer = ImageView(this)
            //添加自定义view的覆盖物
            buyer.setImageResource(R.mipmap.order_buyer_icon)
            aMap.addMarker(
                MarkerOptions().position(LatLng(31.004911, 114.018296)).icon(
                    BitmapDescriptorFactory.fromView(buyer)
                ).title("孝感西河镇街上").snippet("买家位置")
            )
            //计算骑手距离买家距离
            val calculateDistance = AMapUtils.calculateLineDistance(
                LatLng(31.004911, 114.018296),
                LatLng(30.999311, 114.018629)
            )
            riderMarket = aMap.addMarker(
                MarkerOptions().position(LatLng(30.999311, 114.018629)).icon(
                    BitmapDescriptorFactory.fromResource(R.mipmap.order_rider_icon)
                ).title("我是骑手,离您还有${calculateDistance}米,正在配送中请稍等……")
//                    .snippet("骑手位置")
            )
            //在窗体显示的覆盖物
            riderMarket.showInfoWindow()
        } else {
            //关闭地图
            mMapView.visibility = View.GONE
        }
        //画出骑手的位置
        val latLngs = ArrayList<LatLng>()
        latLngs.add(LatLng(31.000455, 114.015947))
        latLngs.add(LatLng(31.00326, 114.017996))
        latLngs.add(LatLng(31.001577, 114.019659))
        latLngs.add(LatLng(30.999311, 114.018629))
        val polyline = aMap.addPolyline(
            PolylineOptions().addAll(latLngs).width(5f).color(Color.RED)
        )
    }

    /**
     * 更新骑手位置的方法
     */
    private fun updateRiderLocation(lat: String, lng: String) {
        //更新骑手位置
        riderMarket.hideInfoWindow()
        riderMarket.position = LatLng(lat.toDouble(), lng.toDouble())
        aMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(lat.toDouble(), lng.toDouble()),
                16f
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy()
        //停止定位后，本地定位服务并不会被销毁
//        mLocationClient.stopLocation();
        //销毁定位客户端，同时销毁本地定位服务。
//        mLocationClient.onDestroy();
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