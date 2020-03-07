package com.xiayiye.takeout.presenter

import com.xiayiye.takeout.model.beans.OrderBean
import com.xiayiye.takeout.ui.fragment.OrderFragment
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
 * 创建时间：2020/3/6 20:47
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.presenter
 * 文件说明：
 */
class OrderFragmentPresenter(val orderFragment: OrderFragment) : NetPresenter() {
    fun getAllOrder() {
        /* takeOutService.getAllOrder().enqueue(object : Callback<OrderBean> {
             override fun onFailure(call: Call<OrderBean>, t: Throwable) {

             }

             override fun onResponse(call: Call<OrderBean>, response: Response<OrderBean>) {
                 if (response.body()?.orderData?.size ?: 0 > 0) {
                     orderFragment.onSuccess(response.body())
                 } else {
                     orderFragment.onFail()
                 }
             }

         })*/

        /**
         * 结合RxJava使用调用接口
         */
        takeOutService.getAllOrderByRxJava().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<OrderBean> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: OrderBean) {
                    orderFragment.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    orderFragment.onFail()
                }
            })
    }
}