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
    val type: String
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