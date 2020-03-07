package com.xiayiye.takeout.presenter

import com.xiayiye.takeout.model.beans.ResponseData
import com.xiayiye.takeout.model.beans.ResponseInfo
import com.xiayiye.takeout.ui.fragment.HomeFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
 * 创建时间：2020/3/5 17:14
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.presenter
 * 文件说明：
 */
class HomeFragmentPresenter(private val homeFragment: HomeFragment) : NetPresenter() {

    fun getHomeInfo() {
        //TODO 异步获取接口数据
        /*takeOutService.getHomeInfo().enqueue(object : Callback<ResponseInfo> {
            override fun onFailure(call: Call<ResponseInfo>, t: Throwable) {
                println("打印网络数据错误")
                homeFragment.context?.toast("请求服务器数据错误")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseInfo>, response: Response<ResponseInfo>) {
                val data = response.body()?.data
                val code = response.body()?.code
                if ("0" == code) {
                    parserJson(data)
                    homeFragment.context?.toast("请求服务器数据成功")
                } else {
                    homeFragment.context?.toast("请求服务器数据为空")
                }
            }
        })*/

        /**
         * 使用RxJava调用接口
         */
        takeOutService.getHomeInfoByRxJava().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<ResponseInfo> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(response: ResponseInfo) {
                    val data = response.data
                    val code = response.code
                    if ("0" == code) {
                        parserJson(data)
                        homeFragment.context?.toast("请求服务器数据成功")
                    } else {
                        homeFragment.context?.toast("请求服务器数据为空")
                    }
                }

                override fun onError(e: Throwable) {
                    println("打印网络数据错误")
                    homeFragment.context?.toast("请求服务器数据错误")
                    e.printStackTrace()
                }
            })
    }

    private fun parserJson(data: ResponseData?) {
        val nearbySellerList = data?.nearbySellerList
        val otherSellerList = data?.otherSellerList
        if (nearbySellerList!!.isNotEmpty() && otherSellerList!!.isNotEmpty()) {
            println("打印附近商家信息$nearbySellerList")
            //有数据成功
            homeFragment.homeOnSuccess(nearbySellerList, otherSellerList)
        } else {
            //无数据失败
            homeFragment.homeOnFail()
        }
    }
}