package com.xiayiye.takeout.presenter

import android.util.Log
import android.widget.Toast
import com.xiayiye.takeout.model.beans.User
import com.xiayiye.takeout.ui.activity.LoginActivity
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
 * 创建时间：2020/3/6 15:44
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.presenter
 * 文件说明：
 */
class LoginActivityPresenter(val loginActivity: LoginActivity) : NetPresenter() {
    fun loginByPhone(phone: String) {
        takeOutService.login().enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(loginActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                Log.e("打印登陆数据", user.toString() + Thread.currentThread().name)
                if (user != null) {
                    //成功
                    loginActivity.onSuccess(user)
                    Toast.makeText(loginActivity, "登陆成功", Toast.LENGTH_SHORT).show()
                } else {
                    loginActivity.onFail()
                }
            }
        })
    }
}