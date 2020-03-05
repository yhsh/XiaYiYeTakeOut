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
 * 创建时间：2020/3/5 18:38
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.beans
 * 文件说明：
 */
data class ResponseData(
    val categorieList: List<Categorie>,
    val nearbySellerList: List<NearbySeller>,
    val otherSellerList: List<NearbySeller>,
    val promotionList: List<Promotion>
)

data class Categorie(
    val id: Int,
    val name: String,
    val pic: String
)

data class NearbySeller(
    val activityList: List<Any>,
    val deliveryFee: String,
    val distance: String,
    val ensuer: String,
    val icon: String,
    val id: Int,
    val invoice: String,
    val name: String,
    val pic: String,
    val recentVisit: String,
    val sale: String,
    val score: String,
    val sendPrice: String,
    val time: String
)

data class Promotion(
    val id: Int,
    val info: String,
    val pic: String
)