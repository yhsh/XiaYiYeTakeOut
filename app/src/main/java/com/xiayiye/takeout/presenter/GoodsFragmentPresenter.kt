package com.xiayiye.takeout.presenter

import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.model.beans.GoodsBean
import com.xiayiye.takeout.ui.fragment.GoodsFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
 * 创建时间：2020/3/8 14:49
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.presenter
 * 文件说明：获取店铺信息
 */
class GoodsFragmentPresenter(val goodFragment: GoodsFragment) : NetPresenter() {
    val allTypeGood = arrayListOf<Goods>()
    fun getGoodListUrl() {
        takeOutService.getAllGoodByJava().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GoodsBean> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onNext(goodBean: GoodsBean) {
                    val goodData = goodBean.goodData
                    println("打印商品信息$goodBean 商品种类个数：${goodData.size}")
                    for (i in goodData.indices) {
                        val goodTypeInfo = goodData.get(i)
                        val aTypeList = goodTypeInfo.listGoods
                        for (j in 0 until aTypeList.size) {
                            val goodsInfo = aTypeList[j]
                            goodsInfo.typeId = goodTypeInfo.id
                            goodsInfo.typeName = goodTypeInfo.name
                        }
                        allTypeGood.addAll(aTypeList)
                    }
                    goodFragment.onSuccess(goodBean, allTypeGood)
                }

                override fun onError(e: Throwable) {
                    goodFragment.onFail()
                }
            })
    }

    fun getGoodPositionByTypeId(id: Int): Int {
        // -1 表示未找到
        var position = -1
        for (index in 0 until allTypeGood.size) {
            if (id == allTypeGood[index].typeId) {
                position = index
                break
            }
        }
        return position
    }
}