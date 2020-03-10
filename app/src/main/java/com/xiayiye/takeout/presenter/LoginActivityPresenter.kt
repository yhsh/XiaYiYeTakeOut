package com.xiayiye.takeout.presenter

import android.widget.Toast
import com.j256.ormlite.android.AndroidDatabaseConnection
import com.xiayiye.takeout.model.beans.User
import com.xiayiye.takeout.model.dao.TakeOutOpenHelper
import com.xiayiye.takeout.ui.activity.LoginActivity
import com.xiayiye.takeout.utils.LogTools
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.sql.Savepoint

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
        /* takeOutService.login().enqueue(object : Callback<User> {
             override fun onFailure(call: Call<User>, t: Throwable) {
                 Toast.makeText(loginActivity, t.message, Toast.LENGTH_SHORT).show()
             }

             override fun onResponse(call: Call<User>, response: Response<User>) {
                 val user = response.body()
                 LogTools.showLog("打印登陆数据", user.toString() + Thread.currentThread().name)
                 if (user != null) {
                     loginSuccessData(user)
                 } else {
                     loginActivity.onFail()
                 }
             }
         })*/

        /**
         * 结合RxJava使用
         */
        takeOutService.loginByRxJava().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<User> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(user: User) {
                    LogTools.showLog("打印登陆数据", user.toString() + Thread.currentThread().name)
                    loginSuccessData(user)
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(loginActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun loginSuccessData(user: User) {
        //成功
        loginActivity.onSuccess(user)
        Toast.makeText(loginActivity, "登陆成功", Toast.LENGTH_SHORT).show()
        //保存用户信息到本地数据库
        var connection: AndroidDatabaseConnection? = null
        var startPoint: Savepoint? = null
        try {
            val takeOutOpenHelper = TakeOutOpenHelper(loginActivity)
            val userDao = takeOutOpenHelper.getDao(User::class.java)
            //                    userDao.create(user)
            //创建或者更新用户信息
            //                    userDao.createOrUpdate(user)
            connection =
                AndroidDatabaseConnection(takeOutOpenHelper.writableDatabase, true)
            startPoint = connection.setSavePoint("start")
            //取消自动提交
            connection.isAutoCommit = false
            val queryForAll = userDao.queryForAll()
            var isOlderUser = false
            for (index in 0 until queryForAll.size) {
                if (queryForAll.get(index).data.id == user.data.id) {
                    isOlderUser = true
                }
            }
            if (isOlderUser) {
                //老用户：更新数据库
                userDao.update(user)
            } else {
                //新用户保存到数据库
                userDao.create(user)
            }
        } catch (e: Exception) {
            connection?.rollback(startPoint)
        }
    }
}