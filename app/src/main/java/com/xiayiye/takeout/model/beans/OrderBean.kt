package com.xiayiye.takeout.model.beans

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
 * 创建时间：2020/3/6 20:40
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.beans
 * 文件说明：
 */
data class OrderBean(
    val code: String,
    val orderData: List<OrderData>
)

data class OrderData(
    val detail: Detail,
    val distribution: Distribution,
    val goodsInfo: List<GoodsInfo>,
    val id: String,
    val orderSeller: OrderSeller,
    val rider: Rider,
    var type: String
)

data class Detail(
    val address: String,
    val pay: String,
    val phone: String,
    val time: String,
    val userName: String
)

data class Distribution(
    val des: String,
    val type: String
)

data class GoodsInfo(
    val bargainPrice: Boolean,
    val id: Int,
    val isNew: Boolean,
    val monthSaleNum: Int,
    val name: String,
    val newPrice: String,
    val oldPrice: String,
    val sellerId: Int
)

data class OrderSeller(
    val id: Int,
    val name: String
)

data class Rider(
    val id: Int,
    val name: String,
    val phone: String
)
/*
{
  "code": "0",
  "orderData": [
    {
      "detail": {
        "address": "孝感米酒馆对面",
        "pay": "1",
        "phone": "13675452314",
        "time": "2020/3/6 20:32",
        "userName": "骑手配送1"
      },
      "distribution": {
        "des": "快速配送",
        "type": "1"
      },
      "goodsInfo": [
        {
          "bargainPrice": false,
          "id": 0,
          "isNew": false,
          "monthSaleNum": 1,
          "name": "三鲜牛肉面",
          "newPrice": "12.00",
          "oldPrice": "9.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 1,
          "isNew": false,
          "monthSaleNum": 37,
          "name": "康师傅水",
          "newPrice": "3.00",
          "oldPrice": "1.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 2,
          "isNew": false,
          "monthSaleNum": 89,
          "name": "大牛烧土豆",
          "newPrice": "13.00",
          "oldPrice": "8.50",
          "sellerId": 0
        }
      ],
      "id": "0001",
      "orderSeller": {
        "id": 0,
        "name": "西安面庄"
      },
      "rider": {
        "id": 1,
        "name": "西安骑士",
        "phone": "13736653243"
      },
      "type": "10"
    },
    {
      "detail": {
        "address": "槐荫大道100号",
        "pay": "1",
        "phone": "13675452314",
        "time": "2020/3/6 22:08",
        "userName": "骑手配送1"
      },
      "distribution": {
        "des": "快速配送",
        "type": "1"
      },
      "goodsInfo": [
        {
          "bargainPrice": false,
          "id": 0,
          "isNew": false,
          "monthSaleNum": 1,
          "name": "爱情麻辣烫",
          "newPrice": "37.00",
          "oldPrice": "29.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 1,
          "isNew": false,
          "monthSaleNum": 37,
          "name": "康师傅水",
          "newPrice": "3.00",
          "oldPrice": "1.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 2,
          "isNew": false,
          "monthSaleNum": 89,
          "name": "大牛烧土豆",
          "newPrice": "13.00",
          "oldPrice": "8.50",
          "sellerId": 0
        }
      ],
      "id": "0002",
      "orderSeller": {
        "id": 0,
        "name": "槐荫大道香酥饭"
      },
      "rider": {
        "id": 1,
        "name": "槐荫大道骑士",
        "phone": "13098765243"
      },
      "type": "20"
    },
    {
      "detail": {
        "address": "乾坤大酒店旁边",
        "pay": "1",
        "phone": "13785097842",
        "time": "2020/3/1 10:39",
        "userName": "骑手配送2"
      },
      "distribution": {
        "des": "慢速配送",
        "type": "2"
      },
      "goodsInfo": [
        {
          "bargainPrice": false,
          "id": 0,
          "isNew": false,
          "monthSaleNum": 1,
          "name": "青椒肉丝盖饭",
          "newPrice": "17.00",
          "oldPrice": "12.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 1,
          "isNew": false,
          "monthSaleNum": 37,
          "name": "康师傅水",
          "newPrice": "3.00",
          "oldPrice": "1.50",
          "sellerId": 0
        },
        {
          "bargainPrice": false,
          "id": 2,
          "isNew": false,
          "monthSaleNum": 89,
          "name": "大牛烧土豆",
          "newPrice": "13.00",
          "oldPrice": "8.50",
          "sellerId": 0
        }
      ],
      "id": "0003",
      "orderSeller": {
        "id": 0,
        "name": "孝感米酒管"
      },
      "rider": {
        "id": 1,
        "name": "孝感骑士",
        "phone": "15687435578"
      },
      "type": "30"
    }
  ]
}
 */