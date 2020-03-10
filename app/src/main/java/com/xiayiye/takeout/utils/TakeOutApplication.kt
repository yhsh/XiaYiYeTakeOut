package com.xiayiye.takeout.utils

import cn.jpush.android.api.JPushInterface
import com.mob.MobApplication
import com.xiayiye.takeout.model.beans.CacheSelectedInfo
import java.util.concurrent.CopyOnWriteArrayList

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
 * 创建时间：2020/3/6 14:44
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.utils
 * 文件说明：
 */
class TakeOutApplication : MobApplication() {
    companion object {
        lateinit var sInstance: TakeOutApplication
    }

    //点餐缓存集合
    val infos = CopyOnWriteArrayList<CacheSelectedInfo>()
    val ADD = 1
    val MINUS = -1
    override fun onCreate() {
        super.onCreate()
        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        sInstance = this
    }

    fun queryCacheSelectedInfoByGoodsId(goodsId: Int): Int {
        var count = 0
        for (i in 0..infos.size - 1) {
            val (_, _, goodsId1, count1) = infos[i]
            if (goodsId1 == goodsId) {
                count = count1
                break
            }
        }
        return count
    }

    fun queryCacheSelectedInfoByTypeId(typeId: Int): Int {
        var count = 0
        for (i in 0..infos.size - 1) {
            val (_, typeId1, _, count1) = infos[i]
            if (typeId1 == typeId) {
                count += count1
            }
        }
        return count
    }

    fun queryCacheSelectedInfoBySellerId(sellerId: Int): Int {
        var count = 0
        for (i in 0..infos.size - 1) {
            val (sellerId1, _, _, count1) = infos[i]
            if (sellerId1 == sellerId) {
                count += count1
            }
        }
        LogTools.showLog("打印id和数量${sellerId}==", count.toString())
        return count
    }

    fun addCacheSelectedInfo(info: CacheSelectedInfo) {
        LogTools.showPrintln("打印数量$infos")
        infos.add(info)
    }

    fun clearCacheSelectedInfo(sellerId: Int) {
        val temp = ArrayList<CacheSelectedInfo>()
        for (i in 0..infos.size - 1) {
            val info = infos[i]
            if (info.sellerId == sellerId) {
//                infos.remove(info)
                temp.add(info)
            }
        }
        infos.removeAll(temp)
    }

    fun deleteCacheSelectedInfo(goodsId: Int) {
        for (i in 0..infos.size - 1) {
            val info = infos[i]
            if (info.goodsId == goodsId) {
                infos.remove(info)
                break
            }
        }
    }

    fun updateCacheSelectedInfo(goodsId: Int, operation: Int) {
        for (i in 0..infos.size - 1) {
            val info = infos[i]
            if (info.goodsId == goodsId) {
                when (operation) {
                    ADD -> info.count = info.count + 1
                    MINUS -> info.count = info.count - 1
                }
            }
        }
    }
}