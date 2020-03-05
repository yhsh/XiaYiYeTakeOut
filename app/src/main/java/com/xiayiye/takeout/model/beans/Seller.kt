package com.xiayiye.takeout.model.beans

import android.content.pm.ActivityInfo
import java.util.ArrayList

class Seller {

    /**
     * "id":1,
     * "pic":http://xxxxxxxxxx.jpg,
     * "name":"二十五块半（上地店）",
     *
     * "score":"4.4",
     * "sale":4132,//销量
     * "ensure":1,//是否有转送保证
     *
     * "invoice":1,//是否提供发票
     * "sendPrice":20,//起送价格
     * "deliveryFee":4,//配送费
     *
     * "recentVisit":1,//是否最近光顾
     * "distance":"773m",
     * "time":"34分钟",
     *
     * "activityList":,[{//活动列表
     * "id":1,
     * "type":1,// 活动类型，详见附表
     * "info":"在线支付，满30减8"
     * },{
     * "id":2,
     * "type":2,
     * " info ":"16.9元特价超值套餐！"
     * }]
     *
     */

    var id: Long = 0
    var pic: String? = null
    var name: String? = null

    var score: String? = null
    var sale: String? = null
    var ensure: String? = null

    var invoice: String? = null
    var sendPrice: String? = null
    var deliveryFee: String? = null

    var recentVisit: String? = null
    var distance: String? = null
    var time: String? = null

    var icon: String? = null

    var activityList: ArrayList<ActivityInfo>? = null

}
