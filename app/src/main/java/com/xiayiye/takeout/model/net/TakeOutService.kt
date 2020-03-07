package com.xiayiye.takeout.model.net

import com.xiayiye.takeout.model.beans.OrderBean
import com.xiayiye.takeout.model.beans.ResponseInfo
import com.xiayiye.takeout.model.beans.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

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
 * 创建时间：2020/3/5 17:45
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.net
 * 文件说明：
 */
interface TakeOutService {
    //获取首页数据的接口
    @GET("take_out_home")
    fun getHomeInfo(): Call<ResponseInfo>

    @GET("take_out_login")
    fun login(): Call<User>

    @GET("take_out_order")
    fun getAllOrder(): Call<OrderBean>

    //获取首页数据的接口
    @GET("take_out_home")
    fun getHomeInfoByRxJava(): Observable<ResponseInfo>

    @GET("take_out_login")
    fun loginByRxJava(): Observable<User>

    @GET("take_out_order")
    fun getAllOrderByRxJava(): Observable<OrderBean>
}