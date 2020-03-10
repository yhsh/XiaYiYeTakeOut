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
 * 创建时间：2020/3/8 14:47
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.beans
 * 文件说明：
 */
data class GoodsBean(
    val code: String,
    val goodData: List<GoodData>
)

data class GoodData(
    val id: Int,
    val info: String,
    val listGoods: List<Goods>,
    val name: String,
    var redPointCount: Int = 0
)

data class Goods(
    val bargainPrice: Boolean,
    val from: String,
    val icon: String,
    val id: Int,
    val monthSaleNum: Int,
    val name: String,
    val new: Boolean,
    val newPrice: String,
    val oldPrice: String,
    val sellerId: Int,
    var typeName: String,
    var typeId: Int = 0,
    var count: Int = 0
)
/*
{
    "code": "0",
    "goodData": [
        {
            "id": 101,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "粗米饭+胡萝卜",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "粗粮加工中心1",
                    "new": false,
                    "newPrice": "12.00",
                    "oldPrice": "9.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "青菜腐竹豆腐+鸡肉汤",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 1239,
                    "name": "粗粮美食",
                    "new": false,
                    "newPrice": "57.80",
                    "oldPrice": "95.80",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "番茄鸡蛋+小白菜",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=1602077418,2363915372&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "粗粮加工中心2",
                    "new": false,
                    "newPrice": "27.00",
                    "oldPrice": "38.50",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "台湾卤肉+小块鱼",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3670032204,698617240&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "粗粮加工中心3",
                    "new": false,
                    "newPrice": "9.80",
                    "oldPrice": "13.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "木须肉盖饭",
                    "icon": "http://img5.imgtn.bdimg.com/it/u=3932876682,1502556615&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "粗粮加工中心4",
                    "new": false,
                    "newPrice": "7.00",
                    "oldPrice": "15.90",
                    "sellerId": 4
                }
            ],
            "name": "粗粮主食"
        },
        {
            "id": 102,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "宫保鸡丁+小菜",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3148757325,2365051479&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "营养快餐中心1",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "11.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "西红柿鸡蛋汤+肉丝面",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 981,
                    "name": "营养快餐美食",
                    "new": false,
                    "newPrice": "23.90",
                    "oldPrice": "33.80",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "鱼香肉丝+可乐",
                    "icon": "http://img3.imgtn.bdimg.com/it/u=3759325582,3051941253&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "营养快餐中心2",
                    "new": false,
                    "newPrice": "17.90",
                    "oldPrice": "23.50",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "青椒肉丝+鸡丁",
                    "icon": "http://img5.imgtn.bdimg.com/it/u=3079265673,141680030&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "营养快餐中心3",
                    "new": false,
                    "newPrice": "19.80",
                    "oldPrice": "27.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "可乐鸡翅+小块鱼",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=264359180,2256052058&fm=11&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "营养快餐中心4",
                    "new": false,
                    "newPrice": "13.00",
                    "oldPrice": "19.90",
                    "sellerId": 4
                }
            ],
            "name": "营养快餐"
        },
        {
            "id": 103,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "正宗三杯鸡+美年达",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "三鲜炒饭中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "鸡丝面+香米炒饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 1136,
                    "name": "三鲜炒饭美食",
                    "new": false,
                    "newPrice": "14.90",
                    "oldPrice": "23.70",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "红烧茄子盖饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "三鲜炒饭中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "红烧鸡块盖饭",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "三鲜炒饭中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "土豆丝盖饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "三鲜炒饭中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "三鲜炒饭"
        },
        {
            "id": 104,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "麻烦炒饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "麻辣混沌中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "混沌面+韭菜汤",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 377,
                    "name": "麻辣混沌美食",
                    "new": false,
                    "newPrice": "19.30",
                    "oldPrice": "27.50",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "猴子炒饭+肉丝",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "麻辣混沌中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "京酱肉丝",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "麻辣混沌中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "西红柿鸡蛋面",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "麻辣混沌中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "麻辣混沌"
        },
        {
            "id": 105,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "打卤面",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "爱情麻辣烫中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "土豆麻辣烫+豆腐粉",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 779,
                    "name": "爱情麻辣烫美食",
                    "new": false,
                    "newPrice": "14.90",
                    "oldPrice": "23.70",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "刀削面",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "爱情麻辣烫中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "混沌",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "爱情麻辣烫中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "山东大饼",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "爱情麻辣烫中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "爱情麻辣烫"
        },
        {
            "id": 106,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "红烧狮子头",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "铁板烧饭中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "铁板饭+胡萝卜丁",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 569,
                    "name": "铁板烧饭美食",
                    "new": false,
                    "newPrice": "26.30",
                    "oldPrice": "36.90",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "香酥鸡腿饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "铁板烧饭中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "肉末茄子",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "铁板烧饭中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "火腿炒饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "铁板烧饭中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "铁板烧饭"
        },
        {
            "id": 107,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "雪菜炒饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "小炒肉馆中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "鸡丝面+香米炒饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 948,
                    "name": "小炒肉美食",
                    "new": false,
                    "newPrice": "16.80",
                    "oldPrice": "37.40",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "茄子炒饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "小炒肉馆中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "萝卜+小白菜+紫菜",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "小炒肉馆中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "青椒木耳饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "小炒肉馆中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "小炒肉馆"
        },
        {
            "id": 108,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "青椒大蒜饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "火锅系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "番茄火锅+鸡丁饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 976,
                    "name": "火锅美食",
                    "new": false,
                    "newPrice": "119.90",
                    "oldPrice": "199.70",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "水煮鱼+米饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "火锅系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "番茄炒鸡蛋",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "火锅系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "瓦罐汤+鸡蛋",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "火锅系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "火锅系列"
        },
        {
            "id": 109,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "肉包子+鸡蛋汤",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "自助餐系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "香肠鸡柳面",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 716,
                    "name": "自助餐美食",
                    "new": false,
                    "newPrice": "14.60",
                    "oldPrice": "29.00",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "鸡蛋+油条+豆浆",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "自助餐系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "胡萝卜丁+豆浆",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "自助餐系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "红烧鸡块+米饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "自助餐系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "自助餐系列"
        },
        {
            "id": 110,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "地三鲜盖饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "烧烤系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "韭菜+拷馒头片",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 373,
                    "name": "烧烤美食",
                    "new": false,
                    "newPrice": "191.70",
                    "oldPrice": "298.90",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "外婆家常菜",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "烧烤系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "外婆菜+油条",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "烧烤系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "山东大饼+大葱",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "烧烤系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "烧烤系列"
        },
        {
            "id": 111,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "一锅炖+豆浆",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "海鲜系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "大龙虾+海虾套餐",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 307,
                    "name": "海鲜美食",
                    "new": false,
                    "newPrice": "1935.90",
                    "oldPrice": "2337.70",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "西瓜汁+大饼",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "海鲜系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "鸡蛋饭+肉丝",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "海鲜系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "可乐鸡翅+鱼翅",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "海鲜系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "海鲜系列"
        },
        {
            "id": 112,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "鲍鱼+雪菜",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "面条系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "肉丝红烧肉面",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=2083805598,1100778224&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 1976,
                    "name": "面条美食",
                    "new": false,
                    "newPrice": "37.10",
                    "oldPrice": "53.30",
                    "sellerId": 1
                },
                {
                    "bargainPrice": true,
                    "from": "大龙虾+鲍鱼",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 19,
                    "name": "面条系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "山东大饼+小白菜",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 138,
                    "name": "面条系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "胡萝卜汤+肉丝面",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1044,
                    "monthSaleNum": 113,
                    "name": "面条系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "面条系列"
        },
        {
            "id": 113,
            "info": "",
            "listGoods": [
                {
                    "bargainPrice": true,
                    "from": "肉丝盖饭",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=2775570102,6160372&fm=26&gp=0.jpg",
                    "id": 1040,
                    "monthSaleNum": 103,
                    "name": "大饼系列中心1",
                    "new": false,
                    "newPrice": "17.80",
                    "oldPrice": "31.50",
                    "sellerId": 0
                },
                {
                    "bargainPrice": true,
                    "from": "京酱肉丝炒饭",
                    "icon": "http://img2.imgtn.bdimg.com/it/u=3474946239,2135424771&fm=26&gp=0.jpg",
                    "id": 1041,
                    "monthSaleNum": 19,
                    "name": "大饼系列中心2",
                    "new": false,
                    "newPrice": "9.90",
                    "oldPrice": "16.60",
                    "sellerId": 2
                },
                {
                    "bargainPrice": true,
                    "from": "京酱肉丝面",
                    "icon": "http://img0.imgtn.bdimg.com/it/u=3706320884,2496483266&fm=26&gp=0.jpg",
                    "id": 1042,
                    "monthSaleNum": 138,
                    "name": "大饼系列中心3",
                    "new": false,
                    "newPrice": "16.60",
                    "oldPrice": "21.50",
                    "sellerId": 3
                },
                {
                    "bargainPrice": true,
                    "from": "兰州拉面",
                    "icon": "http://img4.imgtn.bdimg.com/it/u=1341421197,775500571&fm=26&gp=0.jpg",
                    "id": 1043,
                    "monthSaleNum": 113,
                    "name": "大饼系列中心4",
                    "new": false,
                    "newPrice": "11.80",
                    "oldPrice": "17.60",
                    "sellerId": 4
                }
            ],
            "name": "大饼系列"
        }
    ]
}
 */
